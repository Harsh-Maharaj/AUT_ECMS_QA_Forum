package aut_ecms_qa_forum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * The QuestionManager class provides methods to manage questions in the forum.
 * It includes methods to add, remove, and retrieve questions.
 * 
 * @author Harsh & Dillan
 */
public class QuestionManager {

    /**
     * Adds a new question to the database.
     * If the question already exists, shows an error message.
     * 
     * @param question The question to be added
     */
    public void addQuestion(Question question) {
        if (isDuplicateQuestion(question)) {
            // Show popup dialog for duplicate question
            JOptionPane.showMessageDialog(null, "Duplicate question. The question already exists in the database.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "INSERT INTO Questions (title, content, author) VALUES (?, ?, ?)";
        try (Connection conn = DerbyDatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, question.getTitle());
            pstmt.setString(2, question.getContent());
            pstmt.setString(3, question.getAuthor().getUsername());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a question is a duplicate.
     * 
     * @param question The question to be checked
     * @return True if the question is a duplicate, false otherwise
     */
    private boolean isDuplicateQuestion(Question question) {
        String sql = "SELECT COUNT(*) FROM Questions WHERE title = ? AND content = ?";
        try (Connection conn = DerbyDatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, question.getTitle());
            pstmt.setString(2, question.getContent());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Removes a question from the database.
     * First deletes all answers associated with the question.
     * 
     * @param question The question to be removed
     */
    public void removeQuestion(Question question) {
        // Delete all answers associated with this question first
        AnswerManager answerManager = ForumDatabase.getInstance().getAnswerManager();
        List<Answer> answers = answerManager.getAnswersByQuestion(question);
        for (Answer answer : answers) {
            answerManager.removeAnswer(answer);
        }
        // Then delete the question
        String sql = "DELETE FROM Questions WHERE id = ?";
        try (Connection conn = DerbyDatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, question.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all questions from the database.
     * 
     * @return A list of all questions
     */
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM Questions";
        try (Connection conn = DerbyDatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String author = rs.getString("author");
                User user = ForumDatabase.getInstance().getUserManager().getUserByUsername(author);
                questions.add(new Question(id, title, content, user));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    /**
     * Updates an existing question in the database.
     * 
     * @param question The question to be updated
     */
    public void updateQuestion(Question question) {
        String sql = "UPDATE Questions SET title = ?, content = ? WHERE id = ?";
        try (Connection conn = DerbyDatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, question.getTitle());
            pstmt.setString(2, question.getContent());
            pstmt.setInt(3, question.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
