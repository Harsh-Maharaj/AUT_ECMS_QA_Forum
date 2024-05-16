package aut_ecms_qa_forum;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import java.util.ArrayList;

import java.util.ArrayList;

public class AnswerManager {
    private ArrayList<Answer> answers = new ArrayList<>();

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
    }

    public ArrayList<Answer> getAnswersByQuestion(Question question) {
        ArrayList<Answer> result = new ArrayList<>();
        for (Answer answer : answers) {
            if (answer.getQuestion().equals(question)) {
                result.add(answer);
            }
        }
        return result;
    }
}

