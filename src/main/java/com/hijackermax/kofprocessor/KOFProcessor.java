package com.hijackermax.kofprocessor;

import com.hijackermax.kofprocessor.enums.CoordinateSystem;
import com.hijackermax.kofprocessor.model.CoordinatePoint;
import com.hijackermax.utils.lang.CollectionUtils;
import com.hijackermax.utils.lang.StringUtils;
import org.locationtech.proj4j.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

import static com.hijackermax.utils.lang.OptionalUtils.ofBlank;

/**
 * KOF file parser, provides ability to parse coordinates from KOF file and transform them to different coordinate system
 */
public final class KOFProcessor {
    private static final int INSTRUCTION_SIZE = 2;
    private static final String EAST_NORTH_ORDER_FLAG_VALUE = "2";
    private final List<CoordinatePoint> coordinates = new ArrayList<>();
    private final CoordinateSystem providedCoordinateSystem;
    private boolean eastNorthOrder;
    private CoordinateSystem fileCoordinateSystem;

    private KOFProcessor(InputStream inputStream, CoordinateSystem providedCoordinateSystem, boolean eastNorthOrder) throws IOException {
        try (var isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8); var br = new BufferedReader(isr)) {
            br.lines()
                    .filter(StringUtils::isNotBlank)
                    .filter(line -> line.length() > INSTRUCTION_SIZE)
                    .forEach(this::processLine);
        }
        this.providedCoordinateSystem = providedCoordinateSystem;
        this.eastNorthOrder = eastNorthOrder;
    }

    /**
     * Creates instance of {@link KOFProcessor} with provided {@link InputStream} of KOF file
     *
     * @param inputStream              {@link InputStream} with KOF file that should be processed
     * @param providedCoordinateSystem fallback {@link CoordinateSystem} for coordinates,
     *                                 if file does not include this information
     * @param eastNorthOrder           flag that controls default x,y coordinates swap behaviour,
     *                                 if file does not include this information
     * @return KOFProcessor instance with provided configuration
     * @throws IOException exception that can occur during interactions with {@link InputStream}
     */
    public static KOFProcessor readFrom(InputStream inputStream,
                                        CoordinateSystem providedCoordinateSystem,
                                        boolean eastNorthOrder) throws IOException {
        return new KOFProcessor(
                inputStream,
                Objects.requireNonNull(
                        providedCoordinateSystem,
                        "Default coordinate system should be provided in case file does not contain it"
                ),
                eastNorthOrder
        );
    }

    /**
     * Creates instance of {@link KOFProcessor} with provided {@link InputStream} of KOF file
     *
     * @param inputStream              {@link InputStream} with KOF file that should be processed
     * @param providedCoordinateSystem fallback {@link CoordinateSystem} for coordinates,
     *                                 if file does not include this information
     * @return KOFProcessor instance with provided configuration
     * @throws IOException exception that can occur during interactions with {@link InputStream}
     */
    public static KOFProcessor readFrom(InputStream inputStream,
                                        CoordinateSystem providedCoordinateSystem) throws IOException {
        return new KOFProcessor(
                inputStream,
                Objects.requireNonNull(
                        providedCoordinateSystem,
                        "Default coordinate system should be provided in case file does not contain it"
                ),
                true
        );
    }

    static CoordinateTransform buildTransformer(CoordinateSystem from, CoordinateSystem to) {
        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem fromReferenceSystem = crsFactory.createFromParameters(from.getName(), from.getData());
        CoordinateReferenceSystem toReferenceSystem = crsFactory.createFromParameters(to.getName(), to.getData());
        return new BasicCoordinateTransform(fromReferenceSystem, toReferenceSystem);
    }

    private static UnaryOperator<CoordinatePoint> transform(CoordinateTransform transformer) {
        return source -> {
            ProjCoordinate to = new ProjCoordinate();
            transformer.transform(new ProjCoordinate(source.getX(), source.getY(), source.getZ()), to);
            return new CoordinatePoint(source.getName(), to.x, to.y, to.z);
        };
    }

    /**
     * Returns {@link List} of coordinates in file's original coordinate system
     *
     * @return Unmodifiable {@link List} of coordinates in file's original coordinate system
     */
    public List<CoordinatePoint> getCoordinates() {
        return Collections.unmodifiableList(this.coordinates);
    }

    /**
     * Returns size of coordinates list
     *
     * @return size of coordinates list
     */
    public int getCoordinatesCount() {
        return this.coordinates.size();
    }

    /**
     * Returns {@link CoordinatePoint} list in requested {@link CoordinateSystem}
     *
     * @param targetSystem {@link CoordinateSystem} that should be applied to each of the coordinate points
     * @return {@link List} of coordinates in requested {@link CoordinateSystem}
     */
    public List<CoordinatePoint> getCoordinates(CoordinateSystem targetSystem) {
        CoordinateSystem original = Objects.nonNull(fileCoordinateSystem)
                ? fileCoordinateSystem : providedCoordinateSystem;

        if (Objects.equals(targetSystem, original)) {
            return getCoordinates();
        }
        CoordinateTransform transformer = buildTransformer(original, targetSystem);
        return CollectionUtils.map(coordinates, transform(transformer));
    }

    /**
     * Checks if provided file lacks information regarding coordinates system
     *
     * @return true if provided kof file has no information regarding coordinates system
     */
    public boolean isFileWithoutDeclaredCoordinateSystem() {
        return Objects.isNull(fileCoordinateSystem);
    }

    private void processLine(String line) {
        switch (InstructionType.findByValue(line.trim().substring(0, 2))) {
            case ADMINISTRATIVE:
                processAdministrative(line);
                break;
            case COORDINATES:
                processCoordinates(line);
                break;
            default:
                //In this version only coordinates and administrative info are implemented
                break;
        }
    }

    private void processAdministrative(String value) {
        String blockData = StringUtils.padRight(
                value,
                InstructionType.ADMINISTRATIVE.getBlockLength(),
                StringUtils.BLANK
        );
        String units = blockData.substring(43, 56).strip();
        this.eastNorthOrder = StringUtils.isNotBlank(units)
                && EAST_NORTH_ORDER_FLAG_VALUE.equals(units.substring(1, 2).strip());
        ofBlank(blockData.substring(30, 38))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(CoordinateSystem::findByReferenceId)
                .ifPresent(this::setFileCoordinateSystem);
    }

    private void processCoordinates(String value) {
        String blockData = StringUtils.padRight(value, InstructionType.COORDINATES.getBlockLength(), StringUtils.BLANK);
        double x = Double.parseDouble(blockData.substring(37, 48).strip());
        double y = Double.parseDouble(blockData.substring(24, 36).strip());
        Double z = ofBlank(blockData.substring(49, 57))
                .map(String::strip)
                .map(Double::parseDouble)
                .orElse(Double.NaN);
        String pointName = StringUtils.trimToEmpty(blockData.substring(4, 14));
        coordinates.add(buildCoordinatePoint(pointName, x, y, z));
    }

    private CoordinatePoint buildCoordinatePoint(String name, double xAxis, double yAxis, double zAxis) {
        return eastNorthOrder ?
                new CoordinatePoint(name, yAxis, xAxis, zAxis) : new CoordinatePoint(name, xAxis, yAxis, zAxis);
    }

    private void setFileCoordinateSystem(CoordinateSystem fileCoordinateSystem) {
        this.fileCoordinateSystem = fileCoordinateSystem;
    }
}
