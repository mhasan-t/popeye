/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popeye;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tahnoon
 */
public class CalculatorSceneController implements Initializable {
    String currentUser;
    SqliteDatabase db;
    
    public void setUser(String usr){
        currentUser = usr;
    }
    public void setDB(SqliteDatabase d){
        db = d;
    }
    
    @FXML
    ChoiceBox one, two, three, four, five, six, seven;
    
    @FXML
    Label resultP, resultC;
    
    @FXML
    TextArea recommended;
    
//    ObservableList<Food> foods = FXCollections.observableArrayList(
//            new Food(1, 25, "Apples"),
//            new Food(27, 0, "Chicken"),
//            new Food(1.1, 23, "Banana"),
//            new Food(3.4, 5, "Milk"),
//            new Food(13, 1.1, "Egg"),
//            new Food(30, 0, "Tuna Fish"),
//            new Food(9, 49, "White Bread")
//                    
//    );
    
    ObservableList<String> foods = FXCollections.observableArrayList(
           "Apples",
            "Chicken",
           "Banana",
            "Milk",
          "Egg",
       "Tuna Fish",
        "White Bread");
    
    @FXML
    private void onCalculate(ActionEvent event){
        double totalProtein = 0, totalCarbs = 0;
        System.out.println(getProtein(one.getValue().toString()));
        
        totalProtein+=( getProtein(one.getValue().toString()) + getProtein(two.getValue().toString()) +
                getProtein(three.getValue().toString()) +getProtein(four.getValue().toString()) +
                getProtein(five.getValue().toString()) +getProtein(six.getValue().toString()) +
                getProtein(seven.getValue().toString()) );
        
        totalCarbs+=( getCarb(one.getValue().toString()) + getCarb(two.getValue().toString()) +
                getCarb(three.getValue().toString()) +getCarb(four.getValue().toString()) +
                getCarb(five.getValue().toString()) +getCarb(six.getValue().toString()) +
                getCarb(seven.getValue().toString()) );
        
        resultC.setText("Total Carbs (g) : "+totalCarbs+"");
        resultP.setText("Total Proteins (g) : "+totalProtein+"");
        
    }
    
    private double getProtein(String name){
        if(name.toLowerCase().equals("apples")){
            return 1;
        }
        if(name.toLowerCase().equals("chicken")){
            return 27;
        }
        if(name.toLowerCase().equals("banana")){
            return 1.1;
        }
        if(name.toLowerCase().equals("milk")){
            return 3.4;
        }
        if(name.toLowerCase().equals("egg")){
            return 13;
        }
        if(name.toLowerCase().equals("tuna fish")){
            return 30;
        }
        if(name.toLowerCase().equals("white bread")){
            return 9;
        }
        return 0;
    }
    
    private double getCarb(String name){
        if(name.toLowerCase().equals("apples")){
            return 25;
        }
        if(name.toLowerCase().equals("chicken")){
            return 0;
        }
        if(name.toLowerCase().equals("banana")){
            return 23;
        }
        if(name.toLowerCase().equals("milk")){
            return 5;
        }
        if(name.toLowerCase().equals("egg")){
            return 1.1;
        }
        if(name.toLowerCase().equals("tuna fish")){
            return 0;
        }
        if(name.toLowerCase().equals("white bread")){
            return 49;
        }
        return 0;
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
         
         Stage window = (Stage)one.getScene().getWindow();
         Scene scene = new Scene(parent);
         
         window.setScene(scene);
         window.show();
    }
    
    @FXML
    void addItems(){
        one.setItems(foods);
        two.setItems(foods);
        three.setItems(foods);
        four.setItems(foods);
        five.setItems(foods);
        six.setItems(foods);
        seven.setItems(foods);
    }
    
    
    private void getRecommendedNutients(){
        if(currentUser != null){
            int w = db.getWeight(currentUser);
            double rp = w*0.8;
            String data = "Protein (g): "+ rp +"\n" +
                            "Carbs(g) :\n" +
                            "Vitamin D3:\n" +
                            "Vitamin C:\n" +
                                "Calcium :\n" +
                                "Magnesium :";
            
            recommended.setText(data);
        }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getRecommendedNutients();
        
    }    
    
}


class Food{
    double fat, protein;
    String name;

    public Food(double fat, double protein, String name) {
        this.fat = fat;
        this.protein = protein;
        this.name = name;
    }

    @Override
    public String toString() {
        return name; //To change body of generated methods, choose Tools | Templates.
    }

    
    
}