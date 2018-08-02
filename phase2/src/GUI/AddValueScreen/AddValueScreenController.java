package GUI.AddValueScreen;

import GUI.ControllerHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddValueScreenController {

    @FXML
    Button backButton;

    @FXML
    Button addValueButton;

    @FXML
    Label cardIDMessageLabel;

    @FXML
    Label amountMessageLabel;

    @FXML
    TextField cardIDTextField;

    @FXML
    TextField valueAmountTextField;

    ControllerHelper ch = new ControllerHelper();

    public void handleBackButton(ActionEvent e){
        ch.switchScreens(e, "/GUI/ManageCardsScreen/ManageCardsScreen.fxml");
    }

    public void handleAddValueButton(){}







}
