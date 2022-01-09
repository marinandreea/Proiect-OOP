package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Clasa pentru chestionar
 */
public class Quiz {
    private List<Question> questions;
    private int correctAnswers;
    private int falseAnswers;
    private static int ID = 0;

    /**
     * Constructor pentru clasa
     * Initializarea paramentrilor cu valorile din fisier
     */
    public Quiz() {
        this.questions = new ArrayList<>();
        this.correctAnswers = 0;
        this.falseAnswers = 0;
        initializeQuestions();
        ID++;
    }

    /**
     * Getter pentru intrebarile din chestionar
     *
     * @return Lista de intrebari din chestionar
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Getter pentru numarul de raspunsuri corecte
     *
     * @return Numarul de raspunsuri corecte
     */
    public int getCorrectAnswers() {
        return correctAnswers;
    }

    /**
     * Setter pentru numarul de raspunsuri corecte
     *
     * @param correctAnswers contine numarul de raspunsuri corecte
     */
    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    /**
     * Getter pentru numarul de raspunsuri gresite
     *
     * @return Numarul de raspunsuri gresite
     */
    public int getFalseAnswers() {
        return falseAnswers;
    }

    /**
     * Setter pentru numarul de raspunsuri gresite
     *
     * @param falseAnswers contine numarul de raspunsuri gresite
     */
    public void setFalseAnswers(int falseAnswers) {
        this.falseAnswers = falseAnswers;
    }


    /**
     * Getter pentru ID-ul chestionarului
     *
     * @return ID-ul chestionarului
     */
    public static int getID() {
        return ID;
    }

    /**
     * Initializeaza lista de intrebari cu date din fisier
     */
    public void initializeQuestions() {
        File file = new File("C:\\Users\\Andreea\\OneDrive\\Desktop\\Project-OOP\\Andreea_Proiect\\Question.txt");
        Map<String, Integer> map = new HashMap<>();  // folosesc Map pentru a nu se repeta intrebarile

        for (int i = 0; i < 26; ++i)  // chestionarul contine 26 de intrebari
        {
            String s = null;
            do {
                try {
                    s = choose(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } while (map.containsKey(s));
            map.put(s, 0);

            // stochez intrebarea in question
            if (s != null) {
                String[] line = s.split(";");
                Question question = new Question(line[0]);
                int nr = 0;
                int nrCorrect = Integer.parseInt(line[1]);
                while (nr < 3) {   // fiecare intrebare are 3 raspunsuri
                    if (nr < nrCorrect)
                        question.addCorrectAnswer(line[nr + 2]);
                    else
                        question.addFalseAnswer(line[nr + 2]);
                    nr++;
                }
                questions.add(question);
            }
        }
    }

    /**
     * Citeste randuri aleatorii din fisierul Question.txt
     *
     * @param file este fisierul Question.txt
     * @return un rand din fisier
     * @throws FileNotFoundException daca fisierul nu poate fi accesat
     */
    public static String choose(File file) throws FileNotFoundException {
        String result = null;
        Random rand = new Random();
        int n = 0;
        for (Scanner sc = new Scanner(file); sc.hasNext(); ) {
            ++n;
            String line = sc.nextLine();
            if (rand.nextInt(n) == 0)
                result = line;
        }
        return result;
    }
}
