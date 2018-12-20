/*
 * Fie: Address.java
 * Author: Jonathan Rainwater
 * Date: 2018-03-17
 * Lab assignment 3.B for Java II
 * 
 * This class creates a full address for a person that consists of street address, city, state, and zip code.
 */
package lab3;
import java.util.Scanner;

public class Address extends Person{
    private String streetAddress; // The person's street address.
    private String city; // The city where the person lives.
    private String state; // The two-letter code for the state where the person lives. Must be two letters.
    private String zipCode; // The 5-digit zip code of where the person lives. Must be 5 digits.
    
    public Address(String firstName, String lastName, String birthdate, String assocciation,
            String phoneNumber, String streetAddress, String city, String state, String zipCode) {
        super(firstName, lastName, birthdate, assocciation, phoneNumber);
        this.streetAddress = streetAddress;
        this.city = city;
        setState(state);
        setZipCode(zipCode);
    }
    
    // Sets the person's street address to the given String.
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    
    // Sets the person's city to the given String.
    public void setCity(String city) {
        this.city = city;
    }
    
    // Prompts the user to enter the person's two-letter state code in the proper format.
    // If user input is valid, set the state to the input,
    // if input format is invalid, force the user to enter a valid input.
    public void setState() {
        Scanner scannerIn = new Scanner(System.in);
        boolean inputIsNotValid = true; // Controls the following loop.
        String userInput; // Will hold the user's input.
        do {
            System.out.print("Enter the state where the person lives as its two-letter code (Example: TN): ");
            userInput = scannerIn.next(); // Get user input.
            if ( isStateValid(userInput) ) {
                // The user's input is in a valid format for state.
                state = userInput.toUpperCase();
                inputIsNotValid = false; // To end the loop.
            }
            else {
                // The user's input is not valid. Repeat the loop to force the user to enter valid input.
                System.out.println("Incorrect state-code format.");
            }
        } while(inputIsNotValid); // Loop ends when input is in a valid format.
        System.out.println();
    }
    
    // Set the state to the given input if the input is in a valid format,
    // else force the user to enter a valid format.
    public final void setState(String state) {
        if ( isStateValid(state)) {
            // state is in a valid format.
            this.state = state.toUpperCase();
        }
        else {
            // state is not in a valid format. 
            System.out.println("Incorrect state-code format.");
            setState(); // Force user to enter a correct format.
        }
    }
    
    // Returns true if the given state is in the correct two-letter format, else returns false.
    private boolean isStateValid(String state) {
        if ( state.matches("[a-zA-Z]{2}")) {
            // state is in the correct format.
            return true;
        }
        return false; // state is not in the correct format.
    }
    
    // Prompts the user to enter the person's zip code in the proper format.
    // If user input is valid, set the zip code to the input,
    // if input format is invalid, force the user to enter a valid input.
    public void setZipCode() {
        Scanner scannerIn = new Scanner(System.in);
        boolean inputIsNotValid = true; // Controls the following loop.
        String userInput; // Will hold the user's input.
        do {
            System.out.print("Enter the person's 5-digit zip code: ");
            userInput = scannerIn.next(); // Get user input.
            if ( isZipCodeValid(userInput) ) {
                // The user's input is in a valid format for zip code.
                zipCode = userInput;
                inputIsNotValid = false; // To end the loop.
            }
            else {
                // The user's input is not valid. Repeat the loop to force the user to enter valid input.
                System.out.println("Incorrect zip code format.");
            }
        } while(inputIsNotValid); // Loop ends when input is in a valid format.
        System.out.println();
    }
    
    // Set the zip code to the given input if the input is in the valid 5-digit format,
    // else force the user to enter the valid format.
    public final void setZipCode(String zipCode) {
        if ( isZipCodeValid(zipCode)) {
            // zipCode is in the valid format.
            this.zipCode = zipCode;
        }
        else {
            // zipCode is not in the valid format. 
            System.out.println("Incorrect zip code format.");
            setZipCode(); // Force user to enter a correct format.
        }
    }
    
    // Returns true if the given zip code is in the correct 5-digit format, else returns false.
    private boolean isZipCodeValid(String zipCode) {
        if ( zipCode.matches("[\\d]{5}")) {
            // zipCode is in the correct format.
            return true;
        }
        return false; // zipCode is not in the correct format.
    }
    
    // Returns the person's street address as a String.
    public String getStreetAddress() {
        return streetAddress;
    }
    
    // Returns the city the person lives in as a String.
    public String getCity() {
        return city;
    }
    
    // Returns the state the person lives in as a two-letter String.
    public String getState() {
        return state;
    }
    
    // Returns the zip code the person lives in as a 5-digit String.
    public String getZipCode() {
        return zipCode;
    }
    
    // Returns detailed information about this address as a String.
    @Override
    public String toString() {
        return super.toString() + ", " + streetAddress + ", " + city + ", " + state + ", " + zipCode;
    }
    
}
