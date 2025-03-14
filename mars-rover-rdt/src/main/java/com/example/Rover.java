package com.example;

import java.util.Set;

public class Rover {

    /*
     * Stores position (x, y) and direction (N,S,E,W)
     * 
     * Moves forward according to it's direction
     * 
     * Rotates left or right
     */

    private int x, y;
    private Compass direction;
    private Plateau plateau;
    private static Set<String> occupiedPositions = new java.util.HashSet<>();

    public Rover(int x, int y, Compass direction, Plateau plateau) {
        if (x < 0 || x > plateau.getWidth() || y < 0 || y > plateau.getHeight()) {
            throw new IllegalArgumentException("Rover starting position is outside the plateau.");
        }
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.plateau = plateau;

        // Ensure no two rovers start at the same position
        String positionKey = x + "," + y;
        if (occupiedPositions.contains(positionKey)) {
            throw new IllegalArgumentException("Another rover is already at (" + x + ", " + y + ")");
        }
        occupiedPositions.add(positionKey);
    }

    public void processCommands(String commands) {
        if (!commands.matches("[LRM]+")) {
            throw new IllegalArgumentException("Invalid command sequence. Only L, R, and M are allowed.");
        }

        for (char command : commands.toUpperCase().toCharArray()) {
            switch (command) {
                case 'L' -> rotateLeft();
                case 'R' -> rotateRight();
                case 'M' -> move();
            }
        }
    }

    public void move() {
        int newX = x, newY = y;

        switch (direction) {
            case N -> newY++;
            case S -> newY--;
            case E -> newX++;
            case W -> newX--;
        }

        // Ensure movement is within plateau boundaries
        if (plateau.isWithinBounds(newX, newY)) {
            // Check if another rover is in the way
            String newPosition = newX + "," + newY;
            if (!occupiedPositions.contains(newPosition)) {
                // Move rover to the new position
                occupiedPositions.remove(x + "," + y); // Remove old position only if the move is successful
                x = newX;
                y = newY;
                occupiedPositions.add(newPosition); // Add new position
            } else {
                System.out.println("Move blocked! Another rover is at (" + newX + ", " + newY + ")");
            }
        } else {
            System.out.println("Move blocked! Rover cannot leave the plateau.");
        }
    }

    public void rotateLeft() {
        switch (direction) {
            case N -> direction = Compass.W;
            case W -> direction = Compass.S;
            case S -> direction = Compass.E;
            case E -> direction = Compass.N;
        }
    }

    public void rotateRight() {
        switch (direction) {
            case N -> direction = Compass.E;
            case E -> direction = Compass.S;
            case S -> direction = Compass.W;
            case W -> direction = Compass.N;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Compass getDirection() {
        return direction;
    }

    public static void resetOccupiedPositions() {
        occupiedPositions.clear();
    }
}