package GUI.AdminScreens.AdminManageUsersScreen;

import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class AdminManageUserSceneController extends GeneralControllerScreen {

    @FXML
    Button backButton;

    @FXML
    Button suspendButton;

    @FXML
    Button deleteButton;

    @FXML
    Label outcomeLabel;

    @FXML
    TextField cardIDTextField;

    ControllerHelper ch = new ControllerHelper();

    @FXML
    public void handleBackButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/AdminScreens/AdminMainScene.fxml");
    }

    @FXML
    public void handleSuspendButton(ActionEvent e)
    {

    }

    @FXML
    public void handleDeleteButton(ActionEvent e){

    }

    public void setOutcomeLabel(String text){
        outcomeLabel.setText(text);
    }

    public String getCardIDTextFieldText(){
        return cardIDTextField.getText();
    }
}