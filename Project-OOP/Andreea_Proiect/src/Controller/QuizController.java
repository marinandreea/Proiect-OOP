package Controller;

import Model.Question;
import Model.Quiz;
import Model.Timer;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

/**
 * Clasa leaga backend de frontend pentru chestionar
 */
public class QuizController {
    @FXML
    private Button nextButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label question;
    @FXML
    private Label timerLabel;
    @FXML
    private Text nrCorrect;
    @FXML
    private Text nrFalse;
    @FXML
    private CheckBox checkA;
    @FXML
    private CheckBox checkB;
    @FXML
    private CheckBox checkC;
    private Quiz quiz;
    private int currentQuestion;
    private VBox vBox;
    private Timer timer;

    /**
     * Ofera vizualizarea pentru prima intrebare
     */
    public void firstQuestion() {
        quiz = new Quiz();
        currentQuestion = 0; // incepem de la prima intrebare

        // creare scrollPane care indica intrebarea curenta
        vBox = new VBox();
        for (int i = 1; i < 27; i++) {
            Text text = new Text("Intrebare " + i + '\n');
            text.setFill(i == currentQuestion + 1 ? Color.BLACK : Color.GRAY);
            vBox.getChildren().add(text);
        }
        scrollPane.setContent(vBox);
        nextQuestion();

        // creeaza timer-ul
        timer = new Timer();
        timer.start();
        timerLabel.setText(timer.getTime());
        PauseTransition delay = new PauseTransition(Duration.minutes(30));
        delay.setOnFinished(event -> setUpEndScene());
        delay.play();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> timerLabel.setText(timer.getTime())));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }


    /**
     * Actualizarea etichetelor si a butoanelor
     */
    public void nextQuestion() {
        nrCorrect.setText(String.valueOf(quiz.getCorrectAnswers()));
        nrFalse.setText(String.valueOf(quiz.getFalseAnswers()));

        // stabilim textul pentru intrebare
        String text = (currentQuestion + 1) + ") ";
        text = text + quiz.getQuestions().get(currentQuestion).getQuestion();
        question.setText(text);

        // stabilim cele 3 raspunsuri in ordine aleatoare
        List<String> answers = new ArrayList<>(quiz.getQuestions().get(currentQuestion).getCorrectAnswer());
        answers.addAll(quiz.getQuestions().get(currentQuestion).getFalseAnswer());
        Collections.shuffle(answers);
        checkA.setText(answers.get(0));
        checkB.setText(answers.get(1));
        checkC.setText(answers.get(2));

        // deselectam toate cele 3 raspunsuri
        checkA.setSelected(false);
        checkB.setSelected(false);
        checkC.setSelected(false);
    }

    /**
     * Verifica daca raspunsul este corect
     *
     * @return true, daca raspunsul este corect
     * false, in caz contrar
     */
    public boolean checkAnswers() {
        Question question = quiz.getQuestions().get(currentQuestion);
        if ((checkA.isSelected() && question.getFalseAnswer().contains(checkA.getText()))
                || (!checkA.isSelected() && question.getCorrectAnswer().contains(checkA.getText())))
            return false;
        if ((checkB.isSelected() && question.getFalseAnswer().contains(checkB.getText()))
                || (!checkB.isSelected() && question.getCorrectAnswer().contains(checkB.getText())))
            return false;
        return (!checkC.isSelected() || !question.getFalseAnswer().contains(checkC.getText()))
                && (checkC.isSelected() || !question.getCorrectAnswer().contains(checkC.getText()));
    }

    /**
     * Trecerea la urmatoarea intrebare
     */
    public void nextButtonAction() {
        // testeaza raspunsul
        if (checkAnswers())
            quiz.setCorrectAnswers(quiz.getCorrectAnswers() + 1);
        else
            quiz.setFalseAnswers(quiz.getFalseAnswers() + 1);
        // daca chestionarul se inchide
        if (currentQuestion == 25 || quiz.getFalseAnswers() == 5 || !timer.isAlive()) {
            timer.stopExecution();
            setUpEndScene();
            return;
        }
        // actualizeaza intrebarea din scrollPane
        Text text = (Text) vBox.getChildren().get(currentQuestion);
        text.setFill(Color.GRAY);
        currentQuestion++;
        text = (Text) vBox.getChildren().get(currentQuestion);
        text.setFill(Color.BLACK);
        // actualizeaza etichetele si butoanele ramase
        nextQuestion();
    }

    /**
     * setup End Scene
     */
    public void setUpEndScene() {
        Stage stage = (Stage) nextButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/end.fxml"));
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        EndController endController = loader.getController();
        endController.setScene(quiz);
        stage.show();
    }
}





