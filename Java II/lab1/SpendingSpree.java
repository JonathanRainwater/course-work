/*
 * File: SpendingSpree.java
 * Author: Jonathan Rainwater
 * Date: 2018-01-25
 * Lab assignment 1a for Java II
 * 
 * This program allows the user to buy no more than three items from a store with a spending limit of $200.
 * After the user has purchased three items or spent all their money, whichever comes first, the program ends.
 * 
 * 1. Display a greeting to the user.
 * 2. Declare boolean stillShopping to control the main loop.
 * 3. Start the main do/while loop.
 * 4. Display the shopping list to the user.
 * 5. Prompt the user to choose an item to purchase.
 * 6. Use a do/while loop to validate that the user has the money to purchase that item.
 *      If invalid, allow loop to repeat until valid entry is given.
 *      If valid, subtract the purchace from moneyLeft, increment itemsBought, and exit the loop.
 * 7. Check if the item purchase limit has been reached or if the money has run out.
 *      If not, allow the main loop to repeat.
 *      If so, set boolean stillShopping to end the main loop.
 * 8 Display a parting message to the user.
 * 9. Program ends.
 */

package lab1;
import java.util.Scanner;
import java.text.DecimalFormat;


public class SpendingSpree {
    private static final int TOTAL_ITEMS = 10; // The total number of items in the store to choose from.
    // Names of the items in the store.
    private static final String[] ITEM_NAMES = {"Database Textbook (new)", "Java Textbook (new)", "2TB Hard Drive", "21' LED Computer Monitor"
            , "Networking Textbook (new)", "Cargo Pants", "Ice Scraper", "Whoopee Cushion (self-inflating)"
            , "Can of Jumping Snakes", "Used Piece of Chewing Gum"};
    // Prices of items in the store.
    private static final double[] ITEM_PRICES = {250.00, 175.00, 122.46, 103.71, 50.00, 25.00, 12.99, 6.49, 6.39, 0.01};
    private static final int MONEY_START_AMMOUNT = 200; // The ammount of money the user starts with.
    private static double moneyLeft = MONEY_START_AMMOUNT; // Amount of spendable money left.
    private static final int PURCHASE_LIMIT = 3; // The item purchase limit.
    private static int purchaseCount = 0; // Tracks the number of items purchased.
    private static int[] purchaseList = new int[PURCHASE_LIMIT]; // The index list of items that were purchased.
    private static final Scanner INPUT = new Scanner(System.in); // For accepting user input.
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("$0.00"); // To format item prices into proper dollar-format.
        
    public static void main(String[] args) {
        
        initialGreeting(); // Greet the user.
        boolean stillShopping = true; // Used for controlling main loop.
        do {
            displayShoppingList(); // Displays things that can be bought.
            processPurchase(); // Processes one purchase.
            // Determine if money is too low to continue or if item limit has been reached.
            stillShopping = checkIfStillShopping();
        } while (stillShopping);
        partingMessage(); // Displays final message to the user.
    }
    
    /* initialGreeting: Displays a greeting to the user.
     */
    public static void initialGreeting() {
        System.out.println("Welcome to Jon's Shop of Mostly Useful Stuff!\n"
                + "Our selection is limited but diverse!\n"
                + "You have won a $" + MONEY_START_AMMOUNT + " gift certificate to buy whatever you like, but you may only buy three items at most.");
    }
    
    /*displayShoppingList: Displays the list of purchaseable items to the user.
     */
    public static void displayShoppingList(){
        System.out.print("Press enter to display the shopping list:");
        INPUT.nextLine(); // To wait for user to press enter.
        // Display a list of purchaseable items.
        // Each line shows the item/array number, price, and name of each item.
        for (int i = 0; i < TOTAL_ITEMS; i++) {
            System.out.printf("%-11s$%-11.2f%-1s\n", "Item #" + i, ITEM_PRICES[i], ITEM_NAMES[i] );
        }
    }
    
    /*processPurchase: Accepts user input to select the item that they want to purchase.
     *  Also validates that there is enough money to make the purchase.
     */
    public static void processPurchase() {
        System.out.print("Enter the item number of the item you would like to buy (Enter 0-9): ");
        boolean choiceIsInvalid = true; // Controls the following loop.
        do {
            // Get validated input from the user.
            int choice = getSingleDigitInput(0,9);
            // Check if user has enough money to buy the selected item. If not, repeat this loop.
            if (ITEM_PRICES[choice] > moneyLeft) {
                System.out.print("You don't have enough money to buy the " + ITEM_NAMES[choice]
                        + ". You only have " + MONEY_FORMAT.format(moneyLeft) + ".\n"
                        + "Choose an item that costs less (Enter 0-9): ");
            }
            else { // User has enough money to make the purchase.
                choiceIsInvalid = false; // To end this loop.
                System.out.println("\nYou purchased the " + ITEM_NAMES[choice] + " for " + MONEY_FORMAT.format(ITEM_PRICES[choice]) + ".");
                moneyLeft -= ITEM_PRICES[choice]; 
                purchaseList[purchaseCount] = choice; // Tracks the items that was purchased.
                purchaseCount++;
            }
        } while (choiceIsInvalid);
    }
    
    /* getSingleDigitInput: Get input from the user, validate that the input is a single digit between
     * the two parameters, and return the result.
     */
    public static int getSingleDigitInput(int low, int high) {
        int answerInt = -1; // -1 continues the following loop.

        do {
            // Get input.
            String answerStr = INPUT.nextLine();

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
        return(answerInt); // Return the validated user input.
    }
    
    /* checkIfStillShopping: Checks if the user's money has run out or if the item purchase limit has been reached.
     * If so, returns a boolean that ends the main loop.
     * If not, returns a boolean that continues the main loop.
     */
    public static boolean checkIfStillShopping() {
        if (purchaseCount >= PURCHASE_LIMIT) {
            System.out.println("You have reached your purchase limit!");
            return false; // To quit shopping.
        }
        if (moneyLeft <= 0) {
            System.out.println("You have spent all your money!");
            return false; // To quit shopping.
        }
        System.out.println("You have " + MONEY_FORMAT.format(moneyLeft) + " left to spend, and you can make " 
                + (PURCHASE_LIMIT - purchaseCount) + " more purchases.");
        return true; // To keep shopping.
    }
    
    /* partingMessage: Displays a parting message to the user including the list of items purchased.
    */
    public static void partingMessage() {
        System.out.println("\nAltogether, you purchased:");
        for(int i=0; i < purchaseCount; i++) {
            System.out.println(ITEM_NAMES[purchaseList[i]]);
        }
        System.out.println("Thank you for visiting Jon's Shop of Mostly Useful Stuff!");
    }
}
