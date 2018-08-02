package GUI.ManageCardsScreen;

import GUI.ControllerHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ManageCardsScreenController {

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
        ch.switchScreens(e, "/GUI/UserScreen/ViewUserScene.fxml");
    }

    public void handleAddCardButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/AddCardScreen/AddCardScreen.fxml");
    }

    public void handleDeleteCardButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/DeleteCardScreen/DeleteCardScreen.fxml");
    }

    public void handleSuspendCardButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/SuspendCardScreen/SuspendCardScreen.fxml");
    }

    public void handleAddValueButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/AddValueScreen/AddValueScreen.fxml");
    }

}
