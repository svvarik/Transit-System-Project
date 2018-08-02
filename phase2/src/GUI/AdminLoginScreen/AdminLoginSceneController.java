package GUI.AdminLoginScreen;

import Main.Main2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminLoginSceneController {

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


    @FXML
    private void intialize(){
        logInButton.setOnAction(this::handleButtonAction);
    }

    private void handleButtonAction(ActionEvent event){
        if (emailTextField.getText().equals("hello")){

        }
        else {
            System.out.println("Not working.");
            }
        }


}
