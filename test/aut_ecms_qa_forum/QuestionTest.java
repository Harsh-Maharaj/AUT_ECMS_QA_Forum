/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package aut_ecms_qa_forum;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class QuestionTest {
    private Question question;
    private User user;

    @Before
    public void setUp() {
        user = new User("user", "password");
        question = new Question("Sample Question", "This is a sample question content.", user);
    }

    @Test
    public void testGetTitle() {
        assertEquals("Sample Question", question.getTitle());
    }

    @Test
    public void testSetTitle() {
        question.setTitle("New Title");
        assertEquals("New Title", question.getTitle());
    }

    @Test
    public void testGetContent() {
        assertEquals("This is a sample question content.", question.getContent());
    }

    @Test
    public void testSetContent() {
        question.setContent("New Content");
        assertEquals("New Content", question.getContent());
    }

    @Test
    public void testGetAuthor() {
        assertEquals(user, question.getAuthor());
    }

    @Test
    public void testToString() {
        assertEquals("Sample Question", question.toString());
    }
}
