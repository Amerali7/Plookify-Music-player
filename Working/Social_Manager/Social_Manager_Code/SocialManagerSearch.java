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

public class SocialManagerSearch extends javax.swing.JPanel {

    //Connection conn = connectDatabase();
    public static String userLoggedIn = "";
    public static String userFirstName = "";
    public static String userLastName = "";
    public static String userAccountType = "";
    public static String userDiscoverable = "";

    public SocialManagerSearch() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_searchFriend = new javax.swing.JTextField();
        search_Button = new javax.swing.JButton();
        addFriend_button = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        result1 = new javax.swing.JTextPane();

        search_Button.setText("Search");
        search_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_ButtonActionPerformed(evt);
            }
        });

        addFriend_button.setVisible(false);
        addFriend_button.setText("+");

        jScrollPane2.setViewportView(result1);
        jScrollPane2.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_searchFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search_Button, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addFriend_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(search_Button, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(txt_searchFriend))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(addFriend_button, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(327, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public static Connection connectDatabase() {
        //Connecting to database, if successfull will return true
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite://C:\\Users\\Sofia\\Documents\\QMUL\\Year Two\\Semester 4\\Software Engineering Project\\Master_Project\\SE33\\Plookify\\src\\Social\\plookifyDB.sqlite");//plookifyDataBase.sqlite works , there is another user I have added as a test
            c.setAutoCommit(false);
            //System.out.println("Database has opened successfully as usual");

        } catch (Exception e) {
            System.err.println("Connection Database : " + e.getClass().getName() + ": " + e.getMessage());
        }
        return c;
    }

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

                System.out.println("userFirstName : " + userFirstName);
                System.out.println("userLastName : " + userLastName);
                System.out.println("userAccountType : " + userAccountType);
                System.out.println("userDiscoverable : " + userDiscoverable);
                System.out.println();
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
            catch (SQLException ex) {
                Logger.getLogger(TestLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//END userSignedIn

    public boolean isEditable() {
        return false;
    }

//************************************** THIS NOW WORKS....LEAVE IT ALONE ************************************** //
    
    public static String searchedName = "";
    public static int round = 1;
    public static String friendFullName ="";
    public static String friendEmail = "";
    
    private void search_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_ButtonActionPerformed
        System.out.println("user logged in is " + userLoggedIn); // This now works and passes the email signed in with here
        round = 1;
        searchedName = txt_searchFriend.getText();
        jScrollPane2.setVisible(false);
        addFriend_button.setVisible(false);
        System.out.println("You are currently : " + userAccountType);
        Connection conn = connectDatabase();
        System.out.println("search_ButtonActionPerformed : Database has opened successfully");
        if(userAccountType.equals("p")){
            String qry = "select * from Account where firstName=?";
            
            try{
                
                //  System.out.println("!!!!Works till here!!!!");
                PreparedStatement pst = null;

                pst=conn.prepareStatement(qry);
                pst.setString(1, txt_searchFriend.getText());
                
                ResultSet rs = pst.executeQuery();
                
                if(rs.next()){ // Later need to see if the user who is logged in can be exempt from the friends list
                                        //Checks to see if the user logged in is paying or free
       //             if(userAccountType.equals("p")){ 

                        String  Discoverable = rs.getString("Discoverable");
                        String  email = rs.getString("email");
                        System.out.println(email + " is " + Discoverable); 
                        //Need to see if user is private or discoverable before it is displayed in the search reuslts
                            
                     //   System.out.println("addFriend :           " + searchedName);
                        if(Discoverable.equals("true") && round ==1 ){
                            round = round + 1;
                                String FirstName = rs.getString("firstName");
                                String LastName = rs.getString("lastName");
                                friendEmail = rs.getString("email");
                                String friendFullName = FirstName + " " + LastName;
                                
                                
                                String discover = rs.getString("Discoverable");

                                JOptionPane.showMessageDialog(null, friendFullName + " is " + discover);
                                txt_searchFriend.setText("");
                                result1.setText(friendFullName);
                                result1.isEditable();
                                jScrollPane2.setVisible(true);
                                addFriend_button.setVisible(true);
                                

                                //****** Need to add an ActionListener so when button to add friend is clicked need to confirm if user is paying or not            
                                addFriend_button.addActionListener(
                                    new ActionListener() {
                                        public void actionPerformed(ActionEvent event) {
                                            //txt_searchFriend.setText("");
                                            
                                                addFriend(searchedName);
                                                
                                             
                                           // repaint();
                                            //revalidate();
                                            
                                        }
                                    }); //END addActionListener

                                
                        }//END if statement
                        else{
                            JOptionPane.showMessageDialog(null, "Name not found");
                        }
                

                }//END if statement
            pst.close();
            rs.close();
            }//END try

            catch (Exception w){
                JOptionPane.showMessageDialog(null, "search_buttonAction1 : " + w);
            }
            finally{
                try{
                    conn.close();
                    System.out.println("search_ButtonActionPerformed connection closed");
                } 
                catch (SQLException y) {
                    JOptionPane.showMessageDialog(null, "search_buttonAction finally : " + y);
                }
            }            
            
    }//END if statement
    else{
        JOptionPane.showMessageDialog(null, "You are a free user and cannot add friends.");
    }
 

    }                                             

    /*      
    }//GEN-LAST:event_search_ButtonActionPerformed
     */

//************************************** THIS NOW WORKS....LEAVE IT ALONE ************************************** //    
// This method confirms if the user is a paying user or free user    
    public void addFriend(String friendName) {
        jScrollPane2.setVisible(false);
        addFriend_button.setVisible(false);
        PreparedStatement pst = null;
        ResultSet rs = null;

        String qry = "select * from Account";
    //if(friendName.equals(txt_searchFriend.getText())) {   
        try {
            Connection conn = connectDatabase();
            System.out.println("addFriend : Database has opened successfully");
            pst = conn.prepareStatement(qry);
            rs = pst.executeQuery();

            while (rs.next()) {

                String accountType = rs.getString("accountType");
                String FriendFirstName = rs.getString("firstName");

                if (friendName.equals(FriendFirstName)) { // To see the friend's subscriptionTy[e

                    System.out.println("It matched: " + friendName);
                    System.out.println("This user is " + accountType);

                    if (accountType.equals("p")) {// If subscription is 'p' for paid then reconfirm question to user                            
                        rs.close();
                        pst.close();
                        conn.close();
                        System.out.println("addFriend connection closed");
                        
                        confirmRequest(FriendFirstName);
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, friendName + " is not a paying subscriber and cannot add him/her as a friend");
                        break;
                    }
                }

            }//END while statement 

        } //END try
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, ("addFriend error" + e));
        }//}
