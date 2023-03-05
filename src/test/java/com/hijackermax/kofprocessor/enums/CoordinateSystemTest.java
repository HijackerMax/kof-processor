package com.hijackermax.kofprocessor.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoordinateSystemTest {
    @Test
    void testFindByReferenceId() {
        assertThrows(IllegalArgumentException.class, () -> CoordinateSystem.findByReferenceId(-10));
        assertEquals(CoordinateSystem.EPSG_25832, CoordinateSystem.findByReferenceId(22));
        assertEquals(CoordinateSystem.EPSG_4326, CoordinateSystem.findByReferenceId(84));
    }

    @Test
    void testFindByEPSGId() {
        assertThrows(IllegalArgumentException.class, () -> CoordinateSystem.findByEPSGId(-10));
        assertEquals(CoordinateSystem.EPSG_25832, CoordinateSystem.findByEPSGId(25832));
        assertEquals(CoordinateSystem.EPSG_4326, CoordinateSystem.findByEPSGId(4326));
    }
}
