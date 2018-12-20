/*
 * File: AddressBookMain
 * Author: Jonathan Rainwater
 * Date: 2018-03-20
 * Lab assignment 3.B for Java II
 * 
 * This program allows a user to load address entries from an address-book file, search through those address
 * book entries for users by last name or birth-month, and then display those results and/or print them to a file.
 * 
 * Start a main loop that will repeat until the user chooses to quit the program.
 * Display a main menu to the user that lists the options of (1) loading addresses from a file, (2) displaying
 *      the loaded addresses to the screen, (3) printing the loaded addresses to a file, (4) searching for a person
 *      by their last name, (5) searching for people by birth-month, or (6) quitting the program.
 *  If user chooses to load addresses from a file, the user is prompted to enter the name of the file that the
 *      addresses should be loaded from. Any valid entries that are found in the file are read into the array
 *      addressBook.
 *  If user chooses to display the loaded addresses, then the address entries that were read from the file are
 *      displayed on the screen (sorted by last name).
 *  If user chooses to print the loaded addresses, then the address entries are printed to the file
 *      All_Sorted_By_Last_Name.txt (sorted by last name).
 *  If user chooses to search for a person by their last name, then prompt the user to enter a last name and
 *      then add any matching entries to an ArrayList. Then prompt the user to choose whether or not to display
 *      the results to the screen and/or print the results to a file. If printed to a file, the file will be
 *      Search_Results_Of_Last_Name.txt.
 *  If user chooses to search for people by birth-month, then prompt the user to enter a two-digit month and
 *      then add any matching entries to an ArrayList. Then prompt the user to choose whether or not to display
 *      the results to the screen and/or print the results to a file. If printed to a file, the file will be
 *      Search_Results_Of_Birthdate.txt.
 *  If user chooses to quit the program, then return a boolean that causes the main loop to end.
 * Program ends.
 */
package lab3;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

public class AddressBookMain {
    // The directory where all files will be read from and written to.
    private static final String DIRECTORY_PATH = "io/"; 
    private static final byte ADDRESS_BOOK_MAX = 50; // The maximun number of address book entries.
    // An address book that holds up to ADDRESS_BOOK_MAX addresses.
    private static AddressBook[] addressBook = new AddressBook[0];
    
    public static void main(String[] args) {
        greeting(); // Displays an informative greeting to the user.
        boolean continueLoop = true; // Controls the following loop.
        do {
            displayMainMenu(); // Displays the main menu of options to the user.
            // Get the user's choice and implement that choice.
            // Return true if the main loop should continue. Return false if the main loop should end.
            continueLoop = processUserChoice();
        } while (continueLoop); // Loop will repeat until the user chooses to exit the program.
        System.out.println("Exiting program");
    }
    
    // Displays an informative greeting to the user.
    private static void greeting() {
        System.out.println("This program allows a user to load files from an address-book file, \n"
                + "search through those address-book entries for users by last name or birth-month, \n"
                + "and then display those results and/or print them to a file.\n");
    }
    
    // Displays the main menu of options to the user.
    private static void displayMainMenu() {
        System.out.println("Main Menu\n"
                + "(1) Load addresses from a file");
        if (addressBook.length == 0) {
            // addressBook is empty. Only other option is to exit.
            System.out.println("(2) Exit");
        }
        if (addressBook.length != 0) {
            // addressBook has addresses in it. Display other options.
            System.out.println("(2) Display loaded addresses (sorted by last name)\n"
                    + "(3) Print loaded addresses to a file (sorted by last name)\n"
                    + "(4) Search for a person by their last name\n"
                    + "(5) Search for people by birthdate\n"
                    + "(6) Exit");
        }    
    }
    
