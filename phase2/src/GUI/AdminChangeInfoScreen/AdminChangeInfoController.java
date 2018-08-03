package GUI.AdminChangeInfoScreen;

import GUI.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminChangeInfoController extends GeneralControllerScreen {

    @FXML
    Button backButton;

    @FXML
    Button changePasswordButton;

    @FXML
    Button changeEmailButton;

    @FXML
    TextField newEmailTextField;

    @FXML
    PasswordField newPasswordField;

    @FXML
    Label outcomeLabel;

    ControllerHelper ch = new ControllerHelper();

    public void handleBackButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/AdminStatistics/AdminMainScene.fxml");
    }

    public void handlePasswordButton(ActionEvent e){

    }

    public void handleEmailButton(ActionEvent e){

    }
}
