package aut_ecms_qa_forum;

// this is the main admin class that has been updated to work with our GUI

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
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

    // Method to delete a question
    public void deleteQuestion(Question question) {
        ForumDatabase.getInstance().getQuestionManager().removeQuestion(question);
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
}