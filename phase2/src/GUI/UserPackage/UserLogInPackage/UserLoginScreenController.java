package GUI.UserPackage.UserLogInPackage;

import GUI.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.xml.soap.Text;

public class UserLoginScreenController extends GeneralControllerScreen {

    @FXML
    Button backButton;

    @FXML
    Button loginButton;

    @FXML
    TextField emailTextField;

    @FXML
    TextField passwordTextField;

    ControllerHelper ch = new ControllerHelper();

    public void handleBackButton(ActionEvent e){
    ch.switchScreens(e, "/GUI/HomeScreenPackage/MainScreen/MainScene.fxml");
    }

    public void handleLoginButton(ActionEvent e){
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
    }
}
