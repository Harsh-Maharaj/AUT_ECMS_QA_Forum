package aut_ecms_qa_forum;

/**
 *
 * @author Harsh & Dillan
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionManager {
    public void addQuestion(Question question) {
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
