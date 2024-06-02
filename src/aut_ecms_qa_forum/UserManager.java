package aut_ecms_qa_forum;

import java.sql.*;
import java.util.ArrayList;

/**
 * The UserManager class provides methods to manage users in the forum.
 * It includes methods to add, remove, authenticate, and retrieve users.
 */
public class UserManager {

    /**
     * Adds a new user to the database.
     * 
     * @param user The user to be added
     * @throws SQLException If a database access error occurs
     */
    public void addUser(User user) throws SQLException {
        if (!userExists(user.getUsername())) {
            String sql = "INSERT INTO Users (username, password, isAdmin) VALUES (?, ?, ?)";
            try (Connection conn = DerbyDatabaseManager.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getPassword());
                pstmt.setBoolean(3, user instanceof Admin);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Removes a user from the database.
     * 
     * @param user The user to be removed
     * @throws SQLException If a database access error occurs
     */
    public void removeUser(User user) throws SQLException {
        String sql = "DELETE FROM Users WHERE username = ?";
        try (Connection conn = DerbyDatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Authenticates a user by checking the username and password.
     * 
     * @param username The username of the user
     * @param password The password of the user
     * @return The authenticated user, or null if authentication fails
     */
    public User authenticate(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection conn = DerbyDatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                boolean isAdmin = rs.getBoolean("isAdmin");
                return isAdmin ? new Admin(username, password) : new User(username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves a user by their username.
     * 
     * @param username The username of the user
     * @return The user with the specified username, or null if no such user exists
     */
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?";
        try (Connection conn = DerbyDatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");
                boolean isAdmin = rs.getBoolean("isAdmin");
                return isAdmin ? new Admin(username, password) : new User(username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if a user with the specified username exists in the database.
     * 
     * @param username The username to check
     * @return True if the user exists, false otherwise
     * @throws SQLException If a database access error occurs
     */
    public boolean userExists(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Users WHERE username = ?";
        try (Connection conn = DerbyDatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    /**
     * Retrieves all users from the database.
     * 
     * @return A list of all users
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (Connection conn = DerbyDatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                boolean isAdmin = rs.getBoolean("isAdmin");
                users.add(isAdmin ? new Admin(username, password) : new User(username, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
