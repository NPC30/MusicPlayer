package com.example.musicplayer;

import android.media.MediaPlayer;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;

public class MusicControls {
    private MediaPlayer musicPlayer;
    private int currTrack;
    private Context context;
    public MusicControls(){
        musicPlayer = new MediaPlayer();
        musicPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                NextTrack(currTrack+1);
            }
        });
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void NewMusicPlay(int current){
        currTrack = current;
            musicPlayer.stop();
            musicPlayer.reset();
        try {
            musicPlayer.setDataSource(context, Uri.parse(TrackList.getTrackList().get(current).getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            musicPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        musicPlayer.start();
    }
    public void MusicPause(){
        if (musicPlayer.isPlaying())
            musicPlayer.pause();
    }
    public void MusicPlay(){
        if(!musicPlayer.isPlaying())
            musicPlayer.start();
    }
    public void NextTrack(int current){
        currTrack = current;
        if (!(currTrack < TrackList.getTrackList().size() && currTrack > 0))
            currTrack = 0;
        NewMusicPlay(currTrack);
    }
    public int getCurrentTrackIndex(){
        return currTrack;
    }
    public void PrevTrack(int current){
        NextTrack(current);
    }

}
