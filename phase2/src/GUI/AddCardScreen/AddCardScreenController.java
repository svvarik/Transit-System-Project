package GUI.AddCardScreen;

import GUI.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AddCardScreenController extends GeneralControllerScreen{

    @FXML
    Button backButton;

    @FXML
    Label completionLabel;

    ControllerHelper ch = new ControllerHelper();

    @FXML
    public void handleBackButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/ManageCardsScreen/ManageCardsScreen.fxml");
    }
}
