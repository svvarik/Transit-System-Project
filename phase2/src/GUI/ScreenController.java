package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class ScreenController{
    HashMap<String, Parent> screens;

    public ScreenController(){
        super();
        this.screens = new HashMap<>();
        try {
            addScreen("/GUI/HomeScreen/MainScene.fxml", "Main Screen");
            addScreen("/GUI/TapScreen/TapScene.fxml", "Tap Screen");
            addScreen("/GUI/AdminPackage/AdminHomePackage/AdminLoginScreen/AdminHomePage/AdminLoginScene.fxml", "Admin Login Screen");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void addScreen(String url, String name){
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(url));
            Parent loadScreen = (Parent) myLoader.load();
            ControlledScreen myScreenController = ((ControlledScreen) myLoader.getController());
            System.out.println(myScreenController);
            myScreenController.setScreenController(this);
            this.screens.put(name, loadScreen);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void stageScreen(String name, Stage stage){
        Parent root = this.screens.get(name);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
