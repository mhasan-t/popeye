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
public class SignUpSceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    SqliteDatabase db;
    public void setDB(SqliteDatabase d){
        db = d;
    }
    
    @FXML
    private Label signupMsg;
    
    @FXML
    private TextField usrName, passwd;
    
    @FXML
    private Button signupBtn;
    
    
    @FXML
    private void signUpButtonAction(ActionEvent event) {
        
//        SqliteDatabase db=new SqliteDatabase();
        String usrExists = db.returnPassIfUserExists(usrName.getText());
//        System.out.println(usrExists);
        if(usrExists == "-1"){
            
            if(passwd.getText().length() < 6){
                signupMsg.setText("Password must be at least 6 characters.");
            }
            
            else{
                db.enterUserTableData(usrName.getText(), passwd.getText());
                signupMsg.setText("New User Created");

                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }

                try{
                    goToLogin();
                }
                catch(IOException e ){
                    System.out.println("IO EXCEPTION LINE 64 menu scene controller");
                }
            
            }
            
        }
        else{
            signupMsg.setText("USER ALREADY EXISTS");
        }
    }
    
        @FXML
    public void goToLogin() throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("loginScene.fxml"));
        
        Stage window =(Stage) usrName.getScene().getWindow();
        Scene scene = new Scene(parent);
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
