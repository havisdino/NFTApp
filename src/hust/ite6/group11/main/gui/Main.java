package hust.ite6.group11.main.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private Map<String, Scene> sceneMap = new HashMap<>();

    public Main() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
        Scene scene = new Scene(root, 1000, 700);
    }
}
