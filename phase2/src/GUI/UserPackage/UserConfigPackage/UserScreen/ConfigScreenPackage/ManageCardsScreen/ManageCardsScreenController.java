package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.ManageCardsScreen;

import FareSystem.Card;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import Main.TransitSystemInteractions;
import TransitUsers.CardHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageCardsScreenController extends GeneralControllerScreen{

    @FXML
    ListView listOfCards;

    @FXML
    Button backButton;

    @FXML
    Label userLabel;

    @FXML
    Button addCardButton;

    @FXML
    Button deleteCardButton;

    @FXML
    Button suspendCardButton;

    @FXML
    Button addValueButton;

    private CardHolder cardHolder;


    @FXML
    public void handleBackButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ViewUserScene.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTransitSystem(), e, this.cardHolder);
    }

    @FXML
    public void handleAddCardButton(ActionEvent e){
        this.getTransitSystemInteractions().addNewCard(cardHolder);
        this.listOfCards.setItems(this.cardHolder.getObservableCards());
    }

    @FXML
    public void handleDeleteCardButton(ActionEvent e) {

        String cardID = Integer.toString(((Card)
                this.listOfCards.getSelectionModel().getSelectedItem()).getCardID());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deleting Card");
        alert.setHeaderText("Are you sure you want to remove this card?");
        alert.setContentText("You are deleting card: " + cardID + ".");

        ButtonType buttonTypeOne = new ButtonType("Remove anyways");
        ButtonType buttonTypeTwo = new ButtonType("Yikes, no thanks!");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeOne) {
            if (this.listOfCards.getSelectionModel().getSelectedItem() != null) {
                this.getTransitSystemInteractions().removeCard(this.cardHolder, (Card)
                        this.listOfCards.getSelectionModel().getSelectedItem());
                this.listOfCards.setItems(this.cardHolder.getObservableCards());
            }
        } else if (result.isPresent() && result.get() == buttonTypeTwo) {
            alert.close();
        }
    }

    @FXML
    public void handleSuspendCardButton(ActionEvent e){

        String cardID = Integer.toString(((Card)
                this.listOfCards.getSelectionModel().getSelectedItem()).getCardID());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirm Suspending Card");
        alert.setHeaderText("Are you sure you want to suspend this card?");
        alert.setContentText("You are suspending card: " + cardID + ".");

        ButtonType buttonTypeOne = new ButtonType("Suspend anyways");
        ButtonType buttonTypeTwo = new ButtonType("Yikes, no thanks!");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeOne) {
            this.getTransitSystemInteractions().suspendCard((Card)
                    this.listOfCards.getSelectionModel().getSelectedItem());
            this.listOfCards.setItems(this.cardHolder.getObservableCards());
        } else if (result.isPresent() && result.get() == buttonTypeTwo) {
            alert.close();
        }
    }

    @FXML
    public void handleAddValueButton(ActionEvent e){
        Card selectedCard = (Card) this.listOfCards.getSelectionModel().getSelectedItem();
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageScreens/AddValueScreen/AddValueScreen.fxml";
        this.getControllerHelper().openNewWindow(dest, this.getTransitSystem(), "Add Balance", selectedCard);
    }
    public void setUpController(){}

    public void setUpController(Object obj) throws ClassCastException{
        this.cardHolder = (CardHolder) obj;
        this.userLabel.setText(this.cardHolder.toString());
        this.listOfCards.setItems(this.cardHolder.getObservableCards());
    }
}
