/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popeye;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tahnoon
 */
public class EditUsernameController implements Initializable {
    
    String currentUser;
    SqliteDatabase db;
    
    public void setUser(String usr){
        currentUser = usr;
    }
    public void setDB(SqliteDatabase d){
        db = d;
    }
    
    @FXML
    TextField newPass;
    @FXML
    TextField newUsername;
    @FXML
    Label statusMsg;
    
    @FXML
    private void updateButtonAction(ActionEvent event) throws IOException {
        String usrname = newUsername.getText(), 
                pass = newPass.getText();
        
        String usrExists = db.returnPassIfUserExists(usrname);
        
        if( usrExists == "-1" ){
            String query =  "update user set username ='"+usrname+"',password ='"+pass+"'where username = '"+currentUser+"'";
            currentUser = usrname;

            try{
                db.stmt.executeUpdate(query);
                statusMsg.setText("Updated Succesfully!");
            }
            catch(SQLException e){
    //            System.out.println("line52 profilescenecontroller");
                statusMsg.setText("Error! Could not update!");
                e.printStackTrace();
            }
        }
        else{
            statusMsg.setText("Username already exists!");
        }
        
            
    }
    
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
         
         Stage window = (Stage) newUsername.getScene().getWindow();
         Scene scene = new Scene(parent);
         
         window.setScene(scene);
         window.show();
    }
    
    @FXML
    private void logoutBtnAction(ActionEvent event){
        try{
            File f=new File("loginState.txt");
            PrintWriter pw=new PrintWriter(f);
            pw.println("no");
            pw.close();
            
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("loginScene.fxml"));
            Parent parent = (Parent) loader.load();

            loginSceneController controller;

            controller = loader.getController();
             controller.initialize(null, null);

             Stage window = (Stage)newUsername.getScene().getWindow();
             Scene scene = new Scene(parent);

             window.setScene(scene);
             window.show();
            
        }
        catch(IOException e){
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
