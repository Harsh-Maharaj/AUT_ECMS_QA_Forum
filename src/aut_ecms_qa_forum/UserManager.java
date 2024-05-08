
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aut_ecms_qa_forum;

/**
 *
 * @author Harsh& Dillan
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Manages user accounts and authentication for the AUT ECMS Q/A Forum.
 */
public class UserManager {
    private List<User> users = new ArrayList<>();

      /**
     * Initializes the UserManager with predefined users.
     */
    public UserManager() {
                // Add an admin user
        users.add(new AdminUser("admin", "adminpass") {
            @Override
            public void displayMainMenu() {
                // Placeholder implementation
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
         // Add a normal user
        users.add(new NormalUser("user", "userpass") {
            @Override
            public void displayMainMenu() {
                 // Placeholder implementation
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }
    
    /**
     * Attempts to authenticate a user with the provided username and password.
     * @param username The username to authenticate.
     * @param password The password to authenticate.
     * @return An Optional containing the authenticated User or an empty Optional if authentication fails.
     */

    public Optional<User> authenticate(String username, String password) {
        // Stream through users, filter by username and password, and return the first match
        return users.stream()
                    .filter(user -> user.getUsername().equals(username) && user.authenticate(password))
                    .findFirst();
    }
}
