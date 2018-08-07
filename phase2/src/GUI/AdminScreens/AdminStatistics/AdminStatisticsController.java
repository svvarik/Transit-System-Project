package GUI.AdminScreens.AdminStatistics;
import Data.TransitData;
import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import static java.lang.Integer.parseInt;

public class AdminStatisticsController extends GeneralControllerScreen {

        @FXML
        Button backButton;

        @FXML
        private AreaChart OverallRevenueAreaChart;

        @FXML
        private BarChart OverallRevenueBarChart;

        @FXML
        private CategoryAxis x;

        @FXML
        private Button monthly;

        @FXML
        private Button Yearly;

        @FXML
        private Button thisMonth;

        @FXML
        private NumberAxis y;

        private ObservableList<String> monthNames = FXCollections.observableArrayList();

        private ObservableList<String> yearNames = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

    // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"));
        yearNames.addAll(Arrays.asList("2016", "2017", "2018"));
        OverallRevenueBarChart.setVisible(true);
        OverallRevenueAreaChart.setVisible(false);

}

    public void graphMonthlyRevenue() {
        OverallRevenueBarChart.getData().clear();
        OverallRevenueBarChart.setVisible(true);
        OverallRevenueAreaChart.setVisible(false);

        XYChart.Series series = new XYChart.Series<>();
        x.setCategories(monthNames);
        // Create a XYChart.Data object for each month. Add it to the series.
        TransitData td = new TransitData(this.getTransitSystem());
        for (int i = 0; i < monthNames.size(); i++) {
            double fare = td.getMonthlyFareAmount(i, Calendar.YEAR);
            series.getData().add(new XYChart.Data(monthNames.get(i), fare));
        }

        OverallRevenueBarChart.setLegendVisible(false);
        OverallRevenueBarChart.getData().add(series);
    }

    public void graphYearlyRevenue() {
        OverallRevenueBarChart.getData().clear();
        OverallRevenueBarChart.setVisible(true);
        OverallRevenueAreaChart.setVisible(false);

        XYChart.Series series = new XYChart.Series<>();
        x.setCategories(yearNames);
        TransitData td = new TransitData(this.getTransitSystem());
        // Create a XYChart.Data object for each year. Add it to the series.
        for (int i = 0; i < yearNames.size(); i++) {
            double fare = td.getYearlyFareAmount(Integer.parseInt(yearNames.get(i)));
            series.getData().add(new XYChart.Data(yearNames.get(i), fare));
        }

        OverallRevenueBarChart.setLegendVisible(false);
        OverallRevenueBarChart.getData().add(series);
    }

    public void graphCurrentMonthRevenue() {
        OverallRevenueBarChart.getData().clear();
        OverallRevenueAreaChart.getData().clear();
        OverallRevenueBarChart.setVisible(false);
        OverallRevenueAreaChart.setVisible(true);


        TransitData td = new TransitData(this.getTransitSystem());
        XYChart.Series series = new XYChart.Series<>();
        // Create a XYChart.Data object for each day of the month. Add it to the series.
        for (int i = 1; i <= 31; i++) {
            double fare = td.getDailyFare(i, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.YEAR));
            String tempI = Integer.toString(i);
            series.getData().add(new XYChart.Data(tempI, fare));
        }

        OverallRevenueAreaChart.setLegendVisible(false);
        OverallRevenueBarChart.setLegendVisible(false);
        OverallRevenueAreaChart.getData().add(series);
    }

    public void handleBackButton(ActionEvent e) {
        ControllerHelper ch = new ControllerHelper();
        String goingTo = "/GUI/AdminScreens/AdminMainScene.fxml";
        ch.openSameWindow("/GUI/AdminScreens/AdminMainScene.fxml", this.getTransitSystem(), e);
    }
}
