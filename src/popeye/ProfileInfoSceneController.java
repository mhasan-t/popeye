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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tahnoon
 */
public class ProfileInfoSceneController implements Initializable {
    
    String currentUser;
    SqliteDatabase db;
    
    public void setUser(String usr){
        currentUser = usr;
    }
    public void setDB(SqliteDatabase d){
        db = d;
    }
   
    @FXML
    Label nameInfo, ageInfo, heightInfo, weightInfo;
    
    @FXML
    Button editBtn, gobackBtn;
    
    
    
    @FXML
    public void goHome(ActionEvent event) throws IOException{
        
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent parent = (Parent) loader.load();
//         Parent menuSceneRoot = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
         
         
        HomeController controller;
        controller = loader.getController();
         controller.setUser(currentUser);
         controller.setDB(db);
         controller.initialize(null, null);
         
         Stage window = (Stage)nameInfo.getScene().getWindow();
         Scene scene = new Scene(parent);
         
         window.setScene(scene);
         window.show();
    }
    
    
    public void goToProfileEdit() throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("profileEditScene.fxml"));
        Parent parent = (Parent) loader.load();
//         Parent menuSceneRoot = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
         
         
        ProfileEditSceneController controller;
        controller = loader.getController();
        controller.setDB(db);
         controller.setUser(currentUser);
         controller.initialize(null, null);
         
         Stage window = (Stage) nameInfo.getScene().getWindow();
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

             Stage window = (Stage)nameInfo.getScene().getWindow();
             Scene scene = new Scene(parent);

             window.setScene(scene);
             window.show();
            
        }
        catch(IOException e){
                e.printStackTrace();
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(currentUser!=null){
            ResultSet rs = db.getUserInfo(currentUser);
            try {
                if( rs.getString("fullname") != null ){
                    nameInfo.setText(rs.getString("fullname"));
                }
                else{
                    nameInfo.setText("Name has not been set yet.");
                }
                
                if( rs.getString("fullname") != null ){
                    heightInfo.setText(rs.getString("height"));
                }
                else{
                    heightInfo.setText("Height has not been set yet.");
                }
                
                if( rs.getString("fullname") != null ){
                    weightInfo.setText(rs.getString("weight"));
                }
                else{
                    weightInfo.setText("Weight has not been set yet.");
                }
                
                if( rs.getString("fullname") != null ){
                    ageInfo.setText(rs.getString("age"));
                }
                else{
                    ageInfo.setText("Age has not been set yet.");
                }
            
            } catch (SQLException ex) {
    //            Logger.getLogger(ProfileInfoSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            System.out.println("USER COULD NOT BE LOADED");
        }
        
    }    
    
}



//ResultSetMetaData rsmd = rs.getMetaData();
//            int columnsNumber = rsmd.getColumnCount();
//            while (rs.next()) {
//                for (int i = 1; i <= columnsNumber; i++) {
//                    if (i > 1) System.out.print(",  ");
//                    String columnValue = rs.getString(i);
//                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
//                }
//                System.out.println("");
//            }