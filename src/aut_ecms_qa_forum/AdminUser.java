package aut_ecms_qa_forum;

import java.util.Scanner;

/**
 * Abstract class for administrative users in AUT ECMS Q/A Forum.
 */
public abstract class AdminUser extends User {

    /**
     * Constructor initializing an AdminUser with a username, password, and admin role.
     */
    public AdminUser(String username, String password) {
        super(username, password, "admin");
    }

    /**
     * Displays the admin main menu and handles user input for various administrative actions.
     */
    @Override
    public void displayMainMenu(ConsoleUI consoleUI) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Display the admin menu
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("\n                    AUT ECMS ADMIN Q/A Forum Main Menu                      \n");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("1. Add Question");
            System.out.println("2. Remove Question");
            System.out.println("3. Search Questions");
            System.out.println("4. View All Questions");
            System.out.println("5. Answer Questions");
            System.out.println("6. Log Out");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.print("Choose an option: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine()); // Parse user input to integer
            } catch (NumberFormatException e) {
                // Handle non-integer input
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println("Invalid input. Please enter a number.");
                System.out.println("--------------------------------------------------------------------------------");
                continue; // Restart the loop if input is invalid
            }

            // Handle user choice
            switch (choice) {
                case 1:
                    consoleUI.addQuestion();
                    break;
                case 2:
                    consoleUI.removeQuestion();
                    break;
                case 3:
                    consoleUI.searchQuestions();
                    break;
                case 4:
                    consoleUI.viewAllQuestions();
                    break;
                case 5:
                    consoleUI.answerQuestions();
                    break;
                case 6:
                    // Logout and exit the loop
                    consoleUI.getSessionManager().logout();
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("You have been logged out.");
                    System.out.println("--------------------------------------------------------------------------------");
                    return;
                default:
                    // Handle invalid option
                    System.out.println("Invalid option. Please try again.");
                    System.out.println("--------------------------------------------------------------------------------");
            }
        }
    }
}
