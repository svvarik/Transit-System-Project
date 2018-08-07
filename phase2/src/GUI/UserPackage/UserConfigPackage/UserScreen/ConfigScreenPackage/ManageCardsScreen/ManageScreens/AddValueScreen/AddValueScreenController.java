package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.ManageCardsScreen.ManageScreens.AddValueScreen;

import FareSystem.Card;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddValueScreenController extends GeneralControllerScreen implements Initializable{

    @FXML
    Button addFive;
    @FXML
    Button addTen;
    @FXML
    Button addFifteen;
    @FXML
    Button addTwenty;

    @FXML
    Button addValueButton;

    @FXML
    Label cardIDMessageLabel;

    @FXML
    TextField valueAmountTextField;

    private Card selectedCard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void handleAddFive(){
        this.getTransitSystemInteractions().addToBalance(this.selectedCard, 5);
        Stage stage = (Stage) addFive.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleAddTen(){
        this.getTransitSystemInteractions().addToBalance(this.selectedCard, 10);
        Stage stage = (Stage) addTen.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleAddFifteen(){
        this.getTransitSystemInteractions().addToBalance(this.selectedCard, 15);
        Stage stage = (Stage) addFifteen.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleAddTwenty(){
        this.getTransitSystemInteractions().addToBalance(this.selectedCard, 20);
        Stage stage = (Stage) addTwenty.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleAddValueButton() {

        if (valueAmountTextField.getText() != null) {


            try {
                int amount = Integer.parseInt(this.valueAmountTextField.getText());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                alert.setTitle("Confirm Value");
                alert.setHeaderText("Are you sure you want to add money to this card?");
                alert.setContentText("You are adding: " + this.valueAmountTextField.getText() + "to this Card");

                ButtonType buttonTypeOne = new ButtonType("Add away!");
                ButtonType buttonTypeTwo = new ButtonType("Yikes, no thanks!");

                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == buttonTypeOne) {
                    this.getTransitSystemInteractions().addToBalance(this.selectedCard, amount);
                } else {
                    alert.close();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Incorrect Value");
                alert.setHeaderText("Not able to add this value.");
                alert.setContentText("Currently, we are only able to add whole values to your card. No decimals please!");
                ButtonType buttonOne = new ButtonType("Okay");
                alert.getButtonTypes().setAll(buttonOne);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == buttonOne) {
                    alert.close();
                }
            }
        }
    }

    public void setUpController(){

    }

    public void setUpController(Object obj) throws ClassCastException{
        this.selectedCard = (Card) obj;
    }
}
