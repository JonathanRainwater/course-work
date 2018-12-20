/*
 * File: PaintJobEstimator.java
 * Author: Jonathan Rainwater
 * Date: 2018-01-30
 * Lab assignment 1b for Java II
 * 
 * This program calculates an estimate cost for a paint job. User input is taken for the number of rooms,
 * square feet per room, and cost of paint per gallon. User input is then processed with other known values to
 * provide the needed hours of labor, the total amount of paint needed, the costs of paint and labor,
 * and the total combined cost.
 * 
 * 1. Display a greeting to the user.
 * 2. Prompt the user to enter the number of rooms to be painted.
 * 3. Prompt the user to enter the square footage of each room and sum the results.
 * 4. Prompt the user to enter the const of paint per gallon.
 * 5. Calculate the total paint cost based on the total sqaure feet to be painted and the cost per gallon of paint.
 * 6. Calculate the total labor hours required based on the total square feet to be painted.
 * 7. Calculate the total cost of labor based on the total hours of labor required.
 * 8. Calculate the total gallons of paint required based on the total square feet to be painted.
 * 9. Print the total number of gallons of paint required, the total number of labor hours required, the total cost of
 *      the paint, the total cost of the labor, and the total combined costs for the entire job.
 * 10. Program ends.
 */

package lab1;
import java.util.Scanner;
import java.text.DecimalFormat;

public class PaintJobEstimator {
    
    // The company's labor charges per hour.
    private static final double LABOR_CHARGE_PER_HOUR = 18.00;
    // The number of labor hours needed per gallon of pain.
    private static final int LABOR_HOURS_PER_GALLON = 8;
    // The number of square feet that one gallon will cover.
    private static final int SQUARE_FEET_PER_GALLON = 115;
    // The minimum and maximum number of rooms allowed.
    private static final int MINIMUM_NUMBER_OF_ROOMS = 1;
    private static final int MAXIMUM_NUMBER_OF_ROOMS = 100;
    // The minimum and maximum number of square feet allowed per room.
    private static final int MINIMUM_NUMBER_OF_SQUARE_FEET = 16;
    private static final int MAXIMUM_NUMBER_OF_SQUARE_FEET = 4000;
    // The minimum and maximum cost for a gallon of paint.
    private static final double MINIMUM_PAINT_PRICE_PER_GALLON = 0.01;
    private static final double MAXIMUM_PAINT_PRICE_PER_GALLON = 100;
    private static final Scanner INPUT = new Scanner(System.in); // To accept user input.
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("$0.00"); // To format numbers into proper dollar-format.
    private static final DecimalFormat TENTH_FORMAT = new DecimalFormat("0.0"); // To format numbers to one decimal place.
    private static final DecimalFormat HUNDREDTH_FORMAT = new DecimalFormat("0.00"); // To format numbers to two decial places.
    
    public static void main(String[] args) {
        greeting(); // Display a greeting to the user.
        int numberOfRooms = getRooms(); // Get the number of rooms to be painted.
        int totalSquareFeet = getSquareFeet(numberOfRooms); // Get the total square feet of all the rooms.
        double totalCost = getTotal(totalSquareFeet); // Get the total cost of the job and display other details.
        System.out.println("The total cost of the job is: " + MONEY_FORMAT.format(totalCost));
    }
    
    /* greeting: Displays a greeting to the user.
     */    
    public static void greeting() {
        System.out.println("This program estimates the cost of a paint job based on the answers given to vaious questions.");
    }
    
    /* getRooms: Prompts the user to enter the number of rooms to be painted and returns the result.
     */
    public static int getRooms() {
        System.out.print("Enter the total number of rooms to be painted "
                + "(Enter " + MINIMUM_NUMBER_OF_ROOMS + "-" + MAXIMUM_NUMBER_OF_ROOMS + "): ");
        // Prompt user for an integer input within a given range and return the result.
        return getIntegerInput(MINIMUM_NUMBER_OF_ROOMS, MAXIMUM_NUMBER_OF_ROOMS);
    }
    
