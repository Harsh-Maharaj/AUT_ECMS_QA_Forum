package aut_ecms_qa_forum;

/**
 * The Answer class represents an answer to a question in the forum.
 * It includes the answer's id, content, author, and the question it belongs to.
 * 
 * @author Harsh & Dillan
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Answer {
    private int id; // Unique identifier for the answer
    private String content; // Content of the answer
    private User author; // Author of the answer
    private Question question; // The question to which this answer belongs

    /**
     * Constructor to create an Answer with an id, content, author, and question.
     * 
     * @param id The unique identifier for the answer
     * @param content The content of the answer
     * @param author The author of the answer
     * @param question The question to which this answer belongs
     */
    public Answer(int id, String content, User author, Question question) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.question = question;
    }

    /**
     * Constructor to create an Answer with content, author, and question.
     * The id is not included and can be set later.
     * 
     * @param content The content of the answer
     * @param author The author of the answer
     * @param question The question to which this answer belongs
     */
    public Answer(String content, User author, Question question) {
        this.content = content;
        this.author = author;
        this.question = question;
    }

    /**
     * Gets the unique identifier for the answer.
     * 
     * @return The unique identifier for the answer
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the content of the answer.
     * 
     * @return The content of the answer
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the answer.
     * 
     * @param content The new content of the answer
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the author of the answer.
     * 
     * @return The author of the answer
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Gets the question to which this answer belongs.
     * 
     * @return The question to which this answer belongs
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Returns a string representation of the answer.
     * The string representation is the content of the answer.
     * 
     * @return The content of the answer
     */
    @Override
    public String toString() {
        return content;
    }
}
