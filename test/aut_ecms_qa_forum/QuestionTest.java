/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package aut_ecms_qa_forum;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * The QuestionTest class provides unit tests for the Question class.
 */
public class QuestionTest {
    private Question question; // Sample question for testing
    private User user; // Sample user for testing

    /**
     * Sets up the test environment before each test.
     * Initializes the user and question.
     */
    @Before
    public void setUp() {
        user = new User("user", "password");
        question = new Question("Sample Question", "This is a sample question content.", user);
    }

    /**
     * Tests the getTitle method of the Question class.
     * Verifies that the title of the question is retrieved correctly.
     */
    @Test
    public void testGetTitle() {
        assertEquals("Sample Question", question.getTitle());
    }

    /**
     * Tests the setTitle method of the Question class.
     * Verifies that the title of the question is set correctly.
     */
    @Test
    public void testSetTitle() {
        question.setTitle("New Title");
        assertEquals("New Title", question.getTitle());
    }

    /**
     * Tests the getContent method of the Question class.
     * Verifies that the content of the question is retrieved correctly.
     */
    @Test
    public void testGetContent() {
        assertEquals("This is a sample question content.", question.getContent());
    }

    /**
     * Tests the setContent method of the Question class.
     * Verifies that the content of the question is set correctly.
     */
    @Test
    public void testSetContent() {
        question.setContent("New Content");
        assertEquals("New Content", question.getContent());
    }

    /**
     * Tests the getAuthor method of the Question class.
     * Verifies that the author of the question is retrieved correctly.
     */
    @Test
    public void testGetAuthor() {
        assertEquals(user, question.getAuthor());
    }

    /**
     * Tests the toString method of the Question class.
     * Verifies that the string representation of the question is correct.
     */
    @Test
    public void testToString() {
        assertEquals("Sample Question", question.toString());
    }
}