    /* getSquareFeet: Prompts user to enter the square feet for each room to be painted.
     *      Takes number of rooms to be painted as int rooms.
     *      Returns total square feet for all tooms to be painted.
     */
    public static int getSquareFeet(int rooms) {
        int totalSquareFeet = 0; // Will track the total square feel of all the rooms.
        // Add together the square footage of each room.
        for (int i=1; i < (rooms + 1); i++) {
            System.out.print("Enter the total square feet of room number " + i +
                    " (Enter " + MINIMUM_NUMBER_OF_SQUARE_FEET + "-" + MAXIMUM_NUMBER_OF_SQUARE_FEET + "): ");
            // Prompt user for an integer input within a given range and add the result.
            totalSquareFeet += getIntegerInput(MINIMUM_NUMBER_OF_SQUARE_FEET,MAXIMUM_NUMBER_OF_SQUARE_FEET);
        }
        return totalSquareFeet;
    }
    
    /* getTotal: Calculates the total cost of the paint job and displays other details.
     *      Takes the total square feet to be painted as double squareFeet.
     *      Returns the total cost of the paint job.
     */
    public static double getTotal(double squareFeet) {
        // Get the price of paint per gallon from the user.
        double pricePerGallon = getPricePerGallon();
        // Calculate the total paint cost.
        double totalPaintCost = getPaintCost(squareFeet, pricePerGallon);
        // Calculate the total labor hours needed to do the job.
        double totalHours = getHours(squareFeet);
        // Calculate the total labor cost.
        double totalLaborCost = getLabor(totalHours);
        // Calculate the total gallons of paint needed.
        double totalGallons = getGallons(squareFeet);
        // Display details.
        System.out.println("The total gallons of paint required is: " + TENTH_FORMAT.format(totalGallons));
        System.out.println("The total hours of labor required is: " + TENTH_FORMAT.format(totalHours));
        System.out.println("The total cost of the paint is: " + MONEY_FORMAT.format(totalPaintCost));
        System.out.println("The total labor cost is: " + MONEY_FORMAT.format(totalLaborCost));
        // Return the total cost of the job.
        return (totalPaintCost + totalLaborCost);
    }
    
    /* getPricePerGallon: Prompts the user to enter the price per gallon of paint and returns the result.
     */
    public static double getPricePerGallon() {
        System.out.println("Enter the cost of the paint per gallon (Enter " +
                MINIMUM_PAINT_PRICE_PER_GALLON + "-" + 
                HUNDREDTH_FORMAT.format(MAXIMUM_PAINT_PRICE_PER_GALLON) + "): ");
        // Prompt user for a number input within a given range and return the result.
        return getDoubleInput(MINIMUM_PAINT_PRICE_PER_GALLON, MAXIMUM_PAINT_PRICE_PER_GALLON);
    }
    
    /* getPaintCostDouble: Calculate the total cost of paint and return the result.
     *      Takes the total square feet of all the rooms as sqFeet, and takes the price of paint per gallon as galPrice.
     */
    public static double getPaintCost(double sqFeet, double galPrice) {
        return ((sqFeet / SQUARE_FEET_PER_GALLON) * galPrice);
    }
    
    /* getGallons: Calculate the total number of gallons of paint needed and return the result.
     *      Takes the total square feet of all the rooms as sqFeet.
     */
    public static double getGallons(double sqFeet) {
        return (sqFeet / SQUARE_FEET_PER_GALLON);
    }
    
    /* getHours: Calculate the total number of labor hours required and returns the result.
     * Takes the total square feet of all the rooms as sqFeet.
     */
    public static double getHours(double sqFeet) {
        return ((sqFeet / SQUARE_FEET_PER_GALLON) * LABOR_HOURS_PER_GALLON);
    }
    
