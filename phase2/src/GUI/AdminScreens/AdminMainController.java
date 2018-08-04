package GUI.AdminScreens;

import AdminUsers.AdminUser;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminMainController extends GeneralControllerScreen {

    private AdminUser adminUser;

    @FXML
    private Button changeAccountInfo;

    @FXML
    private Button manageUsers;

    @FXML
    private Button viewStatistics;


    public void setUpController(){}

    public void setUpController(Object admin){
        this.adminUser = (AdminUser) admin;
    }

    @FXML
    private void handleButtonAction(ActionEvent event){
        Object eve = event.getSource();
        if (eve.equals(changeAccountInfo)){
            String tapScreen = "/GUI/AdminScreens/AdminChangeInfoScreen/AdminChangeInfoScene.fxml";
            this.getControllerHelper().openSameWindow(tapScreen, this.getTs(), event, this.adminUser);
        }
        if (eve.equals(manageUsers)){
            String manageUsers = "GUI/AdminScreens/AdminManageUsersScreen/AdminManageUserScene.fxml";
            this.getControllerHelper().openSameWindow(manageUsers, this.getTs(), event, this.adminUser);
        }
        if (eve.equals(viewStatistics)){
            String statsScreen = "/GUI/AdminScreens/AdminStatistics/AdminStatisticsScene.fxml";
            this.getControllerHelper().openSameWindow(statsScreen, this.getTs(), event, this.adminUser);
        }
    }
}

