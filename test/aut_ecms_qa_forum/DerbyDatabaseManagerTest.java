/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package aut_ecms_qa_forum;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * The DerbyDatabaseManagerTest class provides unit tests for the DerbyDatabaseManager class.
 */
public class DerbyDatabaseManagerTest {

    /**
     * Sets up the test environment before the test class is executed.
     * Initializes the database.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        DerbyDatabaseManager.initializeDatabase();
    }

    /**
     * Cleans up the test environment after the test class has been executed.
     * Deletes any existing lock files.
     */
    @AfterClass
    public static void tearDownAfterClass() {
        DerbyDatabaseManager.deleteLockFiles();
    }

    /**
     * Tests the getConnection method of the DerbyDatabaseManager class.
     * Verifies that a database connection can be obtained.
     */
    @Test
    public void testGetConnection() {
        try (Connection conn = DerbyDatabaseManager.getConnection()) {
            assertNotNull(conn);
        } catch (SQLException e) {
            fail("Failed to get connection: " + e.getMessage());
        }
    }

    /**
     * Tests the initializeDatabase method of the DerbyDatabaseManager class.
     * Verifies that the database can be initialized.
     */
    @Test
    public void testInitializeDatabase() {
        try (Connection conn = DerbyDatabaseManager.getConnection()) {
            assertNotNull(conn);
        } catch (SQLException e) {
            fail("Failed to initialize database: " + e.getMessage());
        }
    }
}
