/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package radio;
import java.sql.*;

public class sqlite extends RadioGUI{
    public static void main(String args[]) {    
        testStub();
       
    }
    public static void testStub(){//learning how to use JDBC for SQLite
        String qry = "SELECT * FROM track;";
        executeStmt(qry);
    }
    public static Connection connectToDB(){
        //method to connect to database returns true on success
        Connection c = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\abbas\\Documents\\NetBeansProjects\\Plookify\\build\\classes\\Abbas\\plookifyDB.sqlite");
          c.setAutoCommit(false);
          System.out.println("Opened database successfully");
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        //System.out.println("Opened database successfully");
        return c;
    }
     public static void executeStmt(String statement){
        Connection c = connectToDB();
        Statement stmt = null;
        try{            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(statement);           
            while ( rs.next() ) {
                int trackID = rs.getInt("trackID");
                String  title = rs.getString("title");
                String  artist = rs.getString("artist");
                String  album = rs.getString("album");
                String  genre = rs.getString("genre");
                int time  = rs.getInt("time");
                System.out.println( "Track ID = " + trackID );
                System.out.println( "Title = " + title );
                System.out.println( "Artist = " + artist );
                System.out.println( "Album = " + album );
                System.out.println( "Genre = " + genre );
                System.out.println( "Time = " + time );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
  /* private void Updatetable(){
       
    Connection c = connectToDB();
        Statement stmt = null;
        try {       
          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM track;" );
        
          
          rs=stmt.executeQuery(null);
          
          Tables.setModel(DbUtils.resultSetToTableModel(rs));
          
           rs.close();
          stmt.close();
          c.close();
         
          }
       catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
       
   }*/
     
    public static void selectStatement(){
        Connection c = connectToDB();
        Statement stmt = null;
        try {       
          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM track;" );
          while ( rs.next() ) {
             int trackID = rs.getInt("trackID");
             String  title = rs.getString("title");
             String  artist = rs.getString("artist");
             String  album = rs.getString("album");
             String  genre = rs.getString("genre");
             int time  = rs.getInt("time");
             System.out.println( "Track ID = " + trackID );
             System.out.println( "Title = " + title );
             System.out.println( "Artist = " + artist );
             System.out.println( "Album = " + album );
             System.out.println( "Genre = " + genre );
             System.out.println( "Time = " + time );
             System.out.println();
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
   
}