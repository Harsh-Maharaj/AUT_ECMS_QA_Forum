package aut_ecms_qa_forum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class QuestionManager {

    public void addQuestion(Question question) {
        if (isDuplicateQuestion(question)) {
            // Show popup dialog
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
}
