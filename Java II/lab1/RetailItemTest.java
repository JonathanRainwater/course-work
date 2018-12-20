/*
 * File: RetailItemTest.java
 * Author: Jonathan Rainwater
 * Date: 2018-02-05
 * Lab assignment 1c for Java II
 * 
 * The purpose of this program is to allow a user to view current inventory or create new items to add to
 * the inventory.
 * 
 * 1. Display a greeting message to the user.
 * 2. Create a small initial inventory based on predefined values.
 * 3. Start a main loop that will repeat until the user chooses to exit the program.
 * 3a. Check if the inventory is full. If so, omit options to create new inventory. If not, allow user to create new inventory.
 * 3b. Prompt user to choose if they want to (1)create a new item manually, (2)create a new item with randomly selected
 *      values, (3)view the current inventory, or (4) end the program.
 * 3b1. If user chooses to create an item manually, user input will be taken and used to create a new item with
 *          the supplied values.
 * 3b2. If user chooses to create a new item with randomly selected values, random values are chosen and the
 *          user is asked if they want to change those values. Then create an item based on values that were allowed or
 *          created by the user.
 * 3b3. If user chooses to view inventory, display the current inventory to the user.
 * 3b4. If user chooses to exit the program, return a value that signals for the main loop to end.
 * 4. When main loop is signaled to end, display a parting message to the user.
 * 5. Program ends. 
 */
package lab1;
import java.util.Scanner;

public class RetailItemTest {
    // Define the descriptions, prices, and inventory counts of the initial inventory.
    private static final String[] INITIAL_DESCRIPTION = {"Jacket", "Designer Jeans", "Shirt"};
    private static final double[] INITIAL_PRICE = {59.95, 34.95, 24.95};
    private static final int[] INITIAL_UNITS_ON_HAND = {12, 40, 20};
    // Define the maximum number of types of items that can be held in inventory.
    private static final int MAX_INVENTORY_TYPE_AMOUNT = 10;
    // Create variable to track the number of created item types.
    private static int itemObjectCount = 0;
    // Create an array that will track all created item objects based on their item type.
    private static RetailItem[] allItemObjects = new RetailItem[MAX_INVENTORY_TYPE_AMOUNT];
    private static final Scanner INPUT = new Scanner(System.in); // A reuseable scanner object.
    
    public static void main(String[] args) {
        
        greeting(); // Display a greeting to the user.
        initializeInventory(); // Create a small initial inventory and show it to the user.
        boolean continueLoop = true; // Controls the main loop.
        do {
            // Ask the user if they want to add itmes to the inventory, view the inventory, or exit the program.
            continueLoop = askUserToEnterData();
            
        }while(continueLoop);
        parting(); // Display a parting message to the user.
    }
    
    // greeting: Desplays a greeting to the user.
    public static void greeting() {
        System.out.print("This program is an inventory tracking system. \n"
                + "You can use this to record new inventory and view existing inventory. \n"
                + "Press enter to view existing inventory:");
        INPUT.nextLine(); // Wait for user input to proceed.
    }
    
    // initializeInventory: Creates a small initial inventory and shows it to the user.
    public static void initializeInventory() {
        // Reads the predefined values in arrays INITIAL_DESCRIPTION, INITIAL_PRICE, and INITIAL_UNITS_ON_HAND,
        // adds them to objects that are created, and adds those objects to the allItemObjects array.
        for (int i=0; i < INITIAL_DESCRIPTION.length; i++) {
            allItemObjects[i] = new RetailItem(INITIAL_DESCRIPTION[i], INITIAL_PRICE[i], INITIAL_UNITS_ON_HAND[i]);
            itemObjectCount++;
        }
        viewInventory(); // Displays the current inventory to the user.
    }
    
    // viewInventory: Displays the current inventory to the user.
    public static void viewInventory() {
        System.out.println();
        for (int i=0; i < itemObjectCount; i++) {
            System.out.println(allItemObjects[i]);
        }
        System.out.println("Press enter to continue: ");
        INPUT.nextLine(); // Wait for user input to proceed.
    }
    
