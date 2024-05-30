/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package aut_ecms_qa_forum;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AnswerTest {
    private Answer answer;
    private User user;
    private Question question;

    @Before
    public void setUp() {
        user = new User("user", "password");
        question = new Question("Sample Question", "This is a sample question content.", user);
        answer = new Answer("Sample Answer", user, question);
    }

    @Test
    public void testGetContent() {
        assertEquals("Sample Answer", answer.getContent());
    }

    @Test
    public void testSetContent() {
        answer.setContent("New Answer Content");
        assertEquals("New Answer Content", answer.getContent());
    }

    @Test
    public void testGetAuthor() {
        assertEquals(user, answer.getAuthor());
    }

    @Test
    public void testGetQuestion() {
        assertEquals(question, answer.getQuestion());
    }

    @Test
    public void testToString() {
        assertEquals("Sample Answer", answer.toString());
    }
}
