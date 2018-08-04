package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.ManageCardsScreen;

import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ManageCardsScreenController extends GeneralControllerScreen {

    @FXML
    Button backButton;

    @FXML
    Button addCardButton;

    @FXML
    Button deleteCardButton;

    @FXML
    Button suspendCardButton;

    @FXML
    Button addValueButton;

    ControllerHelper ch = new ControllerHelper();


    public void handleBackButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/UserPackage/UserConfigPackage/UserScreen/ViewUserScene.fxml");
    }

    public void handleAddCardButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageScreens/AddCardScreen/AddCardScreen.fxml");
    }

    public void handleDeleteCardButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageScreens/DeleteCardScreen/DeleteCardScreen.fxml");
    }

    public void handleSuspendCardButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageScreens/SuspendCardScreen/SuspendCardScreen.fxml");
    }

    public void handleAddValueButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageScreens/AddValueScreen/AddValueScreen.fxml");
    }

}
