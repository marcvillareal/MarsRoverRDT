package com.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import com.example.Rover;
import com.example.Plateau;
import com.example.Compass;

class RoverTest {

    @BeforeEach
    void resetRoverPositions() {
        // Reset occupied positions before each test to avoid conflicts
        Rover.resetOccupiedPositions();
    }

    @Test
    void testRotateLeft() {
        Plateau plateau = new Plateau(5, 5);
        Rover rover = new Rover(1, 1, Compass.N, plateau);

        rover.rotateLeft();
        assertEquals(Compass.W, rover.getDirection());
    }

    @Test
    void testRotateRight() {
        Plateau plateau = new Plateau(5, 5);
        Rover rover = new Rover(1, 1, Compass.N, plateau);

        rover.rotateRight();
        assertEquals(Compass.E, rover.getDirection());
    }

    @Test
    void testMoveForwardWithinBounds() {
        Plateau plateau = new Plateau(5, 5);
        Rover rover = new Rover(1, 1, Compass.N, plateau);

        rover.move();
        assertEquals(1, rover.getX());
        assertEquals(2, rover.getY());
    }

    @Test
    void testMoveDoesNotExceedBounds() {
        Plateau plateau = new Plateau(5, 5);
        Rover rover = new Rover(0, 0, Compass.S, plateau);

        rover.move(); // Should not move
        assertEquals(0, rover.getX());
        assertEquals(0, rover.getY());
    }

    @Test
    void testProcessValidCommands() {
        Plateau plateau = new Plateau(5, 5);
        Rover rover = new Rover(1, 2, Compass.N, plateau);

        rover.processCommands("LMLMLMLMM");
        assertEquals(1, rover.getX());
        assertEquals(3, rover.getY());
        assertEquals(Compass.N, rover.getDirection());
    }

    @Test
    void testProcessInvalidCommands() {
        Plateau plateau = new Plateau(5, 5);
        Rover rover = new Rover(1, 1, Compass.N, plateau);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rover.processCommands("LMXRM"); // 'X' is invalid
        });

        assertEquals("Invalid command sequence. Only L, R, and M are allowed.", exception.getMessage());
    }

    @Test
    void testCollisionDetection() {
        Plateau plateau = new Plateau(5, 5);
        Rover rover1 = new Rover(1, 1, Compass.N, plateau);
        Rover rover2 = new Rover(1, 2, Compass.S, plateau); // Directly in front of rover1

        rover1.move(); // Should be blocked by rover2
        assertEquals(1, rover1.getX());
        assertEquals(1, rover1.getY());
    }
}