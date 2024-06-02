/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package aut_ecms_qa_forum;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.List;

/**
 * The AdminTest class provides unit tests for the Admin class.
 * It includes tests for adding, editing, and deleting questions and answers.
 */
public class AdminTest {
    private Admin admin; // Admin user for testing
    private User user; // Regular user for testing
    private Question question; // Sample question for testing
    private Answer answer; // Sample answer for testing

    /**
     * Sets up the test environment before each test.
     * Initializes the admin, user, question, and answer, and clears the database.
     */
    @Before
    public void setUp() {
        admin = new Admin("admin", "password");
        user = new User("user", "password");
        question = new Question("Sample Question", "This is a sample question content.", user);
        answer = new Answer("Sample Answer", user, question);
        clearDatabase();
    }

    /**
     * Cleans up the test environment after each test.
     * Clears the database.
     */
    @After
    public void tearDown() {
        clearDatabase();
    }

    /**
     * Clears the database by removing all questions.
     */
    private void clearDatabase() {
        List<Question> questions = ForumDatabase.getInstance().getQuestionManager().getAllQuestions();
        for (Question q : questions) {
            ForumDatabase.getInstance().getQuestionManager().removeQuestion(q);
        }
    }

    /**
     * Tests the addQuestion method of the Admin class.
     * Verifies that a new question is added to the database.
     */
    @Test
    public void testAddQuestion() {
        List<Question> questionsBefore = ForumDatabase.getInstance().getQuestionManager().getAllQuestions();
        System.out.println("Questions before adding new question: " + questionsBefore);

        admin.addQuestion("New Question", "New question content", user);

        List<Question> questionsAfter = ForumDatabase.getInstance().getQuestionManager().getAllQuestions();
        System.out.println("Questions after adding new question: " + questionsAfter);

        assertFalse(questionsAfter.isEmpty());
        assertEquals("New Question", questionsAfter.get(questionsAfter.size() - 1).getTitle());
    }

    /**
     * Tests the editQuestion method of the Admin class.
     * Verifies that an existing question is edited correctly.
     */
    @Test
    public void testEditQuestion() {
        admin.addQuestion(question.getTitle(), question.getContent(), user);
        Question addedQuestion = ForumDatabase.getInstance().getQuestionManager().getAllQuestions().get(0);
        admin.editQuestion(addedQuestion, "Edited Title", "Edited Content");
        assertEquals("Edited Title", addedQuestion.getTitle());
        assertEquals("Edited Content", addedQuestion.getContent());
    }

    /**
     * Tests the deleteQuestion method of the Admin class.
     * Verifies that a question is deleted from the database.
     */
    @Test
    public void testDeleteQuestion() {
        admin.addQuestion(question.getTitle(), question.getContent(), user);
        Question addedQuestion = ForumDatabase.getInstance().getQuestionManager().getAllQuestions().get(0);
        admin.deleteQuestion(addedQuestion);
        assertTrue(ForumDatabase.getInstance().getQuestionManager().getAllQuestions().isEmpty());
    }

    /**
     * Tests the addAnswer method of the Admin class.
     * Verifies that a new answer is added to a question.
     */
    @Test
    public void testAddAnswer() {
        admin.addQuestion(question.getTitle(), question.getContent(), user);
        Question addedQuestion = ForumDatabase.getInstance().getQuestionManager().getAllQuestions().get(0);
        admin.addAnswer("New Answer", user, addedQuestion);
        assertFalse(ForumDatabase.getInstance().getAnswerManager().getAnswersByQuestion(addedQuestion).isEmpty());
        assertEquals("New Answer", ForumDatabase.getInstance().getAnswerManager().getAnswersByQuestion(addedQuestion).get(0).getContent());
    }

    /**
     * Tests the editAnswer method of the Admin class.
     * Verifies that an existing answer is edited correctly.
     */
    @Test
    public void testEditAnswer() {
        admin.addQuestion(question.getTitle(), question.getContent(), user);
        Question addedQuestion = ForumDatabase.getInstance().getQuestionManager().getAllQuestions().get(0);
        admin.addAnswer(answer.getContent(), user, addedQuestion);
        Answer addedAnswer = ForumDatabase.getInstance().getAnswerManager().getAnswersByQuestion(addedQuestion).get(0);
        admin.editAnswer(addedAnswer, "Edited Answer Content");
        assertEquals("Edited Answer Content", addedAnswer.getContent());
    }
}
