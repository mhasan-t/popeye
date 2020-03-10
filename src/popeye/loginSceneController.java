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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Tahnoon
 */
public class loginSceneController implements Initializable {
    
    @FXML
    private Label loginMsg;
    
    @FXML
    private TextField usrName, passwd;
    
    @FXML
    private Button loginBtn;
    
    
    SqliteDatabase db=new SqliteDatabase();
    
    @FXML
    private void loginButtonAction(ActionEvent event) {
        
        
        String usrExists = db.returnPassIfUserExists(usrName.getText());
//        System.out.println(usrExists);
        if(usrExists == "-1"){
            loginMsg.setText("USER DOES NOT EXIST");
        }
        else if(usrExists.equals(passwd.getText())){
//            loginMsg.setText("got it");
            try{
                goHome();
            }
            catch(IOException e){
                System.out.println("line 54 login scene controller");
            }
        }
        else{
            loginMsg.setText("PASS NO MATCH");
        }
        
        
    }
    
    @FXML
    public void goToSignUp() throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("signUpScene.fxml"));
        Parent parent = (Parent) loader.load();
        
        SignUpSceneController controller;
        
        controller = loader.getController();
         controller.setDB(db);
         controller.initialize(null, null);
         
         Stage window = (Stage)usrName.getScene().getWindow();
         Scene scene = new Scene(parent);
         
         window.setScene(scene);
         window.show();
        
        
    }
    
    
    public void goHome() throws IOException{
        
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent parent = (Parent) loader.load();
//         Parent menuSceneRoot = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
         
         
        HomeController controller;
        controller = loader.getController();
         controller.setUser(usrName.getText());
         controller.setDB(db);
         controller.initialize(null, null);
         
         Stage window = (Stage)usrName.getScene().getWindow();
         Scene scene = new Scene(parent);
         
         window.setScene(scene);
         window.show();
    }
    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
