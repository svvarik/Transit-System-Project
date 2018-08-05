package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.ManageCardsScreen.ManageScreens.DeleteCardScreen;

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

public class DeleteCardScreenController extends GeneralControllerScreen implements Initializable {

    @FXML
    Button backButton;

    @FXML
    Button deleteCardButton;

    @FXML
    TextField cardIDEntry;

    @FXML
    Label messageLabel;

    @FXML
    Label userLabel;

    private CardHolder cardHolder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleBackButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageCardsScreen.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTs(), e, this.cardHolder);
    }

    public void handleDeleteCardButton(){
        int destCard;
        try{
            destCard = Integer.parseInt(this.cardIDEntry.getText());
            Card tempCard = this.cardHolder.getCard(destCard);
            if(tempCard == null){
                this.messageLabel.setText("The Card Does Not Exist");
            }
            else{
                this.cardHolder.removeCard(tempCard.getCardID());
                this.messageLabel.setText("Card Was Successfully Removed");
            }

        }catch(Exception e){
            this.messageLabel.setText("Invalid Input");
        }

    }

    public void setUpController(){

    }

    public void setUpController(Object obj) throws ClassCastException{
        CardHolder ch = (CardHolder) obj;
        this.cardHolder = ch;
        this.userLabel.setText(this.cardHolder.toString());

    }
}
