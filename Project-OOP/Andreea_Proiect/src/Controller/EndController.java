package Controller;

import Model.Quiz;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class EndController {
    @FXML
    private Label result;
    @FXML
    private Button restartButton;
    @FXML
    private Button exitButton;
    @FXML
    private Text correct;
    @FXML
    private Text wrong;

    /**
     * seteaza view-ul pentru End Scene
     */
    public void setScene(Quiz quiz) {
        if (quiz.getCorrectAnswers() >= 22) {
            result.setText("Reusit!");
            result.setTextFill(Paint.valueOf("#019f11"));
        } else {
            result.setText("Picat!");
            result.setTextFill(Paint.valueOf("#ac0404"));
        }
        correct.setText("Raspunsuri corecte: " + quiz.getCorrectAnswers());
        wrong.setText("Raspunsuri gresite: " + quiz.getFalseAnswers());
    }

    /**
     * Creeaza un nou chestionar
     */
    public void restartButtonAction() {
        Stage stage;
        stage = (Stage) restartButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/quiz.fxml"));
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        QuizController quizController = loader.getController();
        quizController.firstQuestion();
        stage.setTitle("Chestionar " + Quiz.getID());
        stage.show();
    }

    /**
     * Aplicatia se inchide
     */
    public void exitButtonAction() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
