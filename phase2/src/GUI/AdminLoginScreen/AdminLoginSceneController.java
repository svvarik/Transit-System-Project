package GUI.AdminLoginScreen;

import GUI.ControlledScreen;
import GUI.MainScreen.MainScene;
import GUI.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminLoginSceneController implements ControlledScreen {
    @FXML
    Label titleLabel;

    @FXML
    Label loginOutComeLabel;

    @FXML
    TextField emailTextField;

    @FXML
    PasswordField passwordField;

    @FXML
    Button logInButton;

    ScreenController sc;

    @FXML
    private void intialize(){
        logInButton.setOnAction(this::handleButtonAction);
    }

    public void setScreenController(ScreenController screenController){
        sc=screenController;
    }

    private void handleButtonAction(ActionEvent event){
        //Check email and password combo and move to user screen
    }

}
