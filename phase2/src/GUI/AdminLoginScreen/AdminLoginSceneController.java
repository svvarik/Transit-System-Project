package GUI.AdminLoginScreen;

import GUI.ControllerHelper;
import Main.Main2;
import Main.TransitSystemIO;
import Main.TransitSystemStarter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminLoginSceneController {

    TransitSystemIO tsIO;

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
    private void intialize() throws IOException, ClassNotFoundException {
        TransitSystemStarter tsStart = new TransitSystemStarter();
        tsIO = tsStart.getTsIO();
        logInButton.setOnAction(this::handleButtonAction);
    }

    private void handleButtonAction(ActionEvent event){
        //Find and check if the Transit System contains this AdminUser
        boolean adminExists = tsIO.loginAdmin(emailTextField.getText(), passwordField.getText());
        if (adminExists){
            ControllerHelper newControllerHelper = new ControllerHelper();
            String tapScreen = "/GUI/AdminStatistics/AdminMainScene.fxml";
            newControllerHelper.switchScreens(event, tapScreen);
        }
        else {
            System.out.println("Not working.");
            }
        }


}
