/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popeye;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Tahnoon
 */
public class Popeye extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        SqliteDatabase db=new SqliteDatabase();
        
        
        //starting reminders
        ResultSet rsReminders = db.returnTableData("reminder");
        try{
            while(rsReminders.next()){
               
                new InitReminder(rsReminders.getString("hour"), rsReminders.getString("mins"), rsReminders.getString("note")).start();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        
        
        //checking login session
        File f=new File("loginState.txt");
        String fxmlFile = "loginScene.fxml";
        try {
            Scanner fileScanner =new Scanner(f);
            String state="";
            try{
                state = fileScanner.nextLine();
            }
            catch(Exception e){
                System.out.println("No state found.");
            }
            
            if (state.equals("yes")){
                System.out.println("it says yes");
                String loggedInUser = fileScanner.nextLine();
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("home.fxml"));
                Parent parent = (Parent) loader.load();
                
                HomeController controller;
                controller = loader.getController();
                 controller.setUser(loggedInUser);
                 controller.setDB(new SqliteDatabase());
                 controller.initialize(null, null);
                
                 Scene scene = new Scene(parent);
                 stage.setScene(scene);
                  stage.show();
            }
            else{
                
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("loginScene.fxml"));
                Parent root = (Parent) loader.load();
                
                loginSceneController controller;
                controller = loader.getController();
                controller.setDB(db);
                controller.initialize(null, null);
        
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            }
        } catch (FileNotFoundException e) {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("loginScene.fxml"));
            Parent root = (Parent) loader.load();

            loginSceneController controller;
            controller = loader.getController();
            controller.setDB(db);
            controller.initialize(null, null);

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        }
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}



class InitReminder extends Thread{
    String hour,mins, note;

    public InitReminder(String hour, String mins, String note) {
        this.hour = hour;
        this.mins = mins;
        this.note = note;
    }

    
    
    
    @Override
    public void run() {
        
        Timer timer=new Timer();
        MyTask task=new MyTask(hour, mins, note);
        Date currentTime=new Date();
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(currentTime).substring(0,11);
        
        String currentHour = dateFormat.format(currentTime).substring(11,13);
        String currentMin = dateFormat.format(currentTime).substring(14,16);
        String alarmTime = hour+":"+mins+":00";
        
        if( Integer.parseInt(hour) < Integer.parseInt(currentHour) ){
            System.out.println("A REMINDER PASSED");
            return;
        }
        else if ( Integer.parseInt(hour) == Integer.parseInt(currentHour) ){
            if ( Integer.parseInt(mins) <= Integer.parseInt(currentMin) ){
                System.out.println("A REMINDER PASSED");
                return;
            }
        }
        
        
        Date alarm;
        try {
             alarm= dateFormat.parse(date+alarmTime);
             timer.schedule(task, alarm);
             System.out.println("STARTED REMINDER FOR "+hour+":"+mins);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}




