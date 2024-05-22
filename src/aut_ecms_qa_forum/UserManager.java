package aut_ecms_qa_forum;

import java.sql.*;
import java.util.ArrayList;

public class UserManager {
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
