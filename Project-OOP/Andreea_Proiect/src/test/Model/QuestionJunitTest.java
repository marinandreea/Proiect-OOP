package Model;

import Model.Question;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class QuestionJunitTest extends TestCase {
    Question question;

    @BeforeEach
    public void setUp() {
        question = new Question("What is 2+2?");

    }

    @Test
    void testAddCorrectAnswerPositiv() {
        question.addCorrectAnswer("2");
        question.addCorrectAnswer("2.0");
        assertEquals(question.getCorrectAnswer().get((0)), "2");
        assertEquals(question.getCorrectAnswer().get((1)), "2.0");
    }

    @Test
    void testAddCorrectAnswerNegativ() {
        question.addCorrectAnswer("2");
        question.addCorrectAnswer("2.0");
        assertNotEquals(question.getCorrectAnswer().get((0)), "1");
        assertNotEquals(question.getCorrectAnswer().get((1)), "2.3");
    }

    @Test
    void testAddFalseAnswerPositiv() {
        question.addFalseAnswer("5");
        question.addFalseAnswer("6");
        assertEquals(question.getFalseAnswer().get((0)), "5");
        assertEquals(question.getFalseAnswer().get((1)), "6");
    }

    @Test
    void testAddFalseAnswerNegativ() {
        question.addFalseAnswer("5");
        question.addFalseAnswer("6");
        assertNotEquals(question.getFalseAnswer().get((0)), "6");
        assertNotEquals(question.getFalseAnswer().get((1)), "5");
    }

}