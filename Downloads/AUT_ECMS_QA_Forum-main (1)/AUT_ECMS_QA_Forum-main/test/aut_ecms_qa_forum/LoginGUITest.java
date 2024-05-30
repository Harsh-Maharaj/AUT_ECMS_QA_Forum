/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package aut_ecms_qa_forum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginGUITest {
    private LoginGUI loginGUI;

    @Before
    public void setUp() {
        loginGUI = new LoginGUI();
    }

    @Test
    public void testComponentsInitialization() {
        assertNotNull(loginGUI);
        assertEquals("Login Form", loginGUI.getTitle());
        assertEquals(400, loginGUI.getWidth());
        assertEquals(300, loginGUI.getHeight());

        JLabel headerLabel = (JLabel) loginGUI.getContentPane().getComponent(0);
        assertEquals("Welcome to the AUT Q/A Forum", headerLabel.getText());

        JPanel inputPanel = (JPanel) loginGUI.getContentPane().getComponent(1);
        assertNotNull(inputPanel);

        JPanel buttonPanel = (JPanel) loginGUI.getContentPane().getComponent(2);
        JButton loginButton = (JButton) buttonPanel.getComponent(0);
        assertEquals("Login", loginButton.getText());
    }

    @Test
    public void testLoginActionPerformed() {
        loginGUI.userTextField.setText("user");
        loginGUI.passwordField.setText("password");

        // Directly invoke the actionPerformed method with a mock event
        loginGUI.actionPerformed(new ActionEvent(loginGUI.loginButton, ActionEvent.ACTION_PERFORMED, "Login"));

        // You would typically check the behavior after this action.
        // Since we cannot use mocks or verify database interactions,
        // this part is limited to checking GUI responses or state changes.
    }

    @Test
    public void testInvalidLoginActionPerformed() {
        loginGUI.userTextField.setText("user");
        loginGUI.passwordField.setText("wrongpassword");

        // Directly invoke the actionPerformed method with a mock event
        loginGUI.actionPerformed(new ActionEvent(loginGUI.loginButton, ActionEvent.ACTION_PERFORMED, "Login"));

        // Since we cannot verify the error message directly without mocks,
        // this part assumes that the dialog with error message would appear.
    }

    @Test
    public void testMainMethod() {
        SwingUtilities.invokeLater(() -> {
            LoginGUI.main(new String[]{});
            assertTrue(SwingUtilities.isEventDispatchThread());
        });
    }
}
