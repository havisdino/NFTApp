package nftspy.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NewsfeedController extends Controller {
    @FXML
    private Label content;

    @FXML
    private Label hashtags;

    @FXML
    private Label title;

    @Override
    void onHomeButtonClicked(ActionEvent event) {}
}
