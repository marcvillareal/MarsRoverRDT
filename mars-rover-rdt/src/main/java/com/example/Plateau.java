package com.example;

public class Plateau {

   // Stores the grid size (x, y)
   // Ensure rover stays within grid

   /*
    * Fields: width and height (x & y)
    * A constructor to initialise them
    * A method to validate whether the position is within bounds
    */

   private int width; // x
   private int height; // y

   public Plateau(int width, int height) {

      if (width <= 0 || height <= 0) {
         throw new IllegalArgumentException("Plateau size must be positive integers.");
      }
      this.width = width;
      this.height = height;
   }

   public boolean isWithinBounds(int x, int y) {
      // Validate position of rover if within bounds
      return x >= 0 && y >= 0 && x <= width && y <= height;
   }

   public int getWidth() {
      return width;
   }

   public int getHeight() {
      return height;
   }
}
