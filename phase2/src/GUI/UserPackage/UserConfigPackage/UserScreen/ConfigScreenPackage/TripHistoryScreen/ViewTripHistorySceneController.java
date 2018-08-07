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
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewTripHistorySceneController extends GeneralControllerScreen{
    @FXML
    Button backButton;

    @FXML
    TableView<Trip> table;

    @FXML
    TableColumn<Trip, String> sDateColumn;

    @FXML
    TableColumn<Trip, String> startLocationColumn;

    @FXML
    TableColumn<Trip, String > eDateColumn;

    @FXML
    TableColumn<Trip, String> endLocationColumn;

    @FXML
    TableColumn<Trip, Double> fareColumn;

    private CardHolder cardHolder;

    public void handleBackButton(ActionEvent event){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ViewUserScene.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTransitSystem(), event, this.cardHolder);
    }

    public void initialize() {
        if(cardHolder != null) {
            sDateColumn.setCellValueFactory(new PropertyValueFactory<Trip, String>("sDate"));
            eDateColumn.setCellValueFactory(new PropertyValueFactory<Trip, String>("eDate"));
            table.setItems(cardHolder.getObservableTrip());
        }
    }

    public void setCardHolder(CardHolder c){
        cardHolder = c;
    }

    public void setUpController(Object obj) throws ClassCastException{
        CardHolder ch = (CardHolder) obj;
        cardHolder = ch;
        initialize();
    }
}
