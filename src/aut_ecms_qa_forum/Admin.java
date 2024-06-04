package aut_ecms_qa_forum;

/**
 * @author Harsh & Dillan
 */

// This is the main admin class that has been updated to work with our GUI

import java.sql.SQLException;

// Check admin class


// Testing purposes for Branch merging
public class Admin extends User {
    /**
     * Constructor for the Admin class.
     * @param username The admin's username.
     * @param password The admin's password.
     */
    public Admin(String username, String password) {
        super(username, password);
    }

    /**
     * Adds a new question to the forum.
     * @param title The title of the question.
     * @param content The content of the question.
     * @param author The author of the question.
     */
    public void addQuestion(String title, String content, User author) {
        Question newQuestion = new Question(title, content, author);
        ForumDatabase.getInstance().getQuestionManager().addQuestion(newQuestion);
    }

    /**
     * Edits an existing question.
     * @param question The question to be edited.
     * @param newTitle The new title of the question.
     * @param newContent The new content of the question.
     */
    public void editQuestion(Question question, String newTitle, String newContent) {
        question.setTitle(newTitle);
        question.setContent(newContent);
    }

    /**
     * Deletes an existing question.
     * @param question The question to be deleted.
     */
    public void deleteQuestion(Question question) {
        ForumDatabase.getInstance().getQuestionManager().removeQuestion(question);
    }

    /**
     * Adds a new answer to a question.
     * @param content The content of the answer.
     * @param author The author of the answer.
     * @param question The question to which the answer belongs.
     */
    public void addAnswer(String content, User author, Question question) {
        Answer newAnswer = new Answer(content, author, question);
        ForumDatabase.getInstance().getAnswerManager().addAnswer(newAnswer);
    }

    /**
     * Edits an existing answer.
     * @param answer The answer to be edited.
     * @param newContent The new content of the answer.
     */
    public void editAnswer(Answer answer, String newContent) {
        answer.setContent(newContent);
    }

    /**
     * Deletes an existing answer.
     * @param answer The answer to be deleted.
     */
    public void deleteAnswer(Answer answer) {
        ForumDatabase.getInstance().getAnswerManager().removeAnswer(answer);
    }

    /**
     * Adds a new user to the forum.
     * @param username The username of the new user.
     * @param password The password of the new user.
     * @param isAdmin Whether the new user is an admin.
     * @throws SQLException If there is an error accessing the database.
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
     * Deletes an existing user from the forum.
     * @param user The user to be deleted.
     * @throws SQLException If there is an error accessing the database.
     */
    public void deleteUser(User user) throws SQLException {
        ForumDatabase.getInstance().getUserManager().removeUser(user);
    }
}
