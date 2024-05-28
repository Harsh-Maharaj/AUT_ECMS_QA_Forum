
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QAndAForum extends JFrame {
    private User currentUser;
    private QuestionPanel questionPanel;
    private AnswerPanel answerPanel;
    private JButton addQuestionButton;
    private JButton addAnswerButton;
    private JButton removeQuestionButton;
    private JButton homeButton;    // Home button to see all questions
    private JButton logoutButton;  // Logout button
    private JTextField searchField;
    private JButton searchButton;
    private JFrame loginFrame;     // Reference to the login frame

    public QAndAForum(User user, JFrame loginFrame) {
        this.currentUser = user;
        this.loginFrame = loginFrame;  // Set the reference to the login frame

        setTitle("AUT Q/A Forum");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Header
        JLabel headerLabel = new JLabel("AUT Q/A Forum", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 28));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(headerLabel, BorderLayout.NORTH);

        // Question Panel
        questionPanel = new QuestionPanel();
        questionPanel.setBorder(BorderFactory.createTitledBorder("Questions"));
        add(new JScrollPane(questionPanel), BorderLayout.CENTER);

        // Answer Panel
        answerPanel = new AnswerPanel();
        answerPanel.setPreferredSize(new Dimension(300, getHeight()));
        answerPanel.setBorder(BorderFactory.createTitledBorder("Answers"));
        add(new JScrollPane(answerPanel), BorderLayout.EAST);

        // Search Panel
        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        searchField = new JTextField();
        searchButton = new JButton("Search");
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        addQuestionButton = new JButton("Add Question");
        addAnswerButton = new JButton("Add Answer");
        removeQuestionButton = new JButton("Remove Question");
        homeButton = new JButton("Home");    // Home button
        logoutButton = new JButton("Logout");  // Logout button

        buttonPanel.add(addQuestionButton);
        buttonPanel.add(addAnswerButton);
        buttonPanel.add(removeQuestionButton);
        buttonPanel.add(homeButton);
        buttonPanel.add(logoutButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                questionPanel.loadQuestions();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                loginFrame.setVisible(true);
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
