package MemoryGame;

import MemoryGame.MainWindow.MainWindowClasses.CardFactor;
import MemoryGame.MainWindow.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow/MainWindow.fxml"));
        primaryStage.setTitle("Memory Game");
        primaryStage.setScene(new Scene(root, 536, 450));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
