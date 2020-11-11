package playlist;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class login {

    public static Account doLogin() {

        Account theAccount;

        String em = JOptionPane.showInputDialog("Input Email");
        String inPwd = JOptionPane.showInputDialog("Input Password");
        int AccountID = -1;
        //q account table
        try { //get results from account given email
            //connect to database
            Connection c = Common.db.connectToDB();
            //q account table
            Statement stmt = null;
            stmt = c.createStatement();
            String q = "SELECT accountID,password FROM Account WHERE email LIKE '" + em + "'";

            ResultSet rsPwdCheck = stmt.executeQuery(q);

            //check password is correct
            AccountID = doPasswordCheck(rsPwdCheck, inPwd);
            //password is correct so get the AccountID
            String qq = "SELECT * FROM Account WHERE email LIKE '" + em + "'";
            ResultSet rsAccount = stmt.executeQuery(qq);

            int accID = AccountID;
            String fName;
            String lName;
            String email;
            char paymentType;
            char accountType;

            fName = rsAccount.getString("firstName");
            lName = rsAccount.getString("lastName");
            email = rsAccount.getString("email");
            paymentType = rsAccount.getString("paymentType").charAt(0);
            accountType = rsAccount.getString("accountType").charAt(0);

            rsPwdCheck.close();
            rsAccount.close();
            stmt.close();
            c.close();
            theAccount = new Account(accID, fName, lName, email, paymentType, accountType);
            return theAccount;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    private static int doPasswordCheck(ResultSet rs, String inPwd) {
        String filePwd;
        int AccID;
        try {
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Email does not exist, try again.");
                System.exit(0);
                return -1;
            } else {
                do {
                    filePwd = rs.getString("password");
                    AccID = rs.getInt("accountID");
                } while (rs.next());
            }
        } catch (SQLException | HeadlessException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return -1;
        }
        if (passwordCorrect(inPwd, filePwd)) {
            return AccID;
        } else {
            JOptionPane.showMessageDialog(null, "Password incorrect, try again.");
            System.exit(0);
            return -1;
        }
    }

    private static boolean passwordCorrect(String inPwd, String filePwd) {
        return inPwd.equals(filePwd);
    }
}
