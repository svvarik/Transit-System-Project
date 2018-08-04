package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.ManageCardsScreen;

import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageCardsScreenController extends GeneralControllerScreen implements Initializable{

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleBackButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ViewUserScene.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTs(), e, this.cardHolder);
    }

    public void handleAddCardButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageScreens/AddCardScreen/AddCardScreen.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTs(), e, this.cardHolder);
    }

    public void handleDeleteCardButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageScreens/DeleteCardScreen/DeleteCardScreen.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTs(), e, this.cardHolder);
    }

    public void handleSuspendCardButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageScreens/SuspendCardScreen/SuspendCardScreen.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTs(), e, this.cardHolder);
    }

    public void handleAddValueButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageScreens/AddValueScreen/AddValueScreen.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTs(), e, this.cardHolder);
    }
    public void setUpController(){}

    public void setUpController(Object obj) throws ClassCastException{
        CardHolder ch = (CardHolder) obj;
        this.cardHolder = ch;
        this.userLabel.setText(this.cardHolder.toString());
    }
}
