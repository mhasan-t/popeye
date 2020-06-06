/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popeye;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tahnoon
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    String currentUser;
    SqliteDatabase db;
    public void setUser(String usr){
        currentUser = usr;
    }
    public void setDB(SqliteDatabase d){
        db = d;
    }
    @FXML
    Label title;
    

    
    @FXML
    private void goToProfileInfo() throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("profileInfoScene.fxml"));
        Parent parent = (Parent) loader.load();

        ProfileInfoSceneController controller;
        controller = loader.getController();
        controller.setDB(db);
         controller.setUser(currentUser);
         controller.initialize(null, null);
         
         Stage window = (Stage) title.getScene().getWindow();
         Scene scene = new Scene(parent);
         
         window.setScene(scene);
         window.show();
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
