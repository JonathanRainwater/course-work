/*
* Written by Jonathan Rainwater
* Lab assignment 5
*
* This program creates pets for the user.
* The user will be asked to choose a pet or to be offered a random pet.
* The user can choose the type of pet, name of pet, and age of the pet.
* After a maximum of 5 possible selections, the user will be asked which pets
*   they want to keep, which will be displayed to the user, and then the program will end. 
 * 
 * 1. Create a variable to track the totalNumberOfPets.
 * 2. Display a greeting to the user.
 * 3. Create an 5-member array to hold allPets created.
 * 4. Create boolean seeMorePets to control the main loop.
 * 5. Start the main loop.
 * 6. Call method askToSeePet to ask the user if they would like to select their own pet or to have one selected for them.
 * 7. If user chooses to have a pet selected for them, create a no-argument pet object to generate a random pet, 
 *      then call method askToChangeTraits to ask the user if they want to change the name, type, or age of the created pet.
 * 8. If user chose to select their own pet, then prompt user to enter values for name, type, and age, and create
 *      a pet object with those arguments.
 * 9. After a pet is created by either choice, increment totalNumberOfPets by one to account for the new pet.
 * 10. Now back in the main loop,  check if the toalNumberOfPets is equal to the limit of 5.
 * 11. If total pets is 5, display message to user and set boolean seeMorePets to false to end main loop.
 * 12. If total pets is less than 5, call method to ask user if they would like to see more pets.
 * 13. If user wants to see more pets, return to top of main loop.
 * 14. If user does not want to see more pets, set boolean seeMorePets to false to end main loop.
 * 15. When main loop has ended, create a 5-member array keptPets, then call method choosePetsToKeep to ask
 *      the user which of the created pets that they want to keep.
 * 16. Loop through allPets and ask if each pet is to be kept.
 * 17. If so, add to keptPets and increment totalNumberOfKeptPets to track the pets kept.
 * 18. Now back in the main loop, call method leaveWithPets and loop through keptPets to display the names
 *      of the pets that were kept.
 * 19. Program ends.
*/

package lab5;
import java.util.Scanner;

public class PetTest {
    private static int totalNumberOfPets = 0; // To track the total number of pets seen.

    public static void main(String[] args) {
        greeting(); // Greet the user.
        Pet[] allPets = new Pet[5]; // Contains all pets shown to the user.
        boolean seeMorePets = true; // Controls the main loop.
        
        do {
            // Ask the user if they would like to choose their own pet or to have one
            // chosen for them. Add that pet to the allPets array.
            askToSeePet(allPets);
            // Check if maximum number of pets has been reached.
            // If not, ask user if they want to see another pet.
            if (PetTest.totalNumberOfPets == 5) {
                System.out.println("Okay, we have 5 animals out here running around,"
                        + " it's time to pick one!");
                seeMorePets = false;
            }
            else {
                seeMorePets = askIfFinishedSeeingPets();
            }
        } while(seeMorePets);
        
        Pet[] keptPets = new Pet[5]; // Points to all pets user wants to keep.
        // Ask user which pets they want to keep.
        int totalNumberOfKeptPets = choosePetsToKeep(allPets, keptPets);
        // Display list of kept pets and parting message.
        leaveWithPets(totalNumberOfKeptPets, keptPets);
    }
    
    // Diplay list of kept pets and parting message.
    public static void leaveWithPets(int totalNumberOfKeptPets, Pet[] keptPets) {
        if (totalNumberOfKeptPets == 0) {
            // User didn't keep any pets.
            System.out.println("So you don't want any pets after all? All that for nothing!\n"
                    + "Thank you for visiting Jon's Pet Store, where all sales are final!");
        }
        else {
            System.out.println("Now we will display a list of the pets you have choosen. (Press ENTER) ");
            Scanner input = new Scanner(System.in);
            input.nextLine(); // Create a pause before displaying list.
            // Display each pet that was kept.
            for(int i = 0; i < totalNumberOfKeptPets; i++) {
                System.out.println((i +1) + ":  " + keptPets[i].toString());
            }
            System.out.println("\nThank you for shopping at Jon's Pet Store, where all sales are final!");
        }
    }
    
    // Ask the user which pets they want to keep.
    public static int choosePetsToKeep(Pet[] allPets, Pet[] keptPets) {
        int totalNumberOfKeptPets = 0; // To track the total number of kept pets.
        System.out.println("\nNow it's time to decide which pets you would like to keep!");
        for (int i = 0; i < PetTest.totalNumberOfPets; i++) {
            System.out.print("Would you like to keep " + allPets[i] + " ? (Enter 1 for YES, or 2 for NO): ");
            // Prompt user to enter 1 or 2.
            int choice = getSingleDigitInput(1,2);
            if (choice == 2) {
                // User chose not to keep this pet.
                System.out.println("\nSorry " + allPets[i].getName() + ", better luck next time!\n");
            }
            else {
                // User chose to keep this pet.
                keptPets[totalNumberOfKeptPets] = allPets[i];
                System.out.println("\nPack your bags " + keptPets[totalNumberOfKeptPets].getName() + ", you're movin out!\n");
                totalNumberOfKeptPets ++;
            }
        }
        return(totalNumberOfKeptPets);
    }
    
    // Ask the user if they want to see any more pets.
    public static boolean askIfFinishedSeeingPets() {
        System.out.print("Would you like to see any more pets?"
                + " (Enter 1 for YES or 0 for NO): ");
        // Prompt user to enter 0 or 1.
        int tmpAnswer = getSingleDigitInput(0,1);
        if (tmpAnswer == 1) {
            return(true);
        }
        return(false);
    }
    