    // Get the user's choice and implement that choice.
    // Return true if the main loop should continue. Return false if the main loop should end.
    private static boolean processUserChoice() {
        int choice; // Will hold the user's choice.
        if (addressBook.length == 0) {
            // addressBook is empty. Loading new addresses from a file or quitting should be the only options.
            choice = ValidateInput.getIntegerInput(1, 2);
            System.out.println();
            if (choice == 2) {
                // User chose to exit the program.
                return false; // To end the main loop.
            }
        }
        else {
            // addressBook has addresses in it. Allow user to choose additional options.
            choice = ValidateInput.getIntegerInput(1, 6);
            System.out.println();
            if (choice == 2) {
                // User chose to display all addresses that are currently loaded into addressBook.
                for (AddressBook ab: addressBook) {
                    System.out.println(ab);
                }
                ValidateInput.pause(); // Wait for user input to proceed.
            }
        }
        if (choice == 1) {
            // User chose to load addresses from a file.
            fileReadMenu(); // Prompt user to enter a file name and then read addresses from that file.
        }
        if (choice == 3) {
            // User chose to print loaded addresses to a file (sorted by last name).
            writeToFile("all", new ArrayList<AddressBook>(Arrays.asList(addressBook)));
        }
        if (choice == 4) {
            // User chose to search for a person by their last name.
            // Perform the search, ask user if they want to display or print the results, and implement that choice.
            searchByLastName();
        }
        if (choice == 5) {
            // User chose to search for people by their birthdate.
            // Perform the search, ask user if they want to display or print the results, and implement that choice.
            searchByBirthdate();
        }
        if (choice == 6) {
            // User chose to exit the program.
            return false; // To end the main loop.
        }
        return true; // To continue the main loop.
    }
    
    // Prompt user to enter a file name and then read addresses from that file.
    private static void fileReadMenu() {
        Scanner scannerIn = new Scanner(System.in);
        boolean continueLoop = true; // Controls the following loop.
        do {
            System.out.println("Enter the name of a file (including file-type extention) that contains addresses:\n"
                    + "(file must be located in the /" + DIRECTORY_PATH
                    + " directory, and it's addresses must be in a valid format) ");
            File inputFileName = new File(DIRECTORY_PATH + scannerIn.nextLine()); // Get file name from user.
            if ( ! inputFileName.exists() ) {
                System.out.println("ERROR: That file does not exist!");
                ValidateInput.pause(); // Wait for user input to proceed.
                break; // To exit this loop and return to the main menu.
            } 
            // At this point, file is confirmed to exist.
            try {
                readFile(inputFileName); // Read address entries from the given file into the addressBook array.
                continueLoop = false; // Read was successful. Return the the main menu.
            }
            catch (IOException ex) {
                System.out.println(ex); // Display the error.
                continueLoop = false; // Return to the parent menu.
            }
        } while(continueLoop);
    }
    
    // Read address entries from the given file into the addressBook array.
    private static void readFile(File inputFileName) throws IOException {
        if (addressBook.length != ADDRESS_BOOK_MAX) {
            // addressBook has not been initialized or has been previously resized.
            // Set size of array to ADDRESS_BOOK_MAX
            addressBook = Arrays.copyOf(addressBook, ADDRESS_BOOK_MAX);
        }
        Scanner fileIn = new Scanner(inputFileName); // Create scanner for reading from file.
        byte completeAddressesRead = 0; // The number of complete address entries successfully read.
        String[] lines = new String[10]; // An array that contains each line of one whole address-book entry.
        while (fileIn.hasNext() && completeAddressesRead < ADDRESS_BOOK_MAX) {
            // File has more readable contents and the ADDRESS_BOOK_MAX has not yet been reached.
            byte linesRead = 0; // Records the number of lines that have been read.
            while (fileIn.hasNext() && linesRead < 10) {
                // File has more readable contents and the total number of lines that constitute one complete
                // address entry has not yet been reached.  9 lines (+1 emply line) = 1 entry.
                lines[linesRead] = fileIn.nextLine(); // Add the line read to an array of lines for one entry.
                linesRead++; // Record that one additional line has been read.
            } // End inner while loop.
            if (linesRead >= 9) {
                // At least all 9 data fields of an address entry have been read. (Maybe minus the 10th empty line.) 
                // Create an address book object with the read lines and add it to the addressBook array.
                addressBook[completeAddressesRead] = new AddressBook(lines[0], lines[1], lines[2], lines[3],
                        lines[4], lines[5], lines[6], lines[7], lines[8]);
                completeAddressesRead++; // Record that one additional address has been read.
            }
            else {
                // The number of lines that were read do not constitute a full address entry.
                break; // End loop since there are no more addresses.
            }
        } // End outter while loop.
        System.out.println("\n" + completeAddressesRead + " addresses book entries found and read successfully.");
        if (fileIn.hasNext()) {
            // There may be more than ADDRESS_BOOK_MAX entries in the input file.
            System.out.println("The maximum number of allowable address book entries have been read.\n"
                    + "There may be more entries that remain unread in the input file.");
        }
        fileIn.close(); // Close the read stream.
        // Resize the addressBook array to omit null entries.
        addressBook = Arrays.copyOf(addressBook, completeAddressesRead);
        Arrays.sort(addressBook); // Sorts the address books entries by last name.
        ValidateInput.pause(); // Wait for input from the user to proceed.
    }

