package aut_ecms_qa_forum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerManager {

    /**
     * Adds a new answer to the database.
     * 
     * @param answer The answer to be added.
     */
    public void addAnswer(Answer answer) {
        String sql = "INSERT INTO Answers (content, author, questionId) VALUES (?, ?, ?)";
        try (Connection conn = DerbyDatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the parameters for the prepared statement
            pstmt.setString(1, answer.getContent());
            pstmt.setString(2, answer.getAuthor().getUsername());
            pstmt.setInt(3, answer.getQuestion().getId());
            // Execute the insert statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes an existing answer from the database.
     * 
     * @param answer The answer to be removed.
     */
    public void removeAnswer(Answer answer) {
        String sql = "DELETE FROM Answers WHERE id = ?";
        try (Connection conn = DerbyDatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the ID parameter for the prepared statement
            pstmt.setInt(1, answer.getId());
            // Execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all answers for a specific question.
     * 
     * @param question The question for which to retrieve answers.
     * @return A list of answers associated with the given question.
     */
    public List<Answer> getAnswersByQuestion(Question question) {
        List<Answer> answers = new ArrayList<>();
        String sql = "SELECT * FROM Answers WHERE questionId = ?";
        try (Connection conn = DerbyDatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the question ID parameter for the prepared statement
            pstmt.setInt(1, question.getId());
            // Execute the query
            ResultSet rs = pstmt.executeQuery();
            // Iterate through the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String content = rs.getString("content");
                String authorUsername = rs.getString("author");
                // Retrieve the author using the username
                User author = ForumDatabase.getInstance().getUserManager().getUserByUsername(authorUsername);

                // Check if the author exists
                if (author == null) {
                    System.err.println("Error: Author not found for answer ID " + id);
                    continue; // Skip this answer if the author is not found
                }

                // Add the answer to the list
                answers.add(new Answer(id, content, author, question));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }
}
