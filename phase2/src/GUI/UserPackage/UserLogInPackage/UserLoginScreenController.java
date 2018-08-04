package GUI.UserPackage.UserLogInPackage;

import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import GUI.UserPackage.UserConfigPackage.UserScreen.ViewUserScreenController;
import TransitUsers.CardHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.soap.Node;
import javax.xml.soap.Text;
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
    Label messageLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setUpController(){}

    public void setUpController(Object obj){}

    public void handleBackButton(ActionEvent e){
    this.getControllerHelper().openSameWindow("/GUI/HomeScreen/MainScene.fxml", this.getTs(), e);
    }

    public void handleLoginButton(ActionEvent e) throws IOException{
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        CardHolder cardHolder = null;
        cardHolder =  this.getTs().getCardHolders().findCardHolder(email);

        if(cardHolder == null){
            this.getControllerHelper().openSameWindow("/GUI/UserPackage/NewUserScreen/NewUserScreen.fxml", this.getTs(), e);
        }
        else if (!cardHolder.isPassCorrect(password)){
                this.messageLabel.setText("Invalid Password!");
        }
        else{
        //    changeSceneCardCardHolderView(cardholder, e);
            this.getControllerHelper().openSameWindow("/GUI/UserPackage/UserConfigPackage/UserScreen/ViewUserScene.fxml", this.getTs(), e, cardHolder);
//
        }
    }

//    public void changeSceneCardCardHolderView(CardHolder cardHolder, ActionEvent event)throws IOException{
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("GUI/UserPackage/UserConfigPackage/UserScreen/ViewUserScreenController.java"));
//        Parent tableViewParent = loader.load();
//
//        Scene UserScene = new Scene(tableViewParent);
//
//        ViewUserScreenController controller = new ViewUserScreenController();
//        controller.initData(cardHolder);
//
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//
//        window.setScene(UserScene);
//        window.show();
//    }
  //  }
}
