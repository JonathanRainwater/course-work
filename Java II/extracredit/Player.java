/*
 * File: Player.java
 * Author: Jonathan Rainwater
 * Date: 2018-03-03
 * Lab assignment extra-credit for Java II
 * 
 * This class creates a player object that has a name, player number, and batting-average.
 */
package extracredit;
import java.text.DecimalFormat;

public class Player {
    private String name; // The player's last name.
    private int number; // The number on the player's jersey.
    private double battingAverage; // The player's batting average.
    private static final String DEFAULT_NAME = ""; // Default player name.
    private static final int DEFAULT_NUMBER = 1; // Default player number.
    private static final int MIN_NUMBER = 1; // Minimun valid player number.
    private static final int MAX_NUMBER = 99; // Maximum valid player number.
    private static final double DEFAULT_BATTING_AVERAGE = 0.0; // Default batting average.
    private static final double MIN_BATTING_AVERAGE = 0.0; // Minimum valid batting average.
    private static final double MAX_BATTING_AVERAGE = 1.0; // Maximum valid batting average.
    // Format for batting average.
    private static final DecimalFormat BATTING_AVERAGE_FORMAT = new DecimalFormat("0.###");
    
    // Create a generic player with default values.
    public Player(){
        name = DEFAULT_NAME;
        number = DEFAULT_NUMBER;
        battingAverage = DEFAULT_BATTING_AVERAGE;
    }
    
    // Create a player with given string as name.
    // Player number and batting average will be default.
    public Player(String name) {
        this();
        this.name = name;
    }
    
    // Create a player with given string as name and given int as player number.
    // Batting average will be default.
    public Player(String name, int number) {
        this(name);
        if (number >= MIN_NUMBER && number <= MAX_NUMBER) {
            this.number = number;
        }
        else {
            System.out.println(number + " is an invalid player number.");
            setNumber(); // Force user to enter a valid player number.
        }
    }
    
    // Create a player with given string as name, given int as player number, and given double as batting average.
    public Player(String name, int number, double battingAverage) {
        this(name, number);
        if (battingAverage >= MIN_BATTING_AVERAGE && battingAverage <= MAX_BATTING_AVERAGE) {
            this.battingAverage = battingAverage;
        }
        else {
            System.out.println(battingAverage + " is an invalid batting average.");
            setBattingAverage(); // Force user to enter valid batting average.
        }
    }
    
    // Change the player's name.
    public void setName() {
        name = ValidateInput.getStringInput();
    }
    
    // Change the player's number to a valid integer.
    public final void setNumber() {
        this.number = ValidateInput.getIntegerInput(MIN_NUMBER, MAX_NUMBER);
    }
    
    // Change the player's batting-average to a valid double.
    public final void setBattingAverage() {
        this.battingAverage =
                toBattingAverageFormat(ValidateInput.getDoubleInput(MIN_BATTING_AVERAGE, MAX_BATTING_AVERAGE));
    }
    
    // Takes a double and returns it as a double in a valid batting-average format.
    public double toBattingAverageFormat(double d) {
        return Double.parseDouble(BATTING_AVERAGE_FORMAT.format(d));
    }
    
    // Returns the player's name as a string.
    public String getName() {
        return name;
    }
    
    // Returns the player's number as an integer.
    public int getNumber() {
        return this.number;
    }
    
    // Returns the player's batting-average as a double.
    public double getBattingAverage() {
        return this.battingAverage;
    }
    
    // Returns the player's name, number, and batting-average as a string.
    @Override
    public String toString() {
        return this.name + " (number " + this.number + ") has a batting average of " + this.battingAverage;
    }
}