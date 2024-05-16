
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aut_ecms_qa_forum;

/**
 *
 * @author Harsh & Dillan
 */



import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class QAndAForum extends JFrame {
    private User currentUser;
    private QuestionPanel questionPanel;
    private AnswerPanel answerPanel;
    private JButton addQuestionButton;
    private JButton addAnswerButton;
    private JButton removeQuestionButton;
    private JTextField searchField;
    private JButton searchButton;

    public QAndAForum(User user) {
        this.currentUser = user;
        setTitle("AUT Q/A Forum");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Header
        JLabel headerLabel = new JLabel("AUT Q/A Forum", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

        questionPanel = new QuestionPanel();
        add(questionPanel, BorderLayout.CENTER);

        answerPanel = new AnswerPanel();
        add(answerPanel, BorderLayout.EAST);

        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        searchField = new JTextField();
        searchButton = new JButton("Search");
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);
        add(searchPanel, BorderLayout.NORTH);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                if (searchText != null && !searchText.trim().isEmpty()) {
                    questionPanel.searchQuestions(searchText);
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        addQuestionButton = new JButton("Add Question");
        addAnswerButton = new JButton("Add Answer");
        removeQuestionButton = new JButton("Remove Question");

        buttonPanel.add(addQuestionButton);
        buttonPanel.add(addAnswerButton);
        buttonPanel.add(removeQuestionButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter question title:");
                String content = JOptionPane.showInputDialog("Enter question content:");
                if (title != null && content != null && !title.trim().isEmpty() && !content.trim().isEmpty()) {
                    currentUser.addQuestion(title, content, currentUser);
                    questionPanel.loadQuestions();
                }
            }
        });

        addAnswerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = JOptionPane.showInputDialog("Enter answer content:");
                Question selectedQuestion = questionPanel.getSelectedQuestion();
                if (selectedQuestion != null && content != null && !content.trim().isEmpty()) {
                    currentUser.addAnswer(content, currentUser, selectedQuestion);
                    questionPanel.loadQuestions();  // This assumes that answers are also reloaded; adjust if necessary
                }
            }
        });

        removeQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Question selectedQuestion = questionPanel.getSelectedQuestion();
                if (selectedQuestion != null) {
                    if (currentUser instanceof Admin) {
                        ((Admin) currentUser).deleteQuestion(selectedQuestion);
                        questionPanel.loadQuestions();
                    } else {
                        JOptionPane.showMessageDialog(QAndAForum.this, "Only admins can remove questions.", "Access Denied", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(QAndAForum.this, "No question selected.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        questionPanel.addQuestionSelectionListener(e -> {
            Question selectedQuestion = questionPanel.getSelectedQuestion();
            if (selectedQuestion != null) {
                answerPanel.displayAnswers(selectedQuestion);
            }
        });

        questionPanel.loadQuestions();
    }
}
