package GUI.UserScreen;

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
        ch.switchScreens(e, "/GUI/MainScreen/MainScene.fxml");
    }

    @FXML
    public void handleManageCardsButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/ManageCardsScreen/ManageCardsScreen.fxml");
    }

    @FXML
    public void handleGiftMoneyButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/UserGiftScreen/UserGiftScene.fxml");
    }

    @FXML
    public void handleViewStatsButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/UserStatsScreen/ViewUserStatsScene.fxml");
    }

    @FXML
    public void handleChangeUserInfoButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/ManageUserInfoScreen/ManageUserInfoScene.fxml");
    }

}
