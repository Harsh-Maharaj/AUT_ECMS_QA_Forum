package aut_ecms_qa_forum;

/**
 *
 * @author Harsh & Dillan
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages the collection of questions within the AUT ECMS Q/A Forum,
 * including loading, saving, adding, and searching for questions.
 */
public class QuestionBank {
    private List<Question> questions = new ArrayList<>(); // Stores all questions.
    private FilePersistence filePersistence; // Handles file operations for question persistence.
    private int nextId; // Tracks the next available ID for new questions.

    /**
     * Constructs a QuestionBank with a reference to a FilePersistence instance for data storage.
     * 
     * @param filePersistence The FilePersistence instance used for question storage.
     */
    public QuestionBank(FilePersistence filePersistence) {
        this.filePersistence = filePersistence;
        loadQuestions(); // Load existing questions from file.
        initializeNextId(); // Initialize the ID counter based on loaded questions.
    }

    /**
     * Loads questions from the persistence layer into the question bank.
     */
    private void loadQuestions() {
        this.questions = filePersistence.getAllQuestions();
    }

    /**
     * Initializes the nextId field to ensure unique IDs for new questions.
     */
    private void initializeNextId() {
        nextId = questions.stream()
                          .mapToInt(q -> Integer.parseInt(q.getId()))
                          .max()
                          .orElse(0) + 1; // Find the highest ID and prepare the next ID.
    }

    /**
     * Adds a new question to the question bank after checking for duplicates.
     * 
     * @param question The question to be added.
     * @throws DuplicateQuestionException if a duplicate question is found.
     */
    public void addQuestion(Question question) throws DuplicateQuestionException {
        String preprocessedNewQuestion = preprocessText(question.getQuestionText());

        // Check for duplicates by comparing preprocessed text.
        for (Question existingQuestion : questions) {
            String preprocessedExistingQuestion = preprocessText(existingQuestion.getQuestionText());
            if (preprocessedNewQuestion.equals(preprocessedExistingQuestion)) {
                throw new DuplicateQuestionException("A similar question is already in the system.");
            }
        }

        question.setId(generateNextId()); // Assign a unique ID to the new question.
        questions.add(question);
        saveQuestions(); // Persist the updated list of questions.
    }

    /**
     * Removes a question from the bank by its ID.
     * 
     * @param id The ID of the question to remove.
     * @return true if the question was removed, false otherwise.
     */
    public boolean removeQuestion(String id) {
        boolean removed = questions.removeIf(question -> question.getId().equals(id));
        if (removed) {
            saveQuestions(); // Persist changes after removal.
        }
        return removed;
    }

    /**
     * Searches for questions containing a specified keyword in their text.
     * 
     * @param keyword The keyword to search for.
     * @return A list of questions that contain the keyword.
     */
    public List<Question> searchQuestions(String keyword) {
        return questions.stream()
                .filter(question -> question.getQuestionText().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList()); // Collect and return matching questions.
    }

    /**
     * Persists the current state of the question list to the file.
     */
    void saveQuestions() {
        try {
            filePersistence.saveQuestions(questions);
        } catch (IOException e) {
            System.out.println("Failed to save questions: " + e.getMessage());
        }
    }

    /**
     * Retrieves a question by its ID.
     * 
     * @param id The ID of the question.
     * @return The question if found, or null otherwise.
     */
    public Question getQuestionById(String id) {
        for (Question question : questions) {
            if (question.getId().equals(id)) {
                return question;
            }
        }
        return null; // If no match is found, return null.
    }

    /**
     * Generates the next unique ID for a new question.
     * 
     * @return A string representing the next unique ID.
     */
    private String generateNextId() {
        return String.valueOf(nextId++); // Increment and return the next ID.
    }

    /**
     * Returns all questions in the question bank.
     * 
     * @return A list of all questions.
     */
    public List<Question> getAllQuestions() {
        return new ArrayList<>(questions);
    }

    /**
     * Preprocesses text by converting it to lowercase and removing special characters.
     * 
     * @param text The text to preprocess.
     * @return The preprocessed text.
     */
    private static String preprocessText(String text) {
        return text.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "").trim(); // Normalize text for comparison.
    }
}
