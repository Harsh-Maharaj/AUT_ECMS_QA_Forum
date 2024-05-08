package aut_ecms_qa_forum;

/**
 *
 * @author Harsh & Dillan
 */
import java.io.Serializable;

/**
 * Represents a question in the AUT ECMS Q/A Forum, including its text, answer, and status.
 */
public class Question implements Serializable {
    private static final long serialVersionUID = 1L; // Ensures class can be reliably serialized and deserialized.

    private String id; // Unique identifier for the question.
    private String questionText; // The text of the question.
    private String answerText; // The text of the answer.
    private String status; // Indicates whether the question is "open" or "answered".

    /**
     * Default constructor for use in serialization and deserialization.
     */
    public Question() {
        // Intentionally empty for framework use.
    }

    /**
     * Constructs a Question with specified question text, initializing it as unanswered.
     *
     * @param questionText The text of the question being asked.
     */
    public Question(String questionText) {
        this.questionText = questionText;
        this.status = "open"; // Default status for new, unanswered questions.
    }

    /**
     * Constructs a Question with specified question and answer text. Useful for legacy data without an ID.
     *
     * @param questionText The text of the question.
     * @param answerText The text of the answer.
     */
    public Question(String questionText, String answerText) {
        this.questionText = questionText;
        this.answerText = answerText;
        this.status = answerText.isEmpty() ? "open" : "answered"; // Determines status based on presence of answer.
    }

    /**
     * Fully specified constructor including an ID, question text, and answer text.
     *
     * @param id Unique identifier for the question.
     * @param questionText The text of the question.
     * @param answerText The text of the answer.
     */
    public Question(String id, String questionText, String answerText) {
        this.id = id;
        this.questionText = questionText;
        this.answerText = answerText;
        this.status = answerText.isEmpty() ? "open" : "answered"; // Status depends on answer presence.
    }

    // Standard getters and setters.

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
        this.status = !answerText.isEmpty() ? "answered" : "open"; // Updates status based on answer.
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Provides a string representation of the question, including its ID, text, answer, and status.
     *
     * @return A string representation of the question.
     */
}
