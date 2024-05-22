package aut_ecms_qa_forum;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerManager {
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
                String author = rs.getString("author");
                User user = ForumDatabase.getInstance().getUserManager().authenticate(author, "");
                answers.add(new Answer(id, content, user, question));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }
}

