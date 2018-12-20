/*
 * File: Team.java
 * Author: Jonathan Rainwater
 * Date: 2018-03-03
 * Lab assignment extra credit for Java II
 * 
 * This class creates a team object that contains an array of Player objects and a name for the team.
 */
package extracredit;

import java.util.Scanner;
import java.util.Arrays;


public class Team {
    private Player[] players; // An array of player objects.
    private String name; // Name of the team.
    private static final int MIN_NUM_PLAYERS = 9; // The minimum number of players on a team.
    private static final String DEFAULT_NAME = ""; // Default player name.
    
    // Creates a team with predefined players.
    public Team() {
        players = new Player[MIN_NUM_PLAYERS];
        players[0] = new Player("Berra", 8, 0.285);
        players[1] = new Player("Ryan", 34, 0.0);
        players[2] = new Player("Aparicio", 11, 0.262);
        players[3] = new Player("Pujols", 5, 0.328);
        players[4] = new Player("Jackson", 9, 0.262);
        players[5] = new Player("Schoendienst", 10, 0.289);
        players[6] = new Player("Carew", 29, 0.328);
        players[7] = new Player("Aaron", 44, 0.305);
        players[8] = new Player("Mantle", 7, 0.298);
        name = DEFAULT_NAME;
    }
    
    // Creates a team out of the given array of players.
    public Team(Player[] p) {
        players = new Player[0];
        // Add players incrementally to check for duplicate player numbers, which are forbidden.
        for (int i=0; i < p.length; i++) {
            this.addPlayer(p[i]);
        }
    }
    
    // Creates a team with the given array of players and the given name.
    public Team(Player[] p, String name) {
        this(p);
        this.name = name;
    }
    
    // Adds the given player to the team while making sure no two players will have the same number.
    public final void addPlayer(Player newP) {
        // Check if the player to be added has a number that is already assigned to a current team member.
        // If so, forse user to choose a new number for that player that isn't already taken.
        boolean playerNumberAlreadyExists = true; // Controls following loop.
        do {
            boolean numberWasChanged = false;
            for (Player currentP: players) { 
                if (currentP.getNumber() == newP.getNumber()) {
                    System.out.println("ERROR: This team already has a player with that number.\n"
                            + "Choose a new number for player " + newP.getName());
                    newP.setNumber(); // Force user to choose a new number for that player.
                    numberWasChanged = true; // To force main loop to repeat.
                    break;
                }
            } // End for.
            // If player number was not changed, the main loop may end.
            // Else the main loop to check for duplicate numbers must be run again.
            if ( ! numberWasChanged) {
                playerNumberAlreadyExists = false; // To end main loop.
            }
        } while(playerNumberAlreadyExists);
        // Increase the size of the team by one to make room for the new player.
        players = Arrays.copyOf(players, players.length +1);
        players[players.length - 1] = newP; // Add the given player to the team.
    }
    
    // Removes a player with the given index from the team.
    public void removePlayer(int playerNum) {
        // Check if the given player number is currently on the team.
        if ( ! playerIsOnTeam(playerNum)) {
            return; // Exit method.
        }
        // Create a new array that is one size smaller 
        Player[] tmpPlayers = new Player[players.length - 1];
        for (int a=0, b=0; a < players.length; a++) {
            // If players[a] is not the player to be removed, then copy from players[a] to tmpPlayers[b]
            // if a is player to be removed, allow a to increment without b, skipping the player to be removed.
            if (players[a].getNumber() != playerNum) {
                // a is not player to be removed.
                tmpPlayers[b] = players[a];
                b++; // Track next position to fill in new array.
            }
        }// End for loop.
        players= tmpPlayers;
    }
    
    // Check if the given player number is currently on the team.
    public boolean playerIsOnTeam(int playerNum) {
        boolean playerIsOnTeam = false;
        for (Player p: players) {
            if (playerNum == p.getNumber()) {
                // Player with the given number was found.
                playerIsOnTeam = true; 
                break;
            }
        } // End for.
        if ( ! playerIsOnTeam) {
            // No player with that number was found.
            System.out.println("ERROR: No player with the number " + playerNum + " was found.");
        }
        return playerIsOnTeam;
    }
    
    // Displays the name, number, and batting average for the player of the given player number.
    public void printPlayer(int playerNum) {
        // Check if the given player number is currently on the team.
        if ( ! playerIsOnTeam(playerNum)) {
            return; // Exit method.
        }
        System.out.println(players[playerNum]);
    }
    
    // Sets the name of the team to the given string.
    public void setName(String name) {
        this.name = name;
    }
    
    // Returns the name of the team as a string.
    public String getName() {
        return name;
    }
    
    // Sorts the players in the players array in ascending order based on player number.
    public void sort() {
        int[] playerNumbers = new int[players.length]; // Creates an array to hold the player numbers.
        for (int i=0; i < players.length; i++) {
            playerNumbers[i] = players[i].getNumber(); // Fill the array with each player's number.
        }
        Arrays.sort(playerNumbers); // Sort the array of player numbers.
        // Note: for the following to work properly, no two players can have the same player number.
        Player[] tmpPlayers = new Player[players.length]; // Creates a new array to sort the players into.
        for (int a=0; a < players.length; a++) {
            // For each sorted player number, find the player that matches it and add that player to the new array.
            for (Player p: players) {
                if (p.getNumber() == playerNumbers[a]) {
                    // If player has the matching number then assign them to the new array, else keep looking.
                    tmpPlayers[a] = p;
                    break;
                }
            }// End inner for.
        }// End outer for.
        players = tmpPlayers; // Replace the old unsorted array with the new sorted array.
    }
    
    // Search for player with the given player number.
    // Return that player if found. Return null is not found.
    public Player search(int num) {
        for (Player p: players) {
            if (p.getNumber() == num) {
                return p; // Return the found player.
            }
        }
        return null; // No player with the given number was found.
    }
    
    // Displays a formatted list of all the players on the team including their name, player number, and batting-average.
    public void printRoster() {
        System.out.println("\n" + name + " Roster");
        System.out.printf("%s\t%7s\t%6s\n", "Name", "Number", "BA");
        System.out.println("---------------------------------------------------------------");
        for (Player p: players) {
            System.out.printf("%s\t%7s\t%6.3f\n", p.getName(), p.getNumber(), p.getBattingAverage());
        }
        System.out.println("Press enter to contintue:");
        new Scanner(System.in).nextLine(); // Wait for user input to proceed.
    }
    
    // Return the name of the team as a string.
    @Override
    public String toString() {
        return getName();
    }
    
}