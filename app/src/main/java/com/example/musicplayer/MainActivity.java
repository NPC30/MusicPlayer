package com.example.musicplayer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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


public class MainActivity extends AppCompatActivity implements OnClickListener {
    private Button play;
    private ArrayList <Track> audio;
    private MusicControls musicControls;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = (Button) findViewById(R.id.btnPlay);
        play.setOnClickListener(this);
        musicControls = new MusicControls();
        getTracks();
        buildTable(audio);

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClick (View v){
        if (play.equals(v)){
            musicControls.MusicPlay();
        }
    }
    public void buildTable(final ArrayList<Track> audio){
        TableLayout tl = (TableLayout) findViewById(R.id.tlAudio);
        tl.removeAllViews();
        TextView[] textArray = new TextView[audio.size()];
        TableRow[] tr_head = new TableRow[audio.size()];
        for (int i = 0; i < audio.size(); i++){
            tr_head[i] = new TableRow(this);
            tr_head[i].setId(i+1);
            tr_head[i].setBackgroundColor(Color.GRAY);
            tr_head[i].setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            textArray[i] = new TextView(this);
            textArray[i].setText(audio.get(i).getData());
            textArray[i].setTextColor(Color.WHITE);
            textArray[i].setPadding(5, 5, 5, 5);
            tr_head[i].addView(textArray[i]);
            tl.addView(tr_head[i], new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            final int current = i;
            tr_head[i].setOnClickListener(new View.OnClickListener(){

                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    Uri myUri = Uri.parse(audio.get(current).getData());
                    musicControls.NewMusicPlay(myUri);
                }
            });
        }

    }
    public void getTracks(){
        audio=new ArrayList<Track>();
        Cursor musicCursor=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (musicCursor!=null && musicCursor.moveToFirst()) {
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            int durationColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.DURATION);
            int dataColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int sizeColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.SIZE);

            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                String thisData = musicCursor.getString(dataColumn);
                String thisSize = musicCursor.getString(sizeColumn);
                String thisDuration = musicCursor.getString(durationColumn);
                audio.add(new Track(thisId, thisTitle, thisArtist, thisData, thisDuration,thisSize));
            }
            while (musicCursor.moveToNext());
        }
    }
    public void onBtnPauseClick(View view){
        musicControls.MusicPause();
    }
}

