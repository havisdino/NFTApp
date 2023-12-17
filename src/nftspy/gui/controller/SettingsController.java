package nftspy.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nftspy.utils.Config;

import java.io.IOException;

public class SettingsController extends Controller {
    @FXML
    private Button analyzerButton;

    @FXML
    private Button trendingButton;

    @FXML
    private Button fetchButton;

    @FXML
    private Button newsfeedButton;

    @FXML
    private Button searchButton;



    @FXML
    private TextField chromeField;

    @FXML
    private TextField driverField;

    @FXML
    private ImageView notificationIcon;

    @FXML
    private Label notificationLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void onSaveButtonClicked(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String chromePath = chromeField.getText();
        String driverPath = driverField.getText();

        if (username.equals("") || password.equals("")
                || chromePath.equals("") || driverPath.equals("")) {
            notificationLabel.setText("Please complete all the fields");
            notificationLabel.setStyle("-fx-text-fill: -color-danger-7");
            notificationIcon.setImage(new Image("/resources/images/warning.png"));
        } else {
            try {
                Config.dumpConfig(username, password, chromePath, driverPath);
            } catch (IOException ignored) {}

            notificationLabel.setText("Settings saved");
            notificationLabel.setStyle("-fx-text-fill: -color-success-7");
            notificationIcon.setImage(new Image("/resources/images/done.png"));
        }
    }

    @Override
    void onSettingsButtonClicked(ActionEvent event) {}
}
