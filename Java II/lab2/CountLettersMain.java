/*
 * File: CountLettersMain.java
 * Author: Jonathan Rainwater
 * Date: 2018-02-25
 * Lab assignment 2.B for Java II
 * 
 * The purpose of this program is to accept a line of user input and to process that input to determine the
 * number of occurances of each letter of the alphabet in that input. The results will then be displayed.
 * 
 * 1. Display a greeting to the user.
 * 2. Crete an object to hold the input data and prompt the user for input.
 * 3. Convert all characters in the input string to uppercase for ease of provessing.
 * 4. Convert the string to a character array.
 * 5. Use an integer array and a series of loops to count the cumulative number of occurances of each 
 *      letter of the alphabet.
 * 6. Display the counts of each letter to the user.
 * 7. Program ends.
 * 
 */
package lab2;
import java.util.Scanner;

public class CountLettersMain {
    public static void main(String[] args) {
        greeting(); // Display a greeting to the user.
        // Get a line of input from the user and use that to create an instance of CountLetters.
        CountLetters countOfLetters = new CountLetters(getInput());
        // Get the letter-count and display the results.
        showLetterCount(countOfLetters.getCharCount());
    }
    
    // greeting: Displays a greeting to the user.
    private static void greeting() {
        System.out.println("This program is designed to take a line of user input and then count the occurances"
                + " of each letter of the alphabet.");
    }
    
    // getInput: Prompts the user for a line of input and returns that input as a string.
    private static String getInput() {
        System.out.print("Type a line of characters and press enter: ");
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    
    // showLetterCount: Takes an integer array from an instance of a CountLetters object and uses it to 
    //  display a count of how many times each letter of the alphabet appears in the array.
    private static void showLetterCount(int[] charCount) {
        System.out.println("Here is a count of how many of each letter was found: ");
        for (byte b=0; b < charCount.length; b++) {
            // Display each letter along with the count of each letter.
            System.out.printf("%-8s", (char)('A' + b) + "=" + charCount[b]);
            // Limit display of elements to rows of 13.
            if ((b+1)%13 == 0) {
                System.out.println("");
            }
        }
    }
    
}