    /* getLabor: Calculates the total labor cost and returns the result.
     * Takes the total number of labor hours required as hours.
     */
    public static double getLabor(double hours) {
        return (hours * LABOR_CHARGE_PER_HOUR);
    }
    
    /* getIntegerInput: Get input from the user, validate that the input is an integer between
     * the two parameters, and return the result.
     */    
    public static int getIntegerInput(int low, int high) {
        boolean inputIsInvalid = true; // Controls the following loops.
        int intInput = 0; // This will become the validated input.
        do {
            // Get user input.
            String strInput = INPUT.nextLine();
            // Check that something was entered.
            if (strInput.length() == 0) {
                System.out.println("You didn't enter anything!");
            }
            else if (strInput.length() > 9) {
                // String is too large to be parsed as a valid integer.
                // Skip to end and repeat main loop.
            }
            else {
                // Check that every character is an integer.
                for (int i=0; i < strInput.length(); i++) {
                    if ( Character.isDigit(strInput.charAt(i)) ) {
                        // Character is a digit.
                    }
                    else {
                        // Character is not a digit.
                        break; // Break out of for loop and repeat main loop.
                    }
                    // Check if the entire input string has been processed.
                    if ( i == (strInput.length() - 1) ) {
                        // All characters are digits.
                        // Convert string input to int.
                        intInput = Integer.parseInt(strInput);
                        // Check if number is between low and high
                        if ( (intInput >= low) && (intInput <= high) ) {
                            // Number is between low and high..
                            inputIsInvalid = false;
                        }
                    }
                } // End for loop.
            }// End else.
            
            if (inputIsInvalid) {
                System.out.print("Invalid input. "
                        + "Enter an whole number between " + low + " and " + high + ": ");
            }
        } while (inputIsInvalid);
        
        return intInput;
    }

    /* getDoubleInput: Get input from the user, validate that the input is a number between
     * the two parameters, and return the result.
     */    
    public static double getDoubleInput(double low, double high) {
        boolean inputIsInvalid = true; // Controls the following loops.
        double doubleInput = 0; // This will become the validated input.
        do {
            // Get user input.
            String strInput = INPUT.nextLine();
            // Check that something was entered and that it wasn't just a decimal.
            if ( (strInput.length() == 0) || ( (strInput.length() == 1) && (strInput.charAt(0) == '.') ) ) {
                System.out.println("You didn't enter anything!");
            }
            // Check if input is so large that it may lead to parsing errors..
            else if (strInput.length() > 100) {
                // Skip to end and repeat main loop.
            }
            else {
                // Check that every character is either a digit or a decimal..
                for (int i=0, decimalCount=0; i < strInput.length(); i++) {
                    if ( Character.isDigit(strInput.charAt(i))  || strInput.charAt(i) == '.' ) {
                        // Character is a digit or a decimal.
                        // Track decimals and make sure no more than one if found.
                        if (strInput.charAt(i) == '.') {
                            // Character is a decimal.
                            decimalCount++;
                            if (decimalCount > 1) {
                                // Too many decimals to be a valid double.
                                break; // Break out of for loop and repeat main loop.
                            }
                        }
                    }
                    else {
                        // Character is not a digit or a decimal.
                        break; // Break out of for loop and repeat main loop.
                    }
                    // Check if the entire input string has been processed.
                    if ( i == (strInput.length() - 1) ) {
                        // All characters are digits or contians a decimal.
                        // Convert string input to double.
                        doubleInput = Double.parseDouble(strInput);
                        // Check if number is between low and high
                        if ( (doubleInput >= low) && (doubleInput <= high) ) {
                            // Number is between low and high..
                            inputIsInvalid = false;
                        }
                    }
                } // End for loop.
            }// End else.
            
            if (inputIsInvalid) {
                System.out.print("Invalid input. "
                        + "Enter a number between " + low + " and " + high + ": ");
            }
        } while (inputIsInvalid);
        
        return doubleInput;
    }

}
