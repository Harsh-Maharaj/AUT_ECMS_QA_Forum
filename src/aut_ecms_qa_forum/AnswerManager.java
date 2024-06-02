package aut_ecms_qa_forum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The AnswerManager class provides methods to manage answers in the forum.
 * It includes methods to add, remove, and retrieve answers by question.
 */
public class AnswerManager {

    /**
     * Adds a new answer to the database.
     * 
     * @param answer The answer to be added
     */
    public void addAnswer(Answer answer) {
        String sql = "INSERT INTO Answers (content, author, questionId) VALUES (?, ?, ?)";
        try (Connection conn = DerbyDatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, answer.getContent());
            pstmt.setString(2, answer.getAuthor().getUsername());
            pstmt.setInt(3, answer.getQuestion().getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes an answer from the database.
     * 
     * @param answer The answer to be removed
     */
    public void removeAnswer(Answer answer) {
        String sql = "DELETE FROM Answers WHERE id = ?";
        try (Connection conn = DerbyDatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, answer.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of answers for a given question from the database.
     * 
     * @param question The question for which answers are to be retrieved
     * @return A list of answers for the specified question
     */
    public List<Answer> getAnswersByQuestion(Question question) {
        List<Answer> answers = new ArrayList<>();
        String sql = "SELECT * FROM Answers WHERE questionId = ?";
        try (Connection conn = DerbyDatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, question.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String content = rs.getString("content");
                String authorUsername = rs.getString("author");
                User author = ForumDatabase.getInstance().getUserManager().getUserByUsername(authorUsername);

                if (author == null) {
                    System.err.println("Error: Author not found for answer ID " + id);
                    continue; // Skip this answer if the author is not found
                }

                answers.add(new Answer(id, content, author, question));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }
}
