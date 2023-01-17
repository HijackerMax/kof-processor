package com.hijackermax.kofprocessor;

import com.hijackermax.kofprocessor.enums.CoordinateSystem;
import com.hijackermax.kofprocessor.model.CoordinatePoint;
import org.junit.jupiter.api.Test;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.ProjCoordinate;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KOFProcessorTest {
    @Test
    void testKOFProcessorWithAdminDetailsFile() throws IOException {
        try (var kofStream = getClass().getClassLoader().getResourceAsStream("w_admin.kof")) {
            KOFProcessor kofProcessor = KOFProcessor.readFrom(kofStream, CoordinateSystem.EPSG_25832);
            assertNotNull(kofProcessor);

            assertFalse(kofProcessor.isFileWithoutDeclaredCoordinateSystem());

            assertEquals(5, kofProcessor.getCoordinatesCount());

            List<CoordinatePoint> originalCoordinates = kofProcessor.getCoordinates();
            assertEquals(5, originalCoordinates.size());

            CoordinatePoint firstOriginalPoint = originalCoordinates.get(0);
            assertEquals(448533.299, firstOriginalPoint.getX());
            assertEquals(6495379.058, firstOriginalPoint.getY());
            assertEquals(301.169, firstOriginalPoint.getZ());

            CoordinatePoint fourthOriginalPoint = originalCoordinates.get(3);
            assertEquals(448526.450, fourthOriginalPoint.getX());
            assertEquals(6495384.356, fourthOriginalPoint.getY());
            assertEquals(312.100, fourthOriginalPoint.getZ());

            List<CoordinatePoint> wsg84Coordinates = kofProcessor.getCoordinates(CoordinateSystem.EPSG_4326);
            assertEquals(5, wsg84Coordinates.size());

            CoordinatePoint firstWSG84Point = wsg84Coordinates.get(0);
            assertEquals(8.114524316209401, firstWSG84Point.getX());
            assertEquals(58.59574677988897, firstWSG84Point.getY());
            assertEquals(Double.NaN, firstWSG84Point.getZ());

            CoordinatePoint fourthWSG84Point = wsg84Coordinates.get(3);
            assertEquals(8.114405294144147, fourthWSG84Point.getX());
            assertEquals(58.59579354526062, fourthWSG84Point.getY());
            assertEquals(Double.NaN, fourthWSG84Point.getZ());
        }
    }

    @Test
    void testKOFProcessorWithoutAdminDetailsFile() throws IOException {
        try (var kofStream = getClass().getClassLoader().getResourceAsStream("wo_admin.kof")) {
            KOFProcessor kofProcessor = KOFProcessor.readFrom(kofStream, CoordinateSystem.EPSG_25832);
            assertNotNull(kofProcessor);

            assertTrue(kofProcessor.isFileWithoutDeclaredCoordinateSystem());

            assertEquals(4, kofProcessor.getCoordinatesCount());

            List<CoordinatePoint> originalCoordinates = kofProcessor.getCoordinates();
            assertEquals(4, originalCoordinates.size());

            CoordinatePoint firstOriginalPoint = originalCoordinates.get(0);
            assertEquals(448533.299, firstOriginalPoint.getX());
            assertEquals(6495379.058, firstOriginalPoint.getY());
            assertEquals(301.169, firstOriginalPoint.getZ());

            CoordinatePoint fourthOriginalPoint = originalCoordinates.get(3);
            assertEquals(448526.450, fourthOriginalPoint.getX());
            assertEquals(6495384.356, fourthOriginalPoint.getY());
            assertEquals(312.100, fourthOriginalPoint.getZ());

            List<CoordinatePoint> wsg84Coordinates = kofProcessor.getCoordinates(CoordinateSystem.EPSG_4326);
            assertEquals(4, wsg84Coordinates.size());

            CoordinatePoint firstWSG84Point = wsg84Coordinates.get(0);
            assertEquals(8.114524316209401, firstWSG84Point.getX());
            assertEquals(58.59574677988897, firstWSG84Point.getY());
            assertEquals(Double.NaN, firstWSG84Point.getZ());

            CoordinatePoint fourthWSG84Point = wsg84Coordinates.get(3);
            assertEquals(8.114405294144147, fourthWSG84Point.getX());
            assertEquals(58.59579354526062, fourthWSG84Point.getY());
            assertEquals(Double.NaN, fourthWSG84Point.getZ());
        }
    }

    @Test
    void testKOFProcessorRequiredData() throws IOException {
        try (var kofStream = InputStream.nullInputStream()) {
            assertThrows(
                    NullPointerException.class,
                    () -> KOFProcessor.readFrom(kofStream, null)
            );
        }
    }

    @Test
    void testTransformer() {
        CoordinateTransform pseudoMercatorToWSG84 = KOFProcessor.buildTransformer(
                CoordinateSystem.EPSG_3857,
                CoordinateSystem.EPSG_4326
        );
        ProjCoordinate to = new ProjCoordinate();
        pseudoMercatorToWSG84.transform(new ProjCoordinate(158231.305325, 4708356.859196, Double.NaN), to);
        assertEquals(1.4214159999963012, to.x);
        assertEquals(38.906985999993395, to.y);
    }
}
