/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aut_ecms_qa_forum;

/**
 *
 * @author Harsh & Dillan
 */

import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class AnswerPanel extends JPanel {
    private DefaultListModel<Answer> listModel;
    private JList<Answer> answerList;

    public AnswerPanel() {
        setLayout(new BorderLayout(10, 10));
        listModel = new DefaultListModel<>();
        answerList = new JList<>(listModel);
        answerList.setCellRenderer(new AnswerListCellRenderer());
        add(new JScrollPane(answerList), BorderLayout.CENTER);
    }

    public void displayAnswers(Question question) {
        listModel.clear();
        List<Answer> answers = ForumDatabase.getInstance().getAnswerManager().getAnswersByQuestion(question);
        for (Answer answer : answers) {
            listModel.addElement(answer);
        }
    }

    public Answer getSelectedAnswer() {
        return answerList.getSelectedValue();
    }

    private class AnswerListCellRenderer extends JPanel implements ListCellRenderer<Answer> {
        private JLabel label;

        public AnswerListCellRenderer() {
            setLayout(new BorderLayout(10, 10));
            setBorder(new EmptyBorder(10, 10, 10, 10));
            label = new JLabel();
            label.setHorizontalAlignment(SwingConstants.CENTER);
            add(label, BorderLayout.CENTER);
            setBackground(Color.WHITE);
            setBorder(new LineBorder(Color.BLACK, 1, true));
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Answer> list, Answer value, int index, boolean isSelected, boolean cellHasFocus) {
            label.setText("<html>Answer by " + value.getAuthor().getUsername() + ":<br>" + value.getContent() + "</html>");
            if (isSelected) {
                setBackground(Color.LIGHT_GRAY);
            } else {
                setBackground(Color.WHITE);
            }
            return this;
        }
    }
}
