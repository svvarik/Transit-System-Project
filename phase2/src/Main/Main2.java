package Main;


import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main2 extends Application {

    private TransitSystem transitSystem;


    @Override
    public void start(Stage primaryStage) throws Exception {

        //this.transitSystem = new TransitSystem();
        TransitSystemStarter tsStart = new TransitSystemStarter();
        this.transitSystem = tsStart.getTs();
        
   //     this.transitSystem.getCardHolders().addCardHolder("faraz","faraz", "pass", this.transitSystem);
        String mainScreen = "/GUI/HomeScreen/MainScene.fxml";
//String userpath = "/GUI/UserPackage/UserLogInPackage/UserLoginScreen.fxml"
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource(mainScreen)));
        Parent root = loader.load();
        GeneralControllerScreen controller = loader.getController();
        controller.setTransitSystem(this.transitSystem);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
//        TransitSystemStarter tsStart = new TransitSystemStarter();
//        tsIO = tsStart.getTsIO();

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        launch(args);
    }

}


