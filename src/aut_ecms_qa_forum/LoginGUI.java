package aut_ecms_qa_forum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The LoginGUI class provides a graphical user interface for users to log in to the AUT Q/A Forum.
 * It includes fields for username and password, and a button to submit the login information.
 */
public class LoginGUI extends JFrame implements ActionListener {
    JTextField userTextField; // Text field for entering the username
    JPasswordField passwordField; // Password field for entering the password
    JButton loginButton; // Button to trigger the login action

    /**
     * Constructs the LoginGUI and initializes the GUI components.
     */
    public LoginGUI() {
        setTitle("Login Form");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Header label
        JLabel headerLabel = new JLabel("Welcome to the AUT Q/A Forum", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(headerLabel, BorderLayout.NORTH);

        // Input panel for username and password
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        add(inputPanel, BorderLayout.CENTER);

        // Username label and text field
        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(userLabel, gbc);

        userTextField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(userTextField, gbc);

        // Password label and text field
        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(passLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(passwordField, gbc);

        // Button panel for the login button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        buttonPanel.add(loginButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Handles the login button action. Authenticates the user and opens the forum if successful.
     * 
     * @param e The action event triggered by the login button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userTextField.getText();
        String password = new String(passwordField.getPassword());

        UserManager userManager = ForumDatabase.getInstance().getUserManager();
        User user = userManager.authenticate(username, password);

        if (user != null) {
            dispose();
            new QAndAForum(user, this).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Main method to launch the login GUI.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Delete any existing lock files before initializing the database
        DerbyDatabaseManager.deleteLockFiles();

        // Initialize the database before launching the GUI
        DerbyDatabaseManager.initializeDatabase();

        // Launch the GUI
        SwingUtilities.invokeLater(() -> new LoginGUI().setVisible(true));
    }
}
