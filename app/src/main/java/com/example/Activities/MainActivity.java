package com.example.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import Service.PlayerService;
import SupportLibs.ItemClickSupport;
import TrackCore.TrackAddapter;
import TrackCore.TrackList;


public class MainActivity extends AppCompatActivity {

    private PlayerService.PlayerServiceBinder playerServiceBinder;
    private MediaControllerCompat mediaController;
    private MediaControllerCompat.Callback callback;
    private ServiceConnection serviceConnection;
    private TrackAddapter addapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TrackList.getTracksFromContext(getApplicationContext());
        initializeRecycler();
        initialiseSpinner();
        callback = new MediaControllerCompat.Callback() {
            @Override
            public void onPlaybackStateChanged(PlaybackStateCompat state) {
                if (state == null)
                    return;
                boolean playing = state.getState() == PlaybackStateCompat.STATE_PLAYING;
            }
        };

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                playerServiceBinder = (PlayerService.PlayerServiceBinder) service;
                try {
                    mediaController = new MediaControllerCompat(MainActivity.this, playerServiceBinder.getMediaSessionToken());
                    mediaController.registerCallback(callback);
                    callback.onPlaybackStateChanged(mediaController.getPlaybackState());
                }
                catch (RemoteException e) {
                    mediaController = null;
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                playerServiceBinder = null;
                if (mediaController != null) {
                    mediaController.unregisterCallback(callback);
                    mediaController = null;
                }
            }
        };
        bindService(new Intent(this, PlayerService.class), serviceConnection, BIND_AUTO_CREATE);

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onBtnPauseClick(View view){ if (mediaController != null) mediaController.getTransportControls().pause(); }
    public void onBtnPlayClick(View view) {if (mediaController != null) mediaController.getTransportControls().play();}
    public void onBtnNextClick(View view) {if (mediaController != null) mediaController.getTransportControls().skipToNext();}
    public void onBtnPrevClick(View view) {if (mediaController != null) mediaController.getTransportControls().skipToPrevious();;}
    public void initializeRecycler(){
        RecyclerView rv = findViewById(R.id.rv);
        addapter = new TrackAddapter(TrackList.getTrackList());
        rv.setAdapter(addapter);
        ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                if (position !=RecyclerView.NO_POSITION){
                    if (mediaController.getPlaybackState() != null)
                        if (mediaController.getPlaybackState().getState() == PlaybackStateCompat.STATE_PLAYING)
                            mediaController.getTransportControls().pause();
                    TrackList.setCurrentItemIndex(position);
                    mediaController.getTransportControls().play();
                }
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
    public void initialiseSpinner(){
        String[] data = {"По умолчанию", "По названию", "По автору", "По длительности"};
        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Сортировка");
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                addapter.SortData(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        playerServiceBinder = null;
        if (mediaController != null) {
            mediaController.unregisterCallback(callback);
            mediaController = null;
        }
        unbindService(serviceConnection);
    }
}




