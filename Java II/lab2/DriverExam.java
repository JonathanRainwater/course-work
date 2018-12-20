/*
 * File: DriverExam.java
 * Author: Jonathan Rainwater
 * Date: 2018-02-18
 * Lab assignment 2.A.1 for Java II
 * 
 * This class creates an object for storing and processing the results of a driver exam. Once the object is constructed
 * with the requied input, various methods can be used to process that information and return the results.
 */
package lab2;

public class DriverExam {    
    private char[] correctAnswers; // Contains the list of correct answers.
    private char[] studentAnswers; // Contains the list of the student's answers.
    private byte totalNeededToPass; // The total number of correct answers needed to pass the exam.
    
    /* Creates an driver exam object and initializes its variables. */
    public DriverExam(byte totalNumberOfQuestions, StringBuilder correctAnswers, char[] studentAnswers,
            byte totalNeededToPass) {
        // Initialize the arrays that store the answers with the known number of test questions.
        this.correctAnswers = new char[totalNumberOfQuestions];
        this.studentAnswers = new char[totalNumberOfQuestions];
        this.totalNeededToPass = totalNeededToPass;
        // Copy the answers to seperate arrays within this object.
        correctAnswers.getChars(0, (totalNumberOfQuestions), this.correctAnswers, 0);
        System.arraycopy(studentAnswers, 0, this.studentAnswers, 0, totalNumberOfQuestions);
    }
    
    /* passed: Compares the list of correct answers to the list of student answers to determine if the test was
     * passed or failed and return true if passed.
     */
    public boolean passed() {
        byte numberCorrect = 0; // Number of correct answers.
        for (byte i=0; i < correctAnswers.length; i++) {
            // Compare each correct answer to each student answer.
            if (correctAnswers[i] == studentAnswers[i]) {
                // Record correct answer count.
                numberCorrect++;
            }
        } // End loop.
        if (numberCorrect >= this.totalNeededToPass) {
            return true; // Exam was passed.
        }
        return false; // Exam was failed.
    }
    
    /* totalCorrect: Compares the list of correct answer to the list of student answers to determine the number
     * of questions answered correctly and returns that number.
     */
    public byte totalCorrect() {
        byte numberCorrect = 0; // Number of correct answers.
        for (byte i=0; i < correctAnswers.length; i++) {
            // Compare each correct answer to each student answer.
            if (correctAnswers[i] == studentAnswers[i]) {
                // Record the correct answer count.
                numberCorrect++;
            }
        } // End loop.
        return numberCorrect;
    }

    /* totalIncorrect: Compares the list of correct answer to the list of student answers to determine the number
     * of questions answered incorrectly and returns that number.
     */    
    public byte totalIncorrect() {
        byte numberIncorrect = 0; // Number of incorrect answers.
        for (byte i=0; i < correctAnswers.length; i++) {
            // Compare each correct answer to each student answer.
            if (correctAnswers[i] != studentAnswers[i]) {
                // Record the incorrect answer count.
                numberIncorrect++;
            }
        } // End loop.
        return numberIncorrect;
    }
    
    /* questionsMissed: Creates an array that contains the numbers of the questions that were missed and returns
     * that array.
     */
    public int[] questionsMissed() {
        int[] missed = new int[this.totalIncorrect()];
        byte currentIncorrect = 0;
        for (byte b=0; b < correctAnswers.length; b++) {
            // Compare each correct answer to each student answer.
            if (correctAnswers[b] != studentAnswers[b]) {
                // Answers do not match, so add answer number to array.
                missed[currentIncorrect] = (b + 1);
                currentIncorrect++;
            }
        } // End loop.
        return missed;
    }

}
