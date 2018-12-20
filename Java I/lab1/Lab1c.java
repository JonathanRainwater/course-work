/*
* Written by Jonathan Rainwater
* Chapters 1,2
* Assignment 1c:
*/
package lab1;

public class Lab1c {
    public static void main(String[] args) {
        int bubbaLab = 111 ;
        int bubbaBonus = 222 ;
        int bubbaTotal = bubbaLab + bubbaBonus ;
        
        int randyLab = 222 ;
        int randyBonus = 333 ;
        int randyTotal = randyLab + randyBonus ;
        
        int dudeLab = 444 ;
        int dudeBonus = 555 ;
        int dudeTotal = dudeLab + dudeBonus ;
        
        
        System.out.println("///////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n"
                + "     == Student Points ==\n"
                + "\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\///////////////\n\n"
                + "Name\t Lab\t Bonus\t Total\t\n\n"
                + "----\t ---\t ----\t ----\t\n\n"
                + "Bubba\t " + bubbaLab + "\t " + bubbaBonus + "\t " + bubbaTotal + "\n"
                + "Randy\t " + randyLab + "\t " + randyBonus + "\t " + randyTotal + "\n"
                + "Dude\t " + dudeLab + "\t " + dudeBonus + "\t " + dudeTotal
                ) ;
       
        
    }
}