    // Ask the user what if they would like to choose their own pet or to have one
    // chosen for them. Add that pet to the allPets array.
    // (Uses both constructor types.)
    public static void askToSeePet(Pet[] allPets) {
        System.out.print("Would you like to choose which pet to see,"
                + " or would you like for us to choose one for you?\n"
                + "(Enter 1 to choose your own pet, or enter 2 to have us choose one for you): ");
        // Prompt user to enter 1 or 2.
        int choice = getSingleDigitInput(1,2); 
        if (choice == 2) {
            // User has selected to have a pet created for them.
            // Create a pet with randomly selected type, age, and name.
            allPets[PetTest.totalNumberOfPets] = new Pet();
            // Ask user if they want to change any of the randomly selected values.
            askToChangeTraits(allPets);
        }
        else {
            // User has selected to choose their own pet.
            // Ask user to enter a pet type.
            System.out.print("What type of pet do you want? (Spell with letters A-Z / a-z): ");
            String type = getStringInput();
            // Ask user to enter an age for the pet.
            System.out.print("How old of a " + type + " do you want? (Enter a number between 1 and 9): ");
            int age = getSingleDigitInput(1,9);
            // Ask user to enter a name for the pet.
            System.out.print("What should we name this " + age + " year old " + type + "? "
                    + "(Spell with letters A-Z / a-z): ");
            String name = getStringInput();
            // Create a new pet object with the given values.
            allPets[PetTest.totalNumberOfPets] = new Pet(name, type, age);
        }
        System.out.println("\nSay hello to " + allPets[PetTest.totalNumberOfPets] + "!\n");
        PetTest.totalNumberOfPets ++; 
    }

    // Ask user is they want to change the pet's default traits.
    // (Uses accessors and mutators.)
    public static void askToChangeTraits(Pet[] allPets) {
        // Ask user if they want to change the pet's type.
        System.out.print("The pet we have chosen is a " + allPets[PetTest.totalNumberOfPets].getType() +
                ", do you want to change it to a different animal?"
                        + " (Enter 1 to CHANGE, or 2 to KEEP): ");
        // Prompt user to enter 1 or 2.
        int choice = getSingleDigitInput(1,2);
        if (choice == 1) {
            System.out.print("Enter the type of the animal that you want us to change it to "
                    + "(Spell with letters A-Z / a-z): ");
            allPets[PetTest.totalNumberOfPets].setType(getStringInput());
        }

        // Ask the user if they want to change the pet's name.
        System.out.print("This " + allPets[PetTest.totalNumberOfPets].getType() +
                "'s name is " + allPets[PetTest.totalNumberOfPets].getName() +
                " do you want to change the name? (Enter 1 to CHANGE, or 2 to KEEP): ");
        // Prompt user to enter 1 or 2.
        choice = getSingleDigitInput(1,2);
        if (choice == 1) {
            System.out.print("Enter a new name (Spell with letters A-Z / a-z): ");
            allPets[PetTest.totalNumberOfPets].setName(getStringInput());
        }

        // Ask the user if they want to change the pet's age.
        System.out.print(allPets[PetTest.totalNumberOfPets].getName() + " the " +
                allPets[PetTest.totalNumberOfPets].getType() + " is " +
                allPets[PetTest.totalNumberOfPets].getAge() + " years old, "
                + "do you want to change " + allPets[PetTest.totalNumberOfPets].getName() +
                "'s age? (Enter 1 to CHANGE, or 2 to KEEP): ");
        // Prompt user to enter 1 or 2.
        choice = getSingleDigitInput(1,2);
        if (choice == 1) {
            System.out.print("Choose a new age for " + allPets[PetTest.totalNumberOfPets].getName() +
                    " (Enter a number between 1 and 9): ");
            allPets[PetTest.totalNumberOfPets].setAge(getSingleDigitInput(1,9));
        }

    }

    // Get input from the user, validate that the input is a String
    //  with only letters a-z or A-Z, and return the result.
    public static String getStringInput() {
        Scanner tmpInput = new Scanner(System.in);
        //int answerInt = -1; // -1 continues the following loop.
        String answerStr = "";
        boolean inputIsValid = false;

        do {
            inputIsValid = false;

            // Get input.
            answerStr = tmpInput.nextLine();

            // Check that something was entered.
            // Also validate that only letters were entered.
            if (answerStr.length() == 0)
                System.out.println("You didn't enter anything!");
            else {
                // Check that every character is a letter between A-Z or a-z.
                for (int i = 0; i < answerStr.length(); i++) {
                    char tmpChar = answerStr.charAt(i);
                    if ( ((tmpChar >= 65) && (tmpChar <= 90))
                            || (tmpChar >=97) && (tmpChar <= 122)) {
                        // Character is between A-Z or a-z.
                    }
                    else {  
                        // if (Character.isLetter(answerStr.charAt(i)) == false) {
                        break;  
                    }
                    if (i == (answerStr.length() -1)) {
                        inputIsValid = true;
                    }
                } // End for loop.
            } // End if/else.

            if (inputIsValid ==false) {
                // Ask user to enter valid input.
                System.out.print("Invalid input. "
                        + "Enter only letters A-Z or a-z:");            
            }

        } while (inputIsValid == false);
        return(answerStr);
    }


    // Get input from the user, validate that the input is a single digit between
    //    two values, and return the result.
    public static int getSingleDigitInput(int low, int high) {
        Scanner tmpInput = new Scanner(System.in);
        int answerInt = -1; // -1 continues the following loop.

        do {
            // Get input.
            String answerStr = tmpInput.nextLine();

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
        return(answerInt);
    }

    // Initial greeting to the user.
    public static void greeting() {
        System.out.println("Welcome to Jon's Pet Store!\n"
                + "We have have a wide selection of pets to choose from!\n"
                + "But please don't tease the orangutan while you're here "
                + "or he'll start throwing things again!\n");
    }
}
