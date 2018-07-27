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
        viewAdminUser.setOnAction(this::handleButtonAction);
        tapButton.setOnAction(this::handleButtonAction);
        viewUserButton.setOnAction(this::handleButtonAction);
    }

    private void handleButtonAction(ActionEvent event){
        Object eve=event.getSource();
        if (eve.equals(viewAdminUser)){
            System.out.println("Admin User");
        }
        if (eve.equals(tapButton)){
            ControllerHelper newControllerHelper = new ControllerHelper();
            String tapScreen = "/GUI/TapScreen/TapScene.fxml";
            newControllerHelper.switchScreens(event, tapScreen);
        }
        if (eve.equals(viewUserButton)){
            System.out.println("User");
            //switch to user login
        }
    }


}
