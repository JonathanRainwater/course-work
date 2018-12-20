/*
 * File: Employee.java
 * Author: Jonathan Rainwater
 * Date: 2018-08-14
 * Lab assignment 3.A for Java II
 * 
 * This class creates a basic Employee object and defines methods for accessing and modifying its variables.
*/
package lab3;

import java.util.Scanner;

public class Employee {
    private String employeeName; // The employee's name.
    // The employee's number in the format of xxx-L , where x is a digit and L is a capital letter from A to M.
    private String employeeNumber; 
    private String hireDate; // The employee's hire date in the format of ##/##/####
    
    // Constructs an employee object with a randomly selcted name and employee number.
    public Employee() {
        String[] eNames = {"Adam", "Bob", "Colin", "Derrick", "Evan", "Frank", "Gary", "Hank", "Ivan", "Jon"};
        employeeName = eNames[ (int)(Math.random() *10)];
        employeeNumber = (byte)(Math.random() *10) + "" + (byte)(Math.random() *10)
                + "" + (byte)(Math.random() *10) + "-" + (char)('A' + (byte)(Math.random()*13));
        hireDate = "01/01/2018";
    }
    
    // Constructs an employee object with the given name, employee number, and hire date.
    // If employee number and hire date are not given in the valid format, the user is forced to enter the
    // correct format.
    public Employee(String eName, String eNumber, String hDate) {
        employeeName = eName;
        setEmployeeNumber(eNumber);
        setHireDate(hDate);
    }
    
    // Sets the employee name to the given String.
    public void setEmployeeName(String eName) {
        employeeName = eName;
    }
    
    // Prompts the user to enter the employee number in the proper format.
    // If user input is valid, set the employee number to the input,
    // if input format is invalid, force the user to enter a valid input.
    public void setEmployeeNumber() {
        Scanner scannerIn = new Scanner(System.in);
        boolean inputIsNotValid = true; // Controls the following loop.
        String userInput;
        do {
            System.out.println("Enter the employee's number in the format of xxx-L, where each x is a digit and L is a "
                    + "capital letter begween A and M inclusive.\n"
                    + "Example: 123-A");
            userInput = scannerIn.next(); // Get user input.
            if ( employeeNumberIsValid(userInput) ) {
                // The user's input is a valid format for employeeNumber.
                employeeNumber =userInput;
                inputIsNotValid = false; // To end the loop.
            }
            else {
                // The user's input is not valid. Repeat the loop to force the user to enter valid input.
                System.out.println("Incorrect employee-number format.");
            }
        } while(inputIsNotValid); // Loop ends when input is in a valid format.
        System.out.println();
    }
    
    // Set the employee name to the given input if the input is in a valid format,
    // else force the user to enter a valid format.
    public final void setEmployeeNumber(String eNumber) {
        if (employeeNumberIsValid(eNumber)) {
            // eNumber is a valid format.
            employeeNumber = eNumber;
        }
        else {
            // eNumber is not a valid format. Force user to enter a correct format.
            System.out.println("Incorrect employee-number format.");
            setEmployeeNumber();
        }
    }
    
    // Returns true if the given employee number is in the correct format, else returns false.
    private boolean employeeNumberIsValid(String eNumber) {
        if ( eNumber.matches("[\\d]{3}-[A-M]") ) {
            // eNumber is in the correct format.
            return true;
            }
        return false; // eNumber is not in the correct format.
    }
    
    // Prompts the user to enter the hire date in the proper format.
    // If user input is valid, set the hire date to the input,
    // if input format is invalid, force the user to enter a valid input.
    public void setHireDate() {
        Scanner scannerIn = new Scanner(System.in);
        boolean inputIsNotValid = true; // Controls the following loop.
        String userInput;
        do {
            System.out.print("Enter the employee's hire date in the format of ##/##/#### (month/date/year): ");
            userInput = scannerIn.next(); // Get user input.
            if ( hireDateIsValid(userInput) ) {
                // The user's input is a valid format for hireDate.
                hireDate = userInput;
                inputIsNotValid = false; // To end the loop.
            }
            else {
                // The user's input is not valid. Repeat the loop to force the user to enter valid input.
                System.out.println("Incorrect hire-date format.");
            }
        } while(inputIsNotValid); // Loop ends when input is in a valid format.
        System.out.println();
    }
    
    // Set the hire date to the given input if the input is in a valid format,
    // else force the user to enter a valid format.
    public final void setHireDate(String hDate) {
        if ( hireDateIsValid(hDate)) {
            // hDate is a valid format.
            hireDate = hDate;
        }
        else {
            // hDate is not a valid format. 
            System.out.println("Incorrect hire-date format.");
            setHireDate(); // Force user to enter a correct format.
        }
    }
    
    // Returns true if the given hire date is in the correct format, else returns false.
    private boolean hireDateIsValid(String hDate) {
        if ( hDate.matches("[\\d]{2}/[\\d]{2}/[\\d]{4}")) {
            // hDate is in the correct format.
            return true;
        }
        return false; // hDate is not in the correct format.
    }
    
    // Returns the employee name as a String.
    public String getEmployeeName() {
        return employeeName;
    }
    
    // Returns the employee number as a String.
    public String getEmployeeNumber() {
        return employeeNumber;
    }
    
    // Returns the hire date as a String.
    public String getHireDate() {
        return hireDate;
    }
    
    // Returns detailed information about this Employee as a String.
    @Override
    public String toString() {
        return employeeName + ", whose number is " + employeeNumber + ", was hired on " + hireDate;
    }
}