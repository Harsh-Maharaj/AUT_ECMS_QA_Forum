
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionPanel extends JPanel {
    private DefaultListModel<Question> listModel;
    private JList<Question> questionList;

    public QuestionPanel() {
        setLayout(new BorderLayout(10, 10));
        listModel = new DefaultListModel<>();
        questionList = new JList<>(listModel);
        questionList.setCellRenderer(new QuestionListCellRenderer());
        add(new JScrollPane(questionList), BorderLayout.CENTER);
        loadQuestions();
    }

    public void loadQuestions() {
        listModel.clear();
        List<Question> questions = ForumDatabase.getInstance().getQuestionManager().getAllQuestions();
        for (Question question : questions) {
            listModel.addElement(question);
        }
    }

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

    public Question getSelectedQuestion() {
        return questionList.getSelectedValue();
    }

    public void addQuestionSelectionListener(ListSelectionListener listener) {
        questionList.addListSelectionListener(listener);
    }

    private class QuestionListCellRenderer extends JPanel implements ListCellRenderer<Question> {
        private JLabel titleLabel;
        private JLabel authorLabel;

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
