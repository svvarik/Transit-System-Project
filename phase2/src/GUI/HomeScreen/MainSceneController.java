package GUI.HomeScreen;

import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainSceneController extends GeneralControllerScreen {

    @FXML
    Button viewUserButton;

    @FXML
    Button tapButton;

    @FXML
    Button viewAdminUser;

    public MainSceneController(){
    }

    // May not be necessary
    @FXML
    private void initialize(){
        //do something?
    }

    public void setUpController(){}

    /**
     * This controller should only ever receive a TransitSystem object
     * to be passed in.
     *
     * @param passedInObject an Object passed in to this controller
     */
    public void setUpController(Object passedInObject){}

    /**
     * Method changes screen from main to tap screen where user
     * can enter card ID and card Machine ID to tap a Card.
     *
     * @param event the event where the button is clicked
     */
    @FXML
    private void handleTapButton(ActionEvent event){
        String tapScreen = "/GUI/TapScreen/TapScene.fxml";
        this.getControllerHelper().openSameWindow(tapScreen, this.getTs(), event);
    }

    /**
     * Method changes screen from main to user screen where cardholders can login
     * to manage their account.
     *
     * @param event the event where the button is clicked
     */
    @FXML
    private void handleUserButton(ActionEvent event){
        String userScreen = "/GUI/UserPackage/UserLogInPackage/UserLoginScreen.fxml";
        this.getControllerHelper().openSameWindow(userScreen, this.getTs(), event);
    }

    /**
     * Method changes screen from main to admin screen where admins can login
     * to manage their account and the transit system.
     *
     * @param event the event where the button is clicked
     */
    @FXML
    private void handleAdminButton(ActionEvent event){
        String goingTo = "/GUI/AdminScreens/AdminLoginScreen/AdminLoginScene.fxml";
        this.getControllerHelper().openSameWindow(goingTo, this.getTs(), event);
    }

}