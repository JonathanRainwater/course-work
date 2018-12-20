/*
 * Written by Jonathan Rainwater
 * Lab Assignment 4
 * 
 * This program is a user-interactive game of rock, paper, sciccors.
 * The user will be prompted to enter a number choice which will then
 *   be compared to a randomly generated choice for the computer. 
 * The results will then be displayed to the user. The user will 
 *  then be able to choose to continue playing or to quit the program.
 * 
 * 1. Declare variables to record number of wins/loses/ties and sentinel boolean for loop.
 * 2. Create a main loop to repeat the game util user input ends the game.
 * 3. Generate a random number between 1 and 3 for the computer.
 * 4. Display a dialog to the user to inform them of their choices.
 * 5. Validate that user input is a single digit between 1 and 3,
 *      then record that value to a variable.
 * 6. Compare the two numbers for the user and the computer to determine
 *      if the result is a win, lose, or tie, and display the retult.
 * 7. Increment win, lose, or tie vairable by one to record result.
 * 8. Display to user options to keep playing or to end the game.
 * 9. Validate that user input is a single digit between 0 and 1 and return result,
 *      which determines if the main loop will repeat, thereby repeating the game,  or end.
 * 10. If the main loop ends, display the results of win/loses/ties and the program ends.
*/
package lab4;
import java.util.Scanner;

public class Lab4 {
    public static void main(String[] args) {
        int userWins =0, userLoses =0, ties = 0;
        boolean keepPlaying = true;
        
        do {
            String result = "";
            // Create a number for the cumputer choice.
           int computerNumber = ((int)(Math.random() * 3) + 1);
           // Get a valid number from the user.
           int userNumber = getUserChoice();
           // Compare the two numbers and determine the result.
           result = determineResult(userNumber, computerNumber);
           // Set the win/lose/tie count and display summary result.
           if (result.equals("tie"))
               ties++;
           else if (result.equals("win"))
               userWins++;
           else
               userLoses++;
           // Ask user if the want to keep playing or quit.
           keepPlaying = askToKeepPlaying();
        } while (keepPlaying);
        // Print number of wins/loses/ties.
        System.out.println("Total wins:" + userWins
                + " Total loses: " + userLoses
                + " Total ties: " + ties);
    }
    
// Prompt user to enter a choice between 1 and 3..
public static int getUserChoice() {
    
    System.out.print("Let's play a game of ROCK, PAPER, SCICCORS\n"
            + "To pick ROCK, enter: 1\n"
            + "To pick PAPPER, enter: 2\n"
            + "To pick SCICCORS, enter: 3\n"
            + "Your choice:");
    return(getSingleDigitInput(1, 3)); 
}

// Get input from the user, validate that the input is a single digit between
//    two values, and return the result.
public static int getSingleDigitInput(int low, int high) {
    Scanner tmpInput = new Scanner(System.in);
    int answerInt = -1; // -1 continues the following loop.

    do {
        // Get input.
        String answerStr = tmpInput.nextLine();
        
        // Validate that exactly one character was entered.
        if (answerStr.length() == 1) {
            answerInt = Character.getNumericValue(answerStr.charAt(0));
            // Also validate that the character entered was between low and high.
            if ((answerInt >=  low) && (answerInt <= high))
                break;
            else
                answerInt = -1; // To repeat the loop.
        }
        
        // Ask user to enter valid input.
        System.out.print("Invalid input. "
                + "Enter a number between " + low + " and " + high + ": ");
    } while (answerInt == -1);
    return(answerInt);
}

// Compare the userNumber and the computerNumber to determine the result,
//      print the result, and return the result.
public static String determineResult(int userNumber, int computerNumber) {
    String result = "";
    
    if (userNumber == computerNumber) {
        result = "tie";
        System.out.print("A TIE! ");
        switch (userNumber) {
            case 1: System.out.println("Two frustrated ROCKS!"); break;
            case 2: System.out.println("Two frustrated PAPERS!"); break;
            case 3: System.out.println("Two frustrated SCICCORS!"); break;
        }
    }
    // 1=ROCK, 2=PAPER, 3=SCICCORS
    else if (userNumber == 1) {
        if (computerNumber == 3) {
            result = "win";
            System.out.println("You WIN! ROCK smashes SCICCORS!");
        }
        else {
            result = "lose";
            System.out.println("You LOSE! ROCK gets smothered by PAPER!");
        }
        }
    else if (userNumber == 2) {
        if (computerNumber == 1) {
            result = "win";
            System.out.println("You WIN! PAPAER smothers ROCK!");
        }
        else {
            result = "lose";
            System.out.println("You LOSE! PAPER gets cut by SCICCORS!");
        }
    }
    else if (userNumber == 3) {
        if (computerNumber == 2) {
            result = "win";
            System.out.println("You WIN! SCICCORS cuts PAPER!");
        }
        else {
            result = "lose";
            System.out.println("You LOSE! SCICCORS gets smashed by ROCK!");
        }
   
    }
    
    return(result);
}

// Ask the user if they want to keep playing or quit and return the result.
public static boolean askToKeepPlaying() {
    System.out.print("Do you want to play again? Enter 1 to keep playing or 0 to quit: ");
    if (getSingleDigitInput(0,1) == 1) {
        System.out.println();
        return(true);
    }
    return(false);
}

}
