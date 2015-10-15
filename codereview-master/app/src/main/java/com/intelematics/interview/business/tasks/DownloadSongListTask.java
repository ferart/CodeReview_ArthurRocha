package com.intelematics.interview.business.tasks;

import java.util.ArrayList;

import com.intelematics.interview.ApplicationManager;
import com.intelematics.interview.SongListActivity;
import com.intelematics.interview.beans.models.Song;
import com.intelematics.interview.business.net.ConnectionManager;
import com.intelematics.interview.business.net.ConnectionManagerImpl;
import com.intelematics.interview.business.util.JsonParser;
import com.intelematics.interview.dao.db.SongManager;

import android.os.AsyncTask;

import javax.inject.Inject;

/**
 *
 */
public class 	DownloadSongListTask extends AsyncTask<Void, Void, Void> {

	private SongListActivity activity;
	private ArrayList<Song> songList;

	@Inject
	ConnectionManager connectionManager;

	
	public DownloadSongListTask(SongListActivity activity) {
		this.activity = activity;
		songList = new ArrayList<Song>();


	}
	
	@Override
	protected Void doInBackground(Void... params) {
		JsonParser parser = new JsonParser();

        // Rock version of the app
        connectionManager = new ConnectionManagerImpl(activity);

        // Pop version of the app
        //connectionManager = new ConnectionManagerImpl(activity, https://itunes.apple.com/search?term=popk&amp;media=music&amp;entity=song&amp;limit=50);

        // Classic version of the app
        //connectionManager = new ConnectionManagerImpl(activity, https://itunes.apple.com/search?term=classick&amp;media=music&amp;entity=song&amp;limit=50);


		songList = parser.parseSongList(connectionManager.requestJson());
		connectionManager.getSongManager().saveSongsList(songList);
		for (Song song:songList) {
			connectionManager.getCover(song);
		}
		connectionManager.closeConnection();


		
		return null;
	}

    protected void onPostExecute(Void result) {
    	activity.updateSongList(songList);
    }

	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
	}
}
