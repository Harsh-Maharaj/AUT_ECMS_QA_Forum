package aut_ecms_qa_forum;

/**
 * The Question class represents a question in the forum.
 * It includes the question's id, title, content, author, and a list of answers.
 * 
 * @author Harsh & Dillan
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int id; // Unique identifier for the question
    private String title; // Title of the question
    private String content; // Content of the question
    private User author; // Author of the question
    private List<Answer> answers; // List of answers to the question

    /**
     * Constructs a Question with an id, title, content, and author.
     * 
     * @param id The unique identifier for the question
     * @param title The title of the question
     * @param content The content of the question
     * @param author The author of the question
     */
    public Question(int id, String title, String content, User author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.answers = new ArrayList<>();
    }

    /**
     * Constructs a Question with a title, content, and author.
     * The id is not included and can be set later.
     * 
     * @param title The title of the question
     * @param content The content of the question
     * @param author The author of the question
     */
    public Question(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.answers = new ArrayList<>();
    }

    /**
     * Gets the unique identifier for the question.
     * 
     * @return The unique identifier for the question
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the title of the question.
     * 
     * @return The title of the question
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the question.
     * 
     * @param title The new title of the question
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the content of the question.
     * 
     * @return The content of the question
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the question.
     * 
     * @param content The new content of the question
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the author of the question.
     * 
     * @return The author of the question
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Gets the list of answers to the question.
     * 
     * @return The list of answers to the question
     */
    public List<Answer> getAnswers() {
        return answers;
    }

    /**
     * Adds a new answer to the list of answers for the question.
     * 
     * @param newAnswer The new answer to be added
     */
    public void addAnswer(Answer newAnswer) {
        answers.add(newAnswer);
    }

    /**
     * Returns a string representation of the question.
     * The string representation is the title of the question.
     * 
     * @return The title of the question
     */
    @Override
    public String toString() {
        return title;
    }
}
