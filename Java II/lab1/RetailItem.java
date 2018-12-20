/*
 * File: RetailItem.java
 * Author: Jonathan Rainwater
 * Data: 2018-02-05
 * Lab assignment 1c for Java II
 * 
 * This class creates item objects, where each object represents an item
 *  with values for description, price, and the number of units in inventory. 
 * Either an item can be created from randomly selected values with the default constructor,
 * or an itme can be created from  user input with the overloaded constructor.
 */

package lab1;

public class RetailItem {
    private String description; // A description of this item object.
    private double price; // The current retail price of this item.
    private int unitsOnHand; // How many of this item is currently in inventory.
    private static final double MAX_RANDOM_PRICE = 100.00; // The maximum price that can be randomly generated.
    private static final int MAX_RANDOM_UNITS = 100; // The maximum inventory count that can be randomly generated.
    
    /* Creates an item object with randomly selected description, price, and inventory count.
     */
    public RetailItem() {
        // Define a list of item descriptions.
        String[] descriptionList = {"Backpack", "Unicycle", "Pogo Stick", "Boomarang", "Nunchuks",
            "Pool Noodle", "Flashlight", "Fishing Rod", "Back-Scratcher", "Didgeridoo"};
        // Assign a random description to the new item..
        this.description = descriptionList[(int)(Math.random() * descriptionList.length)];
        // Assign a random price to the new item.
        this.price = (Math.random() * MAX_RANDOM_PRICE) + 0.01;
        // Assign a random inventory count to the new item.
        this.unitsOnHand = (int)(Math.random() * MAX_RANDOM_UNITS) + 1;
        
    }
    
    /* Creates an item object with user input.
     */
    public RetailItem(String description, double price, int units) {
        this.description = description;
        this.price = price;
        this.unitsOnHand = units;
    }
    
    /* getDescription: Get the description of the item and returns the result.
     */
    public String getDescription() {
        return this.description;
    }
    
    /* getUnitsOnHand: Get the number of units currently in inventory and return the result.
     */
    public int getUnitsOnHand() {
        return this.unitsOnHand;
    }
    
    /* getPrice: Gets the current retail price of the item and returns the result.
     */
    public double getPrice() {
        return this.price;
    }
    
    /* setDescription: Takes a String input and assigns that input to the item's current description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /* setUnitsOnHand: Takes an integer input and assigns that input to the item's current inventory count.
     */
    public void setUnitsOnHand(int unitCount) {
        this.unitsOnHand = unitCount;
    }
    
    /* setPrice: Takes a couble input and assigns that input to the item's current price.
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return String.format("%-20s \tPrice: $%-6.2f\t%-1s", "Item: " + this.description,  this.price, "Total Available: " + this.unitsOnHand);
    }
}
