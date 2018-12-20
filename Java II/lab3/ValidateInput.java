/*
 * File: ValidateInput.java
 * Author: Jonathan Rainwater
 * Date: 2018-03-17
 * Lab assignment 3.A and 3.B for Java II
 * 
 * The methods in this class validate input from the user and return the result.
 */
package lab3;
import java.util.Scanner;
import java.util.InputMismatchException;

public abstract class ValidateInput {
    private static final Scanner INPUT = new Scanner(System.in); // A reuseable scanner object.
    
    // Halts the progreesion of the program to wait for user input to proceed.
    public static void pause() {
        System.out.print("Press enter to continue:");
        new Scanner(System.in).nextLine(); // Wait for user input to proceed.
        System.out.println();
    }
    
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
    
    // Get input from the user, validate that the input is an integer that is at least the given low vaue
    // and return the result.
    public static int getIntegerInputFrom(int low) {
        //Scanner input = new Scanner(System.in);
        int choice = 0;
        boolean invalidInput = true;
        do {
            System.out.print("Enter an integer greater than or equal to " + low + " :");
            try {
                //choice = input.nextInt();
                choice = new Scanner(System.in).nextInt();
                if (choice >= low) {
                    invalidInput = false; // Loop will end.
                }
                else {
                    System.out.println("Invalid number. Number too small."); // Loop will repeat.
                }
            }
            catch (InputMismatchException ex) {
                System.out.println("Invalid integer.");
            }
        } while (invalidInput);
        return choice;
    }
    
    /* getIntegerInput: Get input from the user, validate that the input is an integer between
     * the two parameters, and return the result.
     */    
    public static int getIntegerInput(int low, int high) {
        Scanner scannerIn = new Scanner(System.in);
        boolean inputIsNotValid = true; // Controls the following loops.
        int intInput = 0; // This will become the validated input.
        do {
            System.out.print("Enter an integer between " + low + " and " + high + ": ");
            try {
                intInput = scannerIn.nextInt();
                if (intInput >= low && intInput <= high) {
                    // The input is within the valid range of low and high.
                    inputIsNotValid = false; // To end loop.
                }
                else {
                    // The input is not within the valid range of low and high.
                    System.out.println("ERROR: " + intInput +" is outside the valid range of " + low + " and " + high);
                    // The loop will repeat until a valid value is given.
                }
            }
            catch (InputMismatchException ex) {
                System.out.println("ERROR: That is not a valid integer.");
                scannerIn.nextLine(); // To clear the buffer.
                // The loop will repeat until a valid value is given.
            }
        } while (inputIsNotValid); // Loop ends when input is valid.
        return intInput; // Return the validated integer that is between low and high.
    }
    
/* Get input from the user, validate that the input is a double that is at least the given low value,
     and return the result. */
    public static double getDoubleInputFrom(double low) {
        Scanner scannerIn = new Scanner(System.in);
        double userInput = 0;
        boolean inputIsNotValid = true; // Controls the following loop.
        do {
            System.out.print("Enter a number greater than or equal to " + low + " (may contain decimal places): ");
            try {
                userInput = scannerIn.nextDouble();
                if (userInput >= low) {
                    // The input value is high enough to be valid.
                    inputIsNotValid = false; // To end loop.
                }
                else {
                    // The input value is too low.
                    System.out.println("ERROR: " + userInput + " is below " + low);
                    // The loop will repeat until a valid value is given.
                }
            }
            catch (InputMismatchException ex) {
                System.out.println("ERROR: That is not a valid value.");
                scannerIn.nextLine(); // To clear the buffer.
                // The loop will repeat until a valid value is given.
            }
        } while(inputIsNotValid); // Loop ends when input is valid.
        return userInput;
    }
    
/* getDoubleInput: Get input from the user, validate that the input is a number between
     * the two parameters, and return the result.
     */    
    public static double getDoubleInput(double low, double high) {
        System.out.print("Enter an number between " + low + " and " + high + ": ");
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
    public static String getStringInputOfLetters() {
        System.out.print("Enter any combination of letters from A/a to Z/z: ");
        //int answerInt = -1; // -1 continues the following loop.
        String answerStr = "";
        boolean inputIsValid = false;

        do {
            inputIsValid = false;

            // Get input.
            answerStr = new Scanner(System.in).nextLine();

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
    
    // Get input from the user, validate that it is a String
    // consisting of only letters or spaces, and return the result.
    public static String getStringOfLettersAndSpaces() {
        Scanner scannerIn = new Scanner(System.in);
        String userInput;
        boolean inputIsNotValid = true; // Controls the following loop.
        do {
            System.out.print("Enter only letters or spaces: ");
            userInput = scannerIn.nextLine(); // Get user input.
            if (userInput.matches("[a-zA-Z][a-z A-Z]*")) {
                // Input is in the correct format.
                inputIsNotValid = false; // To end loop
            }
            else {
                // Input is not in the correct format.
                System.out.println("Invalid format.");
            }
        } while(inputIsNotValid); // Loop ends when input is valid.
        return userInput;
    }

}
