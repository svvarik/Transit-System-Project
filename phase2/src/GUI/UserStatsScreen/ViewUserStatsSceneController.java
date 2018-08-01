package GUI.UserStatsScreen;

import Data.TransitData;
import GUI.ControllerHelper;
import Main.TransitSystem;
import TransitUsers.CardHolder;
import TransitUsers.Trip;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ViewUserStatsSceneController {

    @FXML
    Button backButton;

    @FXML
    LineChart<Number, Number> userFareGraph;

    @FXML
    NumberAxis xAxis;

    @FXML
    NumberAxis yAxis;

    private CardHolder cardHolder;

    private XYChart.Series series;

    public void handleBackButton(ActionEvent e){
        //go back to previous screen
    }

    public void initialize() {
        series = new XYChart.Series();
        for(int i=0; i<=31; i++){
            series.getData().add(new XYChart.Data(i, Math.random()*10));
        }
        if (cardHolder != null) {
            for(int i=1; i<=31; i++){
                series.getData().add(new XYChart.Data<>(i, cardHolder.getDailyFare(i, Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.YEAR))));
            }
        }
        userFareGraph.getData().add(series);
    }

    public void setCardHolder(CardHolder c){
        cardHolder = c;
    }
}
