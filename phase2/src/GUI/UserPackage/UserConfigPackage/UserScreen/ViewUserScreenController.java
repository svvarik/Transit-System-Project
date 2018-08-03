package GUI.UserPackage.UserConfigPackage.UserScreen;

import GUI.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class ViewUserScreenController extends GeneralControllerScreen {

    @FXML
    Button backButton;

    @FXML
    Button manageCardsButton;

    @FXML
    Button giftMoneyButton;

    @FXML
    Button viewStatsButton;

    @FXML
    Button changeUserInfoButton;

    ControllerHelper ch = new ControllerHelper();

    @FXML
    public void handleBackButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/HomeScreenPackage/MainScreen/MainScene.fxml");
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

}
