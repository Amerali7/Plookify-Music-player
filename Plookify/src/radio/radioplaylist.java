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
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author abbas
 */
public class radioplaylist {

    public static String saveradio(String emails) throws SQLException {
        String play = JOptionPane.showInputDialog("playlist name?");

        if (play.length() == 0) {
            JOptionPane.showMessageDialog(null, "playlist name cannot be blank");
            //return;
        }
        String art = "";
        int check=0;
        try {

            String sql = "Select * from Account";
            Connection con = Common.db.connectToDB();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String query = "select accountID from Account where email='" + emails + "' ";
            // PreparedStatement pst= con.prepareStatement(query);
            ResultSet rsAccount = stmt.executeQuery(query);
            art = rsAccount.getString("accountID");
            stmt.close();
            rsAccount.close();
            rs.close();
            con.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        try {
            Connection con = Common.db.connectToDB();
            String sql = "Select name from PlaylistManager  where  name = '" + play + "' and accountID = '" + art + "' ";
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
           if(rs.next()) {
                JOptionPane.showMessageDialog(null, "playlist name already exists");
                play="no";
                System.out.println(play);
            }
            
            stmt.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
         System.out.println(play);
        if (!play.equals("no"))
        {
        try {
            Connection conn = Common.db.connectToDB();
            Statement smt;
            smt = conn.createStatement();
            String insertPlaylistManage = "INSERT INTO PlaylistManager ('accountID','name') VALUES ('" + art + "','" + play + "')";
            smt.executeUpdate(insertPlaylistManage);

            smt.close();
            conn.close();
        } catch (Exception e) {

        }
        }

        return play;
    }

    public static void addradiosongs(String[] id, String name) throws SQLException {

        String sql = "Select * from PlaylistManager";
        Connection con = Common.db.connectToDB();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        String query = "select playlistID from PlaylistManager where name='" + name + "' ";
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rsAccount = stmt.executeQuery(query);
        String plist = rsAccount.getString("playlistID");
        stmt.close();
        rsAccount.close();
        rs.close();
        con.close();

        Connection conn = Common.db.connectToDB();
        Statement smt = null;

        for (int i = 0; i < id.length; i++) {
            smt = conn.createStatement();
            String insertPlaylistManage = "INSERT INTO Playlist ('playlistID','trackID') VALUES ('" + plist + "','" + id[i] + "')";
            smt.executeUpdate(insertPlaylistManage);
        }
        smt.close();
        conn.close();

    }

}
