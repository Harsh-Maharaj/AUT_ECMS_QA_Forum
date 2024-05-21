
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aut_ecms_qa_forum;

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

    User() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    User(String username) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    // Method to add a question
    public void addQuestion(String title, String content, User author) {
        Question newQuestion = new Question(title, content, author);
        ForumDatabase.getInstance().getQuestionManager().addQuestion(newQuestion);
    }

    // Method to edit a question
    public void editQuestion(Question question, String newTitle, String newContent) {
        question.setTitle(newTitle);
        question.setContent(newContent);
    }

    // Method to add an answer
    public void addAnswer(String content, User author, Question question) {
        Answer newAnswer = new Answer(content, author, question);
        ForumDatabase.getInstance().getAnswerManager().addAnswer(newAnswer);
    }

    // Method to edit an answer
    public void editAnswer(Answer answer, String newContent) {
        answer.setContent(newContent);
    }

    // Method to delete an answer
    public void deleteAnswer(Answer answer) {
        ForumDatabase.getInstance().getAnswerManager().removeAnswer(answer);
    }

    // Method to add a user
    public void addUser(String username, String password, boolean isAdmin) {
        User newUser;
        if (isAdmin) {
            newUser = new Admin(username, password);
        } else {
            newUser = new User(username, password);
        }
        ForumDatabase.getInstance().getUserManager().addUser(newUser);
    }

    // Method to delete a user
    public void deleteUser(User user) {
        ForumDatabase.getInstance().getUserManager().removeUser(user);
    }

    void addQuestion(Question newQuestion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void addAnswer(Answer newAnswer, Question selectedQuestion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
