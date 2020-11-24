package com.example.musicplayer;

import android.net.Uri;

import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class Track {
    private long id;
    private String artist,title,duration,size, data, genre;
    public Track(long songID, String songTitle, String songArtist, String thisData, String trackDuration, String trackSize, String trackGenre) {
        id=songID;
        title=songTitle;
        artist=songArtist;
        data=thisData;
        duration=millisecToMins(trackDuration);
        size = trackSize;
        genre = trackGenre;

    }

    public Track() {

    }

    public long getID(){return id;}
    public String getTitle(){return title;}
    public String getArtist(){return artist;}
    public String getData(){return data;}
    public String getDuration(){return duration;}
    public String millisecToMins(String duration){
        String formated = "";
        long minutes = TimeUnit.MILLISECONDS.toMinutes(parseInt(duration));
        long hours = TimeUnit.MILLISECONDS.toHours(parseInt(duration));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(parseInt(duration));
        if (hours > 0)
            formated += hours + ":";
        if (minutes >= 0 && minutes < 60) {
            if (minutes < 10)
                formated+="0";
            formated += minutes + ":";
        }
        else if (minutes >= 60)
            formated += minutes/60 + ":";
        if (seconds > 0 && seconds < 60) {
            if (seconds < 10)
                formated+="0";
            formated += seconds;
        }
        else if (seconds >=60){
            if (seconds/60 < 10)
                formated +="0";
            formated += seconds/60;
        }
        return formated;
    }
}
