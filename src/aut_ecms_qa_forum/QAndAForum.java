package aut_ecms_qa_forum;

/**
 * @author Harsh & Dillan
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The QAndAForum class provides the main graphical user interface for the Q/A forum.
 * It includes panels for displaying questions and answers, and buttons for various actions.
 */
public class QAndAForum extends JFrame {
    private User currentUser; // The current user interacting with the forum
    private QuestionPanel questionPanel; // Panel for displaying questions
    private AnswerPanel answerPanel; // Panel for displaying answers
    private JButton addQuestionButton; // Button to add a new question
    private JButton editQuestionButton; // Button to edit a selected question
    private JButton addAnswerButton; // Button to add a new answer
    private JButton removeQuestionButton; // Button to remove a selected question
    private JButton homeButton; // Button to reload all questions
    private JButton logoutButton; // Button to log out of the forum
    private JTextField searchField; // Text field for entering search queries
    private JButton searchButton; // Button to perform the search
    private JFrame loginFrame; // The login frame to return to upon logout

    /**
     * Constructs the QAndAForum with the specified user and login frame.
     * Initializes the GUI components and sets up the action listeners.
     * 
     * @param user The current user
     * @param loginFrame The login frame
     */
    public QAndAForum(User user, JFrame loginFrame) {
        this.currentUser = user;
        this.loginFrame = loginFrame;

        setTitle("AUT Q/A Forum");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Header label
        JLabel headerLabel = new JLabel("AUT Q/A Forum", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 28));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(headerLabel, BorderLayout.NORTH);

        // Question panel
        questionPanel = new QuestionPanel();
        questionPanel.setBorder(BorderFactory.createTitledBorder("Questions"));
        add(new JScrollPane(questionPanel), BorderLayout.CENTER);

        // Answer panel
        answerPanel = new AnswerPanel(currentUser);
        answerPanel.setPreferredSize(new Dimension(300, getHeight()));
        answerPanel.setBorder(BorderFactory.createTitledBorder("Answers"));
        add(new JScrollPane(answerPanel), BorderLayout.EAST);

        // Search panel
        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        searchField = new JTextField();
        searchButton = new JButton("Search");
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(searchPanel, BorderLayout.NORTH);

        // Search button action listener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                if (searchText != null && !searchText.trim().isEmpty()) {
                    questionPanel.searchQuestions(searchText);
                }
            }
        });

        // Button panel for various actions
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

        // Add question button action listener
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

        // Edit question button action listener
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

        // Add answer button action listener
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

        // Remove question button action listener
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

        // Home button action listener
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                questionPanel.loadQuestions();
            }
        });

        // Logout button action listener
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                loginFrame.setVisible(true);
            }
        });

        // Question selection listener to display answers for the selected question
        questionPanel.addQuestionSelectionListener(e -> {
            Question selectedQuestion = questionPanel.getSelectedQuestion();
            if (selectedQuestion != null) {
                answerPanel.displayAnswers(selectedQuestion);
            }
        });

        // Load all questions initially
        questionPanel.loadQuestions();
    }
}
