
package Social.Social_Manager_Code;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class socialManagerPanel extends javax.swing.JPanel {
    //Connection conn = SocialManagerSearch.connectDatabase();
    public static String userLoggedIn = "";
    public static String userFirstName = "";
    public static String userLastName = "";
    public static String userAccountType = "";
    public static String userDiscoverable = "";
    
    public static int counter = 0;
    public static int noButtons = 100;

    public static String userID = "";
    public static String[] friendID = new String[noButtons];
    
    public static int noMessages = 0;
    public static int noMessagesANDone = 100;
    public static String[] ofMessages = new String[noMessagesANDone];
    public static String[] ofFriendName = new String[noMessagesANDone];
    

    public socialManagerPanel() {
        initComponents();
        updateFriendList();
    }//END socialManagerPanel

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button_becomeDiscoverable = new javax.swing.JButton();
        button_becomePrivate = new javax.swing.JButton();
        Button_Messages = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_friends = new javax.swing.JTable();
        Button_refreshFriends = new javax.swing.JButton();
        Button_removeFriends = new javax.swing.JButton();
        Button_search = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(121, 378));

        button_becomeDiscoverable.setText("Discoverable");
        button_becomeDiscoverable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        button_becomeDiscoverable.setBorderPainted(false);
        button_becomeDiscoverable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_becomeDiscoverableActionPerformed(evt);
            }
        });

        button_becomePrivate.setText("Private");
        button_becomePrivate.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        button_becomePrivate.setBorderPainted(false);
        button_becomePrivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_becomePrivateActionPerformed(evt);
            }
        });

        Button_Messages.setText("Messages");
        Button_Messages.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Button_Messages.setBorderPainted(false);
        Button_Messages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_MessagesActionPerformed(evt);
            }
        });

        table_friends.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Friends"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_friends);

        Button_refreshFriends.setText("Refresh");
        Button_refreshFriends.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Button_refreshFriends.setBorderPainted(false);
        Button_refreshFriends.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_refreshFriendsActionPerformed(evt);
            }
        });

        Button_removeFriends.setText("Remove Friends");
        Button_removeFriends.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Button_removeFriends.setBorderPainted(false);
        Button_removeFriends.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_removeFriendsActionPerformed(evt);
            }
        });

        Button_search.setText("Search");
        Button_search.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Button_search.setBorderPainted(false);
        Button_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Button_search, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_refreshFriends, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(button_becomeDiscoverable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(button_becomePrivate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Button_removeFriends, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Button_Messages, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_refreshFriends)
                    .addComponent(Button_search))
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(Button_Messages, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_removeFriends)
                .addGap(1, 1, 1)
                .addComponent(button_becomePrivate)
                .addGap(1, 1, 1)
                .addComponent(button_becomeDiscoverable)
                .addGap(4, 4, 4))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void userSignedIn(String name) {
        table_friends.revalidate();
        userLoggedIn = name;
        System.out.println("user logged in is as it is passed is  " + userLoggedIn);
        
        updateUserDetails();  
    }//END userSignedIn    
       
    public void updateUserDetails(){
        String qry = "select * from Account where email= '" + userLoggedIn + "'";
        Connection conn = SocialManagerSearch.connectDatabase();
        System.out.println("updateUserDetails : Database has opened successfully");
        try {
            PreparedStatement pst = conn.prepareStatement(qry);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                userFirstName = rs.getString("firstName");
                userLastName = rs.getString("lastName");
                userAccountType = rs.getString("accountType");
                userDiscoverable = rs.getString("Discoverable");

                System.out.println("FirstName : " + userFirstName);
                System.out.println("LastName : " + userLastName);
                System.out.println("AccountType : " + userAccountType);
                System.out.println("Discoverable : " + userDiscoverable);
                System.out.println();
                pst.close();
                rs.close();
            }
        } 
        catch (Exception w) {
            JOptionPane.showMessageDialog(null, "updateUserDetails  : " + w);
            w.printStackTrace();
        }
        finally{
            try{
                conn.close();
                System.out.println("updateUserDetails connection closed");
            } 
            catch (Exception w) {
                JOptionPane.showMessageDialog(null, "updateUserDetails  : " + w);
                w.printStackTrace();
            }
        }
    }//END updateUserDetails
   
    public void updateFriendList(){
        counter = 0;
        Connection conn = SocialManagerSearch.connectDatabase();
        System.out.println("updateFriendList : Database has opened successfully");
        
        String qry = "select * from FriendConfirmed where userID = '" + userLoggedIn + "'"; //NEED TO MAKE IT AN EMAIL userLoggedIn
        try{
            PreparedStatement pst = conn.prepareStatement(qry);
            ResultSet rs = pst.executeQuery();
            //System.out.println("gets here");
            while (rs.next()) {
                
                userID = rs.getString("userID");
                friendID[counter] = rs.getString("friendID");
                
                if(userID.equals(userLoggedIn)){
                    System.out.println("Your Name : " + userID);
                    System.out.println("Friend's name : " + friendID[counter]); //NEED TO CALL THE DATABASE AGAIN TO COLLECT THE FRIENDS FIRST AND LAST NAME TO BE PRINTED 
                    counter ++;
                }
                else{
                    friendID[counter] = "No friends";
                }
            }
            pst.close();
            rs.close();
        }
        catch (Exception w) {
            JOptionPane.showMessageDialog(null, "updateFriendList  : " + w);
            w.printStackTrace();
        }
        finally{
            try{
                conn.close();
                System.out.println("updateFriendList connection closed");
                addToFriendTable();
            } 
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "updateFriendList 1 : " + ex);
                ex.printStackTrace();
            }
        }
    }//END updateFriendList

    public void addToFriendTable(){
        for(int g = 0; g <= counter; g++){
            Connection conn = SocialManagerSearch.connectDatabase();
            System.out.println("addToFriendTable : Database has opened successfully");
        
            String qry = "select * from Account where email = '"+ friendID[g]+"'";
            try{
                PreparedStatement pst = conn.prepareStatement(qry);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    String firstNameFriend = rs.getString("firstName");
                    String lastNameFriend = rs.getString("lastName");

                    String FullFriendName = firstNameFriend + " " + lastNameFriend;
                    friendID[g] = FullFriendName; //the position in the array is now set to the first message              
                }
            }        
            catch(Exception e){
                    JOptionPane.showMessageDialog(null, "addToFriendTable  : " + e);
                    e.printStackTrace();
            }
            finally{
                try{
                    conn.close();
                    System.out.println("addToFriendTable connection closed");
                    updatingFriendTable();
                } 
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "addToFriendTable 1 : " + ex);
                    ex.printStackTrace();
                }
            }
        }
    }//END addToFriendTable
      
    public void updatingFriendTable(){
        DefaultTableModel models = (DefaultTableModel) table_friends.getModel();
            try{
                models.setRowCount(0);
                for(int m = 1; m <= counter; m++){  
                    models.addRow(new String[]{friendID[m-1]});
                    System.out.println("updating the friend table " + friendID[m-1]); 
                   // table_friends.revalidate();
                }
            }     
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "updatingFriendTable " + e);
                e.printStackTrace();
            } 
        //table_friends.revalidate();
    }//END updatingFriendTable
 
    private void button_becomePrivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_becomePrivateActionPerformed
        //System.out.println("Private - Your account : " + userDiscoverable);
        Connection conn = SocialManagerSearch.connectDatabase();
        System.out.println("button_becomePrivateActionPerformed : Database has opened successfully");
        try {
            if(userDiscoverable.equals("true")){
                String qry = "update Account set Discoverable = 'false' where email = '" + userLoggedIn + "' ";
                PreparedStatement pst = conn.prepareStatement(qry);
                pst.execute();
                pst.close();
                try {
                    conn.commit();
                    //JOptionPane.showMessageDialog(null, "The table should be updated");
                    updateUserDetails();
                } catch (Exception t) {
                    JOptionPane.showMessageDialog(null, "button_becomePrivateActionPerformed " + t);
                    t.printStackTrace();
                }
            }//END if statement
            else{
                JOptionPane.showMessageDialog(null, "Your account is already private.");
            }
        } catch (Exception q) {
            JOptionPane.showMessageDialog(null, "button_becomePrivateActionPerformed " + q);
            q.printStackTrace();
        }
        finally{
            try{
                conn.close();
                System.out.println("button_becomePrivateActionPerformed connection closed");
            } 
            catch (Exception O) {
                JOptionPane.showMessageDialog(null, "button_becomePrivateActionPerformed 1 " + O);
                O.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_button_becomePrivateActionPerformed
    //END button_becomePrivateActionPerformed
    private void button_becomeDiscoverableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_becomeDiscoverableActionPerformed
        //System.out.println("Discoverable - Your account : " + userDiscoverable);
        Connection conn = SocialManagerSearch.connectDatabase();
        System.out.println("button_becomeDiscoverableActionPerformed : Database has opened successfully");
        try {
            if(userDiscoverable.equals("false")){
                String qry = "update Account set Discoverable = 'true' where email = '" + userLoggedIn + "' ";

                PreparedStatement pst = conn.prepareStatement(qry);
                pst.execute();
                pst.close();
                try {
                    conn.commit();
                    JOptionPane.showMessageDialog(null, "The table should be updated");
                    
                    updateUserDetails();
                } catch (Exception t) {
                    JOptionPane.showMessageDialog(null, "button_becomeDiscoverableActionPerformed " + t);
                    t.printStackTrace();
                }
            }//END if statement
            else{
                JOptionPane.showMessageDialog(null, "Your account is already visible to other users.");
            }
        } catch (Exception q) {
            JOptionPane.showMessageDialog(null, "Become Private Button " + q);
            q.printStackTrace();
        }     
        finally{
            try{
                conn.close();
                System.out.println("button_becomeDiscoverableActionPerformed connection closed");
            } 
            catch (Exception q) {
            JOptionPane.showMessageDialog(null, "button_becomeDiscoverableActionPerformed " + q);
            q.printStackTrace();
            }
        }
    }//GEN-LAST:event_button_becomeDiscoverableActionPerformed
    //END button_becomeDiscoverableActionPerformed  
    private void Button_MessagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_MessagesActionPerformed
        Messages n = new Messages();
        n.getMessagesUpdated();
        sendMessages();
    }//GEN-LAST:event_Button_MessagesActionPerformed
    //END Button_MessagesActionPerformed
    private void Button_refreshFriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_refreshFriendsActionPerformed
        updateFriendList();
        updatingFriendTable();
    }//GEN-LAST:event_Button_refreshFriendsActionPerformed
    //END Button_refreshFriendsActionPerformed
    private void Button_removeFriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_removeFriendsActionPerformed
        removeFriend a = new removeFriend();
        a.main(userFirstName, userLoggedIn);
    }//GEN-LAST:event_Button_removeFriendsActionPerformed
    //END Button_removeFriendsActionPerformed
    private void Button_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_searchActionPerformed
        SocialManagerSearch searchBar = new SocialManagerSearch();
        searchBar.userSignedIn(userLoggedIn);
    }//GEN-LAST:event_Button_searchActionPerformed
    //END Button_searchActionPerformed
     public void sendMessages(){
        Messages a = new Messages();
        a.main(userFirstName, userLoggedIn);  
    }//END sendMessages

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Messages;
    private javax.swing.JButton Button_refreshFriends;
    private javax.swing.JButton Button_removeFriends;
    private javax.swing.JButton Button_search;
    private javax.swing.JButton button_becomeDiscoverable;
    private javax.swing.JButton button_becomePrivate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_friends;
    // End of variables declaration//GEN-END:variables
}
