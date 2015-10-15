package com.intelematics.interview.business.tasks;

import java.util.ArrayList;

import com.intelematics.interview.ApplicationManager;
import com.intelematics.interview.SongListActivity;
import com.intelematics.interview.beans.models.Song;
import com.intelematics.interview.dao.db.SongManager;

import android.os.AsyncTask;

import javax.inject.Inject;

/**
 *
 */
public class ReadDBSongListTask extends AsyncTask<Void, Void, Void> {
	@Inject
	SongManager songManager ;

	private SongListActivity activity;
	private ArrayList<Song> songList;
	
	
	public ReadDBSongListTask(SongListActivity activity) {
		songManager= ((ApplicationManager)activity.getApplicationContext()).getDaoComponent().injectSongManager();
		this.activity = activity;

		songList = new ArrayList<Song>();
	}

	
	@Override
	protected Void doInBackground(Void... params) {

		songList = songManager.getSongsList();
		
		return null;
	}

    protected void onPostExecute(Void result) {
    	activity.updateSongList(songList);
    }



}
