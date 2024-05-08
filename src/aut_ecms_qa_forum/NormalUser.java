
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aut_ecms_qa_forum;

/**
 *
 * @author Harsh & Dillan
 */
import java.util.Scanner;

/**
 * Represents a normal user in the AUT ECMS Q/A Forum. 
 * Provides functionalities specific to normal users through a menu-driven interface.
 */
public abstract class NormalUser extends User {

    /**
     * Constructor initializing a NormalUser with username, password, and setting the user role to "user".
     *
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public NormalUser(String username, String password) {
        super(username, password, "user"); // Initializes the superclass (User) with the given credentials and role.
    }

    /**
     * Displays the main menu specific to normal users and handles user input for menu options.
     *
     * @param consoleUI The ConsoleUI instance to interact with the system's functionality.
     */
    @Override
    public void displayMainMenu(ConsoleUI consoleUI) {
        Scanner scanner = new Scanner(System.in); // Scanner for reading user input from the console.
        while (true) {
            // Print menu options
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("\n                      AUT ECMS Q/A Forum User Menu                          \n");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("1. Search Questions");
            System.out.println("2. Answer Questions");
            System.out.println("3. Add Question");
            System.out.println("4. View All Questions");
            System.out.println("5. Logout");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.print("Choose an option: ");
            
            int choice; // Variable to store the user's menu choice.
            try {
                choice = Integer.parseInt(scanner.nextLine()); // Parse the user input to an integer.
            } catch (NumberFormatException e) {
                // Handle invalid input (non-integer).
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println("Invalid input. Please enter a number.");
                System.out.println("--------------------------------------------------------------------------------");
                continue; // Restart the loop to display the menu again.
            }

            // Handle the chosen menu option.
            switch (choice) {
                case 1:
                    consoleUI.searchQuestions(); // Option to search questions.
                    break;
                case 2:
                    consoleUI.answerQuestions(); // Option to answer a question.
                    break;
                case 3:
                    consoleUI.addQuestion(); // Option to add a new question.
                    break;
                case 4:
                    consoleUI.viewAllQuestions(); // Option to view all questions.
                    break;
                case 5:
                    consoleUI.getSessionManager().logout(); // Option to logout.
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("You have been logged out.");
                    System.out.println("--------------------------------------------------------------------------------");
                    return; // Exit the method upon logout.
                default:
                    // Handle selection of an invalid menu option.
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("Invalid option. Please try again.");
                    System.out.println("--------------------------------------------------------------------------------");
            }
        }
    }
}
