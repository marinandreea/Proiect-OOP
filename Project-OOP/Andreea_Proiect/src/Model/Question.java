package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru obiecte de tip intrebare
 */
public class Question {
    private String question;
    private List<String> correctAnswer;
    private List<String> falseAnswer;

    /**
     * Constructor pentru clasa intrebare
     *
     * @param question pune intrebarea
     */
    public Question(String question) {
        this.question = question;
        this.correctAnswer = new ArrayList<>();
        this.falseAnswer = new ArrayList<>();
    }

    /**
     * Getter pentru intrebare
     *
     * @return intrebarea
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Getter pentru lista cu raspunsuri corecte
     *
     * @return lista cu raspunsuri corecte
     */
    public List<String> getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Getter pentru lista cu raspunsuri gresite
     *
     * @return lista cu raspunsuri gresite
     */
    public List<String> getFalseAnswer() {
        return falseAnswer;
    }

    /**
     * Adauga un raspuns corect la lista de raspunsuri corecte
     *
     * @param correctAnswer reprezinta un raspuns corect
     */
    public void addCorrectAnswer(String correctAnswer) {
        this.correctAnswer.add(correctAnswer);
    }

    /**
     * Adauga un raspuns gresit la lista de raspunsuri gresite
     *
     * @param falseAnswer reprezinta un raspuns gresit
     */
    public void addFalseAnswer(String falseAnswer) {
        this.falseAnswer.add(falseAnswer);
    }
}