    // User chose to search for a person by their last name.
    // Perform the search, ask user if they want to display or print the results, and implement that choice.
    private static void searchByLastName() {
        System.out.print("Enter the person's last name: ");
        String enteredName = new Scanner(System.in).nextLine(); // Get last name to search for from the user.
        String enteredNameUppercase = enteredName.toUpperCase(); // Convert search name to uppercase.
        byte entriesFound = 0; // A count of the number of people found with the entered last name.
        String entryName = ""; // Will be the last name in each address-book entry.
        // Create an ArrayList that will hold the matching entries that are found.
        ArrayList<AddressBook> searchResults = new ArrayList<AddressBook>();
        for (AddressBook entry: addressBook) {
            entryName = entry.getLastName().toUpperCase(); // Get this entry's last name in uppercase.
             if (entryName.contains(enteredNameUppercase)) {
                 // enteredName matches this entryName.
                 searchResults.add(entry); // Add this whole addressBook entry to an ArrayList.
                 entriesFound++; // Record that a matching entry was found.
             }
        } // End for loop.
        if (searchResults.isEmpty()) {
            // No matching entries were found.
            System.out.println("No entries found matching the last name of " + enteredName);
            ValidateInput.pause(); // Wait for user input to proceed.
        }
        else {
            // Matching entries were found.
            System.out.println(entriesFound + " matching entries were found.");
            ValidateInput.pause(); // Wait for user input to proceed.
            searchMenu("name", searchResults); // Allow user to chose to display or print the results.
        }
        // Returns to main menu after this method ends.
    }
    
    // User chose to search for people by their birthdate.
    // Perform the search, ask user if they want to display or print the results, and implement that choice.
    private static void searchByBirthdate() {
        System.out.println("Enter the two-digit month of birth that you want to find matching birthdate "
                + "entries for: ");
        int enteredMonth = ValidateInput.getIntegerInput(1, 12); // Get the month of birth in two-digit format.
        byte entriesFound = 0; // A count of the number of people with matching birthdates found.
        // Create an ArrayList that will hold the matching entries that are found.
        ArrayList<AddressBook> searchResults = new ArrayList<AddressBook>();
        for (AddressBook entry: addressBook) {
            // Record the birth-month of the current entry.
            String entryMonth = entry.getBirthdate().substring(0, 2); 
             if ( Integer.parseInt(entryMonth) == enteredMonth ) {
                 // The entered birth-month matches this addressBook entry.
                 searchResults.add(entry); // Save matching entry in the ArrayList.
                 entriesFound++; // Record that a matching entry was found.
             }
        } // End for loop.
        if (searchResults.isEmpty()) {
            // No matching entries were found.
            System.out.println("No entries found matching the birth-month of  " + enteredMonth);
            ValidateInput.pause(); // Wait for user input to proceed.
        }
        else {
            // Matching entries were found.
            System.out.println(entriesFound + " matching entries were found.");
            ValidateInput.pause(); // Wait for user input to proceed.
            searchMenu("birthdate", searchResults); // Allow user to chose to display or print the results.
        }
        // Returns to main menu after this method ends.
    }
    
