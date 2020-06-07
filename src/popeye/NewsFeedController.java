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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tahnoon
 */
public class NewsFeedController implements Initializable {
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
    TextField makeApost;
    
    @FXML
    Button postBtn;
    
    @FXML
    Button gobackBtn;
    
    @FXML
    Button refreshBtn;
    
    @FXML
    Label statusMsg;
    
    @FXML
    ScrollPane feedPane;
    

    
    @FXML
    private void postButtonAction(ActionEvent event) {
        String post="";
//        System.out.println(makeApost.getText());
        try{
            post = makeApost.getText();
            
        }
        catch(Exception e){
            post="";
        }
        if( !post.equals("") ){
            db.enterPostTableData(currentUser, post);
            if (db!=null){
                rs = db.returnTableData("post");
                if (rs != null){
                   try {
                       feedPane.setContent(browser);

                       String content = "<body style='background-color:#d4f5fa;padding-bottom:30px;padding-top:30px;padding-right:20px;padding-left:20px;'>";
                       while(rs.next()){
                           content += "<div style='background-color:#ffffff;border-radius:200px;'> <span style='color:#66c2ed;'>@"+rs.getString("username")+"</span>" + "<br/>"+
                                   "<span style='vertical-align: middle;'>"+rs.getString("post")+"</span>"+ "</div><br/><br/>"
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
            statusMsg.setText("Posted!");
        }
        else{
            statusMsg.setText("You must write something to post!");
        }
    }
    
    
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
         
         Stage window = (Stage)makeApost.getScene().getWindow();
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

             Stage window = (Stage)makeApost.getScene().getWindow();
             Scene scene = new Scene(parent);

             window.setScene(scene);
             window.show();
            
        }
        catch(IOException e){
                e.printStackTrace();
        }
    }
    
    @FXML
    private void refreshFeed(ActionEvent event){
        if (db!=null){
            rs = db.returnTableData("post");
            if (rs != null){
               try {
                   feedPane.setContent(browser);

                   String content = "<body style='background-color:#1f3740;padding-bottom:30px;padding-top:30px;padding-right:20px;padding-left:20px;'>";
                   while(rs.next()){
                       content += "<div style='background-color:#ffffff;padding-bottom:10px;padding-top:5px;padding-right:10px;padding-left:10px;border-radius:10px;'> <span style='color:#66c2ed;'>@"+rs.getString("username")+"</span>" + "<br/>"+
                               "<span style='vertical-align: middle;'>"+rs.getString("post")+"</span>"+ "</div><br/><br/>"
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
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (db!=null){
            rs = db.returnTableData("post");
            if (rs != null){
               try {
                   feedPane.setContent(browser);

                   String content = "<body style='background-color:#1f3740;padding-bottom:30px;padding-top:30px;padding-right:20px;padding-left:20px;'>";
                   while(rs.next()){
                       content += "<div style='background-color:#ffffff;padding-bottom:10px;padding-top:5px;padding-right:10px;padding-left:10px;border-radius:10px;'> <span style='color:#66c2ed;'>@"+rs.getString("username")+"</span>" + "<br/>"+
                               "<span style='vertical-align: middle;'>"+rs.getString("post")+"</span>"+ "</div><br/><br/>"
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
        
        feedPane.setFitToWidth(true);
    }    
    
}
