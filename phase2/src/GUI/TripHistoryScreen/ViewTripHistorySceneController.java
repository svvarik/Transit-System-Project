package GUI.TripHistoryScreen;

import FareSystem.CardMachine;
import Stations.Station;
import TransitUsers.Trip;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewTripHistorySceneController implements Initializable {
    @FXML
    Button backButton;

    @FXML
    TableView<Trip> table;

    @FXML
    TableColumn<Trip, String> startDateColumn;

    @FXML
    TableColumn<Trip, String> startLocationColumn;

    @FXML
    TableColumn<Trip, String > endDateColumn;

    @FXML
    TableColumn<Trip, String> endLocationColumn;

    @FXML
    TableColumn<Trip, Double> fareColumn;

    ObservableList<Trip> tripData ;

    public void handleBackButton(ActionEvent event){
        //go back to previous screen
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setItems(tripData);
    }
}