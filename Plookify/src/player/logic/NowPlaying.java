/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.logic;
import java.sql.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.TableColumn;
/**
 *
 * @author Fionnuala
 */
public class NowPlaying extends player.GUI.nowPlayingGUI {
    public NowPlaying(){
        
    }
    public static void nowP(JTable table){
        SaveNowPlaying.getTrackID(table,"save");
        display();
    }
    public static void hideColumn(JTable table){
        TableColumn playerID = table.getColumnModel().getColumn(0);
        TableColumn trackID = table.getColumnModel().getColumn(1);
        TableColumn duration = table.getColumnModel().getColumn(4);
        playerID.setMinWidth(0);
        trackID.setMinWidth(0);
        duration.setMinWidth(0);
        playerID.setMaxWidth(0);
        trackID.setMaxWidth(0);
        duration.setMaxWidth(0);
    }
    public static void deleteAll(){
        String url = "jdbc:sqlite:src/Common/plookifyDB.sqlite";
        
        try(Connection connection = DriverManager.getConnection( url)){
            Statement stmt = connection.createStatement();
            String delete = "DELETE FROM NowPlaying;";
            stmt.executeUpdate(delete);
            
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println( e.getMessage() );
        }  
    }
}
