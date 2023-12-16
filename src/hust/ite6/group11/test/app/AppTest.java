package hust.ite6.group11.test.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppTest extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/hust/ite6/group11/test/app/apptest.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("NFTSpy");
        stage.setResizable(false);
        stage.show();
    }
}
