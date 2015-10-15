package com.intelematics.interview.ui.adapters;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import com.intelematics.interview.R;
import com.intelematics.interview.SongListActivity;
import com.intelematics.interview.business.net.ConnectionManager;
import com.intelematics.interview.business.net.ConnectionManagerImpl;
import com.intelematics.interview.dao.db.DBManager;
import com.intelematics.interview.dao.db.SongManagerImpl;
import com.intelematics.interview.beans.models.Song;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */
public class SongListArrayAdapter extends ArrayAdapter<Song> implements Filterable{
	private ConnectionManager connectionManager;
	private final SongListActivity activity;
	private ArrayList<Song> filteredSongsList;
	private ArrayList<Song> songsList;

	public SongListArrayAdapter(SongListActivity activity, ArrayList<Song> songs, ConnectionManager connectionManager) {
		super(activity, R.layout.song_list_row,R.id.song_title, songs);
		this.activity = activity;
		this.songsList = songs;
		this.filteredSongsList = songs;
		this.connectionManager=connectionManager;

	}

	public void updateList(ArrayList<Song> songs) {
		this.songsList = songs;
		this.filteredSongsList.clear();
		this.filteredSongsList.addAll(songs);
		this.notifyDataSetChanged();
	}
	
	public void updateList(ArrayList<Song> songs, Editable sequence) {
		this.songsList = songs;
		this.filteredSongsList.clear();
		this.filteredSongsList.addAll(songs);
		this.getFilter().filter(sequence);
	}
	

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		try {
			View v = super.getView(position, convertView, parent);

			ViewHolder holder;
			if (v == null || !(v.getTag() instanceof ViewHolder)) {

				LayoutInflater inflater = activity.getLayoutInflater();
				v = inflater.inflate(R.layout.song_list_row, parent, false);
				holder = new ViewHolder(v);
				v.setTag(holder);
			} else {
				holder = (ViewHolder) v.getTag();
			}


			final Song song = filteredSongsList.get(position);

			holder.songName.setText(song.getTitle());
			holder.songArtist.setText(song.getArtist());
			holder.songPrice.setText("$" + String.valueOf(song.getPrice()));

			if (song.getCover() != null) {
				holder.albumCover.setImageBitmap(song.getCover());
			} else {
				//holder.albumCover.setImageResource(R.drawable.img_cover);
				//holder.progressBar.setVisibility(View.VISIBLE);
				//connectionManager.getCover(song);
			}
			return v;
		}catch (IndexOutOfBoundsException e){
			Log.d("error", e.getMessage());
		}
		return null;

	}

	@Override
	public int getCount() {
		return this.songsList.size();
	};

	@Override
	public Song getItem(final int position) {
		return this.songsList.get(position);
	}
	
	@Override
	public Filter getFilter() {

    Filter filter = new Filter() {

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
    filteredSongsList = (ArrayList<Song>) results.values;
    notifyDataSetChanged();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
    FilterResults results = new FilterResults();
    ArrayList<Song> filteredSongs = new ArrayList<Song>();

    constraint = constraint.toString().toLowerCase();
    for (int i = 0; i < songsList.size(); i++) {
    Song song = songsList.get(i);
    if (song.getArtist().toLowerCase().contains(constraint.toString()) ||
            song.getTitle().toLowerCase().contains(constraint.toString()))  {
    filteredSongs.add(song);
    }
    }

    results.count = filteredSongs.size();
    results.values = filteredSongs;

    return results;
    }
    };

    return filter;
	}



	//butterknife is used to simplify the view holder pattern inside of an adapter.
	static class ViewHolder {
		@Bind(R.id.song_title)
		TextView songName;
		@Bind(R.id.song_artist)
		TextView songArtist;
		@Bind(R.id.song_price)
		TextView songPrice;
		@Bind(R.id.progress_bar)
		ProgressBar progressBar;
		@Bind(R.id.album_cover)
		ImageView albumCover;

		public ViewHolder(View view) {
			ButterKnife.bind(this, view);
		}
	}

	
}
