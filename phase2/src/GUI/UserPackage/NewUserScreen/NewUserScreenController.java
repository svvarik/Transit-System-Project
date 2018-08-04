package GUI.UserPackage.NewUserScreen;

import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NewUserScreenController extends GeneralControllerScreen implements Initializable {

    @FXML
    Label messageLabel;

    @FXML
    Label nameLabel;

    @FXML
    Label emailLabel;

    @FXML
    Label passLabel;

    @FXML
    Button makeUserButton;

    @FXML
    Button backButton;

    @FXML
    TextField nameTextField;

    @FXML
    TextField emailTextField;

    @FXML
    TextField passTextField;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleBackButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserLogInPackage/UserLoginScreen.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTs(), e);
    }

    public void handleMakeUserButton(ActionEvent e){
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String pass = passTextField.getText();

        if(name.equals(""))messageLabel.setText("Please Enter Your Name");
        else if(email.equals(""))messageLabel.setText("Please Enter Your email");
        else if(pass.equals(""))messageLabel.setText("Please Enter Your Password");
        else{
            this.getTs().getCardHolders().addCardHolder(name, email, pass, this.getTs());
            this.messageLabel.setText("Hello "+ name + "! Please Go Back Now");
        }
    }
}
