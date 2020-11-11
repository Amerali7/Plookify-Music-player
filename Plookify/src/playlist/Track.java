package playlist;
/**
 *
 * @author David
 */
public class Track {
    int trackID;String title;String artist;String album;String genre;int time;
    public int getTrackID(){return trackID;}
    public String getTitle(){return title;}
    public String getArtist(){return artist;}
    public String getAlbum(){return album;}
    public String getGenre(){return genre;}
    public int getTime(){return time;}
    //CONSTRUCT
    public Track(int TrackID, String Title, String Artist, String Album, String Genre, int Time){
        trackID=TrackID;title=Title;artist=Artist;album=Album;genre=Genre;time=Time;
    }
}
