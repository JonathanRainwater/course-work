/*
 * File: EmployeeMain.java
 * Author: Jonathan Rainwater
 * Date: 2018-03-14
 * Lab assignment 3.A for Java II
 * 
 * This program stores and retrieves information about employees of various types. A sample employee for
 * each employee type is automatically created when the program is first run. The user may create additional
 * employees of any valid type through an interactive menu. All employees that have been created may also be
 * displayed.
 * 
 * 1. Automatically create one employee of each type using default contructors for each class.
 * 2. Begin a main loop that will repeat until the user chooses to quit the program.
 *  2a. Display a menu of choices to the user.
 *      2aa. If user chooses to display employee info, then display detailed information about each employee that
 *          has been created.
 *      2ab. If user chooses to create an employee, then create an employee of the type determined by the users
 *          choice, then display detailed information about that newly created employee.
 *      2ac. If user chooses to quit the program, then return a value to the main loop that causes it to end.
 * 3. End program.
 */

package lab3;
import java.util.ArrayList;
import java.util.Scanner;

public final class EmployeeMain {
    // An ArrayList that contains all created employees of all types.
    private static ArrayList<Employee> allEmployees = new ArrayList<Employee>();
    public static void main(String[] args) {
        greeting(); // Displays a greeting to the user.
        createSampleEmployees(); // Creates a generic employee of each type.
        boolean continueLoop = true; // Controls the following loop.
        do {
            displayMenu(); // Displays a menu of choices to the user.
            /* Get the user's choice, perform actions based on that choice,
             and return whether or not to continue this loop. */
            continueLoop = processUserChoice();
        } while (continueLoop);
        System.out.println("Quiting program.");
    }
    
    // Displays a greeting to the user.
    private static void greeting() {
        System.out.println("This program stores and retrieves information about employees of various types.");
    }
    
    // Creates a generic employee of each type.
    private static void createSampleEmployees() {
            allEmployees.add(new Employee());
            allEmployees.add(new ProductionWorker());
            allEmployees.add(new TeamLeader());
            allEmployees.add(new ShiftSupervisor());
    }
    
    // Displays a menu of choices to the user.
    private static void displayMenu() {
        System.out.println("\n(1) Display all current employees of all types\n"
                + "(2) Create a new basic Employee\n"
                + "(3) Create a new Production Worker\n"
                + "(4) Create a new Team Leader\n"
                + "(5) Create a new Shift Supervisor\n"
                + "(6) Quit this program");
    }
    
    /* Get the user's choice, perform actions based on that choice,
      and return whether or not to continue the main loop. */
    private static boolean processUserChoice() {
        // Prompt the user to enter a value between 1 and 6 inclusive.
        int choice = ValidateInput.getIntegerInput(1, 6);   
        System.out.println();
        if (choice == 1) {
            // User chose to display all cuurent employees of all types.
            displayAllEmployees(); // Display detailed information about each employee that has been created.
            return true; // To repeat main loop.
        }
        if (choice == 2) {
            // User chose to create a new basic-Employee.
            createEmployee(2);
            return true; // To repeat the main loop.
        }
        if (choice == 3) {
            // User chose to create a new Production Worker.
            createEmployee(3); // Creates a Production Worker.
            return true; // To repeat the main loop.
        }
        if (choice == 4) {
            // User chose to create a new Team Leader.
            createEmployee(4); // Creates a Team Leader.
            return true; // To repeat the main loop.
        }
        if (choice == 5) {
            // User chose to create a new Shift Supervisor.
            createEmployee(5); // Creates a Shift Supervisor.
            return true; // To repeat the main loop.
        }
        // The user choice was 6. The user has chosen the quit the program.
        return false; // To end the main loop.
    }
    
    // Displays detailed information about each employee that has been created.
    private static void displayAllEmployees() {
        if(allEmployees.isEmpty()) {
            System.out.println("There are no employees to display.");
            ValidateInput.pause(); // Wait for user input to proceed.
        }
        else {
            for (int i = 0; i < allEmployees.size(); i++) {
                System.out.println(allEmployees.get(i));
                displayEmployeeActualType(i); // Displays the actual type of the employee.
                System.out.println("-----------------------------------------------");
            }
            ValidateInput.pause(); // Wait for user input to proceed.
        }
    }
    
