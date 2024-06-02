/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aut_ecms_qa_forum;

/**
 * The QuestionPanel class is a custom JPanel that displays a list of questions.
 * It provides functionalities to load, search, and select questions.
 * 
 * @author Harsh & Dillan
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionPanel extends JPanel {
    private DefaultListModel<Question> listModel; // Model to store the list of questions
    private JList<Question> questionList; // JList to display the questions

    /**
     * Constructs a QuestionPanel and initializes the GUI components.
     */
    public QuestionPanel() {
        setLayout(new BorderLayout(10, 10));
        listModel = new DefaultListModel<>();
        questionList = new JList<>(listModel);
        questionList.setCellRenderer(new QuestionListCellRenderer());
        add(new JScrollPane(questionList), BorderLayout.CENTER);
        loadQuestions();
    }

    /**
     * Loads all questions from the database and displays them in the list.
     */
    public void loadQuestions() {
        listModel.clear();
        List<Question> questions = ForumDatabase.getInstance().getQuestionManager().getAllQuestions();
        for (Question question : questions) {
            listModel.addElement(question);
        }
    }

    /**
     * Searches for questions containing the specified text in their title or content.
     * 
     * @param searchText The text to search for in the questions
     */
    public void searchQuestions(String searchText) {
        listModel.clear();
        List<Question> questions = ForumDatabase.getInstance().getQuestionManager().getAllQuestions();
        List<Question> filteredQuestions = questions.stream()
                .filter(q -> q.getTitle().toLowerCase().contains(searchText.toLowerCase()) || q.getContent().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
        for (Question question : filteredQuestions) {
            listModel.addElement(question);
        }
    }

    /**
     * Gets the currently selected question from the list.
     * 
     * @return The selected question, or null if no question is selected
     */
    public Question getSelectedQuestion() {
        return questionList.getSelectedValue();
    }

    /**
     * Adds a listener for selection events on the question list.
     * 
     * @param listener The listener to be added
     */
    public void addQuestionSelectionListener(ListSelectionListener listener) {
        questionList.addListSelectionListener(listener);
    }

    /**
     * Custom list cell renderer for displaying questions in the JList.
     */
    private class QuestionListCellRenderer extends JPanel implements ListCellRenderer<Question> {
        private JLabel titleLabel; // Label to display the question title
        private JLabel authorLabel; // Label to display the question author

        /**
         * Constructs a QuestionListCellRenderer.
         */
        public QuestionListCellRenderer() {
            setLayout(new BorderLayout(10, 10));
            setBorder(new EmptyBorder(10, 10, 10, 10));
            titleLabel = new JLabel();
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setFont(new Font("Serif", Font.BOLD, 16));
            authorLabel = new JLabel();
            authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
            authorLabel.setFont(new Font("Serif", Font.PLAIN, 12));
            add(titleLabel, BorderLayout.NORTH);
            add(authorLabel, BorderLayout.SOUTH);
            setBackground(Color.WHITE);
            setBorder(new LineBorder(Color.BLACK, 1, true));
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Question> list, Question value, int index, boolean isSelected, boolean cellHasFocus) {
            titleLabel.setText(value.getTitle());
            if (value.getAuthor() != null) {
                authorLabel.setText("by " + value.getAuthor().getUsername());
            } else {
                authorLabel.setText("by Unknown");
            }
            if (isSelected) {
                setBackground(Color.LIGHT_GRAY);
            } else {
                setBackground(Color.WHITE);
            }
            return this;
        }
    }
}
