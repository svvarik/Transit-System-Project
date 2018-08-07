package GUI.UserPackage.UserLogInPackage;

import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserLoginScreenController extends GeneralControllerScreen implements Initializable {

    @FXML
    Button backButton;

    @FXML
    Button loginButton;

    @FXML
    TextField emailTextField;

    @FXML
    TextField passwordTextField;

    @FXML
    Label failedLoginMessage;

    @FXML
    Button newAccountButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setUpController(){}

    public void setUpController(Object obj){
        CardHolder cardHolder = (CardHolder) obj;
        String name = cardHolder.getName();
        this.failedLoginMessage.setText("Welcome " + name + "! Account created successfully. Log in.");
        this.failedLoginMessage.setTextFill(Color.BLACK);
    }

    public void handleBackButton(ActionEvent e){
    this.getControllerHelper().openSameWindow("/GUI/HomeScreen/MainScene.fxml", this.getTransitSystem(), e);
    }

    public void handleLoginButton(ActionEvent e) throws IOException{
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        CardHolder cardHolder =  this.getTransitSystem().getCardHolders().findCardHolder(email);

        if(cardHolder == null){
            this.failedLoginMessage.setText("Incorrect Username");
        }
        else if (!cardHolder.isPassCorrect(password)){
            failedLoginMessage.setText("Incorrect Password");
        }
        else{
            this.getControllerHelper().openSameWindow("/GUI/UserPackage/UserConfigPackage/UserScreen/ViewUserScene.fxml", this.getTransitSystem(), e, cardHolder);
        }
    }

    public void handleMakeAccountButton(ActionEvent e) {
        this.getControllerHelper().openSameWindow("/GUI/UserPackage/NewUserScreen/NewUserScreen.fxml", this.getTransitSystem(), e);
    }
}