    // Creates an employee of a certain type as determined by the given integer.
    private static void createEmployee(int choice) {
        System.out.println("Enter the employee's name.");
        String employeeName = ValidateInput.getStringOfLettersAndSpaces(); // Get name from user.
        System.out.println("\nEnter the employee's number in the format of xxx-L, where each x is a digit and L is a "
                + "capital letter begween A and M inclusive.\n"
                + "Example: 123-A");
        String employeeNumber = new Scanner(System.in).next(); // Get employee number from the user.
        System.out.print("\nEnter the employee's hire date in the format of ##/##/#### (month/date/year): ");
        String hireDate = new Scanner(System.in).next(); // Get employee hire date.
        System.out.println();
        if (choice == 2) {
            // User chose to create a new basic employee.
            allEmployees.add(new Employee(employeeName, employeeNumber, hireDate));
            displayLastCreatedEmployee();
            return; // Main loop will be repeated.
        }
        if (choice ==3 || choice == 4) {
            // User either chose to create a new production worker or a new team leader.
             System.out.print("Enter the production worker's shift number (1=Day Shift, 2=Night Shift): ");
            int shift = ValidateInput.getIntegerInput(1,2); // Get user input.
            System.out.print("\nEnter the production worker's hourly pay rate: ");
            // Get an hourly pay rate from the user. Must be at least 0.
            double hourlyPayRate = ValidateInput.getDoubleInputFrom(0);
            System.out.println();
            if (choice == 3) {
                // User chose to create a new production worker.
                allEmployees.add(new ProductionWorker(
                        employeeName, employeeNumber, hireDate, shift, hourlyPayRate));
                displayLastCreatedEmployee();
                return; // Main loop will be repeated.
            }
            System.out.print("Enter the team leader's monthly bonus: ");
            // Get a monthly bonus from the user. Must be at least 0.
            double monthlyBonus = ValidateInput.getDoubleInputFrom(0);
            System.out.print("\nEnter the team leader's number of required training hours: ");
            // Get the required training hours from the user. Must be at least 0.
            int trainingHoursRequired = ValidateInput.getIntegerInputFrom(0);
            System.out.print("\nEnter the team leader's number of attended training hours: ");
            // Get the attended training hours from the user. Must be at least 0.
            int trainingHoursAttended = ValidateInput.getIntegerInputFrom(0);
            System.out.println();
            if (choice == 4) {
                // User chose to create a new team leader.
                allEmployees.add(new TeamLeader(employeeName, employeeNumber, hireDate, shift, hourlyPayRate,
                        monthlyBonus, trainingHoursRequired, trainingHoursAttended));
                displayLastCreatedEmployee();
                return; // Main loop will be repeated.
            }
        } // End if statement for processing choices 3 and 4.
        // The user choice was 5. User chose to create a new shift supervisor.
        System.out.print("Enter the shift supervisor's annual sallary: ");
        // Get an annual sallary from the user. Must be at least 0.
        double annualSallary = ValidateInput.getDoubleInputFrom(0);
        System.out.print("\nEnter the shift supervisor's annual production bonus: ");
        // Get an annual production bonus from the user. Must be at least 0.
        double annualProductionBonus = ValidateInput.getDoubleInputFrom(0);
        System.out.println();
        allEmployees.add(new ShiftSupervisor(employeeName, employeeNumber, hireDate,
                annualSallary, annualProductionBonus));
        displayLastCreatedEmployee();
        // Main loop will be repeated.
    }
    
    // Displays detailed information about the last employee that was created.
    private static void displayLastCreatedEmployee() {
        System.out.println(allEmployees.get(allEmployees.size() - 1));
        displayEmployeeActualType(allEmployees.size() - 1); // Display the actual type of the employee.
        ValidateInput.pause(); // Wait for user input to proceed.
    }
    
    // Displays the actual type of the employee.
    private static void displayEmployeeActualType(int listNumber) {
        if (allEmployees.get(listNumber) instanceof TeamLeader) {
            System.out.println(allEmployees.get(listNumber).getEmployeeName() + " is a Team Leader." );
        }
        else if (allEmployees.get(listNumber) instanceof ProductionWorker) {
            System.out.println(allEmployees.get(listNumber).getEmployeeName() + " is a Production Worker." );
        }
        else if (allEmployees.get(listNumber) instanceof ShiftSupervisor) {
            System.out.println(allEmployees.get(listNumber).getEmployeeName() + " is a Shift Supervisor." );
        }
        else if (allEmployees.get(listNumber) instanceof Employee) {
            System.out.println(allEmployees.get(listNumber).getEmployeeName() + " is an Employee." );
        }
    }
    
}
