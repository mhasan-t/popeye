/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popeye;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Tahnoon
 */
public class ProfileSceneController implements Initializable {
    String currentUser;
    public void setUser(String usr){
        currentUser = usr;
    }
    
    SqliteDatabase db;
    public void setDB(SqliteDatabase d){
        db = d;
    }
    
    @FXML
    TextField name, age, weight, height, usrname, passwd;
    
    @FXML
    Label msg;
    
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
