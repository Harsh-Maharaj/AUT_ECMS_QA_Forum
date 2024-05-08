package aut_ecms_qa_forum;

/**
 * Custom exception class for handling duplicate question scenarios within the AUT ECMS Q/A Forum.
 * Extends the standard Exception class to provide additional context specific to duplicate questions.
 *
 * @author Harsh & Dillan
 */
public class DuplicateQuestionException extends Exception {
    
    /**
     * Constructs a DuplicateQuestionException with the specified detail message.
     * The message provides more information about the exception that occurred.
     */
    public DuplicateQuestionException(String message) {
        super(message); // Calls the superclass constructor with the provided message.
    }
}
