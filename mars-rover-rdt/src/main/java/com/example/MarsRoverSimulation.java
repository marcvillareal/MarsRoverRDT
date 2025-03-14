package com.example;

import java.util.Scanner;

public class MarsRoverSimulation {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter plateau size (width, height):");
            int width = scanner.nextInt();
            int height = scanner.nextInt();
            Plateau plateau = new Plateau(width, height);

            System.out.println("Enter number of rovers:");
            int numRovers = scanner.nextInt();

            for (int i = 0; i < numRovers; i++) {
                System.out.println("Enter rover " + (i + 1) + " start position (x, y, direction):");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                Compass direction = Compass.valueOf(scanner.next().toUpperCase());

                Rover rover = new Rover(x, y, direction, plateau);

                System.out.println("Enter movement commands for rover " + (i + 1) + ":");
                String commands = scanner.next();

                rover.processCommands(commands);

                // Print final position
                System.out.println("Final Position of Rover " + (i + 1) + ": " + rover.getX() + " " + rover.getY() + " " + rover.getDirection());
            }
        }
    }
}