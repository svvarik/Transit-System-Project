package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.ManageCardsScreen.ManageScreens.SuspendCardScreen;

import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SuspendCardScreenController extends GeneralControllerScreen {

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
        ch.switchScreens(e, "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageCardsScreen.fxml");
    }

    public void handleSuspendCardButton(){}


}
