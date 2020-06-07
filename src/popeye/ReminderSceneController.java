/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popeye;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;




/**
 * FXML Controller class
 *
 * @author Tahnoon
 */
public class ReminderSceneController implements Initializable {
        String currentUser;
    SqliteDatabase db= null;
    ResultSet rs;
    WebView browser=new WebView();
    WebEngine we= browser.getEngine();
    
    public void setUser(String usr){
        currentUser = usr;
    }
    public void setDB(SqliteDatabase d){
        db = d;
    }
    
    @FXML
    TextField note, hour, mins;
    
    @FXML
    Button addBtn;
    
    @FXML
    Label statusMsg;
    
    @FXML
    ScrollPane reminders;
    

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
         
         Stage window = (Stage)note.getScene().getWindow();
         Scene scene = new Scene(parent);
         
         window.setScene(scene);
         window.show();
    }
    
    
    @FXML
    private void addBtnAction(ActionEvent event){
        if( hour.getText().equals("") || mins.getText().equals("")){
            statusMsg.setText("Please fill in hour and minutes boxes!");
        }
        else{
            try {
                db.stmt.executeUpdate("insert into reminder values('"+currentUser+"', '"+note.getText()+"', '"+hour.getText()+"' , '"+mins.getText()+"' )");
                statusMsg.setText("New reminder created successfully");
                if (db!=null){
                    rs = db.returnTableData("reminder");
                    if (rs != null){
                       try {
        //                   System.out.println(reminders);
                           reminders.setContent(browser);

                           String content = "<body style='background-color:#616161;padding-bottom:15px;padding-top:15px;padding-right:10px;padding-left:10px;'>";
                           while(rs.next()){
                               content += "<div style='background-color:#ffffff;padding-bottom:10px;padding-top:5px;padding-right:10px;padding-left:10px;border-radius:20px;'> <span style='color:#ff0000;'>"+rs.getString("hour")+": "+rs.getString("mins")+"</span>" + "<br/>"+
                                       "<span style='vertical-align: middle;'>"+rs.getString("note")+"</span>"+ "</div><br/><br/>"
                                       ;
                           }
                           content+="</body>";

                           we.loadContent(content);
                       } 
                       catch (SQLException ex) {
           //                Logger.getLogger(NewsFeedController.class.getName()).log(Level.SEVERE, null, ex);
                           ex.printStackTrace();
                       }

                   }
                }
                
            } catch (SQLException ex) {
//                Logger.getLogger(ReminderSceneController.class.getName()).log(Level.SEVERE, null, ex);
                statusMsg.setText("Error while creaiting reminder");
                ex.printStackTrace();
            }
        }
    }
    
      
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        if (db!=null){
            rs = db.returnTableData("reminder");
            if (rs != null){
               try {
//                   System.out.println(reminders);
                   reminders.setContent(browser);

                   String content = "<body style='background-color:#616161;padding-bottom:15px;padding-top:15px;padding-right:10px;padding-left:10px;'>";
                   while(rs.next()){
                       content += "<div style='background-color:#ffffff;padding-bottom:10px;padding-top:5px;padding-right:10px;padding-left:10px;border-radius:20px;'>"
                               + " <span style='color:#ff0000;'>"+rs.getString("hour")+": "+rs.getString("mins")+"</span>" + "<br/>"+
                               "<span style='vertical-align: middle;'>"+rs.getString("note")+"</span>"+ "</div><br/><br/>"
                               ;
                   }
                   content+="</body>";

                   we.loadContent(content);
               } 
               catch (SQLException ex) {
   //                Logger.getLogger(NewsFeedController.class.getName()).log(Level.SEVERE, null, ex);
                   ex.printStackTrace();
               }

           }
        }
        

        reminders.setFitToWidth(true);
    }    
    
}
