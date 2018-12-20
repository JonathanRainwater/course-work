/*
 * File: ValidateInput.java
 * Author: Jonathan Rainwater
 * Date: 2018-02-18
 * Lab assignment 2.A.1 for Java II
 * 
 * The methods in this class validate input from the user and return the result.
 * Since we haven't covered exception handling yet, this is my attempt to reinvent the wheel.
 */
package lab2;
import java.util.Scanner;

public class ValidateInput {
    private static final Scanner INPUT = new Scanner(System.in); // A reuseable scanner object.
    
    /* getCharacterInput: Get input from the user, validate that the input is a character between the two
     * parameters, and return the result.
     */
    public static char getCharacterInput(char low, char high) {
        boolean inputIsInvalid = true; // Controls the following loop.
        char charInput = 0; // This will become the validated input.
        do {
            // Get user input.
            String strInput = INPUT.nextLine();
            // Check that something was entered.
            if(strInput.length() == 0) {
                System.out.println("You didn't enter anything!");
                // Skip to end and repeat loop.
            }
            else if (strInput.length() > 1) {
                // More than one character was entered.
                // Skip to end and repeat loop.
            }
            else {
                // convert string to char.
                charInput = strInput.charAt(0);
                // Convert any lowercase to uppercase.
                charInput = Character.toUpperCase(charInput); 
                // Check that character is between low and high.
                if ( (charInput >= low) && (charInput <= high ) ) {
                    // Character is between low and high.
                    inputIsInvalid = false;
                } 
            }
            
            if (inputIsInvalid) {
                System.out.print("Invalid input. "
                        + "Enter a character between " + low + " and " + high + ": ");
            }
            
        } while (inputIsInvalid);
        return charInput;
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

    // Get input from the user, validate that the input is a String
    //  with only letters a-z or A-Z, and return the result.
    public static String getStringInput() {
        //int answerInt = -1; // -1 continues the following loop.
        String answerStr = "";
        boolean inputIsValid = false;

        do {
            inputIsValid = false;

            // Get input.
            answerStr = INPUT.nextLine();

            // Check that something was entered.
            // Also validate that only letters were entered.
            if (answerStr.length() == 0)
                System.out.println("You didn't enter anything!");
            else {
                // Check that every character is a letter between A-Z or a-z.
                for (int i = 0; i < answerStr.length(); i++) {
                    char tmpChar = answerStr.charAt(i);
                    if ( ((tmpChar >= 65) && (tmpChar <= 90))
                            || (tmpChar >=97) && (tmpChar <= 122)) {
                        // Character is between A-Z or a-z.
                    }
                    else {  
                        // if (Character.isLetter(answerStr.charAt(i)) == false) {
                        break;  
                    }
                    if (i == (answerStr.length() -1)) {
                        inputIsValid = true;
                    }
                } // End for loop.
            } // End if/else.

            if (inputIsValid ==false) {
                // Ask user to enter valid input.
                System.out.print("Invalid input. "
                        + "Enter only letters A-Z or a-z:");            
            }

        } while (inputIsValid == false);
        return(answerStr);
    }

}
