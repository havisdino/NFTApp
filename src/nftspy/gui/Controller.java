package nftspy.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller {
    @FXML
    private TextField searchTextField;

    public Controller() {
        System.out.println(getClass().getSimpleName() + " has been created");
    }

    private void goTo(String fxmlPath, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        ((Node) event.getSource()).getScene().setRoot(root);
    }

    @FXML
    void onHomeButtonClicked(ActionEvent event) throws IOException {
        goTo("/resources/fxml/newsfeed.fxml", event);
    }

    @FXML
    void onAnalyzerButtonClicked(ActionEvent event) throws IOException {
        goTo("/resources/fxml/analyzer.fxml", event);
    }

    @FXML
    void onTrendingButtonClicked(ActionEvent event) throws IOException {
        goTo("/resources/fxml/trending.fxml", event);
    }

    @FXML
    void onSettingsButtonClicked(ActionEvent event) throws IOException {
        goTo("/resources/fxml/settings.fxml", event);
    }

    @FXML
    void onSearchButtonClicked(ActionEvent event) {
        if (!searchTextField.getText().equals("")) {
            System.out.println(searchTextField.getText());
        }
    }

    @FXML
    void onFetchButtonClicked(ActionEvent event) {

    }

}
