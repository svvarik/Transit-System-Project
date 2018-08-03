package Main;


import Data.TransitSystemSerializer;
import FareSystem.Card;
import Stations.BusStation;
import Stations.SubwayStation;
import TransitUsers.CardHolder;
import AdminUsers.AdminUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class Main2 extends Application {

    TransitSystemIO tsIO;


    @Override
    public void start(Stage primaryStage) throws Exception {
    Parent root =
        FXMLLoader.load(
            getClass().getResource("/GUI/HomeScreenPackage/MainScreen/MainScene.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
//        TransitSystemStarter tsStart = new TransitSystemStarter();
//        tsIO = tsStart.getTsIO();

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        launch(args);
    }

}


