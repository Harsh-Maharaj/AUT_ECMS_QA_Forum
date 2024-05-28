package aut_ecms_qa_forum;

// this is the main admin class that has been updated to work with our GUI

import java.sql.SQLException;

// check admin class

//token for harsh github: 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

// testing for git purpose
public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    public void addQuestion(String title, String content, User author) {
        Question newQuestion = new Question(title, content, author);
        ForumDatabase.getInstance().getQuestionManager().addQuestion(newQuestion);
    }

    public void editQuestion(Question question, String newTitle, String newContent) {
        question.setTitle(newTitle);
        question.setContent(newContent);
    }

    public void deleteQuestion(Question question) {
        ForumDatabase.getInstance().getQuestionManager().removeQuestion(question);
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
