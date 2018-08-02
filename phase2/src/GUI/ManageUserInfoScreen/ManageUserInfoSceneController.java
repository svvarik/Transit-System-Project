package GUI.ManageUserInfoScreen;

import GUI.ControllerHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ManageUserInfoSceneController {

    @FXML
    Button backButton;

    @FXML
    Button changeNameButton;

    @FXML
    TextField nameTextField;

    ControllerHelper ch = new ControllerHelper();

    public void handleBackButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/UserScreen/ViewUserScene.fxml");
    }

    public void handleChangeButton(ActionEvent e){

    }
}
