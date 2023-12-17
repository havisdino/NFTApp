package nftspy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import nftspy.exceptions.NullConfigException;
import nftspy.utils.Config;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        String resourcePath = "/resources/fxml/newsfeed.fxml";
        try {
            Config.getInstance();
        } catch (IOException | NullConfigException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setResizable(false);
            alert.setTitle("Warning!");
            alert.setHeaderText("No configuration found");
            alert.setContentText("Initialize the settings first");
            ButtonType choice = alert.showAndWait().orElse(ButtonType.CANCEL);
            if (choice == ButtonType.CANCEL) {
                System.exit(0);
            }
            resourcePath = "/resources/fxml/settings.fxml";
        }
        Parent root = FXMLLoader.load(getClass().getResource(resourcePath));

        stage.setResizable(false);
        stage.setTitle("NFTSpy");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
