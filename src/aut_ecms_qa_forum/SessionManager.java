
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aut_ecms_qa_forum;

import java.util.Optional;

/**
 *
 * @author Harsh & Dillan
 */

/**
 * Manages user sessions, handling login and logout operations.
 */
public class SessionManager {
    private User currentUser; // Tracks the current user session

    /**
     * Authenticates and logs in a user.
     * @param username User's username
     * @param password User's password
     * @param userManager The userManager to handle authentication
     * @return true if login is successful, false otherwise
     */
    public boolean login(String username, String password, UserManager userManager) {
        Optional<User> user = userManager.authenticate(username, password);
        if (user.isPresent()) {
            this.currentUser = user.get(); // Set the current user if authentication succeeds
            return true;
        }
        return false; // Login failed
    }

    /**
     * Logs out the current user.
     */
    public void logout() {
        currentUser = null; // Clear the current user session
    }

    /**
     * Checks if any user is currently logged in.
     * @return true if a user is logged in, otherwise false
     */
    public boolean isLoggedIn() {
        return currentUser != null; // Check if there's an active user session
    }

    /**
     * Retrieves the currently logged-in user.
     * @return The current user, or null if no user is logged in
     */
    public User getCurrentUser() {
        return currentUser; // Return the current user
    }
}
