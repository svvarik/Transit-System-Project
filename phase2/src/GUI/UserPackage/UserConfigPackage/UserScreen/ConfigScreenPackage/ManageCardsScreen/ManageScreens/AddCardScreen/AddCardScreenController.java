package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.ManageCardsScreen.ManageScreens.AddCardScreen;

import GUI.HelperClasses.ControllerHelper;
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
        ch.switchScreens(e, "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageCardsScreen.fxml");
    }
}
