/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author abbas
 */
public class radiologic {
    public ResultSet getartistlist(Connection connection)throws SQLException{
        ResultSet rs =null;
           try {
             
          String query ="select DISTINCT artist from track";
          PreparedStatement pst= connection.prepareStatement(query);
          rs = pst.executeQuery();
          
       
        } catch ( Exception e ) {
            
           } 
           
           return rs;
    }
    
    public ResultSet searchartist(String searchterm,Connection connection) throws SQLException{
          ResultSet rs =null;
        try {     
          
       
             String query ="select DISTINCT artist from track where artist LIKE '%"+ searchterm +"%'";
             PreparedStatement pst= connection.prepareStatement(query);
             
              //pst.setString(1,searchterm);
                rs = pst.executeQuery();
                //pst.close();
        }
        catch ( Exception e ) {
            
           }
        return rs;
        }
     public String getselectedartist(String selected)throws SQLException{
         ResultSet rs =null;
         String art="";
         try { 
             Connection connection = Common.db.connectToDB();
             String sql="select genre from track where artist='"+selected+"' ";
         PreparedStatement pst= connection.prepareStatement(sql);
         rs = pst.executeQuery();
          art = rs.getString("genre");
        
          System.out.println(art);
          connection.close();
         }
         catch ( Exception e ) {
            
           }
     return art;
     }
     
      public ResultSet generateradio(String theart, Connection connection)throws SQLException{
           ResultSet rs =null;
           String genrename="";
        try {     
         genrename= getselectedartist(theart);
        
        String randoms= "SELECT * FROM track Where genre='"+genrename+"' ORDER BY RANDOM() LIMIT 10";
         PreparedStatement psta= connection.prepareStatement(randoms);  
       
                 rs = psta.executeQuery();
                
        }
          catch ( Exception e ) {
            
           }
         return rs; 
      }
     
    
}
