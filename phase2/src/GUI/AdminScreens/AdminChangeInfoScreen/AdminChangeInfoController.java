package GUI.AdminScreens.AdminChangeInfoScreen;

import AdminUsers.AdminUser;
import AdminUsers.AdminUserList;
import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class AdminChangeInfoController extends GeneralControllerScreen {

    @FXML
    Button backButton;

    @FXML
    Button changePasswordButton;

    @FXML
    Button changeNameButton;

    @FXML
    TextField currentEmailTextField;

    @FXML
    TextField currentPasswordTextField;

    @FXML
    TextField newNameTextField;

    @FXML
    PasswordField newPasswordField;

    @FXML
    Label outcomeLabel;

    ControllerHelper ch = new ControllerHelper();

    public void handleBackButton(ActionEvent e){
        ch.openSameWindow("/GUI/AdminScreens/AdminMainScene.fxml", this.getTransitSystem(), e);
    }

    public void handlePasswordButton(ActionEvent e){
        String email = currentEmailTextField.getText();
        String password = currentPasswordTextField.getText();
        System.out.println("The current transit system in " + this.getClass() + " is " + this.getTransitSystem());
        AdminUser adminUser = this.getTransitSystem().getAdminUsers().findAdminUser(email);

        if(adminUser == null){
            outcomeLabel.setTextFill(Color.web("#FF0000"));
            outcomeLabel.setText(("Cardholder doesn't exist!"));
        }
        else if (!adminUser.isPassCorrect(password)){
            outcomeLabel.setTextFill(Color.web("#339900"));
            outcomeLabel.setText(("Invalid Password!"));
        }
        else{
            if (newPasswordField != null)
                adminUser.setPassword(newPasswordField.getText());
            outcomeLabel.setTextFill(Color.web("#339900"));
            outcomeLabel.setText(("Password change successful!"));
            this.currentPasswordTextField.clear();
            this.currentEmailTextField.clear();
            this.newPasswordField.clear();
        }

    }

    public void handleNameButton(ActionEvent e){
        String email = currentEmailTextField.getText();
        String password = currentPasswordTextField.getText();
        System.out.println("The current transit system in " + this.getClass() + " is " + this.getTransitSystem());
        AdminUser adminUser = this.getTransitSystem().getAdminUsers().findAdminUser(email);

        if(adminUser == null){
            outcomeLabel.setTextFill(Color.web("#FF0000"));
            outcomeLabel.setText(("Admin User doesn't exist!"));
        }
        else if (!adminUser.isPassCorrect(password)){
            outcomeLabel.setTextFill(Color.web("#FF0000"));
            outcomeLabel.setText(("Invalid Password!"));
        }
        else {
            if (newNameTextField != null) {
                adminUser.setName(newNameTextField.getText());
                outcomeLabel.setTextFill(Color.web("#339900"));
                outcomeLabel.setText(("Name change successful!"));
                this.currentEmailTextField.clear();
                this.currentPasswordTextField.clear();
                this.newNameTextField.clear();

            }
        }
    }
}
