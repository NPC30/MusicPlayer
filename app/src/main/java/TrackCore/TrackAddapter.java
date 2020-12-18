package TrackCore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.Activities.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class TrackAddapter extends RecyclerView.Adapter<TrackAddapter.TrackViewHolder> {
    private ArrayList<Track> mTracks;
    private int selectedPos = RecyclerView.NO_POSITION;
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
        trackTitle.setText(track.getArtist() +" – "+track.getTitle());
        TextView trackDuraton = holder.trackDuration;
        trackDuraton.setText(track.getDuration());
        holder.itemView.setSelected(selectedPos == position);
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
    public void SortData(int orderBy){
        Track current = TrackList.getCurrent();
        ArrayList<Track> newList = TrackList.getTrackList();
        if (orderBy == 0) {
            Collections.sort(mTracks, Track.TrackIdComp);
            Collections.sort(newList, Track.TrackIdComp);
        }
        if (orderBy == 1) {
            Collections.sort(mTracks, Track.TrackTitleComp);
            Collections.sort(newList, Track.TrackTitleComp);
        }
        if (orderBy == 2){
            Collections.sort(mTracks, Track.TrackArtistComp);
            Collections.sort(newList, Track.TrackArtistComp);
        }
        if (orderBy == 3) {
            Collections.sort(mTracks, Track.TrackDurationComp);
            Collections.sort(newList, Track.TrackDurationComp);
        }
        TrackList.setTrackList(newList);
        TrackList.setCurrentItemIndex(TrackList.FindPosition(current));
        notifyDataSetChanged();
    }

}

