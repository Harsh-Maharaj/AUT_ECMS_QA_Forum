/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package aut_ecms_qa_forum;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * The AnswerTest class provides unit tests for the Answer class.
 */
public class AnswerTest {
    private Answer answer; // Sample answer for testing
    private User user; // Sample user for testing
    private Question question; // Sample question for testing

    /**
     * Sets up the test environment before each test.
     * Initializes the user, question, and answer.
     */
    @Before
    public void setUp() {
        user = new User("user", "password");
        question = new Question("Sample Question", "This is a sample question content.", user);
        answer = new Answer("Sample Answer", user, question);
    }

    /**
     * Tests the getContent method of the Answer class.
     * Verifies that the content of the answer is retrieved correctly.
     */
    @Test
    public void testGetContent() {
        assertEquals("Sample Answer", answer.getContent());
    }

    /**
     * Tests the setContent method of the Answer class.
     * Verifies that the content of the answer is set correctly.
     */
    @Test
    public void testSetContent() {
        answer.setContent("New Answer Content");
        assertEquals("New Answer Content", answer.getContent());
    }

    /**
     * Tests the getAuthor method of the Answer class.
     * Verifies that the author of the answer is retrieved correctly.
     */
    @Test
    public void testGetAuthor() {
        assertEquals(user, answer.getAuthor());
    }

    /**
     * Tests the getQuestion method of the Answer class.
     * Verifies that the question associated with the answer is retrieved correctly.
     */
    @Test
    public void testGetQuestion() {
        assertEquals(question, answer.getQuestion());
    }

    /**
     * Tests the toString method of the Answer class.
     * Verifies that the string representation of the answer is correct.
     */
    @Test
    public void testToString() {
        assertEquals("Sample Answer", answer.toString());
    }
}