/*       finally{
            try{
                conn.close();
                System.out.println("addFriend connection closed");
            } 
        catch(Exception t) {
            JOptionPane.showMessageDialog(null, "AddFriend " + t);
            t.printStackTrace();
        }
        }     */   
        
    } //END addFriend
    

//************************************** THIS NOW WORKS....LEAVE IT ALONE ************************************** //     
    public static int count = 0;

    public void noRows(){
     //   vacuumTable();

        Connection conn = connectDatabase();
        System.out.println("noRows : Database has opened successfully");
        Statement s = null;
        ResultSet r = null;
        try{   
            s = conn.createStatement();
            r = s.executeQuery("SELECT rowid, * from Friends");
            while(r.next()){
                int rowNo = r.getInt("rowid");
                System.out.println("rowID  : " + rowNo);
                if(rowNo >= count){

                    System.out.println("rowNo is : " + rowNo);
                    System.out.println("count is : " + count);

                    count = rowNo;
                }
            }
        }
        
        catch(Exception t) {
            JOptionPane.showMessageDialog(null, "noRows error 1 " + t);
            t.printStackTrace();
        }
        finally{
            try{
                r.close();
                s.close();
                conn.close();
                System.out.println("noRows connection closed");
            } 
            catch (Exception t) {
                JOptionPane.showMessageDialog(null, "Row update " + t);
                t.printStackTrace();
            }
        }
    }

//************************************** THIS NOW WORKS....LEAVE IT ALONE ************************************** //

    public void confirmRequest(String FriendFirstName) {
        noRows();
        System.out.println(count);
        count = count + 1;
        PreparedStatement insert = null;
//        Connection conn = connectDatabase();
//        System.out.println("confirmRequest : Database has opened successfully");
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
                insert.setString(3, friendEmail);
                insert.setString(4, privateMessage);

                insert.executeUpdate();
                
                //insert.close();
                

//********** This section confirms the database is updated 
              /*  try {
                    String testing = "SELECT * FROM Friends;";
                    Statement stmt = conn.createStatement();
                    ResultSet rst = stmt.executeQuery(testing);
                    while (rst.next()) {
                        //int rowid = rst.getInt("rowid");
                        String accountID = rst.getString("accountID");
                        String friendID = rst.getString("friendID");
                        String PrivateMessage = rst.getString("PrivateMessage");

                        // System.out.println( "Row ID = " + rowid );
                        System.out.println("Account ID = " + accountID);
                        System.out.println("Friend ID = " + friendID);
                        System.out.println("Private Message = " + PrivateMessage);

                        System.out.println();
                    }
                    rst.close();
                    stmt.close();
                    //conn.commit();
                } catch (Exception p) {
                    System.err.println("Confirming table update error : " + p.getClass().getName() + ": " + p.getMessage());
                }*/

                
                
                try {   
                    conn.commit();
                    JOptionPane.showMessageDialog(null, "Private message sent to " + friendFullName);
                    JOptionPane.showMessageDialog(null, "The message should be updated");
                    
                } catch (Exception t) {
                    JOptionPane.showMessageDialog(null, "Message update " + t);
                    t.printStackTrace();
                }
                finally{
                    try{
                        insert.close();
                    } 
                    catch (SQLException y) {
                        Logger.getLogger(TestLogin.class.getName()).log(Level.SEVERE, null, y);
                    }
                }

               
            } 
            catch (Exception g) { //Insert error code for duplicate friend requests if a request has already been sent to the friend
                
                JOptionPane.showMessageDialog(null, ("updating database error" + g));
            }
            finally{
                try{
                    conn.close();
                    System.out.println("confirmRequest connection closed");
                   // conn.commit();
                } 
                catch (Exception t) {
                    JOptionPane.showMessageDialog(null, "Message update again " + t);
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
    private javax.swing.JButton addFriend_button;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane result1;
    private javax.swing.JButton search_Button;
    private javax.swing.JTextField txt_searchFriend;
    // End of variables declaration//GEN-END:variables
}
