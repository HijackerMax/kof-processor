package com.hijackermax.kofprocessor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InstructionTypeTest {
    @Test
    void testFindByValue() {
        assertEquals(InstructionType.UNKNOWN, InstructionType.findByValue("110"));
        assertEquals(InstructionType.COORDINATES, InstructionType.findByValue("05"));
    }
}
