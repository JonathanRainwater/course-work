/*
 * Written by Jonathan Rainwater
 * Lab assignment 3a
 * 
 * This program takes in user input about their bank account information,
 * processes that information to apply the proper service charge and interest,
 * and then display the result.
 * 
 * 1. Define all constants and scanner object.
 * 2. Account number is defined by user input.
 * 3. Account type is defined by validated user input.
 * 4. Minimum account balance is defined by user input.
 * 5  Current account balance is defined by user input.
 * 6. Using if statements, accounts below the minumum balance are
 *      charged the proper service charge based on account type, while accounts
 *      above the minimum balance have the proper ammount of interest added based
 *      on their account type.
 * 7. The results of these operations are then displayed to the user.
 */
package lab3;
import java.util.Scanner;

public class Lab3a {
    public static void main(String[] args) {
        final int SERVICE_CHARGE_SAVINGS = 10;
        final int SERVICE_CHARGE_CHECKING = 25;
        final double INTEREST_SAVINGS = 0.04;
        final double INTEREST_CHECKING_LOW = 0.03;
        final double INTEREST_CHECKING_HIGH = 0.05;
        Scanner input = new Scanner(System.in);
        
        // Prompt user for account number.
        int accountNumber = 0;
        System.out.print("Enter your account number: ");
        accountNumber = input.nextInt();
        
        // Prompt user for account type.
        char accountType = '0';
        while (true) {
            System.out.print("Enter your accout type\n"
            + "Enter C for Checking or S for Savings: ");
            accountType = input.next().charAt(0);
            accountType = Character.toUpperCase(accountType);
            if ((accountType == 'C') || (accountType == 'S')) {
                break;
            }
            else {
                System.out.println("Invalid input");
            }
        } // End loop.
        
        // Prompt user for minimum account balance.
        double minimumBalance = 0;
        System.out.print("Enter the minimum balance required for your account: ");
        minimumBalance = input.nextDouble();
        
        // Prompt user for current account balance.
        double currentBalance = 0;
        System.out.print("Enter your current account balance: ");
        currentBalance = input.nextDouble();
        
        // Apply service charges or interest.
        double newBalance = 0;
        if (currentBalance < minimumBalance) {  // Apply service charge.
            if (accountType == 'C')
                newBalance = currentBalance - SERVICE_CHARGE_CHECKING;
            else
                newBalance = currentBalance - SERVICE_CHARGE_SAVINGS;
        }
        else {  // Add interest.
            if (accountType == 'C') {
                if (currentBalance <= (minimumBalance + 5000))
                    newBalance = currentBalance + (currentBalance * INTEREST_CHECKING_LOW);
                else
                    newBalance = currentBalance + (currentBalance * INTEREST_CHECKING_HIGH);
            }
            else
                newBalance = currentBalance + (currentBalance * INTEREST_SAVINGS);
        }
        
        // Display results.
        String accountLabel = (accountType =='C') ? "Checking" : "Savings";
        System.out.println("---------- Results ----------"
                + "\nYour account number is " + accountNumber
                + "\nYour account type is " + accountLabel);
        System.out.printf("Your current balance is $%.2f"
                + "\nYour new balance will be $%.2f\n", currentBalance, newBalance);
    }
}
