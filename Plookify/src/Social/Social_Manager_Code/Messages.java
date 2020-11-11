
package Social.Social_Manager_Code;

import java.awt.Component;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

import javax.swing.table.*;

public class Messages extends javax.swing.JFrame {

    public Messages() {
        initComponents();
        LoadMessages();
    }//END Messages

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Panel_messages = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_messages = new javax.swing.JTable();
        Button_decline = new javax.swing.JButton();
        Button_refresh = new javax.swing.JButton();
        checkBox_selectAll = new javax.swing.JCheckBox();
        Button_cancel = new javax.swing.JButton();
        Button_accept = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Your Messages");

        Table_messages.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Private Message", "Select"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table_messages.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Table_messages);
        if (Table_messages.getColumnModel().getColumnCount() > 0) {
            Table_messages.getColumnModel().getColumn(0).setMinWidth(150);
            Table_messages.getColumnModel().getColumn(0).setPreferredWidth(150);
            Table_messages.getColumnModel().getColumn(0).setMaxWidth(150);
            Table_messages.getColumnModel().getColumn(2).setMinWidth(50);
            Table_messages.getColumnModel().getColumn(2).setPreferredWidth(50);
            Table_messages.getColumnModel().getColumn(2).setMaxWidth(50);
        }

        javax.swing.GroupLayout Panel_messagesLayout = new javax.swing.GroupLayout(Panel_messages);
        Panel_messages.setLayout(Panel_messagesLayout);
        Panel_messagesLayout.setHorizontalGroup(
            Panel_messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
        );
        Panel_messagesLayout.setVerticalGroup(
            Panel_messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        Button_decline.setText("Decline");
        Button_decline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_declineActionPerformed(evt);
            }
        });

        Button_refresh.setText("Refresh");
        Button_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_refreshActionPerformed(evt);
            }
        });

        checkBox_selectAll.setText("Select All");
        checkBox_selectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_selectAllActionPerformed(evt);
            }
        });

        Button_cancel.setText("Cancel");
        Button_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_cancelActionPerformed(evt);
            }
        });

        Button_accept.setText("Accept");
        Button_accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_acceptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Button_refresh)
                        .addGap(18, 18, 18)
                        .addComponent(checkBox_selectAll))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(Panel_messages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Button_accept, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Button_decline, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(Button_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBox_selectAll)
                    .addComponent(Button_refresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Panel_messages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_decline, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_accept, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static int noMessages = 0;
    public static int noMessagesANDone = 100;
    public static String[] ofMessages = new String[noMessagesANDone];
    public static String[] ofFriendName = new String[noMessagesANDone];
    
    public static String privateMessage; 
    public static int count = 0;
    
    public static int numDelete = 0;
    public static int limit = 100;
    public static String[] deleteRequest = new String[limit];
    
    public static int numDeclined = 0;
    public static int limits = 100;
    public static String[] deleteDeclined = new String[limit];
    
    public static int counts = 0;
    
    public static String userFirstName;
    public static String userLoggedIn ="";

    public void getMessagesUpdated(){
        noMessages = 0;
        String qry = "select * from Friends where friendID = '" + userLoggedIn + "'"; //gets the username and if matches friendID display people who requested their friendship
        Connection conn = SocialManagerSearch.connectDatabase();
        System.out.println("getMessagesUpdated : Database has opened successfully");
        try {
            PreparedStatement pst = conn.prepareStatement(qry);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                noMessages = noMessages + 1;
                String accountID = rs.getString("accountID");
                String friendID = rs.getString("friendID");
                privateMessage = rs.getString("PrivateMessage");

                System.out.println("Your Name : " + userFirstName);
                System.out.println("Your email : " + friendID);
                System.out.println("FriendID : " + accountID);
                System.out.println("PrivateMessage : " + privateMessage);

                System.out.println();
                noMessagesANDone = noMessagesANDone + 1; // array will continue rising to keep space for over 100 friend requests
                
                if(friendID.equals(userLoggedIn)){
                    System.out.println("PrivateMessage again is : " + privateMessage);
                    ofMessages[noMessages -1] = privateMessage; //the position in the array is now set to the first message  
                    ofFriendName[noMessages -1] = accountID;
                }
            }
            pst.close();
            rs.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "getMessagesUpdated " + e);
            e.printStackTrace();
        }
        finally{
            try{
                conn.close();
                System.out.println("getMessagesUpdated connection closed");
                friendNameUpdate();
                //sendMessages(noMessages,ofMessages,ofFriendName);
            } 
            catch (Exception q) {
            JOptionPane.showMessageDialog(null, "Become getMessagesUpdated " + q);
            q.printStackTrace();
            }
        }    
    }//END getMessagesUpdated
    
    public void friendNameUpdate(){
        for(int g = 0; g <= noMessages; g++){
            Connection conn = SocialManagerSearch.connectDatabase();
            System.out.println("friendNameUpdate : Database has opened successfully");
            String qry = "select * from Account where email = '"+ ofFriendName[g]+"'";
            try{
                PreparedStatement pst = conn.prepareStatement(qry);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    String firstNameFriend = rs.getString("firstName");
                    String lastNameFriend = rs.getString("lastName");

                    String FullFriendName = firstNameFriend + " " + lastNameFriend;
                    ofFriendName[g] = FullFriendName; //the position in the array is now set to the first message  
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "friendNameUpdate  : " + e);
                e.printStackTrace();
            }
            finally{
                try{
                    conn.close();
                    System.out.println("friendNameUpdate connection closed");
                } 
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "friendNameUpdate 1 : " + ex);
                    ex.printStackTrace();
                }
            }
        }
    }//END friendNameUpdate
   
    private void LoadMessages(){
        getMessagesUpdated();
        DefaultTableModel model = (DefaultTableModel) Table_messages.getModel();
        try{
            model.setRowCount(0);
            for(int m = 1; m <= noMessages; m++){            
                model.addRow(new Object[]{ofFriendName[m-1],ofMessages[m-1],Boolean.FALSE});//need to change code to allow refresh to only display the messages that are not responded to           
            }
        }     
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "getMessage " + e);
            e.printStackTrace();
        } 
    }
    
    
    private void Button_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_refreshActionPerformed
        getMessagesUpdated();
        DefaultTableModel model = (DefaultTableModel) Table_messages.getModel();
        try{
            model.setRowCount(0);
            for(int m = 1; m <= noMessages; m++){            
                model.addRow(new Object[]{ofFriendName[m-1],ofMessages[m-1],Boolean.FALSE});//need to change code to allow refresh to only display the messages that are not responded to           
            }
        }     
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "getMessage " + e);
            e.printStackTrace();
        }       
    }//GEN-LAST:event_Button_refreshActionPerformed
    //END Button_refreshActionPerformed
    public void numRows(){
        Connection conn = SocialManagerSearch.connectDatabase();
        System.out.println("numRows friends: Database has opened successfully");
        Statement s = null;
        ResultSet r = null;
        try{   
            s = conn.createStatement();
            r = s.executeQuery("SELECT COUNT(*) AS rowid FROM FriendConfirmed");
            r.next();
            count = r.getInt("rowid");

            System.out.println("My confirmed friends table has " + count + " row(s).");
            count = count + 1;
            r.close();
            s.close();
        }
        catch(Exception t) {
            JOptionPane.showMessageDialog(null, "numRows friends update " + t);
            t.printStackTrace();
        }
        finally{
            try{
                conn.close();
                System.out.println("numRows friends connection closed");
            } 
            catch (Exception t) {
                JOptionPane.showMessageDialog(null, "numRows friends update " + t);
                t.printStackTrace();
            }
        }
    }//END numRows
    
    private void Button_acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_acceptActionPerformed
        for (int i = 0; i < Table_messages.getRowCount(); i++) {
            boolean isChecked = (Boolean) Table_messages.getValueAt(i, 2);

            if (isChecked == true) {
                numRows();
                numDelete = numDelete + 1;
                limit = limit + 1;
                //add friend if confirmed to friendConfirmed table in database
                //JOptionPane.showMessageDialog(null, "it gets here " + isChecked);
                //JOptionPane.showMessageDialog(null, ofFriendName[i]);

                deleteRequest[numDelete -1] = ofFriendName[i];
                String query = "insert into FriendConfirmed(rowid,userID,FriendID) values (?,?,?)";
                Connection conn = SocialManagerSearch.connectDatabase();
                System.out.println("Button_acceptActionPerformed : Database has opened successfully");

                try {    
                    PreparedStatement insert = conn.prepareStatement(query);

                    insert.setInt(1, count);
                    insert.setString(2, userLoggedIn); 
                    insert.setString(3, ofFriendName[i]);

                    insert.executeUpdate();

                    try {   
                        conn.commit();
                        JOptionPane.showMessageDialog(null, "The friend should be updated");
                    } 
                    catch (Exception t) {
                        JOptionPane.showMessageDialog(null, "Button_acceptActionPerformed " + t);
                        t.printStackTrace();
                    }
                    finally{
                        try{
                            insert.close();
                        } 
                        catch (Exception y) {
                            JOptionPane.showMessageDialog(null, ("Button_acceptActionPerformed commit error" + y));
                            y.printStackTrace();
                        }
                    }
                } 
                catch (Exception g) { //Insert error code for duplicate friend requests if a request has already been sent to the friend
                    JOptionPane.showMessageDialog(null, ("Button_acceptActionPerformed error" + g));
                    g.printStackTrace();
                }
                finally{
                    try{
                        conn.close();
                        System.out.println("Button_acceptActionPerformed connection closed");
                        deleteRequest();
                    } 
                    catch (Exception t) {
                        JOptionPane.showMessageDialog(null, "Button_acceptActionPerformed again " + t);
                        t.printStackTrace();
                    }
                }
            }
        }
    }//GEN-LAST:event_Button_acceptActionPerformed
    //END Button_acceptActionPerformed
    private void Button_declineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_declineActionPerformed
        for (int i = 0; i < Table_messages.getRowCount(); i++) {
            boolean isChecked = (Boolean) Table_messages.getValueAt(i, 2);
            if (isChecked == true) {
                numRows();
                numDeclined = numDeclined + 1;
                limits = limits + 1;
                //remove friend request if checked from database
                //JOptionPane.showMessageDialog(null, "it gets here " + isChecked);
                //JOptionPane.showMessageDialog(null, ofFriendName[i]);

                deleteDeclined[numDeclined -1] = ofFriendName[i];
                deleteDeclined();

            }
        } 
    }//GEN-LAST:event_Button_declineActionPerformed
    //END Button_declineActionPerformed
    private void Button_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_cancelActionPerformed
        dispose();
    }//GEN-LAST:event_Button_cancelActionPerformed
    //END Button_cancelActionPerformed
    private void checkBox_selectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_selectAllActionPerformed
        if(checkBox_selectAll.isSelected()){
            for(int i=0;i<Table_messages.getRowCount();i++)
                Table_messages.getModel().setValueAt(true, i, 2);
        }
        else{
            for(int i=0;i<Table_messages.getRowCount();i++)
                Table_messages.getModel().setValueAt(false, i, 2);                   
        } 
    }//GEN-LAST:event_checkBox_selectAllActionPerformed
        
    public void DeleteRows(){
        Connection conn = SocialManagerSearch.connectDatabase();
        System.out.println("DeleteRows : Database has opened successfully");
        Statement s = null;
        ResultSet r = null;
        try{   
            s = conn.createStatement();
            r = s.executeQuery("SELECT COUNT(*) AS rowid FROM Friends");
            r.next();
            counts = r.getInt("rowid");
            
            System.out.println("My friends table has " + counts + " row(s) it can delete.");
            counts = counts + 1;
            r.close();
            s.close();
        }
        catch(Exception t) {
            JOptionPane.showMessageDialog(null, "deleteRow friends update " + t);
            t.printStackTrace();
        }
        finally{
            try{
                conn.close();
                System.out.println("deleteRows friends connection closed");
            } 
            catch (Exception t) {
                JOptionPane.showMessageDialog(null, "delete Row friends update error" + t);
                t.printStackTrace();
            }
        }
        
    }//END DeleteRows

    public void deleteRequest(){
      //  JOptionPane.showMessageDialog(null, "Should delete now");
        DeleteRows();
        Connection conn = SocialManagerSearch.connectDatabase();
        System.out.println("deleteRequest : Database has opened successfully");
            String query = "delete from Friends where accountID =? and friendID = ? ";
            int i = 0;
            try {    
                PreparedStatement deleting = conn.prepareStatement(query);
            //NEED TO GET THE FRIEND NAME AND USER NAME TO INSERT INTO FRIEND CONFIRMED TABLE
                    deleting.setString(1, deleteRequest[i]);
                    deleting.setString(2, userLoggedIn);

                    deleting.executeUpdate();

                    try {   
                        conn.commit();
                        //JOptionPane.showMessageDialog(null, "Private message sent to " + name);
                        JOptionPane.showMessageDialog(null, deleteRequest[i] + " : The request should be deleted");
                        i = i + 1;
                    } 
                    catch (Exception t) {
                        JOptionPane.showMessageDialog(null, "deleteRequest commit error " + t);
                        t.printStackTrace();
                    }
                    finally{
                        try{
                            deleting.close();
                        } 
                        catch (Exception y) {
                            JOptionPane.showMessageDialog(null, ("deleteRequest error 1 " + y));
                            y.printStackTrace();
                        }
                    }
            } 
            catch (Exception g) { 
                JOptionPane.showMessageDialog(null, ("deleteRequest error " + g));
                g.printStackTrace();
            }
            finally{
                try{
                    conn.close();
                    System.out.println("deleteRequest connection closed");
                } 
                catch (Exception t) {
                    JOptionPane.showMessageDialog(null, "deleteRequest again " + t);
                    t.printStackTrace();
                }
            }
    }//END deleteRequest
 
    public void deleteDeclined(){
        //JOptionPane.showMessageDialog(null, "Should delete now");
        DeleteRows();
        Connection conn = SocialManagerSearch.connectDatabase();
        System.out.println("deleteDeclined : Database has opened successfully");
        String query = "delete from Friends where accountID =? and friendID = ? ";
        int i = 0;
        try {    
            PreparedStatement deleting = conn.prepareStatement(query);
                deleting.setString(1, deleteDeclined[i]);
                deleting.setString(2, userLoggedIn);
                deleting.executeUpdate();

                try {   
                    conn.commit();
                    JOptionPane.showMessageDialog(null, deleteDeclined[i] + " : The request should be deleted");
                    i = i + 1;
                } 
                catch (Exception t) {
                    JOptionPane.showMessageDialog(null, "deleteDeclined commit error " + t);
                    t.printStackTrace();
                }
                finally{
                    try{
                        deleting.close();
                    } 
                    catch (Exception y) {
                        JOptionPane.showMessageDialog(null, ("deleteDeclined error 1 " + y));
                        y.printStackTrace();
                    }
                }
        } 
        catch (Exception g) { 
            JOptionPane.showMessageDialog(null, ("deleteDeclined error " + g));
            g.printStackTrace();
        }
        finally{
            try{
                conn.close();
                System.out.println("deleteDeclined connection closed");
            } 
            catch (Exception t) {
                JOptionPane.showMessageDialog(null, "deleteDeclined again " + t);
                t.printStackTrace();
            }
        }
    }//END deleteDeclined
    
    public void main(String userName, String userEmail) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Messages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Messages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Messages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Messages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */

        userFirstName = userName;
        userLoggedIn = userEmail;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Messages().setVisible(true); 
            }
        });
    }//END main
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_accept;
    private javax.swing.JButton Button_cancel;
    private javax.swing.JButton Button_decline;
    private javax.swing.JButton Button_refresh;
    private javax.swing.JPanel Panel_messages;
    private javax.swing.JTable Table_messages;
    private javax.swing.JCheckBox checkBox_selectAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

