/*
 * Written by Jonathan Rainwater
 * Chapter 2
 * Assignment 2B
 * 
 *  This program promts the user to enter width and lenth values for a rectangle
 *   and then uses those values to calculate the area and perimeter and print the results.
 */

package lab2;
import java.util.* ;

public class Rectangle {
  public static void main(String[] args) {
      
      // Declare and initialize variables.
      int length;
      int width;
      int area;
      int perimeter;
      Scanner console = new Scanner (System.in);
      
      // Record length.
      System.out.print("Enter the length: ");
      length = console.nextInt();
      
      // Record width
      System.out.print("Enter the width: ");
      width = console.nextInt();
      
      // Calculate area and perimeter.
      area = length * width;
      perimeter = 2 * (length + width);
      
      // Print results.
      System.out.println("\nArea = " + area);
      System.out.println("Perimeter = " + perimeter);
       }
  }