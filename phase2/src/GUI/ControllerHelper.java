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
    private Parent changeScreen(String fxmlResource, TransitSystem model){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource(fxmlResource)));
            Parent root = loader.load();
            GeneralControllerScreen controller = loader.getController();
            controller.setTs(model);
            controller.setUpController();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // METHOD TO CHANGE SCREEN PASSING OVER ONE OBJECT (STRING, ETC.)
    private Parent changeScreen(String fxmlResource, TransitSystem model, Object object) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource(fxmlResource)));
            Parent root = loader.load();
            GeneralControllerScreen controller = loader.getController();
            controller.setTs(model);
            controller.setUpController(object);
            return root;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // METHOD TO OPEN NEW SCREEN WITH JUST TransitSystem
    public void openSameWindow(String fxmlResource, TransitSystem model, ActionEvent event) {
        Parent root = changeScreen(fxmlResource, model);
        setSameWindow(root, event);
    }

    // METHOD TO OPEN NEW SCREEN WITH TransitSystem AND ONE OTHER OBJECT
    public void openSameWindow(String fxmlResource, TransitSystem model, ActionEvent event, Object object) {
        Parent root = changeScreen(fxmlResource, model, object);
        setSameWindow(root, event);
    }

    public void openNewWindow(String fxmlResource, TransitSystem model, String title) {
        Parent root = changeScreen(fxmlResource, model);
        setNewWindow(root, title, 600, 400);
    }

    // Custom Size New Window
    public void openNewWindow(String fxmlResource, TransitSystem model, String title, int width, int height) {
        Parent root = changeScreen(fxmlResource, model);
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
