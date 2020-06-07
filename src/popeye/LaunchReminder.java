/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popeye;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 *
 * @author Tahnoon
 */
public class LaunchReminder extends Application{
    public static String time = "";
    public static String note = "";
    
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println();
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("reminderAlert.fxml"));
        Parent root = (Parent) loader.load();

        ReminderAlertController controller;
        controller = loader.getController();
        controller.setTimeNote(time, note);
        controller.initialize(null, null);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        Thread.sleep(5);
        stage.close();

    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
