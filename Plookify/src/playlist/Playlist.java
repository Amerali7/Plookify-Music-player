package playlist;
import java.util.ArrayList;
/**
 *
 * @author David
 */
public class Playlist {
    public int playlistID;public String name;public int accountID;public char type;public ArrayList<Track> tracks;
    public void setName(String newValue){name = newValue;}
    public String getName(){return name;}
    public int getOwner(){return accountID;}
    public int getPlaylistID(){return playlistID;}
    public char getType(){return type;}
    //toggles playlist type and returns new type
    public char changeType(){switch(type){case 'f':type='p';break;case 'p':type='f';break;}return type;}
    public void addTrack(Track newTrack){
        if(tracks!=null){
            tracks.add(newTrack);
        }else{
            tracks=new ArrayList<>();
            tracks.add(newTrack);
        }
    }
    public ArrayList getTracks(){return tracks;}
    //CONTSTRUCT
    public Playlist(int PlaylistID,String Name,int AccountID,char Type,ArrayList Tracks){
        playlistID=PlaylistID;name=Name;accountID=AccountID;type=Type;tracks=Tracks;
    }
    public Playlist(String Name,int AccountID){
        name=Name;accountID=AccountID;        
    }
}
