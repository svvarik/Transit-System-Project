package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.ManageCardsScreen.ManageScreens.AddValueScreen;

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

public class AddValueScreenController extends GeneralControllerScreen implements Initializable{

    @FXML
    Button backButton;

    @FXML
    Button addValueButton;

    @FXML
    Label cardIDMessageLabel;

    @FXML
    Label userLabel;

    @FXML
    Label amountMessageLabel;

    @FXML
    TextField cardIDTextField;

    @FXML
    TextField valueAmountTextField;


    private CardHolder cardHolder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleBackButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageCardsScreen.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTs(), e, this.cardHolder);
    }

    public void handleAddValueButton(){
        double amount;
        int destCard;
        try{
            destCard = Integer.parseInt(this.cardIDTextField.getText());
            try{
                amount = Double.parseDouble((this.valueAmountTextField.getText()));
                if(this.cardHolder.getCard(destCard) != null){
                    this.cardHolder.getCard(destCard).addValue(amount);
                    this.amountMessageLabel.setText("Your Amount");
                    this.cardIDMessageLabel.setText("Your Card Id");
                    this.userLabel.setText(amount + " Was Added to "+ destCard);
                }
                else{
                    this.cardIDMessageLabel.setText("Card Does Not Exist");
                }
            }catch(Exception e){
                this.amountMessageLabel.setText("Invalid Input");
            }
        }catch(Exception e){
            this.cardIDMessageLabel.setText("Invalid Input!");
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
