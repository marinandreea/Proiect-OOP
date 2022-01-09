package Model;

import Model.Question;
import Model.Quiz;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class QuizJunitTest extends TestCase {
    protected Quiz quiz;

    @BeforeEach
    public void setUp() {
        quiz = new Quiz();
    }

    @Test
    public void testNumberOfAnswersPositiv() {
        assertEquals(quiz.getQuestions().get(0).getCorrectAnswer().size() + quiz.getQuestions().get(0).getFalseAnswer().size(), 3);
        assertEquals(quiz.getQuestions().get(3).getCorrectAnswer().size() + quiz.getQuestions().get(3).getFalseAnswer().size(), 3);
        assertEquals(quiz.getQuestions().get(5).getCorrectAnswer().size() + quiz.getQuestions().get(5).getFalseAnswer().size(), 3);
        assertEquals(quiz.getQuestions().get(20).getCorrectAnswer().size() + quiz.getQuestions().get(20).getFalseAnswer().size(), 3);
    }

    @Test
    public void testNumberOfAnswersNegativ() {
        assertNotEquals(quiz.getQuestions().get(1).getCorrectAnswer().size() + quiz.getQuestions().get(1).getFalseAnswer().size(), 1);
        assertNotEquals(quiz.getQuestions().get(10).getCorrectAnswer().size() + quiz.getQuestions().get(10).getFalseAnswer().size(), 2);
        assertNotEquals(quiz.getQuestions().get(15).getCorrectAnswer().size() + quiz.getQuestions().get(15).getFalseAnswer().size(), 4);
        assertNotEquals(quiz.getQuestions().get(22).getCorrectAnswer().size() + quiz.getQuestions().get(22).getFalseAnswer().size(), 0);
    }

    @Test
    public void testAppearanceOfQuestionPositiv() {
        Question question = quiz.getQuestions().get(1);
        assertTrue(quiz.getQuestions().contains(question));
        Question question1 = quiz.getQuestions().get(6);
        assertTrue(quiz.getQuestions().contains(question1));

    }

    @Test
    public void testAppearanceOfQuestionNegativ() {
        Question question = quiz.getQuestions().get(1);
        quiz.getQuestions().remove(question);
        assertFalse(quiz.getQuestions().contains(question));
        Question question1 = quiz.getQuestions().get(6);
        quiz.getQuestions().remove(question1);
        assertFalse(quiz.getQuestions().contains(question1));

    }
}