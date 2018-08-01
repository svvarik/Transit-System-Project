package GUI.MainScreen;

import GUI.ControllerHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainScene {
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
        String tapScreen = "/GUI/TapScreen/TapScene.fxml";
        newControllerHelper.switchScreens(event, tapScreen);
    }

    @FXML
    private void handleUserButton(ActionEvent event){
        System.out.println("User");
    }

    @FXML
    private void handleAdminButton(ActionEvent event){
        System.out.println("Admin User");
    }

}
