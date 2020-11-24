package com.example.musicplayer;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TrackList {
    private static ArrayList<Track> trackList = new ArrayList<Track>();
    //Tracks DB
    public static void getTracksFromContext(Context appContext){
        Uri uriExternal = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor=appContext.getContentResolver().query(uriExternal, null, null, null, null);
        if (musicCursor!=null && musicCursor.moveToFirst()) {
            int titleColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST);
            int durationColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            int dataColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int sizeColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.SIZE);
            int genreColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.GENRE);
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                String thisData = Uri.withAppendedPath(uriExternal,Long.toString(thisId)).toString();
                //String thisData = musicCursor.getString(dataColumn);
                String thisSize = musicCursor.getString(sizeColumn);
                String thisDuration = musicCursor.getString(durationColumn);
                String thisGenre = "N/A";
                if (genreColumn != -1)
                    thisGenre = musicCursor.getString(genreColumn);
                trackList.add(new Track(thisId, thisTitle, thisArtist, thisData, thisDuration,thisSize,thisGenre));
            }
            while (musicCursor.moveToNext());
        }
    }
    public static ArrayList<Track> getTrackList(){
        return trackList;
    }

}
