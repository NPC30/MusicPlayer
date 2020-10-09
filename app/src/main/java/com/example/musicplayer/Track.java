package com.example.musicplayer;

public class Track {
    private long id;
    private String artist,title,duration,size,data;
    public Track(long songID, String songTitle, String songArtist, String thisData, String trackDuration, String trackSize) {
        id=songID;
        title=songTitle;
        artist=songArtist;
        data=thisData;
        duration=trackDuration;
        size = trackSize;

    }

    public Track() {

    }

    public long getID(){return id;}
    public String getTitle(){return title;}
    public String getArtist(){return artist;}
    public String getData(){return data;}
    public String getDuration(){return duration;}
}
