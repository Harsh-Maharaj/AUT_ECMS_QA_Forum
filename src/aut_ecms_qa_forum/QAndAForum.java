package aut_ecms_qa_forum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QAndAForum extends JFrame {
    private User currentUser;
    private QuestionPanel questionPanel;
    private AnswerPanel answerPanel;
    private JButton addQuestionButton;
    private JButton editQuestionButton; // New edit button
    private JButton addAnswerButton;
    private JButton removeQuestionButton;
    private JButton homeButton;
    private JButton logoutButton;
    private JTextField searchField;
    private JButton searchButton;
    private JFrame loginFrame;

    public QAndAForum(User user, JFrame loginFrame) {
        this.currentUser = user;
        this.loginFrame = loginFrame;

        setTitle("AUT Q/A Forum");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel headerLabel = new JLabel("AUT Q/A Forum", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 28));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(headerLabel, BorderLayout.NORTH);

        questionPanel = new QuestionPanel();
        questionPanel.setBorder(BorderFactory.createTitledBorder("Questions"));
        add(new JScrollPane(questionPanel), BorderLayout.CENTER);

        answerPanel = new AnswerPanel();
        answerPanel.setPreferredSize(new Dimension(300, getHeight()));
        answerPanel.setBorder(BorderFactory.createTitledBorder("Answers"));
        add(new JScrollPane(answerPanel), BorderLayout.EAST);

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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        addQuestionButton = new JButton("Add Question");
        editQuestionButton = new JButton("Edit Question"); // New edit button
        addAnswerButton = new JButton("Add Answer");
        removeQuestionButton = new JButton("Remove Question");
        homeButton = new JButton("Home");
        logoutButton = new JButton("Logout");

        buttonPanel.add(addQuestionButton);
        buttonPanel.add(editQuestionButton); // Add edit button to panel
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

        editQuestionButton.addActionListener(new ActionListener() { // Edit button action listener
            @Override
            public void actionPerformed(ActionEvent e) {
                Question selectedQuestion = questionPanel.getSelectedQuestion();
                if (selectedQuestion != null) {
                    String newTitle = JOptionPane.showInputDialog("Enter new question title:", selectedQuestion.getTitle());
                    String newContent = JOptionPane.showInputDialog("Enter new question content:", selectedQuestion.getContent());
                    if (newTitle != null && newContent != null && !newTitle.trim().isEmpty() && !newContent.trim().isEmpty()) {
                        currentUser.editQuestion(selectedQuestion, newTitle, newContent);
                        questionPanel.loadQuestions();
                    }
                } else {
                    JOptionPane.showMessageDialog(QAndAForum.this, "No question selected.", "Error", JOptionPane.ERROR_MESSAGE);
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
                    questionPanel.loadQuestions();
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
