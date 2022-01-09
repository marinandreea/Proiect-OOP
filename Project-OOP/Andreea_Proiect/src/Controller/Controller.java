package Controller;

import Model.Quiz;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clasa leaga backend de frontend pentru inceputul chestionarului
 */
public class Controller {
    @FXML
    Button startButton;

    /**
     * Creeaza testul
     */
    public void startQuiz() {
        Stage stage = (Stage) startButton.getScene().getWindow();
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
}
