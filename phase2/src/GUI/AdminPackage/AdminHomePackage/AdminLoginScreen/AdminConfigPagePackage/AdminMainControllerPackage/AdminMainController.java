package GUI.AdminPackage.AdminHomePackage.AdminLoginScreen.AdminConfigPagePackage.AdminMainControllerPackage;

import GUI.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminMainController extends GeneralControllerScreen {

    @FXML
    private Button changeAccountInfo;

    @FXML
    private Button manageUsers;

    @FXML
    private Button viewStatistics;


    public void initialize(javafx.event.ActionEvent actionEvent) {
        changeAccountInfo.setOnAction(this::handleButtonAction);
        manageUsers.setOnAction(this::handleButtonAction);
        viewStatistics.setOnAction(this::handleButtonAction);

    }

    private void handleButtonAction(javafx.event.ActionEvent event){
        Object eve=event.getSource();
        if (eve.equals(changeAccountInfo)){
            ControllerHelper newControllerHelper = new ControllerHelper();
            String tapScreen = "/GUI/AdminPackage/AdminHomePackage/AdminLoginScreen/AdminConfigPagePackage/AdminMainControllerPackage/AdminChangeInfoScreen/AdminChangeInfoScene.fxml";
            newControllerHelper.switchScreens(event, tapScreen);
        }
        if (eve.equals(manageUsers)){
            System.out.println("manageUsers");
        }
        if (eve.equals(viewStatistics)){
            ControllerHelper newControllerHelper = new ControllerHelper();
            String tapScreen = "/GUI/AdminPackage/AdminHomePackage/AdminLoginScreen/AdminConfigPagePackage/AdminMainControllerPackage/AdminStatistics/AdminStatisticsScene.fxml";
            newControllerHelper.switchScreens(event, tapScreen);
        }
    }



    }

