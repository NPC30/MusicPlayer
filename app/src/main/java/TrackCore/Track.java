package TrackCore;

import android.net.Uri;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class Track {
    private long id;
    private String artist,title,duration,size, genre, image;
    Uri data;
    private long durationLong;
    public Track(long songID, String songTitle, String songArtist, Uri thisData, String trackDuration, String trackSize, String trackGenre) {
        id=songID;
        title=songTitle;
        artist=songArtist;
        data=thisData;
        duration=millisecToMins(trackDuration);
        size = trackSize;
        genre = trackGenre;
        durationLong= Long.parseLong(trackDuration);


    }

    public Track() {

    }

    public long getID(){return id;}
    public String getTitle(){return title;}
    public String getArtist(){return artist;}
    public Uri getData(){return data;}
    public String getDuration(){return duration;}
    public String millisecToMins(String duration){
        String formated = "";
        long minutes = TimeUnit.MILLISECONDS.toMinutes(parseInt(duration));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(parseInt(duration)) - minutes * 60;
        if (minutes >= 0) {
            if (minutes < 10)
                formated+="0";
            formated += minutes + ":";
        }
        if (seconds > 0) {
            if (seconds < 10)
                formated+="0";
            formated += seconds;
        }
        return formated;
    }
    public long getDurationLong(){
        return durationLong;
    }
    public static Comparator<Track> TrackTitleComp = new Comparator<Track>() {
        @Override
        public int compare(Track track, Track t1) {
            String TrackName1 = track.getTitle();
            String TrackName2 = t1.getTitle();
            return TrackName1.compareTo(TrackName2);
        }
    };
    public static Comparator<Track> TrackArtistComp = new Comparator<Track>() {
        @Override
        public int compare(Track track, Track t1) {
            String TrackArtist1 = track.getArtist();
            String TrackArtist2 = t1.getArtist();
            return TrackArtist1.compareTo(TrackArtist2);
        }
    };
    public static Comparator<Track> TrackDurationComp = new Comparator<Track>() {
        @Override
        public int compare(Track track, Track t1) {
            long TrackDuration1 = track.getDurationLong();
            long TrackDurarion2 = t1.getDurationLong();
            return (int) (TrackDuration1 - TrackDurarion2);
        }
    };
    public static Comparator<Track> TrackIdComp = new Comparator<Track>() {
        @Override
        public int compare(Track track, Track t1) {
            long TrackId1 = track.getID();
            long TrackId2= t1.getID();
            return (int) (TrackId1 - TrackId2);
        }
    };



}
