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

public class AdminTest {
    private Admin admin;
    private User user;
    private Question question;
    private Answer answer;

    @Before
    public void setUp() {
        admin = new Admin("admin", "password");
        user = new User("user", "password");
        question = new Question("Sample Question", "This is a sample question content.", user);
        answer = new Answer("Sample Answer", user, question);
        clearDatabase();
    }

    @After
    public void tearDown() {
        clearDatabase();
    }

    private void clearDatabase() {
        List<Question> questions = ForumDatabase.getInstance().getQuestionManager().getAllQuestions();
        for (Question q : questions) {
            ForumDatabase.getInstance().getQuestionManager().removeQuestion(q);
        }
    }

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

    @Test
    public void testEditQuestion() {
        admin.addQuestion(question.getTitle(), question.getContent(), user);
        Question addedQuestion = ForumDatabase.getInstance().getQuestionManager().getAllQuestions().get(0);
        admin.editQuestion(addedQuestion, "Edited Title", "Edited Content");
        assertEquals("Edited Title", addedQuestion.getTitle());
        assertEquals("Edited Content", addedQuestion.getContent());
    }

    @Test
    public void testDeleteQuestion() {
        admin.addQuestion(question.getTitle(), question.getContent(), user);
        Question addedQuestion = ForumDatabase.getInstance().getQuestionManager().getAllQuestions().get(0);
        admin.deleteQuestion(addedQuestion);
        assertTrue(ForumDatabase.getInstance().getQuestionManager().getAllQuestions().isEmpty());
    }

    @Test
    public void testAddAnswer() {
        admin.addQuestion(question.getTitle(), question.getContent(), user);
        Question addedQuestion = ForumDatabase.getInstance().getQuestionManager().getAllQuestions().get(0);
        admin.addAnswer("New Answer", user, addedQuestion);
        assertFalse(ForumDatabase.getInstance().getAnswerManager().getAnswersByQuestion(addedQuestion).isEmpty());
        assertEquals("New Answer", ForumDatabase.getInstance().getAnswerManager().getAnswersByQuestion(addedQuestion).get(0).getContent());
    }

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
