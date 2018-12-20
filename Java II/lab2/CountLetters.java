/*
 * File: CountLetters.java
 * Author: Jonathan Rainwater
 * Date: 2018-02-25
 * Lab assignment 2.B for Java II
 * 
 * This class creates an object that holds and processes the characters of a line of user input to determine
 * the number of occurances of each letter of the alphabet in the processed string.
 */
package lab2;

public class CountLetters {
    // charInput contains an array of characters sourced from user input.
    private char[] charInput; 
    // charCount ontains a count of each occurance of each letter in the alphabet, where [0]=A, [1]=B, etc.
    private int[] charCount = new int[26]; 
    
    // Creates an instance with string input.
    public CountLetters(String strInput) {
        // Change all letters to uppercase for ease of processing.
        strInput = strInput.toUpperCase(); 
        // Convert string input to char array.
        this.charInput = strInput.toCharArray();
        // Count the letters in the received input.
        countTheLetters();
    }
    
    // countTheLetters: Counts the number of occurances of letters of the alphabet and stores those values
    // in an integer array.
    private void countTheLetters() {
        for (int i=0; i < this.charInput.length; i++) {
            // For each letter in the character array.
            for (byte b=0; b < this.charCount.length; b++) {
                // For each element in the integer array that represents a specific letter.
                if (charInput[i] == ('A' + b) ) {
                    /* If the current character in the character array matches its corresponding place in the integer
                    array, then add a value to that element in the integer array. */
                    charCount[b]++;
                    break;
                }
            }
        }
    }
    
    // getCharCount: Returns the integer array that holds the character count.
    public int[] getCharCount() {
        return this.charCount;
    }
    
}
