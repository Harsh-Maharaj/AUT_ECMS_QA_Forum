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

public class DerbyDatabaseManagerTest {

    @BeforeClass
    public static void setUpBeforeClass() {
        DerbyDatabaseManager.initializeDatabase();
    }

    @AfterClass
    public static void tearDownAfterClass() {
        DerbyDatabaseManager.deleteLockFiles();
    }

    @Test
    public void testGetConnection() {
        try (Connection conn = DerbyDatabaseManager.getConnection()) {
            assertNotNull(conn);
        } catch (SQLException e) {
            fail("Failed to get connection: " + e.getMessage());
        }
    }

    @Test
    public void testInitializeDatabase() {
        try (Connection conn = DerbyDatabaseManager.getConnection()) {
            assertNotNull(conn);
        } catch (SQLException e) {
            fail("Failed to initialize database: " + e.getMessage());
        }
    }
}
