/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author abbas
 */
public class radiologin {
    public static String login(){
      String logged="";       
    String sql="Select * from Account";
    int tmp=0;
          try {
                ResultSet rs =null;
               Connection con = Common.db.connectToDB();
           Statement stmt=con.createStatement();
   rs = stmt.executeQuery(sql);
   String user=JOptionPane.showInputDialog("email");
    String pwd=JOptionPane.showInputDialog("password");
    while(rs.next()) {
    String uname=rs.getString("email");
    //email is the username in which the user uses to log in and is therefore coloumn name in the database table
    String password=rs.getString("password");
    if ((user.equals(uname)) && (pwd.equals(password))){
    logged=user;
    System.out.println("logged in successfully");
    }
    }    
    stmt.close();
    con.close();
    
        //  Table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch ( Exception e ) {
            
           } 
           return logged;
    }
    
    
    public static boolean usertype(String logge){
         String sql="Select * from Account";
         boolean a=false;
         try {
              ResultSet rs =null;
               Connection con = Common.db.connectToDB();
           Statement stmt=con.createStatement();
            rs = stmt.executeQuery(sql);
             String query ="select accountType from Account where email='"+logge+"' ";
             PreparedStatement pst= con.prepareStatement(query);
              rs = pst.executeQuery();
              String art = rs.getString("accountType");
                System.out.println(art);
              if (art.equals("p")){
                  a=true;
              }
              stmt.close();
              con.close();
                          
              
             
        } catch ( Exception e ) {
            
           } 
           return a;
    }
   
}
