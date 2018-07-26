package GUI.TapScreen;

import GUI.ControlledScreen;
import GUI.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TapSceneController implements ControlledScreen {

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

    ScreenController sc;

    @FXML
    private void intialize(){
        tapButton.setOnAction(this::handleButtonAction);
        backButton.setOnAction(this::handleButtonAction);
    }

    private void handleButtonAction(ActionEvent event) {
        Object eve=event.getSource();
        if(eve.equals(tapButton)){
            System.out.println("CardID: "+ cardIDInputTextField.getText() + " " +
                    "CardMachineID: " + cardMachineIDInputTextField.getText());
            //Process tap
        }
        if (eve.equals(backButton)){
            sc.stageScreen("Main Screen", (Stage) tapButton.getScene().getWindow());
        }
    }

    @Override
    public void setScreenController(ScreenController screenController) {
        sc = screenController;
    }
}
