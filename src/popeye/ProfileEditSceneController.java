/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popeye;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tahnoon
 */
public class ProfileEditSceneController implements Initializable {
    String currentUser;
    SqliteDatabase db;
    
    public void setUser(String usr){
        currentUser = usr;
    }
    public void setDB(SqliteDatabase d){
        db = d;
    }
    
    @FXML
    TextField name, age, weight, height, usrname, passwd;
    
    @FXML
    Label msg;
    
    @FXML
    Button gobackBtn;
    
    
    @FXML
    private void goToProfileInfo(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("profileInfoScene.fxml"));
        Parent parent = (Parent) loader.load();

        ProfileInfoSceneController controller;
        controller = loader.getController();
        controller.setDB(db);
         controller.setUser(currentUser);
         controller.initialize(null, null);
         
         Stage window = (Stage) name.getScene().getWindow();
         Scene scene = new Scene(parent);
         
         window.setScene(scene);
         window.show();
    }
    
     @FXML
    private void updateButtonAction(ActionEvent event) {
         System.out.println(currentUser);
        String newName = name.getText() ,
                newUsrname = usrname.getText(), 
                NewPass = passwd.getText();
        
        int newAge = Integer.parseInt(age.getText()) , 
                newWeight =  Integer.parseInt(weight.getText()), 
                newHeight =  Integer.parseInt(height.getText()) ;
                
        
//        SqliteDatabase db=new SqliteDatabase();
        String query =  "update user set age ="+newAge+",weight ="+newHeight+",height ="+newHeight+",fullname ='"+ newName +"'where username = '"+currentUser+"'";
       
        try{
            db.stmt.executeUpdate(query);
        }
        catch(SQLException e){
//            System.out.println("line52 profilescenecontroller");
            e.printStackTrace();
        }
        
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
