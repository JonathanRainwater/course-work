/*
 * File: Person.java
 * Author: Jonathan Rainwater
 * Date: 2018-03-17
 * Lab assignment 3.B for Java II
 * 
 * This class creates a person with a first name, last name, birthdate, association type, and phone number.
 */
package lab3;
import java.util.Scanner;

public class Person implements Comparable<Person>{
    private String firstName; // The person's first name.
    private String lastName; // The person's last name.
    private String birthdate; // The person's birthdate.
    // The person's association to the address-book holder.
    // Valid values are Friend, Family, or Business Associate.
    private String association;
    // The person's phone number. Valid format is xxx-xxx-xxxx .
    private String phoneNumber;
    
    // Constructs a person with the given first name, last name, birthdate, association type, and phone number.
    public Person(String firstName, String lastName, String birthdate, String association, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        setBirthdate(birthdate);
        setAssociation(association);
        setPhoneNumber(phoneNumber);
    }
    
    // Sets the person's first name to the given String.
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    // Sets the person's last name to the given String.
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    // Prompts the user to enter the person's birthdate in the proper format.
    // If user input is valid, set the birthdate to the input,
    // if input format is invalid, force the user to enter a valid input.
    public void setBirthdate() {
        Scanner scannerIn = new Scanner(System.in);
        boolean inputIsNotValid = true; // Controls the following loop.
        String userInput; // Will hold the user's input.
        do {
            System.out.println("Enter the person's birthdate in the format of ##/##/#### (month/day/year)\n"
                    + "(month must be 1-12, day must be 1-31)");
            userInput = scannerIn.next(); // Get user input.
            if ( isBirthdateValid(userInput) ) {
                // The user's input is in a valid format for birthdate.
                birthdate = userInput;
                inputIsNotValid = false; // To end the loop.
            }
            else {
                // The user's input is not in a valid format. Repeat the loop to force the user to enter a valid input.
                System.out.println("\n" + userInput
                        + " is an incorrect birthdate format for " + firstName + " " + lastName);
            }
        } while(inputIsNotValid); // Loop ends when input is in a valid format.
        System.out.println();
    }
    
    // Set the birthdate to the given input if the input is in a valid format,
    // else force the user to enter a valid format.
    public final void setBirthdate(String birthdate) {
        if ( isBirthdateValid(birthdate)) {
            // birthdate is in a valid format.
            this.birthdate = birthdate;
        }
        else {
            // birthdate is not in a valid format. 
            System.out.println("\n" + birthdate
                    + " is an incorrect birthdate format for " + firstName + " " + lastName);
            setBirthdate(); // Force th euser to enter a valid input.
        }
    }
    
    // Returns true if the given birthdate is in the correct format, else returns false.
    private boolean isBirthdateValid(String birthdate) {
        if ( birthdate.matches("[\\d]{2}/[\\d]{2}/[\\d]{4}")
                && Integer.parseInt(birthdate.substring(0, 2)) >= 1
                && Integer.parseInt(birthdate.substring(0, 2)) <= 12
                && Integer.parseInt(birthdate.substring(3, 5)) >= 1
                && Integer.parseInt(birthdate.substring(3, 5)) <= 31) {
            // birthdate is in the correct format.
            // Month is between 1-12 and day is between 1-31.
            return true;
        }
        return false; // birthdate is not in the correct format.
    }
    
    // Prompts the user with a menu to enter the person's association to the address-book holder.
    // The association is then set to that choice.
    public void setAssociation() {
        System.out.println("Choose the person's association to the address-book holder \n"
                + "(1) Friend\n(2) Family\n(3) Business Associate");
        int choice = ValidateInput.getIntegerInput(1, 3); // Prompt the user to enter a choice between 1 and 3.
        switch (choice) {
            case 1: association = "Friend"; break;
            case 2: association = "Family"; break;
            case 3: association = "Business Associate"; break;
        }
        System.out.println();
    }
    
    // Set the person's association (to the address-book holder) to the given input if the input is a valid value,
    // else force the user to choose a valid value.
    public final void setAssociation(String association) {
        if (association.matches("Friend") || association.matches("Family") || 
                association.matches("Business Associate") ) {
            // The given association is a valid option.
            this.association = association;
        }
        else {
            // The given association is not a valid option. Force the user to choose one that is valid.
            setAssociation();
        }
    }
    
    // Prompts the user to enter the person's phone number in the proper format.
    // If user input is valid, set the phone number to the input,
    // if input format is invalid, force the user to enter a valid input.
    public void setPhoneNumber() {
        Scanner scannerIn = new Scanner(System.in);
        boolean inputIsNotValid = true; // Controls the following loop.
        String userInput; // Will hold the user's input.
        do {
            System.out.print("Enter the person's pnone number in the format of ###-###-#### "
                    + "(Example: 111-222-3333): ");
            userInput = scannerIn.next(); // Get user input.
            if ( isPhoneNumberValid(userInput) ) {
                // The user's input is a valid format for phone number.
                phoneNumber = userInput;
                inputIsNotValid = false; // To end the loop.
            }
            else {
                // The user's input is not valid. Repeat the loop to force the user to enter valid input.
                System.out.println("Incorrect phone number format.");
            }
        } while(inputIsNotValid); // Loop ends when input is in a valid format.
        System.out.println();
    }
    
    // Set the phone number to the given input if the input is in a valid format,
    // else force the user to enter a valid format.
    public final void setPhoneNumber(String phoneNumber) {
        if ( isPhoneNumberValid(phoneNumber)) {
            // pnoneNumber is in a valid format.
            this.phoneNumber = phoneNumber;
        }
        else {
            // phoneNumber is not in a valid format. 
            System.out.println("Incorrect phone number format.");
            setPhoneNumber(); // Force user to enter a correct format.
        }
    }
    
    // Returns true if the given phoneNumber is in the correct format, else returns false.
    private boolean isPhoneNumberValid(String phoneNumber) {
        if ( phoneNumber.matches("[\\d]{3}-[\\d]{3}-[\\d]{4}")) {
            // phoneNumber is in the correct format.
            return true;
        }
        return false; // phoneNumber is not in the correct format.
    }
    
    // Returns the person's first name as a String.
    public String getFirstName() {
        return firstName;
    }
    
    // Returns the person's last name as a String.
    public String getLastName() {
        return lastName;
    }
    
    // Returns the person's birthdate as a String.
    public String getBirthdate() {
        return birthdate;
    }
    
    // Returns the person's association to the address-book holder as a String.
    public String getAssociation() {
        return association;
    }
    
    // Returns the person's phone number as a String.
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    // Returns detailed information about this person as a String.
    @Override 
    public String toString() {
        return firstName + " " + lastName + ", "+ birthdate + ", " + association + ", " + phoneNumber;
    }
    
    // Allows the person to be sorted by thier last name.
    @Override
    public int compareTo(Person p) {
        return lastName.compareToIgnoreCase(p.getLastName());
    }
    
}
