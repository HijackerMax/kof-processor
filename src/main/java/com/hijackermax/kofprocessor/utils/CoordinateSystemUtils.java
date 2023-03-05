package com.hijackermax.kofprocessor.utils;

import com.hijackermax.kofprocessor.enums.CoordinateSystem;
import com.hijackermax.kofprocessor.model.CoordinatePoint;
import org.locationtech.proj4j.BasicCoordinateTransform;
import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.ProjCoordinate;

import java.util.Objects;

/**
 * Utils then may be useful during work with kof files
 */
public final class CoordinateSystemUtils {
    private CoordinateSystemUtils() {
    }

    /**
     * Transforms coordinate point from one {@link CoordinateSystem} to other {@link CoordinateSystem}
     *
     * @param from  source {@link CoordinateSystem}
     * @param to    target {@link CoordinateSystem}
     * @param point source {@link CoordinatePoint}
     * @return source {@link CoordinatePoint} point in target coordinate system, or null if source coordinate, or source-target systems are null
     * @since 0.0.2
     */
    public static CoordinatePoint transform(CoordinateSystem from, CoordinateSystem to, CoordinatePoint point) {
        if (Objects.isNull(point) || Objects.isNull(from) || Objects.isNull(to)) {
            return null;
        }
        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem fromReferenceSystem = crsFactory.createFromParameters(from.getName(), from.getData());
        CoordinateReferenceSystem toReferenceSystem = crsFactory.createFromParameters(to.getName(), to.getData());
        BasicCoordinateTransform transformer = new BasicCoordinateTransform(fromReferenceSystem, toReferenceSystem);
        ProjCoordinate toCoordinates = new ProjCoordinate();
        transformer.transform(new ProjCoordinate(point.getX(), point.getY(), point.getZ()), toCoordinates);
        return new CoordinatePoint(point.getName(), toCoordinates.x, toCoordinates.y, toCoordinates.z);
    }
}
