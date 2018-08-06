package GUI.AdminScreens.AdminLoginScreen;

import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import Main.TransitSystemInteractions;
import Main.TransitSystemStarter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class AdminLoginSceneController extends GeneralControllerScreen{

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
        logInButton.setOnAction(this::handleButtonAction);
    }

    private void handleButtonAction(ActionEvent event){
        //Find and check if the Transit System contains this AdminUser
        TransitSystemInteractions tsIO = new TransitSystemInteractions(this.getTs());
        boolean adminExists = tsIO.loginAdmin(emailTextField.getText(), passwordField.getText());
        if (adminExists){
            ControllerHelper newControllerHelper = new ControllerHelper();
            String tapScreen = "/GUI/AdminScreens/AdminMainScene.fxml";
            newControllerHelper.switchScreens(event, tapScreen);
        }
        else {
            System.out.println("Not working.");
            loginOutComeLabel.setTextFill(Color.web("#FF0000"));
            loginOutComeLabel.setText("Admin User Not Found");
        }
    }

}
