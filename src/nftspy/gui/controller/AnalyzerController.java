package nftspy.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Label;

public class AnalyzerController extends Controller {

    @FXML
    private Label correlationLabel;

    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private Label postNumberLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private ScatterChart<?, ?> scatterChart;

    @Override
    void onAnalyzerButtonClicked(ActionEvent event) {}
}
