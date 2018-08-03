package GUI.HomeScreenPackage.MainScreen;

import GUI.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainScene extends GeneralControllerScreen {
    @FXML
    Button viewUserButton;

    @FXML
    Button tapButton;

    @FXML
    Button viewAdminUser;

    public MainScene(){
    }

    @FXML
    private void intialize(){
        //do something?
    }

    @FXML
    private void handleTapButton(ActionEvent event){
        ControllerHelper newControllerHelper = new ControllerHelper();
        String tapScreen = "/GUI/TapPackage/TapScreen/TapScene.fxml";
        newControllerHelper.switchScreens(event, tapScreen);
    }

    @FXML
    private void handleUserButton(ActionEvent event){
        System.out.println("User");
    }

    @FXML
    private void handleAdminButton(ActionEvent event){
        ControllerHelper newControllerHelper = new ControllerHelper();
        String goingTo = "/GUI/AdminPackage/AdminHomePackage/AdminLoginScreen/AdminHomePage/AdminLoginScene.fxml";
        newControllerHelper.switchScreens(event, goingTo);
    }

}
