package com.example.musicplayer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
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

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnClickListener {
    private Button play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = (Button) findViewById(R.id.btnPlay);
        play.setOnClickListener(this);
        TableLayout tableAudio = (TableLayout) findViewById(R.id.tlAudio);
        tableAudio.setStretchAllColumns(true);
        tableAudio.setShrinkAllColumns(true);

        TextView title = new TextView(this);
        title.setText("Audio list");
        TableRow rowTitle = new TableRow(this);
        rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title.setGravity(Gravity.CENTER);
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = 6;
        rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        rowTitle.addView(title, params);

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClick (View v){
        if (play.equals(v)){
            //MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.test);
            //mediaPlayer.start();

            ArrayList audio=new ArrayList();
            Cursor c=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Audio.Media.DISPLAY_NAME}, null, null, null);
            while(c.moveToNext())
            {
                String artist=c.getString(c.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String track = c.getString(c.getColumnIndex(MediaStore.Audio.Media.TRACK));
                

            }

        }
    }

}