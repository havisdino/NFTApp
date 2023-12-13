package hust.ite6.group11.main.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NFTApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("src/hust/ite6/group11/main/gui/NTFApp.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("NTF Board");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
