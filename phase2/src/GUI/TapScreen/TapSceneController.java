package GUI.TapScreen;

import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TapSceneController extends GeneralControllerScreen {

    @FXML
    Button backButton;
    @FXML
    Label tapOutcomeLabel;
    @FXML
    TextField cardMachineIDInputTextField;
    @FXML
    TextField cardIDInputTextField;
    @FXML
    Button tapButton;
    @FXML
    Rectangle outcomeRectangle;



    public void setUpController(){
        Color purple = Color.rgb(91, 87, 213);
        this.outcomeRectangle.setFill(purple);
    }

    /**
     * This Controller should never receive an object to set up.
     */
    public void setUpController(Object object){}

    @FXML
    private void handleBackButton(ActionEvent event) {
        String goingTo = "/GUI/HomeScreen/MainScene.fxml";
        this.getControllerHelper().openSameWindow(goingTo, this.getTs(), event);
    }

    @FXML
    private void handleTapButton(ActionEvent event){
        String cardID = this.cardIDInputTextField.getText();
        String cmID = this.cardMachineIDInputTextField.getText();
        if (this.getTsIO().enterStation(cardID, cmID)){
            this.outcomeRectangle.setFill(Color.GREEN);
            this.tapOutcomeLabel.setText("Success");
        } else {
            this.outcomeRectangle.setFill(Color.RED);
            this.tapOutcomeLabel.setText("Failure");
        }
        //get the things in textfield;s and process the tap
    }
}