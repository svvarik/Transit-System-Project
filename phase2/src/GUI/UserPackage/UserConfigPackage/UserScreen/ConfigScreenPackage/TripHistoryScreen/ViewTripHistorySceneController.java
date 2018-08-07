package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.TripHistoryScreen;

import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import TransitUsers.Trip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewTripHistorySceneController extends GeneralControllerScreen implements Initializable {
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

    private CardHolder cardHolder;

    public void handleBackButton(ActionEvent event){
        //go back to previous screen
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(cardHolder != null) {
            table.setItems(cardHolder.getObservableTrip());
        }
    }

    public void setCardHolder(CardHolder c){
        cardHolder = c;
    }
}