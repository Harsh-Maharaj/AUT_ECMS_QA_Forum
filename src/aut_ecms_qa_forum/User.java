/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aut_ecms_qa_forum;

import java.sql.SQLException;

/**
 * The User class represents a user in the forum.
 * It includes the user's username, password, and methods for interacting with questions and answers.
 * 
 * @author Harsh & Dillan
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class User {
    private String username; // Username of the user
    private String password; // Password of the user

    /**
     * Constructs a User with the specified username and password.
     * 
     * @param username The username of the user
     * @param password The password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username of the user.
     * 
     * @return The username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password of the user.
     * 
     * @return The password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Checks if the provided password matches the user's password.
     * 
     * @param password The password to check
     * @return True if the password matches, false otherwise
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Adds a new question to the forum.
     * 
     * @param title The title of the question
     * @param content The content of the question
     * @param author The author of the question
     */
    public void addQuestion(String title, String content, User author) {
        Question newQuestion = new Question(title, content, author);
        ForumDatabase.getInstance().getQuestionManager().addQuestion(newQuestion);
    }

    /**
     * Edits an existing question.
     * 
     * @param question The question to be edited
     * @param newTitle The new title of the question
     * @param newContent The new content of the question
     */
    public void editQuestion(Question question, String newTitle, String newContent) {
        question.setTitle(newTitle);
        question.setContent(newContent);
    }

    /**
     * Adds a new answer to a question in the forum.
     * 
     * @param content The content of the answer
     * @param author The author of the answer
     * @param question The question to which the answer belongs
     */
    public void addAnswer(String content, User author, Question question) {
        Answer newAnswer = new Answer(content, author, question);
        ForumDatabase.getInstance().getAnswerManager().addAnswer(newAnswer);
    }

    /**
     * Edits an existing answer.
     * 
     * @param answer The answer to be edited
     * @param newContent The new content of the answer
     */
    public void editAnswer(Answer answer, String newContent) {
        answer.setContent(newContent);
    }

    /**
     * Deletes an answer from the forum.
     * 
     * @param answer The answer to be deleted
     */
    public void deleteAnswer(Answer answer) {
        ForumDatabase.getInstance().getAnswerManager().removeAnswer(answer);
    }

    /**
     * Adds a new user to the forum.
     * 
     * @param username The username of the new user
     * @param password The password of the new user
     * @param isAdmin True if the new user is an admin, false otherwise
     * @throws SQLException If a database access error occurs
     */
    public void addUser(String username, String password, boolean isAdmin) throws SQLException {
        User newUser;
        if (isAdmin) {
            newUser = new Admin(username, password);
        } else {
            newUser = new User(username, password);
        }
        ForumDatabase.getInstance().getUserManager().addUser(newUser);
    }

    /**
     * Deletes a user from the forum.
     * 
     * @param user The user to be deleted
     * @throws SQLException If a database access error occurs
     */
    public void deleteUser(User user) throws SQLException {
        ForumDatabase.getInstance().getUserManager().removeUser(user);
    }
}
