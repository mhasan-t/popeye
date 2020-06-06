/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popeye;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
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
                Parent root = FXMLLoader.load(getClass().getResource("loginScene.fxml"));
        
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            }
        } catch (FileNotFoundException e) {
            Parent root = FXMLLoader.load(getClass().getResource("loginScene.fxml"));
        
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
