/*
 * File: ShiftSupervisor.java
 * Author: Jonathan Rainwater
 * Date: 2018-03-14
 * Lab assignment 3.A for Java II
 * 
 * This class creates a Shift Supervisor object and defines methods for accessing and modifying its variables.
 */
package lab3;

public class ShiftSupervisor extends Employee{
    private double annualSallary; // The shift supervisor's annual sallary;
    private double annualProductionBonus; // The shift supervisor's annual production bonus.
    
    // Contructs a shift supervisor object with default values.
    public ShiftSupervisor() {
        annualSallary = 50000;
        annualProductionBonus = 1000;
    }
    
    // Constructs a shuft supervisor object with user-given values.
    // If user input does not match the valid format, the user is forced to enter a valid data.
    public ShiftSupervisor(String eName, String eNumber, String hDate,
            double annualSallary, double annualProductionBonus) {
        super(eName, eNumber, hDate);
        setAnnualSallary(annualSallary);
        setAnnualProductionBonus(annualProductionBonus);
}

    // Set the annual sallary to the given input if the input is in a valid format,
    // else force the user to enter a valid format.
    public final void setAnnualSallary(double annualSallary) {
        if (annualSallary >= 0) {
            // The given annual sallary is a positive number.
            this.annualSallary = annualSallary;
        }
        else {
            // The given annual sallary is a negative number. Force the user to give a positive number.
            this.annualSallary = ValidateInput.getDoubleInputFrom(0);
        }
    }
    
    // Set the annual production bonus to the given input if the input is in a valid format,
    // else force the user to enter a valid format.
    public final void setAnnualProductionBonus(double annualProductionBonus) {
        if (annualProductionBonus >= 0) {
            // The given annual production bonus is a positive number.
            this.annualProductionBonus = annualProductionBonus;
        }
        else {
            // The given annual production bonus is a negative number. Force the user to give a positive number.
            this.annualProductionBonus = ValidateInput.getDoubleInputFrom(0);
        }
    }
    
    // Return the annual sallary for this shift supervisor as a double.
    public double getAnnualSallaryDouble() {
        return annualSallary;
    }
    
    // Return the annual sallary for this shft supervisor as a formatter String.
    public String getAnnualSallaryString() {
        return String.format("$%.2f", annualSallary);
    }
    
    // Return the annual production bonus for this shift supervisor as a double.
    public double getAnnualProductionBonusDouble() {
        return annualProductionBonus;
    }
    
    // Return the annual production bonus as a formatted String.
    public String getAnnualProductionBonusString() {
        return String.format("$%.2f", annualProductionBonus);
    }
    
    // Returns detailed information about this Shift Supervisor as a String.
    @Override
    public String toString() {
        return super.toString() + "\n"
                + getEmployeeName() + " has an annual sallary of " + getAnnualSallaryString()
                + " and an annual production bonus of " + getAnnualProductionBonusString();
    }
    
}
