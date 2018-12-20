/*
 * File: TeamLeader.java
 * Author: Jonathan Rainwater
 * Date: 2018-03-14
 * Lab assignment 3.A for Java II
 * 
 * This class creates a Team Leader object and defines methods for accessing and modifying its variables.
 */
package lab3;

public class TeamLeader extends ProductionWorker{
    private double monthlyBonus; // This team leader's monthly bonus.
    private int trainingHoursRequired; // Number of hours of training required per year.
    private int trainingHoursAttended; // Number of hours of training attended.
    
    // Constructs a team-leader object with default values.
    public TeamLeader() {
        monthlyBonus = 100;
        trainingHoursRequired = 12;
        trainingHoursAttended = 0;
    }
    
    // Constructs a team-leader object with the given values.
    // If user input does not match the valid format, the user is forced to enter a valid data.
    public TeamLeader(String eName, String eNumber, String hDate, int shift, double hPayRate,
            double monthlyBonus, int trainingHoursRequired, int trainingHoursAttended) {
        super(eName, eNumber, hDate, shift, hPayRate);
        setMonthlyBonus(monthlyBonus);
        setTrainingHoursRequired(trainingHoursRequired);
        setTrainingHoursAttended(trainingHoursAttended);
    }
    
    // Set the monthly bonus to the given input if the input is in a valid format,
    // else force the user to enter a valid format.
    public final void setMonthlyBonus(double monthlyBonus) {
        if (monthlyBonus >= 0) {
            // The given monthly bonus is a positive number.
            this.monthlyBonus = monthlyBonus;
        }
        else {
            // The given monthly bonus is a negative number. Force the user to give a positive number.
            this.monthlyBonus = ValidateInput.getDoubleInputFrom(0);
        }
    }
    
    // Set the number of training hours required to the given input if the input is in a valid format,
    // else force the user to enter a valid format.
    public final void setTrainingHoursRequired(int trainingHoursRequired) {
        if (trainingHoursRequired >= 0) {
            // The given training hours required is a positive number.
            this.trainingHoursRequired = trainingHoursRequired;
        }
        else {
            // The given training hours required is a negative number. Force the user to enter a positive number.
            this.trainingHoursRequired = ValidateInput.getIntegerInputFrom(0);
        }
    }
    
    // Set the number of training hours attended to the given input if the input is in a valid format,
    // else force the user to enter a valid format.
    public final void setTrainingHoursAttended(int trainingHoursAttended) {
        if (trainingHoursAttended >= 0) {
            // The given training hours attended is a positive number.
            this.trainingHoursAttended = trainingHoursAttended;
        }
        else {
            // The given training hours attended is a negative number. Force the user to enter a positive number.
            this.trainingHoursAttended = ValidateInput.getIntegerInputFrom(0);
        }
    }
    
    // Returns the monthly bonus as a double.
   public double getMonthlyBonusDouble() {
       return monthlyBonus;
   }
   
   // Returns the montly bonus as a formatted String.
   public String getMonthlyBonusString() {
       return String.format("$%.2f", monthlyBonus);
   }
   
   // Returns the training hours required as an integer.
   public int getTrainingHoursRequired() {
       return trainingHoursRequired;
   }
   
   // Returns the training hours attended as an integer.
   public int getTrainingHoursAttended() {
       return trainingHoursAttended;
   }
    
   // Returns detailed information about this Team Leader as a String.
   @Override
   public String toString() {
       return super.toString() + "\n"
               + getEmployeeName() + " has a monthly bonus of " + getMonthlyBonusString() + ", "
               + trainingHoursRequired + " hours of required training, and "
               + trainingHoursAttended + " hours of attended training.";
   }
   
}
