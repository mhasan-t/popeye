/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popeye;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Tahnoon
 */
public class ReminderAlertController implements Initializable {
    String stime, snote;

    void setTimeNote(String stime, String snote) {
        this.stime = stime;
        this.snote = snote;
    }
    
    
    
    @FXML
    Label time, note;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        if ( stime!=null ){
            time.setText(stime);
        }
        
        if(snote!=null){
            note.setText(snote);
        }
        
    }    
    
}
