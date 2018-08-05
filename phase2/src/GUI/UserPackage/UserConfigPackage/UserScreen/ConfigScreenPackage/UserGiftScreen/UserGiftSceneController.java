package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.UserGiftScreen;

import FareSystem.Card;
import GUI.HelperClasses.ControllerHelper;
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
    Label cardIDMessage;

    @FXML
    Label emailMessage;

    @FXML
    Label amountMessage;

    @FXML
    Label userLabel;

    @FXML
    Button backButton;

    @FXML
    Button giftButton;

    @FXML
    TextField recipientEmailTextField;

    @FXML
    TextField cardIDTextField;

    @FXML
    TextField moneyTextField;

    @FXML
    Label successLabel;

    private CardHolder cardHolder;

    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleBackButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ViewUserScene.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTs(), e, this.cardHolder);
    }

    @FXML
    public void handleGiftButton(ActionEvent e){
        this.emailMessage.setText("");
        this.cardIDMessage.setText("");
        this.amountMessage.setText("");
        int fromCard;
        double amount;
        try{
            String recepient = this.recipientEmailTextField.getText();
            fromCard = Integer.parseInt(this.cardIDTextField.getText());
            try{
                amount = Double.parseDouble(this.moneyTextField.getText());
                Card tempCard = this.cardHolder.getCard(fromCard);
                if(tempCard == null){
                    this.cardIDMessage.setText("Card Does Not Exist");
                }
                else if(this.getTs().getCardHolders().findCardHolder(recepient)==null){
                    this.emailMessage.setText("Recepient Does Not Exist");
                }
                else{
                    if(this.cardHolder.sendGiftMoney(recepient, fromCard, amount)){
                        this.successLabel.setText("Money Was Successfully Gifted");
                    }
                }
            }catch(Exception ee){
                this.amountMessage.setText("Invalid Input");
            }
        }catch(Exception ep){
            this.cardIDMessage.setText("Invalid Input");
        }

    }

    public void changeSuccessLabel(String text){
        successLabel.setText(text);
    }

    public void setUpController(){}

    public void setUpController(Object obj) throws ClassCastException{
        CardHolder ch = (CardHolder) obj;
        this.cardHolder = ch;
        this.userLabel.setText(this.cardHolder.toString());
    }
}
