package Main;


import javafx.application.Application;
import javafx.stage.Stage;


public class Main2 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ScreenController sc = new ScreenController();
        sc.stageScreen("Main Screen", primaryStage);
//        Parent root = FXMLLoader.load(getClass().getResource("/GUI/MainScreen/MainScene.fxml"));
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
    }

    public static void main(String args[]){
        launch(args);
    }
}
