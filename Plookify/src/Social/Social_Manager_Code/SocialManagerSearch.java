package Social.Social_Manager_Code;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Query;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

public class SocialManagerSearch extends javax.swing.JPanel {

    public static String userLoggedIn = "";
    public static String userFirstName = "";
    public static String userLastName = "";
    public static String userAccountType = "";
    public static String userDiscoverable = "";
    
    public static String searchedNameIs = "";
    public static String searchedName = "";
    public static int round = 1;
    public static String friendFullName ="";
    public static String friendEmail = "";
    
    public static int count = 0;
    
    public static String friendToAdd = "";
    
    public static int numUpdateSearch = 0;
    public static int SearchLimit = 100;
    public static String[] updateSearch = new String[SearchLimit];
    public static String[] updateSearchEmail = new String[SearchLimit];

    public SocialManagerSearch() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_searchFriend = new javax.swing.JTextField();
        search_Button = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_searchFriends = new javax.swing.JTable();
        Button_addFriend = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        search_Button.setText("Search");
        search_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_ButtonActionPerformed(evt);
            }
        });

        Table_searchFriends.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Friend Name", "Add as Friend"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table_searchFriends.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(Table_searchFriends);
        if (Table_searchFriends.getColumnModel().getColumnCount() > 0) {
            Table_searchFriends.getColumnModel().getColumn(1).setMinWidth(100);
            Table_searchFriends.getColumnModel().getColumn(1).setPreferredWidth(100);
            Table_searchFriends.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        Button_addFriend.setText("Add Friend/s");
        Button_addFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_addFriendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_searchFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search_Button, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Button_addFriend)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(search_Button, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(txt_searchFriend))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Button_addFriend)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public static Connection connectDatabase() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite://C:\\Users\\Sofia\\Documents\\QMUL\\Year Two\\Semester 4\\Software Engineering Project\\Master_Project\\SE33\\Plookify\\src\\Social\\plookifyDB.sqlite");//plookifyDataBase.sqlite works , there is another user I have added as a test
            c.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println("Connection Database : " + e.getClass().getName() + ": " + e.getMessage());
        }
        return c;
    }//END Connection

    public void userSignedIn(String name) {
        userLoggedIn = name;
        System.out.println("user logged in is  " + userLoggedIn);
        Connection conn = connectDatabase();
        System.out.println("userSignedIn : Database has opened successfully");
        String qry = "select * from Account where email= '" + userLoggedIn + "'";
        try {
            PreparedStatement pst = conn.prepareStatement(qry);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                userFirstName = rs.getString("firstName");
                userLastName = rs.getString("lastName");
                userAccountType = rs.getString("accountType");
                userDiscoverable = rs.getString("Discoverable");

                //System.out.println("userFirstName : " + userFirstName);
                //System.out.println("userLastName : " + userLastName);
                //System.out.println("userAccountType : " + userAccountType);
                //System.out.println("userDiscoverable : " + userDiscoverable);
                //System.out.println();
                pst.close();
                rs.close();
            }
        } 
        catch (Exception w) {
            JOptionPane.showMessageDialog(null, "userSignedIn  : " + w);
        }
        finally{
            try{
                conn.close();
                System.out.println("userSignedIn connection closed");
            } 
            catch (Exception w) {
            JOptionPane.showMessageDialog(null, "userSignedIn  : " + w);
            w.printStackTrace();
            }
        }
    }//END userSignedIn

    public boolean isEditable() {
        return false;
    }//END isEditable

    static public String firstLetterCaps(String name)
    {
      String firstLetter = name.substring(0,1).toUpperCase();
      String restLetters = name.substring(1).toLowerCase();
      return firstLetter + restLetters;
    }
    
    private void search_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_ButtonActionPerformed
        System.out.println("user logged in is " + userLoggedIn); // This now works and passes the email signed in with here
        round = 0;
        searchedNameIs = txt_searchFriend.getText();
        searchedName = firstLetterCaps(searchedNameIs);
        //JOptionPane.showMessageDialog(null,searchedName);
       
        System.out.println("You are currently : " + userAccountType);
        Connection conn = connectDatabase();
        System.out.println("search_ButtonActionPerformed : Database has opened successfully");
        if(userAccountType.equals("p")){
            String qry = "select * from Account where firstName = ? ";
            try{
                PreparedStatement pst = null;

                pst=conn.prepareStatement(qry);
                pst.setString(1, searchedName);
                
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    String  Discoverable = rs.getString("Discoverable");
                    String  email = rs.getString("email");
                    System.out.println(email + " is " + Discoverable); 

                    if(Discoverable.equals("true")  ){//&& round ==1
                        round = round + 1;
                        String FirstName = rs.getString("firstName");
                        String LastName = rs.getString("lastName");
                        friendEmail = rs.getString("email");
                        String friendFullName = FirstName + " " + LastName;
      
                        updateSearch[round-1] = friendFullName;
                        updateSearchEmail[round-1] = friendEmail;
                        numUpdateSearch = numUpdateSearch + 1;

                        txt_searchFriend.setText("");
                    }//END if statement
                    else{
                        JOptionPane.showMessageDialog(null, "Name not found");
                    }
                }//END if statement
                pst.close();
                rs.close();
            }//END try
            catch (Exception w){
                JOptionPane.showMessageDialog(null, "search_ButtonActionPerformed : " + w);
                w.printStackTrace();
            }
            finally{
                try{
                    conn.close();
                    System.out.println("search_ButtonActionPerformed connection closed");
                    updateSearch();
                } 
                catch (Exception y) {
                    JOptionPane.showMessageDialog(null, "search_ButtonActionPerformed 1 : " + y);
                    y.printStackTrace();
                }
            }         
        }//END if statement
        else{
            JOptionPane.showMessageDialog(null, "You are a free user and cannot add friends.");
        }                                             
    }//GEN-LAST:event_search_ButtonActionPerformed
    //END search_ButtonActionPerformed 
    private void Button_addFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_addFriendActionPerformed
        for (int i = 0; i < Table_searchFriends.getRowCount(); i++) {
            boolean isChecked = (Boolean) Table_searchFriends.getValueAt(i, 1);

            if (isChecked == true) {
                friendToAdd = updateSearchEmail[i].toString();
                if(!friendToAdd.equals(userLoggedIn)){
                    addFriend();
                }
                else{
                    JOptionPane.showMessageDialog(null,"You cannot add yourself");
                }
            }
        }
    }//GEN-LAST:event_Button_addFriendActionPerformed

    public void updateSearch(){
        DefaultTableModel model = (DefaultTableModel) Table_searchFriends.getModel();
        try{
            model.setRowCount(0);
            for(int m = 1; m <= numUpdateSearch; m++){            
                model.addRow(new Object[]{updateSearch[m-1],Boolean.FALSE});//need to change code to allow refresh to only display the messages that are not responded to           
                Table_searchFriends.revalidate();
            }
        }     
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "updateSearch " + e);
            e.printStackTrace();
        } 
        Table_searchFriends.revalidate();
    }
    
    public void addFriend() {
        PreparedStatement pst = null;
        ResultSet rs = null;

        String qry = "select * from Account where email = '"+ friendToAdd +"' ";   
        try {
            Connection conn = connectDatabase();
            System.out.println("addFriend : Database has opened successfully");
            pst = conn.prepareStatement(qry);
            rs = pst.executeQuery();

            while (rs.next()) {

                String accountType = rs.getString("accountType");
                String FriendFirstName = rs.getString("firstName");
                String emails = rs.getString("email");

                if (friendToAdd.equals(emails)) { // To see the friend's subscriptionType

                    System.out.println("It matched: " + friendToAdd);
                    System.out.println("This user is " + accountType);

                    if (accountType.equals("p")) {// If subscription is 'p' for paid then reconfirm question to user                            
                        rs.close();
                        pst.close();
                        conn.close();
                        System.out.println("addFriend connection closed");
                        
                        confirmRequest(FriendFirstName);
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, FriendFirstName + " is not a paying subscriber and cannot add him/her as a friend");
                        break;
                    }
                }
            }//END while statement 
        } //END try //END try
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, ("addFriend error" + e));
            e.printStackTrace();
        }
    } //END addFriend

    public void numRows(){
        Connection conn = connectDatabase();
        System.out.println("numRows : Database has opened successfully");
        Statement s = null;
        ResultSet r = null;
        try{   
            s = conn.createStatement();
            r = s.executeQuery("SELECT rowid, * from Friends");
            while(r.next()){
                int rowNum = r.getInt("rowid");
                System.out.println("rowID  : " + rowNum);
                if(rowNum >= count){

                   // System.out.println("rowNo is : " + rowNum);
                   // System.out.println("count is : " + count);

                    count = rowNum;
                }
            }
        }
        catch(Exception t) {
            JOptionPane.showMessageDialog(null, "numRows error 1 " + t);
            t.printStackTrace();
        }
        finally{
            try{
                r.close();
                s.close();
                conn.close();
                System.out.println("numRows connection closed");
            } 
            catch (Exception t) {
                JOptionPane.showMessageDialog(null, "numRows " + t);
                t.printStackTrace();
            }
        }
    }//END numRows

    public void confirmRequest(String FriendFirstName) {
        numRows();
        //System.out.println(count);
        count = count + 1;
        PreparedStatement insert = null;
        int n = JOptionPane.showConfirmDialog(null,
                "Would you like to add " + FriendFirstName + " as a friend?",
                "Yes or No",
                JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
//******* This section now inserts a new row into the friends table to confirm a request has been sent
            Connection conn = connectDatabase();
            System.out.println("confirmRequest : Database has opened successfully");
            String privateMessage = userLoggedIn + " wants to add you as a friend";
            System.out.println(privateMessage);

            String query = "insert into Friends(rowid, accountID, friendID, PrivateMessage) values (?,?,?,?)";
            try {    
                insert = conn.prepareStatement(query);
                insert.setInt(1, count);
                insert.setString(2, userLoggedIn);
                insert.setString(3, friendToAdd);
                insert.setString(4, privateMessage);
                insert.executeUpdate();
                
                try{   
                    conn.commit();
                   //JOptionPane.showMessageDialog(null, "Private message sent to " + friendFullName);
                    //JOptionPane.showMessageDialog(null, "The message should be updated");
                    
                } catch (Exception t) {
                    JOptionPane.showMessageDialog(null, "confirmRequest " + t);
                    t.printStackTrace();
                }
                finally{
                    try{
                        insert.close();
                    } 
                    catch (Exception y) {
                        JOptionPane.showMessageDialog(null, "confirmRequest 1 " + y);
                        y.printStackTrace();
                    }
                }

               
            } 
            catch (Exception g) {
                JOptionPane.showMessageDialog(null, ("confirmRequest error" + g));
                g.printStackTrace();
            }
            finally{
                try{
                    conn.close();
                    System.out.println("confirmRequest connection closed");
                } 
                catch (Exception t) {
                    JOptionPane.showMessageDialog(null, "confirmRequest again " + t);
                    t.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "If you don't want to be friends, then so be it");
        }
    } //END confirmFriend
  
    public void viewFriendPlaylist() {

    }//END viewFriendPlaylist

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_addFriend;
    private javax.swing.JTable Table_searchFriends;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton search_Button;
    private javax.swing.JTextField txt_searchFriend;
    // End of variables declaration//GEN-END:variables
}
