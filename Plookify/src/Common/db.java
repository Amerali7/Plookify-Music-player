package Common;

import java.sql.*;

/**
 *
 * @author db310
 */
public class db {

    public static Connection connectToDB() {
        //connect to database returns Connection on success
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/Common/plookifyDB.sqlite");
            //c.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return c;
    }
}
