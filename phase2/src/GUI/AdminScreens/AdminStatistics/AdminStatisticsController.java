package GUI.AdminScreens.AdminStatistics;
import Data.TransitData;
import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

public class AdminStatisticsController extends GeneralControllerScreen {

        @FXML
        Button backButton;

        @FXML
        private BarChart OverallRevenue;

        @FXML
        private CategoryAxis x;

        @FXML
        private NumberAxis y;

        private ObservableList<String> monthNames = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
    // Get an array with the English month names.
    String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
    // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"));

    // Assign the month names as categories for the horizontal axis.
        x.setCategories(monthNames);
        setOverallRevenueData();
        OverallRevenue.setMaxWidth(1000);
        backButton.setOnAction(this::handleBackButton);
}
    public void setOverallRevenueData(TransitData td) {

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Total Revenue YTD: " + td.totalFareAmount());
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthNames.size(); i++) {
            series.getData().add(new XYChart.Data(monthNames.get(i), td.totalFareAmount()));
        }
        OverallRevenue.getData().add(series);
    }

    public void setOverallRevenueData() {

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthNames.size(); i++) {
            series.getData().add(new XYChart.Data(monthNames.get(i), (i+1)*10));
        }
        OverallRevenue.setLegendVisible(false);
        OverallRevenue.getData().add(series);
    }

    private void handleBackButton(ActionEvent event) {
        ControllerHelper newControllerHelper = new ControllerHelper();
        String goingTo = "/GUI/AdminScreens/AdminMainScene.fxml";
        newControllerHelper.switchScreens(event, goingTo);
    }
}
