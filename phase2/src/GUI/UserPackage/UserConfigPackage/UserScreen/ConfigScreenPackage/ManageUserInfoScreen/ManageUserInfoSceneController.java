package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.ManageUserInfoScreen;

import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ManageUserInfoSceneController extends GeneralControllerScreen {

    @FXML
    Button backButton;

    @FXML
    Button changeNameButton;

    @FXML
    TextField nameTextField;

    @FXML
    Button changePasswordButton;

    @FXML
    TextField oldPasswordTextField;

    @FXML
    TextField newPasswordTextField;

    //TODO IMPLEMENT METHOD TO CHANGE PASSWORD

    ControllerHelper ch = new ControllerHelper();

    public void handleBackButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/UserPackage/UserConfigPackage/UserScreen/ViewUserScene.fxml");
    }

    public void handleChangeButton(ActionEvent e){

    }
}