package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.ManageCardsScreen.ManageScreens.SuspendCardScreen;

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

public class SuspendCardScreenController extends GeneralControllerScreen implements Initializable {



    @FXML
    Button suspendCardButton;

    @FXML
    Button backButton;


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
                this.getTransitSystem(), e, this.cardHolder);
    }

    public void handleSuspendCardButton(){
        int destCard;
        try{
            destCard = Integer.parseInt(this.cardIDEntry.getText());
            Card tempCard = this.cardHolder.getCard(destCard);
            if(tempCard == null){
                this.messageLabel.setText("The Card Does Is Not Yours");
            }
            else{
                tempCard.suspendCard();
                this.messageLabel.setText("Card Was Successfully Suspended");
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

