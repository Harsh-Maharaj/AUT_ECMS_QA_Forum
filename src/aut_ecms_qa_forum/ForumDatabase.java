/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aut_ecms_qa_forum;

import java.sql.SQLException;

/**
 *
 * @author harsh
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class ForumDatabase {
    private static ForumDatabase instance;
    private UserManager userManager;
    private QuestionManager questionManager;
    private AnswerManager answerManager;

    private ForumDatabase() {
        userManager = new UserManager();
        questionManager = new QuestionManager();
        answerManager = new AnswerManager();

        DerbyDatabaseManager.initializeDatabase();

        initializeUsers();
    }

    public static synchronized ForumDatabase getInstance() {
        if (instance == null) {
            instance = new ForumDatabase();
        }
        return instance;
    }

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

    public UserManager getUserManager() {
        return userManager;
    }

    public QuestionManager getQuestionManager() {
        return questionManager;
    }

    public AnswerManager getAnswerManager() {
        return answerManager;
    }
}
