package aut_ecms_qa_forum;

/**
 *
 * @author Harsh & Dillan
 */
/**
 * Primary interface for interaction with the AUT ECMS Q/A System.
 */
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    // Members to hold system components for managing questions, users, sessions, and data persistence.
    private QuestionBank questionBank;
    private UserManager userManager;
    SessionManager sessionManager;
    private FilePersistence filePersistence;
    private Scanner scanner;

    /**
     * Constructor initializing ConsoleUI with system components.
     */
    public ConsoleUI(QuestionBank questionBank1, UserManager userManager, SessionManager sessionManager, FilePersistence filePersistence) {
        // Initializing the question bank, user manager, session manager, and file persistence mechanisms.
        this.questionBank = new QuestionBank(filePersistence);
        this.userManager = userManager;
        this.sessionManager = sessionManager;
        this.filePersistence = filePersistence;
        this.scanner = new Scanner(System.in); // Scanner for reading console input.
    }

    /**
     * Starts the application, handles login, and transitions to the main menu on successful authentication.
     */
    public void start() {
        // Display welcome message.
        System.out.println("================================================================================");
        System.out.println("                       Welcome to the AUT ECMS Q/A System                       ");
        System.out.println("================================================================================");
        // Login loop until successful login
        while (!sessionManager.isLoggedIn()) {
            System.out.println("Please login to continue:");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            // Attempt login and provide feedback.
            if (sessionManager.login(username, password, userManager)) {
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println("Login successful.");
                break; // Exit loop on successful login.
            } else {
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println("Login failed. Please try again.");
                System.out.println("--------------------------------------------------------------------------------");
            }
        }

        // Once logged in, display the appropriate menu based on user type.
        if (sessionManager.isLoggedIn()) {
            User currentUser = sessionManager.getCurrentUser();
            currentUser.displayMainMenu(this); // Displaying main menu for the current user.
        }
    }

    // Additional methods for menu displays could be defined here.
    // ...

    /**
     * Retrieves and returns the session manager.
     */
    public SessionManager getSessionManager() {
        return this.sessionManager;
    }

    /**
     * Displays all questions from the question bank.
     */
public void viewAllQuestions() {
    List<Question> questions = questionBank.getAllQuestions();
    // Handle case where no questions are available.
    if (questions.isEmpty()) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("No questions available.");
        System.out.println("--------------------------------------------------------------------------------");
    } else {
        System.out.println("Available Questions:\n");
        System.out.println("--------------------------------------------------------------------------------");
        questions.forEach(question -> {
            // Display question ID and text with a newline for separation
            System.out.println("Question ID " + question.getId() + ": " + question.getQuestionText());
            // Check if an answer is available and display appropriately
            if (question.getAnswerText() != null && !question.getAnswerText().isEmpty()) {
                System.out.println("Answer: " + question.getAnswerText());
            } else {
                System.out.println("Answer: No answer provided.");
            }
            // Separator after each Q&A pair for clear distinction
            System.out.println("--------------------------------------------------------------------------------");
        });
    }
}

    /**
     * Prompts the user to enter a new question and adds it to the question bank.
     */
    public void addQuestion() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("Enter question text: ");
        String questionText = scanner.nextLine();
        Question question = new Question(questionText, ""); // Instantiating a new question with empty answer.

        // Attempt to add the question to the bank and handle possible exceptions.
        try {
            questionBank.addQuestion(question);
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("Question added successfully.");
            System.out.println("--------------------------------------------------------------------------------");
        }catch (DuplicateQuestionException e) {
            // Notify the user if the question already exists.
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prompts the user to enter a question ID and attempts to remove the corresponding question.
     */
    public void removeQuestion() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("Enter question ID to remove: ");
        String id = scanner.nextLine();
        System.out.println("--------------------------------------------------------------------------------");
        // Attempt to remove the question and provide feedback.
        if (questionBank.removeQuestion(id)) {
            System.out.println("Question removed successfully.");
            System.out.println("--------------------------------------------------------------------------------");
        } else {
            // Notify the user if the question could not be found.
            System.out.println("Question not found.");
            System.out.println("--------------------------------------------------------------------------------");
        }
    }

    /**
     * Allows searching for questions containing specific keywords.
     */
    public void searchQuestions() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("Enter a keyword to search for questions: ");
        String keyword = scanner.nextLine();
        System.out.println("--------------------------------------------------------------------------------");
        // Perform the search and display results.
        List<Question> results = questionBank.searchQuestions(keyword);
        if (results.isEmpty()) {
            // Notify if no questions matching the keyword were found.
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("No questions found.");
            System.out.println("--------------------------------------------------------------------------------");
        } else {
            // Display each found question along with its answer.
            results.forEach(question -> System.out.println(question.getId() + ": " + question.getQuestionText() + " - " + question.getAnswerText()));
        }
    }

    /**
     * Prompts the user to answer a question identified by its ID.
     */
    public void answerQuestions() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("Enter the ID of the question you wish to answer: ");
        String questionId = scanner.nextLine();
        System.out.println("--------------------------------------------------------------------------------");
        // Attempt to retrieve the question by ID.
        Question question = questionBank.getQuestionById(questionId);

        if (question != null) {
            // If the question is found, display the current answer and prompt for a new one.
            System.out.println("Current Answer: " + question.getAnswerText());
            System.out.print("Enter your answer: ");
            String answerText = scanner.nextLine();
            // Update the question with the new answer.
            question.setAnswerText(answerText);
            // Persist the updated list of questions.
            questionBank.saveQuestions();
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("Thank you for your answer!");
            System.out.println("--------------------------------------------------------------------------------");
        } else {
            // Notify the user if the specified question was not found.
            System.out.println("Question not found.");
            System.out.println("--------------------------------------------------------------------------------");
        }
    }
}
