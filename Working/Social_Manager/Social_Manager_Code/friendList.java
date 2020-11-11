/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Social.Social_Manager_Code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Sofia
 */
public class friendList extends javax.swing.JPanel {

    /**
     * Creates new form friendList
     */
    public friendList() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table_friends = new javax.swing.JTable();

        table_friends.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Your Friends"
            }
        ));
        jScrollPane1.setViewportView(table_friends);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    public static int counter = 0;
    public static int noButtons = 100;
    public static String[] noFriends = new String[noButtons];
    private static JButton[] buttons  = new JButton[noButtons];
    
    public void updateFriendList(){
        Connection conn = SocialManagerSearch.connectDatabase();
        System.out.println("updateFriendList : Database has opened successfully");
        
        String qry = "select * from FriendConfirmed where userID = 'Sofia'"; //'" + userFirstName + "'
        try{
            PreparedStatement pst = conn.prepareStatement(qry);
            ResultSet rs = pst.executeQuery();
            System.out.println("gets here");
            while (rs.next()) {
                
                String userID = rs.getString("userID");
                String friendID = rs.getString("friendID");
                
                if(userID.equals(userID)){
                    System.out.println("Your Name : " + userID);
                    System.out.println("Friend's name : " + friendID);
                    
                    //Instead of an array open new buttons here
                    noFriends[counter] = friendID; //the position in the array is now set to the first message  
                    counter = counter + 1;
                }
                
            }
            
            
            

            
        /*    for(int i = 0; i < noFriends.length; i++)
            {
                // make new button name 
                buttons[i]  = new JButton(noFriends[i]);
                buttons[i].setBounds(20,40,50,10);
                // add button to panel
                add(/*new JButton(buttons[i]);
                //System.out.println(buttons[i]);
                repaint();
            }
           // add(friendList_panel);
            //friendList_panel.setVisible(true);*/
       
        
        
        }
        
        catch (Exception w) {
            JOptionPane.showMessageDialog(null, "updateFriendList  : " + w);
        }
        finally{
            try{
                conn.close();
                System.out.println("updateFriendList connection closed");
                updatingFriendTable();
            } 
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "updateFriendList 1 : " + ex);
            }
        }
    }
    
    public void updatingFriendTable(){
        
        DefaultTableModel models = (DefaultTableModel) table_friends.getModel();
            try{
                for(int m = 1; m <= counter; m++){  
                    
                    models.addRow(new Object[]{noFriends[m-1]});  
                    System.out.println("updating the friend table " + noFriends[m-1]);
                    
                }
                
            }     
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "updatingFriendTable " + e);
                e.printStackTrace();
            } 
            
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_friends;
    // End of variables declaration//GEN-END:variables
}
