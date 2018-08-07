package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.UserStatsScreen;

import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import java.util.Calendar;

public class ViewUserStatsSceneController extends GeneralControllerScreen{



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

    ControllerHelper ch = new ControllerHelper();

    public void handleBackButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/UserPackage/UserConfigPackage/UserScreen/ViewUserScene.fxml");
    }

    public void initialize() {
        series = new XYChart.Series();
        if (cardHolder != null) {
            System.out.println(cardHolder);
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

    public void setUpController(Object object){
        CardHolder c = (CardHolder) object;
        setCardHolder(c);
        initialize();
    }
}
