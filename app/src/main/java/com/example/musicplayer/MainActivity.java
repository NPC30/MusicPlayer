package com.example.musicplayer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{
    private Button play;
    private MusicControls musicControls;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicControls = new MusicControls();
        TrackList.getTracksFromContext(getApplicationContext());
        //buildTable(TrackList.getTrackList());
        initializeRecycler();
        musicControls.setContext(getApplicationContext());

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onBtnPauseClick(View view){
        musicControls.MusicPause();
    }
    public void onBtnPlayClick(View view) {musicControls.MusicPlay();}
    public void onBtnNextClick(View view) {musicControls.NextTrack(musicControls.getCurrentTrackIndex()+1);}
    public  void onBtnPrevClick(View view) {musicControls.PrevTrack(musicControls.getCurrentTrackIndex()-1);}
    public void initializeRecycler(){
        RecyclerView rv = findViewById(R.id.rv);
        TrackAddapter addapter = new TrackAddapter(TrackList.getTrackList());
        rv.setAdapter(addapter);
        ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                if (position !=RecyclerView.NO_POSITION){
                    musicControls.NewMusicPlay(position);
                }
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

}