    // Prompt user to chose to display or print the results of a search.
    // Takes a String that indentifies the search-type and takes an ArrayList that contains the search results.
    private static void searchMenu(String searchType, ArrayList<AddressBook> searchResults) {
        boolean continueLoop = true; // Controls the following loop.
        do {
            System.out.println("Search Menu\n"
                    + "(1) Display search results\n"
                    + "(2) Print search results to a file\n"
                    + "(3) Return to main menu");
            int choice = ValidateInput.getIntegerInput(1, 3); // Get the user's choice.
            System.out.println();
            if (searchType.matches("name")) {
                // The search-type was based on last name.
                if (choice == 1) {
                    // User chose to display the search results.
                    for (AddressBook results: searchResults) {
                        System.out.println(results.getFirstName() + " " + results.getLastName() + ", "
                                + results.getStreetAddress() + ", " + results.getPhoneNumber() + ", "
                                + results.getBirthdate());
                    }
                    ValidateInput.pause(); // Wait for user input to proceed.
                }
            }
            if (searchType.matches("birthdate")) {
                // The search-type was based on birth-month.
                if (choice == 1) {
                    // User chose to display the search results.
                    for (AddressBook results: searchResults) {
                        System.out.println(results.getFirstName() + " " + results.getLastName() + ", "
                                + results.getBirthdate());
                    }
                    ValidateInput.pause(); // Wait for user input to proceed.
                }
            }
            if (choice == 2) {
                // User chose to print the search results to a file.
                writeToFile(searchType, searchResults);
            }
            if (choice == 3) {
                // User chose to return to the main menu.
                continueLoop = false; // To return to the main menu.
            }
        } while (continueLoop);
    }
    
    // User chose to print the search results to a file.
    // Takes a String that indentifies the search-type and takes an ArrayList that contains the search results.
    private static void writeToFile(String outputSort, ArrayList<AddressBook> outputList) {
        File outFile; // Will hold the name of the output file.
        try {
            if (outputSort.matches("all")) {
                // User chose to display all addresses that are
                // currently loaded into addressBook (sorted by lase name).
                outFile = new File(DIRECTORY_PATH + "All_Sorted_By_Last_Name.txt");
                PrintWriter outStream = new PrintWriter(outFile);
                for (AddressBook entry: outputList) {
                    outStream.println(entry.getFirstName());
                    outStream.println(entry.getLastName());
                    outStream.println(entry.getBirthdate());
                    outStream.println(entry.getAssociation());
                    outStream.println(entry.getPhoneNumber());
                    outStream.println(entry.getStreetAddress());
                    outStream.println(entry.getCity());
                    outStream.println(entry.getState());
                    outStream.println(entry.getZipCode() + "\n");
                }
                outStream.close();
                System.out.println("File created at " + outFile.getAbsolutePath());
            }
            if (outputSort.matches("name")) {
                // The search-type was based on last name.
                outFile = new File(DIRECTORY_PATH + "Search_Results_Of_Last_Name.txt");
                PrintWriter outStream = new PrintWriter(outFile);
                for (AddressBook entry: outputList) {
                    outStream.println(entry.getFirstName());
                    outStream.println(entry.getLastName());
                    outStream.println(entry.getStreetAddress());
                    outStream.println(entry.getPhoneNumber());
                    outStream.println(entry.getBirthdate() + "\n");
                }
                outStream.close();
                System.out.println("File created at " + outFile.getAbsolutePath());
            }
            if (outputSort.matches("birthdate")) {
                // The search-type was based on birth-month.
                outFile = new File(DIRECTORY_PATH + "Search_Results_Of_Birthdate.txt");
                PrintWriter outStream = new PrintWriter(outFile);
                for (AddressBook entry: outputList) {
                    outStream.println(entry.getFirstName());
                    outStream.println(entry.getLastName());
                    outStream.println(entry.getBirthdate() + "\n");
                }
                outStream.close();
                System.out.println("File created at " + outFile.getAbsolutePath());
            }
            ValidateInput.pause(); // Wait for user input to proceed.
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
}
