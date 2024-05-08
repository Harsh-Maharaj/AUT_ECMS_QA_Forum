/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aut_ecms_qa_forum;

/**
 *
 * @author Harsh & Dillan
 */
public class App {
    public static void main(String[] args) {
        FilePersistence filePersistence = new FilePersistence(); // Create FilePersistence first since it is needed for QuestionBank
        QuestionBank questionBank = new QuestionBank(filePersistence); // Pass it to the QuestionBank constructor
        UserManager userManager = new UserManager();
        SessionManager sessionManager = new SessionManager();

        // If ConsoleUI's constructor expects these 4 arguments in this order and is implemented to handle them,
        // then this part of your code is correct.
        ConsoleUI ui = new ConsoleUI(questionBank, userManager, sessionManager, filePersistence);
        ui.start();
    }
}

// this here is also jus to test the git if its working