package GUI.UserPackage.UserConfigPackage.UserScreen;

import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleBackButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserLogInPackage/UserLoginScreen.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTransitSystem(), e, this.cardHolder);
    }

    @FXML
    public void handleManageCardsButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageCardsScreen.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTransitSystem(), e, this.cardHolder);
    }

    @FXML
    public void handleGiftMoneyButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/UserGiftScreen/UserGiftScene.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTransitSystem(), e, this.cardHolder);
    }

    @FXML
    public void handleViewStatsButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/UserStatsScreen/ViewUserStatsScene.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTransitSystem(), e, this.cardHolder);
    }

    @FXML
    public void handleChangeUserInfoButton(ActionEvent e){
        String dest = "/GUI/UserPackage/ManageUserInfoScreen/ManageUserInfoScene.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTransitSystem(), e, this.cardHolder);
    }

    public void setUpController(){}

    public void setUpController(Object obj) throws ClassCastException{
        CardHolder ch = (CardHolder) obj;
        this.cardHolder = ch;
        this.userName.setText(this.cardHolder.toString());
    }
}
