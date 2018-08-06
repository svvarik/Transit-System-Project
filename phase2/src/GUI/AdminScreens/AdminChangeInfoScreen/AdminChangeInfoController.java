package GUI.AdminScreens.AdminChangeInfoScreen;

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
        ch.switchScreens(e, "/GUI/AdminScreens/AdminMainScene.fxml");
    }

    public void handlePasswordButton(ActionEvent e){
        String email = currentEmailTextField.getText();
        String password = currentPasswordTextField.getText();
        System.out.println("The current transit system in " + this.getClass() + " is " + this.getTs());
        CardHolder cardHolder =  this.getTs().getCardHolders().findCardHolder(email);

        if(cardHolder == null){
            outcomeLabel.setTextFill(Color.web("#FF0000"));
            outcomeLabel.setText(("Cardholder doesn't exist!"));
        }
        else if (!cardHolder.isPassCorrect(password)){
            outcomeLabel.setTextFill(Color.web("#FF0000"));
            outcomeLabel.setText(("Invalid Password!"));
        }
        else{
            if (newNameTextField != null) {
                cardHolder.setName(newNameTextField.getText());
            }
            if (newPasswordField != null)
                cardHolder.setPassword(newPasswordField.getText());
        }

    }

    public void handleEmailButton(ActionEvent e){

    }
}
