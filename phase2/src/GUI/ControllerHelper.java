package GUI;

import GUI.GeneralControllerClass.GeneralControllerScreen;
import Main.TransitSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerHelper {

    // Same Window
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

    // METHOD TO CHANGE SCREEN PASSING NO OBJECTS OVER EXCEPT MODEL
    private Parent changeScreen(GeneralControllerScreen controller, String fxmlResource, TransitSystem model){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource(fxmlResource)));
            Parent root = loader.load();
            controller = loader.getController();
            controller.setTs(model);
            return root;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // METHOD TO CHANGE SCREEN PASSING OVER ONE OBJECT (STRING, ETC.)
    private Parent changeScreen(GeneralControllerScreen controller, String fxmlResource, TransitSystem model, Object object) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource(fxmlResource)));
            Parent root = loader.load();
            controller = loader.getController();
            controller.setTs(model);
            controller.setUpController(object);
            return root;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // METHOD TO OPEN NEW SCREEN WITH JUST TransitSystem
    void openSameWindow(GeneralControllerScreen controller, String fxmlResource, TransitSystem model, ActionEvent event) {
        Parent root = changeScreen(controller, fxmlResource, model);
        setSameWindow(root, event);
    }

    // METHOD TO OPEN NEW SCREEN WITH TransitSystem AND ONE OTHER OBJECT
    void openSameWindow(GeneralControllerScreen controller, String fxmlResource, TransitSystem model, ActionEvent event, Object object) {
        Parent root = changeScreen(controller, fxmlResource, model, object);
        setSameWindow(root, event);
    }

    void openNewWindow(GeneralControllerScreen controller, String fxmlResource, TransitSystem model, String title) {
        Parent root = changeScreen(controller, fxmlResource, model);
        setNewWindow(root, title, 600, 400);
    }

    // Custom Size New Window
    void openNewWindow(GeneralControllerScreen controller, String fxmlResource, TransitSystem model, String title, int width, int height) {
        Parent root = changeScreen(controller, fxmlResource, model);
        setNewWindow(root, title, width, height);
    }

    private void setNewWindow(Parent root, String title, int width, int height) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root, width, height));
        stage.show();
    }

    private void setSameWindow(Parent root, ActionEvent event) {
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(new Scene(root));
        window.show();
    }

    // New Window


}
