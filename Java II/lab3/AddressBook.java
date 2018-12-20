/*
 * File: AddressBook
 * Author: Jonathan Rainwater
 * Date: 2018-03-17
 * Lab assignment 3.B for Java II
 * 
 * This class doesn't really do anything. It's just a copy of the address class. The instructions call for making an
 * array of 50 of this class, and I wasn't sure how else to do it. Otherwise, this doesn't serve any pupose at all.
 */
package lab3;

// Constructs an address-book enty that consists of a person and their address.
public class AddressBook extends Address{
    public AddressBook(String firstName, String lastName, String birthdate, String assocciation,
            String phoneNumber, String streetAddress, String city, String state, String zipCode) {
        super(firstName, lastName, birthdate, assocciation, phoneNumber, streetAddress, city, state, zipCode);
    }
    
}
