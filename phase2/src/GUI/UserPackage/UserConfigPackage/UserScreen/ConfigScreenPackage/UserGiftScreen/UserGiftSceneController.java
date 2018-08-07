package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.UserGiftScreen;

import FareSystem.Card;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserGiftSceneController extends GeneralControllerScreen implements Initializable {

    @FXML
    Label userGiftBalance;

    @FXML
    Button backButton;

    @FXML
    Button giftButton;

    @FXML
    TextField recipientEmailTextField;

    @FXML
    TextField moneyTextField;

    @FXML
    TextField cardID;

    @FXML
    TextField amount;

    @FXML
    Label giftSuccessLabel;

    @FXML
    Label addValueSuccessLabel;

    private CardHolder cardHolder;

    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleBackButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ViewUserScene.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTransitSystem(), e, this.cardHolder);
    }

    @FXML
    public void handleGiftButton(ActionEvent e){
        this.giftSuccessLabel.setText("");
        String recipient = this.recipientEmailTextField.getText();
        CardHolder cardHolderRecipient = this.getTransitSystem().getCardHolders().findCardHolder(recipient);
        if (cardHolderRecipient != null){
            try {
                int amount = Integer.parseInt(this.moneyTextField.getText());
                cardHolderRecipient.receiveMoney(amount);
                this.userGiftBalance.setText(Integer.toString(this.cardHolder.getBalance()));
                this.moneyTextField.clear();
                this.recipientEmailTextField.clear();
            } catch (NumberFormatException exception){
                this.giftSuccessLabel.setText("Whole numbers only please.");
            }
        } else {
            this.giftSuccessLabel.setText("This email is not valid.");
        }
    }

    @FXML
    public void handleAddValueButton(ActionEvent e){
        this.addValueSuccessLabel.setText("");
        try {
            int valueBeingAdded = Integer.parseInt(this.amount.getText());
            if (valueBeingAdded <= this.cardHolder.getBalance()) {
                Card cardBeingModified = this.getTransitSystem().getCardHolders().findCard(Integer.parseInt(this.cardID.getText()));
                if (cardBeingModified != null) {
                    cardBeingModified.addValue(valueBeingAdded);
                    this.cardHolder.setBalance(this.cardHolder.getBalance() - valueBeingAdded);
                    this.userGiftBalance.setText(Integer.toString(this.cardHolder.getBalance()));
                    this.amount.clear();
                    this.cardID.clear();
                } else {
                    this.addValueSuccessLabel.setText("Card doesn't exist.");
                }
            } else {
                this.addValueSuccessLabel.setText("Insufficient funds");
                this.amount.clear();
            }
        } catch (NumberFormatException nfe){
            this.addValueSuccessLabel.setText("Whole numbers only please.");
        }
    }

    public void setUpController(){}

    public void setUpController(Object obj) throws ClassCastException{
        CardHolder ch = (CardHolder) obj;
        this.cardHolder = ch;
        userGiftBalance.setText(Integer.toString(this.cardHolder.getBalance()));
    }
}
