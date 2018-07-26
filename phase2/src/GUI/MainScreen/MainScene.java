package GUI.MainScreen;

import GUI.ControlledScreen;
import GUI.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainScene implements ControlledScreen {
    @FXML
    Button viewUserButton;

    @FXML
    Button tapButton;

    @FXML
    Button viewAdminUser;

    ScreenController sc;

    public MainScene(){
    }

    @FXML
    private void intialize(){
        viewAdminUser.setOnAction(this::handleButtonAction);
        tapButton.setOnAction(this::handleButtonAction);
        viewUserButton.setOnAction(this::handleButtonAction);
    }

    private void handleButtonAction(ActionEvent event){
        System.out.println(sc);
        Object eve=event.getSource();
        if (eve.equals(viewAdminUser)){
            System.out.println("Admin User");
            sc.stageScreen("Admin Login Screen", (Stage) tapButton.getScene().getWindow());
        }
        if (eve.equals(tapButton)){
            System.out.println("Tap Button");
            //switch to tap menu
            sc.stageScreen("Tap Screen",(Stage) tapButton.getScene().getWindow());
        }
        if (eve.equals(viewUserButton)){
            System.out.println("User");
            //switch to user login
        }
    }

    @Override
    public void setScreenController(ScreenController screenController) {
        sc = screenController;
    }
}
