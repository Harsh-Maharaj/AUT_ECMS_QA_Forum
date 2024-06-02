/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aut_ecms_qa_forum;


/**
 * @author Harsh & Dillan
 */

import java.sql.SQLException;

/**
 * The ForumDatabase class is a singleton that manages the UserManager, QuestionManager,
 * and AnswerManager instances. It initializes the database and pre-loads some users.
 * 
 * @author Harsh
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class ForumDatabase {
    private static ForumDatabase instance; // Singleton instance of the ForumDatabase
    private UserManager userManager; // Manager for user-related operations
    private QuestionManager questionManager; // Manager for question-related operations
    private AnswerManager answerManager; // Manager for answer-related operations

    /**
     * Private constructor to prevent direct instantiation.
     * Initializes the managers and the database, and pre-loads some users.
     */
    private ForumDatabase() {
        userManager = new UserManager();
        questionManager = new QuestionManager();
        answerManager = new AnswerManager();

        DerbyDatabaseManager.initializeDatabase();

        initializeUsers();
    }

    /**
     * Gets the singleton instance of the ForumDatabase.
     * 
     * @return The singleton instance of the ForumDatabase
     */
    public static synchronized ForumDatabase getInstance() {
        if (instance == null) {
            instance = new ForumDatabase();
        }
        return instance;
    }

    /**
     * Initializes some default users in the database.
     * Adds an admin and a regular user if they do not already exist.
     */
    private void initializeUsers() {
        try {
            if (!userManager.userExists("admin")) {
                userManager.addUser(new Admin("admin", "adminpass"));
            }
            if (!userManager.userExists("user")) {
                userManager.addUser(new User("user", "userpass"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the UserManager instance.
     * 
     * @return The UserManager instance
     */
    public UserManager getUserManager() {
        return userManager;
    }

    /**
     * Gets the QuestionManager instance.
     * 
     * @return The QuestionManager instance
     */
    public QuestionManager getQuestionManager() {
        return questionManager;
    }

    /**
     * Gets the AnswerManager instance.
     * 
     * @return The AnswerManager instance
     */
    public AnswerManager getAnswerManager() {
        return answerManager;
    }
}
