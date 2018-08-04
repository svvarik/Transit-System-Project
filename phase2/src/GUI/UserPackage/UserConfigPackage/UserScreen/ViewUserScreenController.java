package GUI.UserPackage.UserConfigPackage.UserScreen;

import FareSystem.Card;
import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewUserScreenController extends GeneralControllerScreen implements Initializable {

    @FXML
    Button backButton;

    @FXML
    Label userName;
    @FXML
    Button manageCardsButton;

    @FXML
    Button giftMoneyButton;

    @FXML
    Button viewStatsButton;

    @FXML
    Button changeUserInfoButton;

    private CardHolder cardHolder;
    ControllerHelper ch = new ControllerHelper();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleBackButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/HomeScreen/MainScene.fxml");
    }

    @FXML
    public void handleManageCardsButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageCardsScreen.fxml");
    }

    @FXML
    public void handleGiftMoneyButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/UserGiftScreen/UserGiftScene.fxml");
    }

    @FXML
    public void handleViewStatsButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/UserStatsScreen/ViewUserStatsScene.fxml");
    }

    @FXML
    public void handleChangeUserInfoButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/UserPackage/ManageUserInfoScreen/ManageUserInfoScene.fxml");
    }

public void setUpController(){}

public void setUpController(Object obj) throws ClassCastException{
    CardHolder ch = (CardHolder) obj;
    this.cardHolder = ch;
    this.userName.setText(this.cardHolder.toString());

}
}
