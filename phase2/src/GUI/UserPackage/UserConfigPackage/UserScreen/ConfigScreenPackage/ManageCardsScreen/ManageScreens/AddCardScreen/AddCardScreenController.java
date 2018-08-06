package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.ManageCardsScreen.ManageScreens.AddCardScreen;

import FareSystem.Card;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCardScreenController extends GeneralControllerScreen implements Initializable{

    @FXML
    Label userLabel;

    @FXML
    Button backButton;

    @FXML
    Label completionLabel;

    private CardHolder cardHolder;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    @FXML
    public void handleBackButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageCardsScreen.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTransitSystem(), e, this.cardHolder);
    }

    public void setUpController(){}

    public void setUpController(Object obj) throws ClassCastException{
        CardHolder ch = (CardHolder) obj;
        this.cardHolder = ch;
        this.userLabel.setText(this.cardHolder.toString());
        Card tempCard = new Card(this.cardHolder, this.getTransitSystem().getTapManager());
        this.cardHolder.addCard(tempCard);
        this.completionLabel.setText("Your New CardID is " + tempCard.getCardID());
    }
}
