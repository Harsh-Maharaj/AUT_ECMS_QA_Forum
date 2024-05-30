package aut_ecms_qa_forum;

/**
 *
 * @author Harsh & Dillan
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class Answer {
    private int id;
    private String content;
    private User author;
    private Question question;

    public Answer(int id, String content, User author, Question question) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.question = question;
    }

    public Answer(String content, User author, Question question) {
        this.content = content;
        this.author = author;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public Question getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        return content;
    }
}
