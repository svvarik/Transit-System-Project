package GUI.UserGiftScreen;

import GUI.ControllerHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserGiftSceneController {

    @FXML
    Button backButton;

    @FXML
    Button giftButton;

    @FXML
    TextField recipientEmailTextField;

    @FXML
    TextField cardIDTextField;

    @FXML
    TextField moneyTextField;

    @FXML
    Label successLabel;

    ControllerHelper ch = new ControllerHelper();

    @FXML
    public void handleBackButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/UserScreen/ViewUserScene.fxml");
    }

    @FXML
    public void handleGiftButton(ActionEvent e){

    }

    public void changeSuccessLabel(String text){
        successLabel.setText(text);
    }
}
