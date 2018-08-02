package GUI.UserScreen;

import GUI.ControllerHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class ViewUserScreenController {

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
    }

    @FXML
    public void handleManageCardsButton(ActionEvent e){

    }

    @FXML
    public void handleGiftMoneyButton(ActionEvent e){

    }

    @FXML
    public void handleViewStatsButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/UserStatsScreen/ViewUserStatsScene.fxml");
    }

    @FXML
    public void handleChangeUserInfoButton(ActionEvent e){

    }

}
