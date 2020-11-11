package playlist;

import java.sql.*;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Daivd
 */
public class PlaylistLogic {

    public static String capitalizeFirstLetter(String in) {
        //capitalizes the first character in a string
        if (in.length() < 2) {
            return null;
        }
        String out = in.substring(0, 1).toUpperCase() + in.substring(1);
        return out;
    }

    public static ArrayList<Track> searchTrackByArtist(String queryArtist) {
        //tidy up the search query
        queryArtist = queryArtist.trim();
        queryArtist = queryArtist.toUpperCase();
        //arraylist to hold new track objects
        ArrayList<Track> resultTracks = new ArrayList<>();
        try {
            //do database, do query, get results
            Connection c = Common.db.connectToDB();
            Statement queryStatement = c.createStatement();
            String q = "SELECT * FROM Track WHERE artist LIKE '%" + queryArtist + "%'";
            ResultSet rsTrackByArtist = queryStatement.executeQuery(q);
            //new track object for each row in results
            while (rsTrackByArtist.next()) {
                Track t = new Track(
                        rsTrackByArtist.getInt("trackID"),
                        rsTrackByArtist.getString("title"),
                        rsTrackByArtist.getString("artist"),
                        rsTrackByArtist.getString("album"),
                        rsTrackByArtist.getString("genre"),
                        rsTrackByArtist.getInt("time")
                );
                resultTracks.add(t);
            }
            //done so close the database stuff
            rsTrackByArtist.close();
            queryStatement.close();
            c.close();
            //return arraylist of track objects
            return resultTracks;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public static ArrayList<Account> determineFriendsNew(Account theAccount) {
        ArrayList<String> friendsEmails = new ArrayList<>();
        ArrayList<Account> friendsAccounts = new ArrayList<>();

        try {
            //get email addresses from FriendsConfirmed table where accountID 
            //  like current user's
            Connection c = Common.db.connectToDB();
            Statement stmt = c.createStatement();
            String q = "SELECT * FROM FriendConfirmed WHERE accountID LIKE " + theAccount.getEmail() + "'";
            ResultSet rsEmails = stmt.executeQuery(q);
            if (rsEmails.next()) {
                do {
                    String email = rsEmails.getString("friendID");
                    friendsEmails.add(email);
                } while (rsEmails.next());
            } else {
                //no friends :'(
                return null;
            }
            //look up account details by each email address 
            //  get account object add and to friends arraylist
            for (String em : friendsEmails) {
                friendsAccounts.add(getAccountByEmailAddress(em));
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
        return friendsAccounts;
    }

    public static ArrayList<Account> determineFriends(Account theAccount) {
        ArrayList<Account> friends = new ArrayList<>();
        try {
            Connection c = Common.db.connectToDB();
            Statement stmt = c.createStatement();
            String q = "SELECT * FROM Friends WHERE accountID = " + theAccount.getAccountID();
            ResultSet rsRequests = stmt.executeQuery(q);

            ArrayList<Integer> requests = new ArrayList<>();

            if (rsRequests.next()) {
                do {
                    requests.add(rsRequests.getInt("friendID"));
                } while (rsRequests.next());
            } else {
                return null;
            }

            String qq = "SELECT * FROM Friends WHERE friendID = " + theAccount.getAccountID();
            ResultSet rsRequested = stmt.executeQuery(qq);
            ArrayList<Integer> requested = new ArrayList<>();

            if (rsRequested.next()) {
                do {
                    requested.add(rsRequested.getInt("accountID"));
                } while (rsRequested.next());
            } else {
                return null;
            }
            ArrayList<Integer> friendAccIDs = findMatches(requests, requested);
            rsRequested.close();
            rsRequests.close();
            c.close();

            for (Integer AccID : friendAccIDs) {
                friends.add(getAccountByAccID(AccID));
            }

            //only leave shared playlists
            //if a friend's playlist has type p, remove it
            for (Account f : friends) {
                if (f.playlists != null) {
                    //basic forloop to avoid concurrency exception
                    for (int i = 0; i < f.getPlaylists().size(); i++) {
                        if (f.playlists.get(i).getType() == 'p') {
                            f.playlists.remove(i);
                        }
                    }
                }
            }

            return friends;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public static Account getAccountByAccID(int theAccountID) {
        Account gotAccount;
        try {
            Connection c = Common.db.connectToDB();
            //q account table
            Statement stmt = null;
            stmt = c.createStatement();
            String q = "SELECT * FROM Account WHERE accountID = '" + theAccountID + "'";

            ResultSet rsAccount = stmt.executeQuery(q);

            int accID = theAccountID;
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

            rsAccount.close();
            stmt.close();
            c.close();
            gotAccount = new Account(accID, fName, lName, email, paymentType, accountType);
            gotAccount.playlists = getPlaylistsByAccID(gotAccount.getAccountID());
            return gotAccount;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
    
    public static Account getAccountByEmailAddress(String theEmail) {
        Account gotAccount;
        try {
            Connection c = Common.db.connectToDB();
            //q account table
            Statement stmt = null;
            stmt = c.createStatement();
            String q = "SELECT * FROM Account WHERE email = '" + theEmail + "'";

            ResultSet rsAccount = stmt.executeQuery(q);

            int accID;
            String fName;
            String lName;
            String email = theEmail;
            char paymentType;
            char accountType;
            
            accID = rsAccount.getInt("accountID");
            fName = rsAccount.getString("firstName");
            lName = rsAccount.getString("lastName");
            paymentType = rsAccount.getString("paymentType").charAt(0);
            accountType = rsAccount.getString("accountType").charAt(0);

            rsAccount.close();
            stmt.close();
            c.close();
            gotAccount = new Account(accID, fName, lName, email, paymentType, accountType);
            gotAccount.playlists = getPlaylistsByAccID(gotAccount.getAccountID());
            return gotAccount;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public static ArrayList<Integer> findMatches(ArrayList<Integer> a, ArrayList<Integer> b) {
        //WILL BE OBSOLETE
        //takes two arraylists of integers, returns arraylist of the integers which 
        //are present in both lists
        //Used in plookify to determine which friend requests have been sent and accepted
        ArrayList<Integer> matches = new ArrayList<>();
        for (Integer anInteger : a) {
            if (b.contains(anInteger)) {
                matches.add(anInteger);
            }
        }
        if (matches.size() < 1) {
            matches.add(0, null);
        }
        return matches;
    }

    public static String calculatePlaylistTotalTime(Playlist p) {
        //returns string of total of time of all tracks in a playlist
        if (p.tracks == null) {
            //if no tracks in playlist
            return "00:00";
        }
        int totalSeconds = 0;
        for (Track t : p.tracks) {
            totalSeconds += t.getTime();
        }
        return formatSecondsToMinutes(totalSeconds);
    }

    public static String formatSecondsToMinutes(int inSecs) {
        //converts number of seconds into string of minutes:seconds
        String formatMinutes;
        int mins = inSecs / 60;
        int secs = inSecs - (mins * 60);
        String secString;
        if (String.valueOf(secs).length() == 1) {
            secString = "0" + Integer.toString(secs);
        } else {
            secString = Integer.toString(secs);
        }
        formatMinutes = Integer.toString(mins) + ":" + secString;
        return formatMinutes;
    }

    public static void sendTracksToNowPlaying(ArrayList<Track> tracks) {
        //track player module takes a track ID and deals with playing a track
        // from then on

        //outputs whatever is now playling to system output for standalone operation   
        if (tracks == null) {
            return;
        }
        System.out.println("---------------------------------\nNOW PLAYING\n---------------------------------");
        for (Track t : tracks) {
            //call add to trackplayer method on t
            try {
                //call track player module
                player.logic.SaveNowPlaying.saveS(t.getTrackID(), t.getTitle(), t.getArtist(), t.getTime());
                //        t.getArtist(), t.getTime());
            } catch (Exception e) {
                System.out.println("Error: Could not access track player module!");
            }
            System.out.println(t.getTitle() + "    -   " + t.getArtist());
        }
        System.out.println("---------------------------------");
    }

    public static Playlist[] indexArrayListPlaylist(ArrayList<Playlist> input) {
        Playlist[] array = new Playlist[input.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = input.get(i);
        }
        return array;
    }

    public static Track[] indexArrayListTrack(ArrayList<Track> input) {
        Track[] array = new Track[input.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = input.get(i);
        }
        return array;
    }

    public static void dialog(String input) {
        JOptionPane.showMessageDialog(null, input);
    }

    public static boolean createPlaylist(String pname, Account theAccount) {
        if (pname.equals("")) {
            dialog("Playlist name can't be blank");
            return false;
        }
        Playlist newP = new Playlist(pname, theAccount.getAccountID());
        try {
            Connection c = Common.db.connectToDB();
            Statement stmt = c.createStatement();
            String insertPlaylistManager = "INSERT INTO PlaylistManager ('accountID','name') VALUES ('" + theAccount.getAccountID() + "','" + pname + "')";
            stmt.executeUpdate(insertPlaylistManager);
            stmt.close();
            c.close();
            theAccount.playlists = getPlaylistsByAccID(theAccount.getAccountID());
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public static boolean changePlaylistType(Playlist modifyPlaylist) {
        //get the new type of the playlist (also changes the type)
        char newType = modifyPlaylist.changeType();
        //change it back incase the database op fails
        modifyPlaylist.changeType();
        try {

            Connection c = Common.db.connectToDB();
            Statement stmt = c.createStatement();
            String q = "UPDATE PlaylistManager SET type = '" + newType + "' WHERE playlistID = '" + modifyPlaylist.getPlaylistID() + "'";
            stmt.executeUpdate(q);
            stmt.close();
            c.close();
            modifyPlaylist.changeType();
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }

    }

    public static boolean renamePlaylist(Playlist modifyPlaylist, String newName) {
        if (newName == null) {
            return false;
        }
        newName = newName.trim();

        try {

            Connection c = Common.db.connectToDB();
            Statement renamePlaylistStmt = c.createStatement();
            String q = "UPDATE PlaylistManager SET name = '" + newName + "' WHERE playlistID = '" + modifyPlaylist.getPlaylistID() + "'";
            renamePlaylistStmt.executeUpdate(q);
            renamePlaylistStmt.close();

            c.close();
            modifyPlaylist.setName(newName);
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public static boolean removeTrackFromPlaylist(Playlist p, Track victimTrack) {
        if (p.tracks.contains(victimTrack)) {

            try {
                Connection c = Common.db.connectToDB();
                Statement stmt = c.createStatement();
                String q = "DELETE FROM Playlist WHERE playlistID == " + p.getPlaylistID() + " AND trackID == " + victimTrack.getTrackID();
                stmt.executeUpdate(q);
                stmt.close();
                c.close();
                p.tracks.remove(victimTrack);
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean deletePlaylist(Playlist victimPlaylist) {
        try {
            Connection c = Common.db.connectToDB();
            Statement stmt = c.createStatement();
            String q = "DELETE FROM Playlist WHERE playlistID LIKE '" + victimPlaylist.getPlaylistID() + "'";
            stmt.executeUpdate(q);
            q = "DELETE FROM PlaylistManager WHERE playlistID LIKE '" + victimPlaylist.getPlaylistID() + "'";
            stmt.executeUpdate(q);
            stmt.close();
            c.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public static void addNewTrackToPlaylist(Playlist thePlaylist, Track theNewTrack) {
        //if the playlist already has tracks
        if (thePlaylist.tracks != null) {
            //determine whether track is already in playlist
            boolean alreadyInPlaylist = false;
            //go through playlist comparing track IDs
            for (Track t : thePlaylist.tracks) {
                if (t.getTrackID() == theNewTrack.getTrackID()) {
                    //track ids match
                    alreadyInPlaylist = true;
                }
            }
            if (alreadyInPlaylist) {
                //track is already in playlist so ask whether to add again
                String question = theNewTrack.getTitle() + " is already in playlist "
                        + thePlaylist.getName() + "\nDo you want to add it again?";
                if (JOptionPane.showConfirmDialog(null, question,
                        "Add duplicate track?", JOptionPane.YES_NO_OPTION) > 0) {
                    //user doesn't want to add track again so return
                    return;
                }
            }
        }

        try {
            Connection c = Common.db.connectToDB();
            Statement updatedbStmt = c.createStatement();
            String q = "INSERT INTO Playlist ('playlistID','trackID') VALUES "
                    + "('" + thePlaylist.getPlaylistID() + "','" + theNewTrack.getTrackID() + "')";
            updatedbStmt.executeUpdate(q);
            updatedbStmt.close();
            c.close();
            thePlaylist.addTrack(theNewTrack);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            dialog("Could not add track to playlist");
        }
    }

    public static ArrayList getTracksForPlaylist(int pID) {
        //looks up tracks in playlists and adds to playlist object
        ArrayList tracks = new ArrayList<>();
        try {
            Connection c = Common.db.connectToDB();
            //get tracks in playlist
            Statement stmtPlaylists = c.createStatement();
            String q = "SELECT * FROM Playlist WHERE playlistID LIKE '" + pID + "'";
            ResultSet rsTracks = stmtPlaylists.executeQuery(q);
            //find how many tracks in playlist
            Statement countstmt = null;
            countstmt = c.createStatement();
            q = "SELECT COUNT(*) AS count FROM Playlist WHERE playlistID LIKE '" + pID + "'";
            ResultSet rsTrackCount = countstmt.executeQuery(q);
            int trackCount = rsTrackCount.getInt("count");
            rsTrackCount.close();
            countstmt.close();
            //if no tracks in playlist add a null
            if (trackCount < 1) {
                tracks.add(0, null);
                return null;
            }
            int trackID;
            String title;
            String artist;
            String album;
            String genre;
            int time;
            //make new Track object for each row in rs
            Track t;
            for (int i = 0; i < trackCount; i++) {
                rsTracks.next();
                trackID = rsTracks.getInt("trackID");
                //lookup Track data from Track table                
                Statement trackinfo = null;
                trackinfo = c.createStatement();
                q = "SELECT * FROM Track WHERE trackID LIKE '" + trackID + "'";
                ResultSet rsTrackLookup = trackinfo.executeQuery(q);
                //get values from rs
                title = rsTrackLookup.getString("title");
                artist = rsTrackLookup.getString("artist");
                album = rsTrackLookup.getString("album");
                genre = rsTrackLookup.getString("genre");
                time = rsTrackLookup.getInt("time");
                //new Track from data
                t = new Track(trackID, title, artist, album, genre, time);
                tracks.add(t);
                rsTrackLookup.close();
                trackinfo.close();
            }
            rsTracks.close();
            c.close();
            return tracks;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public static ArrayList<Playlist> getPlaylistsByAccID(int AccountID) {
        ArrayList ownPlaylists = new ArrayList<Playlist>();
        try {
            Connection c = Common.db.connectToDB();
            Statement stmt = null;
            stmt = c.createStatement();
            String q = "SELECT * FROM PlaylistManager WHERE accountID LIKE '" + AccountID + "'";
            ResultSet rsPlaylists = stmt.executeQuery(q);
            Statement countstmt = c.createStatement();
            q = "SELECT COUNT(*) AS count FROM PlaylistManager WHERE accountID LIKE '" + AccountID + "'";
            ResultSet rsPlaylistCount = countstmt.executeQuery(q);
            int playlistCount = rsPlaylistCount.getInt("count");
            rsPlaylistCount.close();
            countstmt.close();
            //if account has no playlists
            if (playlistCount < 1) {
                stmt.close();
                c.close();
                return null;
            }
            String Name;
            int accID;
            char Type;
            ArrayList<Track> Tracks;
            Playlist p;
            int PlaylistID;
            for (int i = 0; i < playlistCount; i++) {
                rsPlaylists.next();
                PlaylistID = rsPlaylists.getInt("playlistID");
                Name = rsPlaylists.getString("name");
                accID = rsPlaylists.getInt("accountID");
                Type = rsPlaylists.getString("type").charAt(0);
                //addTracks
                Tracks = getTracksForPlaylist(PlaylistID);
                p = new Playlist(PlaylistID, Name, accID, Type, Tracks);
                ownPlaylists.add(p);
            }
            rsPlaylists.close();
            stmt.close();
            c.close();
            return ownPlaylists;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
}
