/*
 * File: Test.java
 * Author: Jonathan Rainwater
 * Date: 2018-03-03
 * 
 * This class is for testing the other classes in this package.
 */
package extracredit;

public class Test {
    public static void main(String[] args) {
        testPlayerConstructors();
        testPlayerMethods();
        testTeamConstructorsA();
        testTeamConstructorsB();
        testTeamMethodA();
        testTeamMethodB();
        testTeamMethodC();
    }
    
    private static void testPlayerConstructors() {
        System.out.println("TESTING PLAYER CONSTRUCTORS");
        System.out.println(new Player());
        System.out.println(new Player("Adam"));
        System.out.println(new Player("Bob",5));
        System.out.println(new Player("Carl", 55, 0.123));
        System.out.println("FINISHED TESTING PLAYER CONSTRUCTORS");
    }
    
    private static void testPlayerMethods() {
        System.out.println("TESTING PLAYER METHODS");
        Player dude = new Player("Dude", 99, .321);
        //
        System.out.println("Enter a new player number:");
        dude.setNumber();
        System.out.println("Player number is now " + dude.getNumber());
        //
        System.out.println("Enter new batting average:");
        dude.setBattingAverage();
        System.out.println("Player's batting average is now " + dude.getBattingAverage());
        //
        System.out.println("Enter new player name:");
        dude.setName();
        System.out.println("Player's nmae is now " + dude.getName());
        //
        System.out.println(dude.toString());
        System.out.println("FINISHED TESTING PLAYER METHODS");
    }
    
    private static void testTeamConstructorsA() {
        System.out.println("TESTING TEAM CONSTRUCTORS A");
        Team t1 = new Team();
        t1.addPlayer(new Player("Alpha", 6, 0.98));
        t1.addPlayer(new Player("Beta", 5, 0.87));
        t1.printRoster();
        System.out.println("FINISHED TESTING TEAM CONSTRUCTORS A");
    }
    
    private static void testTeamConstructorsB() {
        System.out.println("TESTING TEAM CONSTRUCTORS B");
        Player[] p = {new Player("Alpha", 1), new Player("Beta", 2), new Player("Charlie", 3)};
        Team t1 = new Team(p);
        t1.printRoster();
        //
        Player[] q = {new Player("Alpha", 1), new Player("Beta", 1), new Player("Charlie", 3)};
        Team t2 = new Team(q);
        t2.printRoster();
        //
        Team t3 = new Team(p, "Snoozers");
        t3.printRoster();
        System.out.println("FINISHED TESTING TEAM CONSTRUCTORS B");
    }
    
    private static void testTeamMethodA() {
        System.out.println("TESTING TEAM METHODS A");
        Team t1 = new Team();
        t1.printRoster();
        t1.removePlayer(5);
        t1.removePlayer(7);
        t1.removePlayer(6);
        t1.printRoster();
        System.out.println("FINISHED TESTING TEAM METHODS A");
    }
    
    private static void testTeamMethodB() {
        System.out.println("TESTING TEAM METHODS B");
        Team t1 = new Team();
        System.out.println(t1.playerIsOnTeam(5));
        System.out.println(t1.playerIsOnTeam(6));
        System.out.println(t1.playerIsOnTeam(7));
        System.out.println("FINISHED TESTING TEAM METHODS B");
    }
    
    private static void testTeamMethodC() {
        System.out.println("TESTING TEAM METHODS C");
        //
        Team t1 = new Team();
        t1.printPlayer(5);
        t1.printPlayer(6);
        t1.printPlayer(7);
        //
        System.out.println(t1.getName());
        t1.setName("Slackers");
        System.out.println(t1.getName());
        t1.setName("Part Timers");
        System.out.println(t1.getName());
        System.out.println(t1.toString());
        //
        System.out.println("FINISHED TESTING TEAM METHODS C");
    }
} // EOF