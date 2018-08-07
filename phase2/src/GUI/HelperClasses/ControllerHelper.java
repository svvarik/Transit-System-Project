package GUI.HelperClasses;

import GUI.GeneralControllerClass.GeneralControllerScreen;
import Main.TransitSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controllerhelper helps the controllers to chnage among themselves and bring up other controllers/screens
 */
public class ControllerHelper {

    /**
     * changes the same window into a new screen.
     * @param event the event that has occured
     * @param fxmlResource the path of the fxml file we need to switch to.
     */
    public void switchScreens(ActionEvent event, String fxmlResource){
        try {
            Parent newParent = FXMLLoader.load((getClass().getResource(fxmlResource)));
            Scene newScene = new Scene(newParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method changes the screen without any objects beside the transitSystem to it
     * @param fxmlResource the path of the fxml file we need to switch to.
     * @param model the transitSystem we want to assign to the destination controller
     * @return returns the parent screen
     */
    private Parent changeScreen(String fxmlResource, TransitSystem model){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource(fxmlResource)));
            Parent root = loader.load();
            GeneralControllerScreen controller = loader.getController();
            controller.setTransitSystem(model);
            controller.setUpController();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method changes the screen with possiblity of passing an object to the new screen
     * @param fxmlResource the path of the fxml file we need to switch to
     * @param model the transitSystem we want to assign to the destination controller
     * @param object the object we want to assign to the new controller
     * @return returns the parent screen
     */
    private Parent changeScreen(String fxmlResource, TransitSystem model, Object object) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource(fxmlResource)));
            Parent root = loader.load();
            GeneralControllerScreen controller = loader.getController();
            controller.setTransitSystem(model);
            controller.setUpController(object);
            return root;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Opens a new window only with a TransitSystem
     * @param fxmlResource the path of the fxml file we need to switch to
     * @param model the transitSystem we want to assign to the destination controller
     * @param event the event which has occurred
     */
    public void openSameWindow(String fxmlResource, TransitSystem model, ActionEvent event) {
        Parent root = changeScreen(fxmlResource, model);
        setSameWindow(root, event);
    }

    /**
     * Opens a new window  with a TransitSystem and an extra object
     * @param fxmlResource the path of the fxml file we need to switch to
     * @param model the transitSystem we want to assign to the destination controller
     * @param event the event which has occurred
     * @param object the object we want to assign to the new controller
     */
    public void openSameWindow(String fxmlResource, TransitSystem model, ActionEvent event, Object object) {
        Parent root = changeScreen(fxmlResource, model, object);
        setSameWindow(root, event);
    }

    /**
     * Opens a new window with a TransitSystem and an extra Object
     * @param fxmlResource the path of the fxml file we need to switch to
     * @param model the transitSystem we want to assign to the destination controller
     * @param title the screen's title
     * @param object the object we want to assign to the new controller
     */
    public void openNewWindow(String fxmlResource, TransitSystem model, String title, Object object) {
        Parent root = changeScreen(fxmlResource, model, object);
        setNewWindow(root, title);
    }

    /**
     * Opens a new with a TransitSystem only
     * @param fxmlResource the path of the fxml file we need to switch to
     * @param model the transitSystem we want to assign to the destination controller
     * @param title the screen's title
     */
    public void openNewWindow(String fxmlResource, TransitSystem model, String title) {
        Parent root = changeScreen(fxmlResource, model);
        setNewWindow(root, title);
    }

    /**
     * it sets the destination  when we want to open a new window
     * @param root the scene to be sat
     * @param title the title of the new screen
     */
    private void setNewWindow(Parent root, String title) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    /**
     * it sets the destination  when we want to open in the same window
     * @param root the scene to be sat
     * @param event the event that has occured
     */
    private void setSameWindow(Parent root, ActionEvent event) {
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(new Scene(root));
        window.show();
    }

    // New Window


}
