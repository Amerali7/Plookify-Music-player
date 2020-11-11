/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.logic;

import java.sql.*;
import javax.swing.JTable;

/**
 *
 * @author Fionnuala
 */
public class SaveNowPlaying {
    public static void saveS(int trackID, String song, String artist,int duration){

        String url = "jdbc:sqlite:src/Common/plookifyDB.sqlite";       
        try(Connection connection = DriverManager.getConnection( url)){
            Statement stmt = connection.createStatement();
            String save = "INSERT INTO NowPlaying (trackID,song,artist,duration) " +
                   "VALUES ('"+trackID+"','"+song+"','"+artist+"','"+duration+"');";
            stmt.executeUpdate(save);
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println( e.getMessage() );
        }
    }
    public static void deleteS(int trackID){          
        //connect and delete from database
        String url = "jdbc:sqlite:src/Common/plookifyDB.sqlite";
        
        try(Connection connection = DriverManager.getConnection( url)){
            Statement stmt = connection.createStatement();
            String delete = "DELETE FROM NowPlaying WHERE trackID='"+trackID+"';";
            stmt.executeUpdate(delete);
            
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println( e.getMessage() );
        }        
    }
    //get values from table to either save or delete from database
    public static void getTrackID(JTable table, String a){
        int[] selectedRow;
        selectedRow = table.getSelectedRows();
        if(selectedRow.length!=0){
            for(int i=0;i<selectedRow.length;i++){
               if(a.equals("save")){
                   int trackID = (int) table.getValueAt(selectedRow[i], 0);
                   String song = (String) table.getValueAt(selectedRow[i], 1);
                   String artist = (String) table.getValueAt(selectedRow[i], 2);
                   int duration = (int) table.getValueAt(selectedRow[i], 4);
                   saveS(trackID,song,artist,duration);
               }
               else if(a.equals("delete")){
                   int trackID = (int) table.getValueAt(selectedRow[i], 1);
                   deleteS(trackID);
               }
            }
        } 
    }
    
}
