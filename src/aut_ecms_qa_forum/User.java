
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aut_ecms_qa_forum;

/**
 *
 * @author Harsh & Dillan
 */
/**
 * Abstract class representing a user in the AUT ECMS Q/A Forum.
 * Defines common properties and behaviors for all user types.
 */
public abstract class User {
    protected String username; // User's username
    protected String password; // User's password
    protected String role; // Role of the user (e.g., "admin" or "user")

    
        /**
     * Constructs a User with specified username, password, and role.
     * @param username User's username
     * @param password User's password
     * @param role User's role
     */
    
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters
    public String getUsername() { return username; }
    public String getRole() { return role; }

    // Method to authenticate a user
    public boolean authenticate(String password) {
        return this.password.equals(password); // In a real system, use password hashing.
    }

    /**
     * Abstract method to display the main menu specific to the user role.
     * Subclasses are required to implement their version of this method.
     */
    public abstract void displayMainMenu();

    void displayMainMenu(ConsoleUI aThis) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
