package GUI.AdminScreens.AdminLoginScreen;

import AdminUsers.AdminUser;
import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import Main.TransitSystemInteractions;
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
    private void handleButtonAction(ActionEvent event){
        System.out.println("The current transit system in " + this.getClass() + " is " + this.getTransitSystem());
        //Find and check if the Transit System contains this AdminUser
        AdminUser adminUser = this.getTransitSystem().getAdminUsers().findAdminUser(emailTextField.getText());
        boolean adminLoginCorrect = this.getTransitSystemInteractions().loginAdmin(this.getTransitSystem(),
                emailTextField.getText(), passwordField.getText());
        if (adminLoginCorrect){
            ControllerHelper newControllerHelper = new ControllerHelper();
            String tapScreen = "/GUI/AdminScreens/AdminMainScene.fxml";
            newControllerHelper.openSameWindow(tapScreen, this.getTransitSystem(), event);
        }
        else {
            if (adminUser == null) {
            loginOutComeLabel.setTextFill(Color.web("#FF0000"));
            loginOutComeLabel.setText("Admin User Not Found"); }
            else if (adminUser.getPassword() == null){
                loginOutComeLabel.setTextFill(Color.web("#FF0000"));
                loginOutComeLabel.setText("Error: This Admin has no password"); }

            else if (passwordField.getText().equals("")){
                loginOutComeLabel.setTextFill(Color.web("#FF0000"));
                loginOutComeLabel.setText("Please enter a password!"); }


            else if (adminUser.getPassword() != passwordField.getText()){
                loginOutComeLabel.setTextFill(Color.web("#FF0000"));
                loginOutComeLabel.setText("Invalid Password!"); }
            }
        }



}
