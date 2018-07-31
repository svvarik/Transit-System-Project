package GUI.TapScreen;

import GUI.ControllerHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TapSceneController {

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
        tapButton.setOnAction(this::handleButtonAction);
        backButton.setOnAction(this::handleBackButton);
    }

    private void handleButtonAction(ActionEvent event) {
        Object eve=event.getSource();
        if(eve.equals(tapButton)){
            System.out.println("CardID: "+ cardIDInputTextField.getText() + " " +
                    "CardMachineID: " + cardMachineIDInputTextField.getText());
            //Process tap
        }
//        if (eve.equals(backButton)){
//            sc.stageScreen("Main Screen", (Stage) tapButton.getScene().getWindow());
//        }
    }

    private void handleBackButton(ActionEvent event) {
        ControllerHelper newControllerHelper = new ControllerHelper();
        String goingTo = "/GUI/MainScreen/MainScene.fxml";
        newControllerHelper.switchScreens(event, goingTo);
    }
}
