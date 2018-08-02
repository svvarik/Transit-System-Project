package GUI.SuspendCardScreen;

import GUI.ControllerHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SuspendCardScreenController {

    @FXML
    Button backButton;

    @FXML
    Button suspendCardButton;

    @FXML
    Label suspendMessage;

    @FXML
    TextField cardIDField;

    ControllerHelper ch = new ControllerHelper();

    public void handleBackButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/ManageCardsScreen/ManageCardsScreen.fxml");
    }

    public void handleSuspendCardButton(){}


}
