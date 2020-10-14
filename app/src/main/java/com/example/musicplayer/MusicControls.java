package com.example.musicplayer;

import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

public class MusicControls {
    private MediaPlayer musicPlayer;
    public MusicControls(){
        musicPlayer = new MediaPlayer();
    }
    public void NewMusicPlay(Uri link){
        if (musicPlayer.isPlaying()){
            musicPlayer.stop();
            musicPlayer.reset();
        }
        try {
            musicPlayer.setDataSource(String.valueOf(link));
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
}
