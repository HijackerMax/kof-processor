package com.hijackermax.kofprocessor.utils;

import com.hijackermax.kofprocessor.enums.CoordinateSystem;
import com.hijackermax.kofprocessor.model.CoordinatePoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateSystemUtilsTest {

    @Test
    void testTransform() {
        assertNull(CoordinateSystemUtils.transform(CoordinateSystem.EPSG_3857, CoordinateSystem.EPSG_4326, null));
        assertNull(CoordinateSystemUtils.transform(CoordinateSystem.EPSG_3857, CoordinateSystem.EPSG_4326, null));
        CoordinatePoint source = new CoordinatePoint(158231.305325, 4708356.859196, Double.NaN);
        assertNull(CoordinateSystemUtils.transform(null, CoordinateSystem.EPSG_4326, source));
        assertNull(CoordinateSystemUtils.transform(CoordinateSystem.EPSG_3857, null, source));
        CoordinatePoint result = CoordinateSystemUtils.transform(CoordinateSystem.EPSG_3857, CoordinateSystem.EPSG_4326, source);
        assertNotNull(result);
        assertEquals(1.4214159999963012, result.getX());
        assertEquals(38.906985999993395, result.getY());
    }
}
