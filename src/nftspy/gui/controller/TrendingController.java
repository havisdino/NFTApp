package nftspy.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class TrendingController extends Controller {
    @FXML
    private Label firstHashTag;

    @FXML
    private ListView<?> listView;

    @FXML
    private Label secondHashTag;

    @FXML
    private Label thirdHashTag;

    @Override
    void onTrendingButtonClicked(ActionEvent event) {}
}
