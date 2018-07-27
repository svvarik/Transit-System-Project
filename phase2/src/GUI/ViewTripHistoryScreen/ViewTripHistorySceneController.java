package GUI.ViewTripHistoryScreen;

import com.sun.tools.javac.util.Name;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewTripHistorySceneController {
    @FXML
    Button backButton;

    @FXML
    TableView table;

    @FXML
    TableColumn startDateColumn;

    @FXML
    TableColumn startLocationColumn;

    @FXML
    TableColumn endDateColumn;

    @FXML
    TableColumn endLocationColumn;

    @FXML
    TableColumn fareColumn;

    public void initialize(){
        backButton.setOnAction(this::handleBackButton);
    }

    public void handleBackButton(ActionEvent event){

    }
}
