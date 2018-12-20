/*
 * File: DriverExamMain.java
 * Author: Jonathan Rainwater
 * Date: 2018-02-18
 * Lab assignment 2.A.1 for Java II
 * 
 * The purpose of this program is to administer a driver exam to the user, record the results, compare the
 * results to a list of correct answers, and display detailed results.
 * 
 * 1. Initialize a StringBuilder with the correct asnwers to the exam.
 * 2. Initialze certain other vaiables based on the number of possible answers.
 * 3. Display a greeting to the user.
 * 4. Use a for loop that asks the user to provide an answer to each question on the exam. Validate the user's
 *      input and record the answer in an array.
 * 5. Create a DriverExam object that is instantiated with the values of the correct answers, the user's
 *      answers, the total number of questions, and the number of correct questions needed to pass.
 * 6. Call methods from the newly created object to display whether or not the user passed the exam, the total
 *      number correct and incorrect answers, and a list of the answers that were incorrect.
 */
package lab2;

public class DriverExamMain {
    // Defines the percentage that must be correct in order to pass.
    private final static double PERCENTAGE_NEEDED_TO_PASS = 0.75;
    private static char firstLetterChoice; // The first letter that can be chosen on the exam. 
    private static char lastLetterChoice; // The last letter that can be chosen on the eam.
    private static byte totalNumberOfQuestions; // Accounts for the total number of questions on the test.
    private static byte totalNeededToPass; // The total number of correct answers needed to pass the exam.
    private static StringBuilder correctAnswers; // Contains the correct answers to the exam.
    private static char[] studentAnswers; // Contains the student's answers to the exam.
    
    public static void main(String[] args) {        
        initializeAnswers(); // Initialize the list of corrent answers.
        setMainVars(); // Initialize certain variables based on the number of possible answers.
        greeting(); // Display a greeting to the user.
        // Prompt the user to answer each question on the exam and store the result in an array.
        for (byte currentQuestion = 1; currentQuestion <= totalNumberOfQuestions; currentQuestion++) {
            askQuestion(currentQuestion); // Ask the student the current question.
        }
        displayResults(); // Display the results of the exam.
    }
    
    /* initializeAnswers: Creates a list of correct answers for the exam and defines the possible letter choices
     * for the answers. */
    private static void initializeAnswers() {
        correctAnswers = new StringBuilder(); // Contains all the answers to the exam.
        // Add new answers or delete old ones as needed.
        // Maximum number of answers is 127 unless byte variables are changed.
        firstLetterChoice = 'A'; // Define the first letter that can be chosen on the exam.
        lastLetterChoice = 'D'; // Define the last letter that can be chosen on the exam.
        correctAnswers.append('B'); // Answer 1
        correctAnswers.append('D'); // Answer 2
        correctAnswers.append('A'); // Answer 3
        correctAnswers.append('A'); // Answer 4
        correctAnswers.append('C'); // Answer 5
        correctAnswers.append('A'); // Answer 6
        correctAnswers.append('B'); // Answer 7
        correctAnswers.append('A'); // Answer 8
        correctAnswers.append('C'); // Answer 9
        correctAnswers.append('D'); // Answer 10
        correctAnswers.append('B'); // Answer 11
        correctAnswers.append('C'); // Answer 12
        correctAnswers.append('D'); // Answer 13
        correctAnswers.append('A'); // Answer 14
        correctAnswers.append('D'); // Answer 15
        correctAnswers.append('C'); // Answer 16
        correctAnswers.append('C'); // Answer 17
        correctAnswers.append('B'); // Answer 18
        correctAnswers.append('D'); // Answer 19
        correctAnswers.append('A'); // Answer 20
        correctAnswers.trimToSize();
    }
    
    /* setMainVars: Initializes certain variables based on the number of answers on the exam. */
    private static void setMainVars() {
        // Set the total number of questions based on the initialized answers.
        totalNumberOfQuestions = (byte)(correctAnswers.length());
        // Set the number of questions needed to pass based on the total questions and the percentage needed to pass.
        totalNeededToPass = (byte)(totalNumberOfQuestions * PERCENTAGE_NEEDED_TO_PASS);
        // Set the size of the studentAnswers array.
        studentAnswers = new char[totalNumberOfQuestions];
    }
    
    /* greeting: Display a greeting to the user. */
    private static void greeting() {
        System.out.println("This program wil administer a driver's license exam in the form of "
                + totalNumberOfQuestions + " multiple-choice questions.\n"
                + "You must answer at least " + totalNeededToPass +" questions correctly in order to pass.");
    }    
    
    /* askQuestion: Promt the user to ask the current question on the exam, validate the input, and store the
     * answer in an array. Takes an argument that indicates the current question on the exam.     */
    private static void askQuestion(byte currentQuestion) {
        System.out.print("Chose the answer to question number " + currentQuestion + ""
                + " (Enter a letter between " + firstLetterChoice + " and " + lastLetterChoice + "): ");
        // Get validated user input and store it in an array.
        studentAnswers[currentQuestion -1] = ValidateInput.getCharacterInput(firstLetterChoice, lastLetterChoice);
    }
    
    /* displayResults: Display whether or not the user passed the exam, the number of questions answered 
     * correctly and incorrectly, and a list of the answers that were incorrect.     */
    private static void displayResults() {
        // Create the exam object that will hold the values of this exam and process the results.
        DriverExam exam = new DriverExam(totalNumberOfQuestions, correctAnswers, studentAnswers, totalNeededToPass);
        if (exam.passed()) {
            System.out.println("You passed the exam!");
        }
        else {
            System.out.println("You failed the exam!");
        }
        System.out.println("Number of questions asnwered correctly: " + exam.totalCorrect());
        System.out.println("Number of questions answered incorrectly: " + exam.totalIncorrect());
        System.out.print("List of questions missed: ");
        // Display a list of the questions that were missed.
        for (int i: exam.questionsMissed()) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }
    
}
