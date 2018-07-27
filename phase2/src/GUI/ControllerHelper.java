package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


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

    // New Window


}
