package GUI.TapPackage.TapScreen;

import GUI.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TapSceneController extends GeneralControllerScreen {

    @FXML
    Button backButton;

    @FXML
    Label titleLabel;

    @FXML
    Label tapOutcomeLabel;

    @FXML
    TextField cardMachineIDInputTextField;

    @FXML
    TextField cardIDInputTextField;

    @FXML
    Button tapButton;


    @FXML
    private void initialize(){
        //do something
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        ControllerHelper newControllerHelper = new ControllerHelper();
        String goingTo = "/GUI/HomeScreenPackage/MainScreen/MainScene.fxml";
        newControllerHelper.switchScreens(event, goingTo);
    }

    @FXML
    private void handleTapButton(ActionEvent event){
        //get the things in textfield;s and process the tap
    }
}
