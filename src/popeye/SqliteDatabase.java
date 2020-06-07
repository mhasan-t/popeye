/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popeye;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tahnoon
 */
public class SqliteDatabase {
    
    Connection c;
    Statement stmt = null;
    public String status = "NO";

    SqliteDatabase(){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
            stmt = c.createStatement();

//            stmt.executeUpdate("insert into post values('admin', 'whatwhatwhatwhatwhatwhat')");
//            this.enterPostTableData("test", "testing");
//            System.out.println("executed");
//            stmt.executeUpdate("create table reminder (username string, note string, hour string, mins string)");
//            System.out.println("new table created");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    void getConn(){
        if( c== null){
            try{
                c = DriverManager.getConnection("jdbc:sqlite:database.db");
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
    void printData(String tablename){
        try{
            ResultSet rs = stmt.executeQuery("select * from "+tablename);
            while (rs.next()){
                System.out.println("USERNAME  : "+rs.getString("username")+" --- PASSWORD : "+ rs.getString("password"));
            }
        }
       catch (Exception e){
            e.printStackTrace();
        }
    }
    
    void enterUserTableData(String username, String password){
        try{
            stmt.executeUpdate("insert into user  values('"+username+"','"+password+"' , null,null,null,null,null)");
//            this.stmt.executeUpdate("insert into user values('first', 10)");
        }
       catch (Exception e){
            e.printStackTrace();
        }
    }
    
    void enterPostTableData(String username, String post){
        try{
            stmt.executeUpdate("insert into post values('"+username+"', '"+post+"')");
        }
       catch (Exception e){
            e.printStackTrace();
        }
    }
    
    
    boolean checkIfStrExists(String tableName, String columnName, String value){ //true if exists
        
        ResultSet res = returnTableData(tableName);
        
        boolean found  = false;
        
        try{
            while(res.next()){
                if(res.getString(columnName).equals(value)){
                    found = true;
                    break;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return found;
    }
    
    
//    String returnPassIfUserExists(String usrName){ //true if exists
//        
//        ResultSet res = returnTableData("user");
//        
//        String found  = "-1";
//        
//        try{
//            while(res.next()){
//                if(res.getString("username").equals(usrName)){
//                    found = res.getString("password");
//                    break;
//                }
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return found;
//    }
//    
//    ResultSet returnTableData(String tablename){
//        try{
//            ResultSet rs = stmt.executeQuery("select * from "+tablename);
//            return rs;
//        }
//       catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }

    
    String returnPassIfUserExists(String usrName){ //true if exists
        String found  = "-1";
        
        try{
            ResultSet res = stmt.executeQuery("select username, password from user where username='"+usrName+"'");
            while(res.next()){
                if(res.getString("username").equals(usrName)){
                    found = res.getString("password");
//                    System.out.println(found);
                    break;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return found;
    }
    
    
    
    int getWeight(String n){
        try{
            ResultSet res = stmt.executeQuery("select username, weight from user where username='"+n+"'");
            while(res.next()){
                if(res.getString("username").equals(n)){
                    if( res.getString("weight") == null ){
                        return 0;
                    }
                    else return Integer.parseInt(res.getString("weight"));
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    
    ResultSet returnTableData(String tablename){
        try{
            ResultSet rs = stmt.executeQuery("select * from "+tablename);
            return rs;
        }
       catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    void closeConnection(){
        try{
            c.close();
        }
        catch(SQLException e){
            
        }
    }
    
    
    ResultSet getUserInfo(String usrName){
        try{
            ResultSet rs = stmt.executeQuery("select * from user where username='"+usrName+"'");
            return rs;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
  
    
//    public static void main(String[] args) {
//        SqliteDatabase db=new SqliteDatabase();
//        
//        try{
//            db.stmt = db.c.createStatement();
//            db.stmt.executeUpdate("drop table user ");
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        
//        
//    }
    
}
