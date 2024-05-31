
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aut_ecms_qa_forum;

import java.sql.SQLException;

/**
 *
 * @author Harsh & Dillan
 */
/**
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void addQuestion(String title, String content, User author) {
        Question newQuestion = new Question(title, content, author);
        ForumDatabase.getInstance().getQuestionManager().addQuestion(newQuestion);
    }

    public void editQuestion(Question question, String newTitle, String newContent) {
        question.setTitle(newTitle);
        question.setContent(newContent);
    }

    public void addAnswer(String content, User author, Question question) {
        Answer newAnswer = new Answer(content, author, question);
        ForumDatabase.getInstance().getAnswerManager().addAnswer(newAnswer);
    }

    public void editAnswer(Answer answer, String newContent) {
        answer.setContent(newContent);
    }

    public void deleteAnswer(Answer answer) {
        ForumDatabase.getInstance().getAnswerManager().removeAnswer(answer);
    }

    public void addUser(String username, String password, boolean isAdmin) throws SQLException {
        User newUser;
        if (isAdmin) {
            newUser = new Admin(username, password);
        } else {
            newUser = new User(username, password);
        }
        ForumDatabase.getInstance().getUserManager().addUser(newUser);
    }

    public void deleteUser(User user) throws SQLException {
        ForumDatabase.getInstance().getUserManager().removeUser(user);
    }
}
// testing for comments
// test