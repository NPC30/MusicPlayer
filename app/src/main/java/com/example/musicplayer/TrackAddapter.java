package com.example.musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class TrackAddapter extends RecyclerView.Adapter<TrackAddapter.TrackViewHolder> {
    private ArrayList<Track> mTracks;
    public  TrackAddapter(ArrayList<Track> trackList){
        mTracks = trackList;
    }
    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View trackItemView = inflater.inflate(R.layout.track_item, parent,false);
        TrackViewHolder viewHolder = new TrackViewHolder(trackItemView);

        return viewHolder;
    }
    public int getItemCount() {
        return mTracks.size();
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {
        Track track = mTracks.get(position);
        TextView trackTitle = holder.trackTitle;
        trackTitle.setText(track.getTitle());
        TextView trackDuraton = holder.trackDuration;
        trackDuraton.setText(track.getDuration());
    }
    public static class TrackViewHolder extends ViewHolder{
        public TextView trackTitle, trackDuration;
        private Context context;
        public TrackViewHolder(View v) {
            super(v);
            this.trackTitle = v.findViewById(R.id.track_title);
            this.trackDuration = v.findViewById(R.id.track_duration);
        }
    }

}

