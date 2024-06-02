package aut_ecms_qa_forum;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The DerbyDatabaseManager class provides methods to manage the database connection
 * and initialize the database for the forum application.
 */
public class DerbyDatabaseManager {
    private static final String DB_URL = "jdbc:derby:./forumDB;create=true";
    private static final String DB_USERNAME = "admin";
    private static final String DB_PASSWORD = "user1"; // Password from the properties file

    static {
        try {
            // Load the embedded Derby driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a connection to the database.
     * 
     * @return A connection to the database
     * @throws SQLException If a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    /**
     * Deletes lock files if they exist to prevent database lock issues.
     */
    public static void deleteLockFiles() {
        File lockFile = new File("forumDB/db.lck");
        if (lockFile.exists()) {
            if (lockFile.delete()) {
                System.out.println("Deleted lock file: " + lockFile.getPath());
            } else {
                System.err.println("Failed to delete lock file: " + lockFile.getPath());
            }
        }
    }

    /**
     * Initializes the database by creating the necessary tables.
     */
    public static void initializeDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            // SQL statement to create the Users table
            String createUserTable = "CREATE TABLE Users (" +
                    "username VARCHAR(255) PRIMARY KEY, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "isAdmin BOOLEAN NOT NULL)";
            
            // SQL statement to create the Questions table
            String createQuestionTable = "CREATE TABLE Questions (" +
                    "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "title VARCHAR(255) NOT NULL, " +
                    "content VARCHAR(255) NOT NULL, " +
                    "author VARCHAR(255) NOT NULL, " +
                    "FOREIGN KEY (author) REFERENCES Users(username))";
            
            // SQL statement to create the Answers table
            String createAnswerTable = "CREATE TABLE Answers (" +
                    "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "content VARCHAR(255) NOT NULL, " +
                    "author VARCHAR(255) NOT NULL, " +
                    "questionId INT NOT NULL, " +
                    "FOREIGN KEY (author) REFERENCES Users(username), " +
                    "FOREIGN KEY (questionId) REFERENCES Questions(id))";

            // Execute the SQL statements to create tables
            stmt.execute(createUserTable);
            stmt.execute(createQuestionTable);
            stmt.execute(createAnswerTable);

            System.out.println("Database initialized successfully");
        } catch (SQLException e) {
            // Check if the exception indicates that the tables already exist
            if (!"X0Y32".equals(e.getSQLState())) {
                e.printStackTrace();
            } else {
                System.out.println("Tables already exist. Skipping creation.");
            }
        }
    }

    /**
     * Main method to delete lock files and initialize the database.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        deleteLockFiles();
        initializeDatabase();
        // Additional code for starting your application
    }
}
