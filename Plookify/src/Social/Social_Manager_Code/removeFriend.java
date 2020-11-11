
package Social.Social_Manager_Code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class removeFriend extends javax.swing.JFrame {
    
    public static int noRemove = 0;
    
    public static int numFriends = 0;
    public static int numOfFriends = 100;
    public static String[] confirmedFriendID = new String[numOfFriends];
    public static String[] confirmedUserID = new String[numOfFriends];
    
    public static String userSignedInName = "";
    public static String userSignedEmail = "";
    public static int numNames = 0;
    public static String[] friendFullName = new String[numOfFriends];
    
    public removeFriend() {
        initComponents();
        loadFriends();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_remove = new javax.swing.JTable();
        Button_remove = new javax.swing.JButton();
        Button_cancel = new javax.swing.JButton();
        Button_refresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Remove Friends ");

        table_remove.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Friend Name", "Select"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_remove);
        if (table_remove.getColumnModel().getColumnCount() > 0) {
            table_remove.getColumnModel().getColumn(1).setMinWidth(60);
            table_remove.getColumnModel().getColumn(1).setPreferredWidth(60);
            table_remove.getColumnModel().getColumn(1).setMaxWidth(60);
        }

        Button_remove.setText("Remove");
        Button_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_removeActionPerformed(evt);
            }
        });

        Button_cancel.setText("Cancel");
        Button_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_cancelActionPerformed(evt);
            }
        });

        Button_refresh.setText("Refresh");
        Button_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_refreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Button_remove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(Button_refresh))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Button_refresh, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_remove)
                    .addComponent(Button_cancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static int count = 0;
    
    public void numberRows(){
        Connection conn = SocialManagerSearch.connectDatabase();
        System.out.println("numberRows : Database has opened successfully");
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
            JOptionPane.showMessageDialog(null, "numberRows friends update " + t);
            t.printStackTrace();
        }
        finally{
            try{
                conn.close();
                System.out.println("numberRows connection closed");
            } 
            catch (Exception t) {
                JOptionPane.showMessageDialog(null, "numberRows " + t);
                t.printStackTrace();
            }
        }
    }//END numberRows
    
    private void Button_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_removeActionPerformed
        for (int i = 0; i < table_remove.getRowCount(); i++) {
            boolean isChecked = (Boolean) table_remove.getValueAt(i, 1);
            if (isChecked == true) {
                numberRows();
                noRemove = noRemove + 1;
                //add friend if confirmed to friendConfirmed table in database
                //JOptionPane.showMessageDialog(null, "it gets here " + isChecked);
                JOptionPane.showMessageDialog(null, confirmedFriendID[i]);
                //JOptionPane.showMessageDialog(null, "and they are " + Table_messages.getValueAt(i, 1));

                String query = "delete from FriendConfirmed where userID =? and friendID =?";
                Connection conn = SocialManagerSearch.connectDatabase();
                System.out.println("Button_removeActionPerformed : Database has opened successfully");
                try {    
                    PreparedStatement removing = conn.prepareStatement(query);
                    removing.setString(1, confirmedUserID[i]);
                    removing.setString(2, confirmedFriendID[i]);
                    removing.executeUpdate();
                    try {   
                        conn.commit();
                        //JOptionPane.showMessageDialog(null, "Private message sent to " + name);
                        JOptionPane.showMessageDialog(null, "The friend should be removed and updated");
                    } 
                    catch (Exception t) {
                        JOptionPane.showMessageDialog(null, "Button_removeActionPerformed " + t);
                        t.printStackTrace();
                    }
                    finally{
                        try{
                            removing.close();
                        } 
                        catch (Exception y) {
                            JOptionPane.showMessageDialog(null, ("Button_removeActionPerformed commit error" + y));
                            y.printStackTrace();
                        }
                    }
                } 
                catch (Exception g) { //Insert error code for duplicate friend requests if a request has already been sent to the friend
                    JOptionPane.showMessageDialog(null, ("friend removed error" + g));
                    g.printStackTrace();
                }
                finally{
                    try{
                        conn.close();
                        System.out.println("Button_removeActionPerformed connection closed");
                        //loadFriends();
                        //removeFriend.main(userSignedInName, userSignedEmail);
                    } 
                    catch (Exception t) {
                        JOptionPane.showMessageDialog(null, "Button_removeActionPerformed again " + t);
                        t.printStackTrace();
                    }
                }
            }
        }        
    }//GEN-LAST:event_Button_removeActionPerformed
    //END Button_removeActionPerformed
    public void getFriendsUpdated(){
        numFriends = 0;
        String qry = "select * from FriendConfirmed"; //gets the friends from the database
        Connection conn = SocialManagerSearch.connectDatabase();
        System.out.println("getFriendsUpdated : Database has opened successfully");
        try {
            PreparedStatement pst = conn.prepareStatement(qry);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                numFriends = numFriends + 1;
                String userID = rs.getString("userID");
                String friendID = rs.getString("friendID");

                System.out.println("Your Name : " + userID);
                System.out.println("FriendID : " + friendID);
                System.out.println();
                
                if(userID.equals(userSignedEmail)){ 
                    System.out.println(userID + " is friends with : " + friendID);
                    confirmedFriendID[numFriends -1] = friendID; //the position in the array is now set to the first message  
                    confirmedUserID[numFriends -1] = userID;
                }
            }
            pst.close();
            rs.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "getFriendsUpdated " + e);
            e.printStackTrace();
        }
        finally{
            try{
                conn.close();
                System.out.println("getFriendsUpdated connection closed");
                //getNames();
            } 
            catch (Exception q) {
            JOptionPane.showMessageDialog(null, "getFriendsUpdated " + q);
            q.printStackTrace();
            }
        }
    }//END getFriendsUpdated
    
    public void getNames(){
        numNames = 0;
        for(int i = 0; i <= numFriends; i++){
        String qry = "select * from Account where email = '"+ confirmedFriendID[numNames] +"'"; //gets the friends names from database
        Connection conn = SocialManagerSearch.connectDatabase();
        System.out.println("getNames : Database has opened successfully");
        
            try {
                PreparedStatement pst = conn.prepareStatement(qry);
                ResultSet rs = pst.executeQuery();
                //pst.setString(1, confirmedFriendID[numNames]);
                while (rs.next()) {
                    numNames++;
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    //String email = rs.getString("email");
                    //if(email.equals(confirmedFriendID[numNames-1])){
                        friendFullName[numNames-1] = firstName + " " + lastName;
                        System.out.println(friendFullName[numNames-1]);
                    //}
                }
            }
            catch (Exception q) {
                JOptionPane.showMessageDialog(null, "getNames " + q);
                q.printStackTrace();
            }
            finally{
                try{
                conn.close();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, "getNames " + e);
                    e.printStackTrace();
                }
            }
            //break;
       }
        
    }//END getNames()
    
    private void Button_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_refreshActionPerformed
        getFriendsUpdated();
        DefaultTableModel model = (DefaultTableModel) table_remove.getModel();
        try{
            model.setRowCount(0);
            for(int m = 1; m <= numFriends; m++){            
                model.addRow(new Object[]{confirmedFriendID[m-1],Boolean.FALSE});//need to change code to allow refresh to only display the messages that are not responded to           
            }
        }     
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Button_refreshActionPerformed " + e);
            e.printStackTrace();
        }  
    }//GEN-LAST:event_Button_refreshActionPerformed
    //END Button_refreshActionPerformed    //END Button_refreshActionPerformed
    
    public void loadFriends(){
        getFriendsUpdated();
        getNames();
        DefaultTableModel model = (DefaultTableModel) table_remove.getModel();
        try{
            model.setRowCount(0);
            for(int m = 1; m <= numNames; m++){            
                model.addRow(new Object[]{friendFullName[m-1],Boolean.FALSE});//need to change code to allow refresh to only display the messages that are not responded to           
                table_remove.revalidate();
            }
        }     
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Button_refreshActionPerformed " + e);
            e.printStackTrace();
        }  
    }
    
    private void Button_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_cancelActionPerformed
        dispose();
    }//GEN-LAST:event_Button_cancelActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String userFirstName, String userLoggedIn ) {
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
            java.util.logging.Logger.getLogger(removeFriend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(removeFriend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(removeFriend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(removeFriend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        userSignedInName = userFirstName;
        userSignedEmail = userLoggedIn;
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new removeFriend().setVisible(true);
            }
        });
    }//END main

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_cancel;
    private javax.swing.JButton Button_refresh;
    private javax.swing.JButton Button_remove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_remove;
    // End of variables declaration//GEN-END:variables
}
