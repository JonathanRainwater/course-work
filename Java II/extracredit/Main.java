/*
 * File: Main.java
 * Author: Jonathan Rainwater
 * Date: 2018-03-03
 * Lab assignment extra credit for Java II 
 * 
 * This program creates a team of players and then allows the user to display a detailed player roster, search
 * for players on the team by player number, or sort the players on the team by player number.
 */
package extracredit;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Team dream = new Team(); // Creates a new team object.
        dream.setName("Dream"); // Sets the name of the team.
        greeting(); // Displays a greeting.
        byte choice = 0; // Controls the following loop.
        do {
            displayOptions(); // Displayer options for the user to choose from.
            choice = (byte)ValidateInput.getIntegerInput(1, 4); // Get the choice from the user.
            if (choice == 1) {
                // Print a formatted list of each player on the team showing their name, player number, and batting-average.
                dream.printRoster(); 
            }
            else if (choice == 2) {
                // Search for a player on the team by player number.
                // If found, display that players name, player number, and batting-average.
                searchForPlayerNumber(dream);
            }
            else if (choice == 3) {
                // Sort the players in the array in ascending order based on player number.
                sortPlayers(dream);
            }
            // Value of choice already validated to be between 1 and 4.
        } while (choice != 4); // End loop if the user chose 4.
        System.out.println("Goodbye");
    }
    
    // Displays a greeting.
    private static void greeting() {
        System.out.println("Welcome to the baseball roster inpector.");
    }
    
    // Displays options for the main menu.
    private static void displayOptions() {
        System.out.println("To print the roster, enter 1:\n"
                + "To look in the roster for a player by number, enter 2:\n"
                + "To sort the roster by number, enter 3:\n"
                + "To quit this program, enter 4:");
    }
    
    // Searches for a player in the given team.
    // If player is found, player info is displayed. If player not found, an error message is displayed.
    private static void searchForPlayerNumber(Team tName) {
        System.out.println();
        int pNumber = ValidateInput.getIntegerInputFrom(0); // Get player number from user.
        Player p = tName.search(pNumber); // Search for that player number on the given team.
        // Check if a player with that number was found.
        if (p == null) {
            System.out.println("Error: no player has number " + pNumber + "\n"
                    + "Press enter to continue:");
            new Scanner(System.in).nextLine(); // Wait for user input to continue.
        }
        else {
            System.out.println(p);
            System.out.println("Press enter to continue:");
            new Scanner(System.in).nextLine(); // Wait for user input to continue.
        }
    }
    
    // Sort the players on the given team in ascending order based on player number.
    private static void sortPlayers(Team tName) {
        tName.sort();
        System.out.println("\nTeam " + tName.getName() + " has been sorted by number.\n"
                + "Press enter to continue:");
        new Scanner(System.in).nextLine(); // Wait for user input to continue.
    }
}
