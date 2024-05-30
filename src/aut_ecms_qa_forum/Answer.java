package aut_ecms_qa_forum;

/**
 * This class represents an Answer in the Q&A forum.
 * Each answer is associated with a question and an author.
 * It contains the content of the answer, the ID, and references to the author and question.
 *
 * @author Harsh & Dillan
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Answer {
    // Unique identifier for the answer
    private int id;
    // The content of the answer
    private String content;
    // The author who provided the answer
    private User author;
    // The question to which this answer belongs
    private Question question;

    // Constructor initializing all fields
    public Answer(int id, String content, User author, Question question) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.question = question;
    }

    // Constructor initializing content, author, and question (ID is not set)
    public Answer(String content, User author, Question question) {
        this.content = content;
        this.author = author;
        this.question = question;
    }

    // Getter method for ID
    public int getId() {
        return id;
    }

    // Getter method for content
    public String getContent() {
        return content;
    }

    // Setter method for content
    public void setContent(String content) {
        this.content = content;
    }

    // Getter method for author
    public User getAuthor() {
        return author;
    }

    // Getter method for question
    public Question getQuestion() {
        return question;
    }

    // Override the toString method to return the content of the answer
    @Override
    public String toString() {
        return content;
    }
}
