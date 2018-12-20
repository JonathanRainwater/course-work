/*
 * File: ProductionWorker.java
 * Author: Jonathan Rainwater
 * Data: 2018-03-14
 * Lab assignment 3.A for Java II
 * 
 * This class creates a Production Worker object and defines methods for accessing and modifying its variables.
 */
package lab3;

public class ProductionWorker extends Employee{
    // The shift that this production worker is assigned to.
    //  Valid value are 1 or 2, where 1 is the day-shift and 2 is the night-shift.
    private int shift; 
    private double hourlyPayRate; // This production worker's hourly pay rate.
    
    // Consstructs a production worker object with random shift and hourly pay-rate values.
    public ProductionWorker() {
        shift = (int)(Math.random()* 2) +1; // Randomly assigns 1 or 2 to shift.
        hourlyPayRate = (Math.random()*5) + 10; // Randomly assigns an hourly pay rate of 10 to 15.
    }
    
    // Constructs a production woker object with the given shift number and hourly pay-rate.
    // If user input does not match the valid format, the user is forced to enter a valid data.
    public ProductionWorker(String eName, String eNumber, String hDate, int shift, double hPayRate) {
        super(eName, eNumber, hDate);
        setShift(shift);
        hourlyPayRate = hPayRate;
    }
    
    // Prompts the user to enter the shift in the proper format.
    // If user input is valid, set the shift to the input,
    // if input format is invalid, force the user to enter a valid input.
    public void setShift() {
        System.out.print("Enter this production worker's shift number (1=Day Shift, 2=Night Shift): ");
        shift = ValidateInput.getIntegerInput(1,2); // Get user input.
        System.out.println();
    }
    
    // Set the shift to the given input if the input is in a valid format,
    // else force the user to enter a valid format.
    public final void setShift(int shift) {
        if (shift == 1 || shift == 2) {
            // shift is in the correct format.
            this.shift = shift;
        }
        else {
            // shift is not in the correct format.
            System.out.println("Incorrect shift format.");
            setShift(); // Force user to enter a valid format.
        }
    }
    
    // Set the hourly pay-rate to the given input if the input is in a valid format,
    // else force the user to enter a valid format.
    public void setHourlyPayRate(double hourlyPayRate) {
        if (hourlyPayRate >= 0) {
            // The given hourly pay rate is at positive number.
            this.hourlyPayRate = hourlyPayRate;
        }
        else {
            // The given hourly pay rate is a negative number. Force the user to give a positive number.
            this.hourlyPayRate = ValidateInput.getDoubleInputFrom(0);
        }
    }
    
    // Returns the shift as an integer.
    public int getShift() {
        return shift;
    }
    
    // Returns the hourly pay-rate as a double.
    public double getHourlyPayRateDouble() {
        return hourlyPayRate;
    }
    
    // Returns the hourly pay-rate as a formatted String.
    public String getHourlyPayRateString() {
        return String.format("$%.2f", hourlyPayRate);
    }
    
    // Returns detailed information about this Production Worker as a String.
    @Override
    public String toString() {
        return super.toString() + "\n"
                + getEmployeeName() + " works shift " + shift
                + " and has an hourly pay rate of " + getHourlyPayRateString();
    }
    
}
