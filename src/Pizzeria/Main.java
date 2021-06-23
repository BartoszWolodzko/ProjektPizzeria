package Pizzeria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Scene root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Windows/sample.fxml")));
        stage.setScene(root);
        stage.setTitle("Bardzo dobra Pizza");
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
