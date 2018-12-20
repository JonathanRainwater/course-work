/*
 * Written by Jonathan Rainwater
 * Lab Assignment 5
 *
 * This class creates pet objects, where each object represents a pet
 *  with a name, type, and age. Either a pet can be created from randomly
 *  selected values with the default constructor, or a pet can be created from
 *  user input with the overloaded constructor.
 */
package lab5;

public class Pet {
    private String name = "";
    private String type = "";
    private int age = 0;
    
    // Creates a default pet object with randomly seleceted predefined values.
    public Pet() {
        // Define a list of names.
        String[] namesList = {"Max", "Charlie", "Buddy", "Cooper", "Jack", "Rocky", "Bear", "Duke", "Toby", "Tucker"
                , "Oliver", "Bentley", "Milo", "Teddy", "Jake", "Leo", "Jax"
                , "Dexter", "Zeus", "Louie", "Riley", "Winston", "Buster", "Murphy", "Jackson", "Bailey", "Harley", "Gus", "Lucky"
                , "Oscar", "Henry", "Cody", "Hank", "Sam", "Baxter", "Ollie", "Diesel", "Bruno", "Bandit", "Beau", "Gizmo"
                , "Moose", "Finn", "Sammy", "Blue", "Marley", "Loki", "Brody", "Jasper", "Ace", "Thor", "Shadow", "Rocco"
                , "Apollo", "Roscoe", "George", "Scout", "Bo", "Rex", "Tank", "Tyson", "Rusty", "Otis", "Koda", "Luke", "Simba"
                , "Gunner", "Romeo", "Hunter", "Joey", "Rudy", "Boomer", "Ziggy", "Copper", "Maverick", "Oreo", "Prince"
                , "Frankie", "Bruce", "Samson", "Peanut", "Benny", "Brutus", "Ranger", "Chester", "Chase", "Chance", "Kobe"
                , "Brady", "Coco", "Chico", "Chewy", "Bubba", "Benji", "Mickey", "Rufus", "King", "Archie", "Cash", "Walter"};
        // Assign a random name.
        this.name = namesList[(int)(Math.random() * namesList.length)];
        
        // Define a list of pet types.
        String[] typesList = {"bird", "burro", "cat", "chameleon", "chicken", "chinchilla", "cow", "dog", "donkey"
                , "dragon", "ferret", "fish", "gecko", "goose", "gerbil", "goat", "guinea pig", "hamster", "hedgehog"
                , "horse", "iguana", "llama", "lizard", "mouse", "mule", "peafowl", "pigeon", "pony", "pot-bellied pig"
                , "rabbit", "rat", "sheep", "skink", "snake", "sugar glider", "tarantula", "turkey", "turtle"};
        // Assign a random pet type.
        this.type = typesList[(int)(Math.random() * typesList.length)];
        
        // Assign a random age between 1 and 9.
        this.age = (int)(Math.random() * 9) + 1;
    }
    
    // Creates a pet object that is defined by user input.
    public Pet(String name, String type, int age) {
        this.name = name;
        this.type = type;
        this.age = age;
    }
    
    // Set the name of the pet.
    public void setName(String name) {
        this.name = name;
    }
    
    // Set the type of the pet.
    public void setType(String type) {
        this.type = type;
    }
    
    // Set the age of the pet.
    public void setAge(int age) {
        this.age = age;
    }
    
    // Get the name of the pet.
    public String getName() {
        return this.name;
    }
    
    // Get the type of the pet.
    public String getType() {
        return this.type;
    }
    
    // Get the age of the pet.
    public int getAge() {
        return this.age;
    }
    
    // Return the name, age, and type of pet.
    @Override
    public String toString() {
        return  (this.name + " the " + this.age + " year old " + this.type);
    }
}