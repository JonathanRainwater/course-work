/*
 * Written by Jonathan Rainwater
 * Chapter 2
 * Assignment 2C
 *
 * This program takes input from the user about their job,
 *  calculates thier income along with tax and other expenses,
 *  and prints the results.
 */

package lab2;
import java.util.Scanner;

public class Lab2c {
    public static void main(String[] args) {
        
        final double TAX_RATIO =  0.14 ;
        final double CLOTHING_COST_RATIO = 0.10 ;
        final double SCHOOL_SUPPLIES_RATIO = 0.01 ;
        final double BONDS_COST_RATIO = 0.25 ;
        final double PARENTS_SPEND_RATIO = 0.50 ;
        
        Scanner input = new Scanner(System.in);
        
        // Promt user to enter hours worked each week and hourly pay-rate.
        System.out.print("Enter the total number of hours you worked over the four-week period: ");
       double hoursWorked = input.nextDouble();
       System.out.print("Enter your hourly pay rate during that time: ");
       double payRate = input.nextDouble();
       
       // Calculate the total income.
       double grossIncome = hoursWorked * payRate;
       System.out.println("Your gross income before taxes is $" + grossIncome);
       
       // Take out taxes.
       double netIncome = grossIncome - (grossIncome * TAX_RATIO);
       System.out.println("Your net income after taxes is $" + netIncome);
       
       // Take out clothing, accessories, and supplies.
       double clothingCost = netIncome * CLOTHING_COST_RATIO;
       System.out.println("You spent $" + clothingCost + " on clothing and accessories");
       double suppliesCost = netIncome * SCHOOL_SUPPLIES_RATIO;
       System.out.println("Yout spent $" + suppliesCost + " on school supplies");
       double leftAfterShopping = netIncome - clothingCost - suppliesCost;
       
       // Take out savings bonds.
       double bondsCost = leftAfterShopping * BONDS_COST_RATIO;
       System.out.println("Yout spent $" + bondsCost + " on savings bonds");
       
       // Figure parents contribution.
       double parentsAdd = bondsCost * PARENTS_SPEND_RATIO;
       System.out.println("Your parents spent $" + parentsAdd + " on additional savings bonds for you");
    }
}
