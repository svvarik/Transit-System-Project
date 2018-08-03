package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.ManageCardsScreen.ManageScreens.DeleteCardScreen;

import GUI.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DeleteCardScreenController extends GeneralControllerScreen{

    @FXML
    Button backButton;

    @FXML
    Button deleteCardButton;

    @FXML
    TextField cardIDEntry;

    @FXML
    Label messageLabel;

    ControllerHelper ch = new ControllerHelper();

    public void handleBackButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageCardsScreen.fxml");
    }

    public void handleDeleteCardButton(){}
}