    // askUserToEnterData: Display a list of options to the user, prompt user for input, and take action based on
    // that input. Options are adding a new item to inventory, viewing the current inventory, or ending the program.
    // A boolean will be returned to inform the main loop if the program should continue or end.
    public static boolean askUserToEnterData() {
        // First, check to see if the inventory limit has been reached.
        // If so, prevent creation of new items.
        if (isInventoryFull()) {
            // Inventory is full.
            System.out.println("The inventory is full. No more items can be added.");
        }
        else {
            // Inventory is not full. Allow item creation options.
            System.out.println("Enter 1 if you want to enter new inventory information manually: \n"
                + "Enter 2 if you would like to have inventory information randomly generated for you: ");
        }
        // Viewing inventory or exiting the program will allways be options.
        System.out.print( "Enter 3 to view the current inventory:  \n"
                + "Enter 4 to exit this program: ");
        int choice = -1; // To record user input.
        if (isInventoryFull()) {
            // Inventory is full. Exclude item creation options.
            choice = ValidateInput.getIntegerInput(3,4);
        }
        else {
            // Inventory is not full. Allow all options.
            choice = ValidateInput.getIntegerInput(1,4);
        }
        if (choice == 3) {
            // User chose to view the current inventory.
            viewInventory(); // Displays the current inventory to the user.
            return true; // Informs the main loop that the program should continue.
        }
        else if (choice == 4) {
            // User chose to exit the program.
            return false; // Informs the main loop that the program should end.
        }
        else if (choice == 1) {
            // User chose to enter info manually.
            System.out.print("Enter a descriptive name for the item that you want to add (Spell with letters A-Z/a-z): ");
            String description = ValidateInput.getStringInput();
            System.out.print("Enter the price for the item (0.01 to 100): ");
            double price = ValidateInput.getDoubleInput(.01, 100);
            System.out.print("Enter how many of this item you want in inventory (1 to 100): ");
            int unitsOnHand = ValidateInput.getIntegerInput(1, 100);
            // Create and new item object with the recorded user input.
            allItemObjects[itemObjectCount] = new RetailItem(description, price, unitsOnHand);
        }
        else if (choice ==2) {
            // User wants inventory to be randomly chosen.
            // Create a new item with randomly selected values.
            allItemObjects[itemObjectCount] = new RetailItem();
            // Ask the user if they way to change any of the randomly selected values.
            askToChangeFields();
        }
        // Display the created inventory item to the user.
        System.out.printf("Your new inventory item is:      Item: %s      Price: $%.2f      Total Available: %s", allItemObjects[itemObjectCount].getDescription(),
                allItemObjects[itemObjectCount].getPrice(), allItemObjects[itemObjectCount].getUnitsOnHand() + "\n");
        itemObjectCount++; // Track how many object have been created.
        System.out.println("Press enter to continue: ");
        INPUT.nextLine(); // Wait for user input to proceed.
        return true; // Informs the main loop that the program should continue.
    }
    
    // askToChangeFields: Asks the user if they want to change any of the values that were randomly selected
    // and changes those values if desired.
    public static void askToChangeFields() {
        // Prompt user to choose if they want to modify the description.
        System.out.print("The item that was randomly selected was " + allItemObjects[itemObjectCount].getDescription()
                + ", do you to keep this item or change it? (Enter 1 to KEEP or 2 to CHANGE): ");
        int choice = ValidateInput.getIntegerInput(1, 2);
        if (choice == 2) {
            // User has chosem to change the description. Get user input and change the description.
            System.out.print("Enter a descriptive name for the item that you want to add (Spell with letters A-Z/a-z): ");
            allItemObjects[itemObjectCount].setDescription(ValidateInput.getStringInput());
        }
        // Prompt user to choose if they want to modify the price.
        System.out.print("The item price that was randomly selected was "
                + String.format("$%.2f", allItemObjects[itemObjectCount].getPrice())
                + " , do you want to keep this price or change it? (Enter 1 to KEEP or 2 to CHANGE): ");
        choice = ValidateInput.getIntegerInput(1,2);
        if (choice == 2) {
            // User has chosen to change the price. Get user input and change the price.
            System.out.print("Enter the price that you want this item to be (Enter 0.01 to 100): ");
            allItemObjects[itemObjectCount].setPrice(ValidateInput.getDoubleInput(0.01, 100));
        }
        // Prompt user to choose if they want to modify the inventory amount.
        System.out.print("The amount of this item that was randomly selected to be kept in inventory was "
                + allItemObjects[itemObjectCount].getUnitsOnHand() + " , do you want keep this amount or "
                + "change it? (Enter 1 to KEEP or 2 to CHANGE): ");
        choice = ValidateInput.getIntegerInput(1, 2);
        if (choice ==2) {
            // User has chosen to change the inventory amount. Get user input and change the inventory amount.
            System.out.print("Enter the amount that you want to keep in inventory (Enter 1 to 100): ");
            allItemObjects[itemObjectCount].setUnitsOnHand(ValidateInput.getIntegerInput(1, 100));
        }
    }
    
    // isInventoryFull: Checks if maximum inventory amount has been reached.
    // Returns true if is has, or false if it has not.
    public static boolean isInventoryFull() {
        if (itemObjectCount > 9) {
            return true; // Inventory is full.
        }
        return false; // Inventory is not full.
    }
    
    // parting: Displays a parting message to the user.
    public static void parting() {
        System.out.println("Goodbye.");
    }    
}
