package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    @Test
    void testValidPlateauCreation() {
        Plateau plateau = new Plateau(5, 5);
        assertEquals(5, plateau.getWidth());
        assertEquals(5, plateau.getHeight());
    }

    @Test
    void testInvalidPlateauCreation() {
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> new Plateau(0, 5));
        assertEquals("Plateau size must be positive integers.", exception1.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> new Plateau(-3, 4));
        assertEquals("Plateau size must be positive integers.", exception2.getMessage());
    }

    @Test
    void testPositionWithinBounds() {
        Plateau plateau = new Plateau(5, 5);

        assertTrue(plateau.isWithinBounds(0, 0)); // Bottom-left corner
        assertTrue(plateau.isWithinBounds(5, 5)); // Upper-right corner
        assertTrue(plateau.isWithinBounds(3, 4)); // Inside plateau
    }

    @Test
    void testPositionOutOfBounds() {
        Plateau plateau = new Plateau(5, 5);

        assertFalse(plateau.isWithinBounds(-1, 2)); // Negative x-coordinate
        assertFalse(plateau.isWithinBounds(2, -1)); // Negative y-coordinate
        assertFalse(plateau.isWithinBounds(6, 5)); // Beyond x boundary
        assertFalse(plateau.isWithinBounds(5, 6)); // Beyond y boundary
    }
}