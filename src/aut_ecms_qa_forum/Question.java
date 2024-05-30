package aut_ecms_qa_forum;

/**
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
    private int id;
    private String title;
    private String content;
    private User author;
    private List<Answer> answers;

    public Question(int id, String title, String content, User author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.answers = new ArrayList<>();
    }

    public Question(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.answers = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer newAnswer) {
        answers.add(newAnswer);
    }

    @Override
    public String toString() {
        return title;
    }
}
