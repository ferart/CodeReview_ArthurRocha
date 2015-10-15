package com.intelematics.interview.ui.presenter;

import android.app.AlertDialog;
import android.text.Editable;

import com.intelematics.interview.SongListActivity;
import com.intelematics.interview.beans.models.Song;
import com.intelematics.interview.business.net.ConnectionManager;
import com.intelematics.interview.ui.adapters.SongListArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Fernando on 14/10/2015.
 */
public interface SongListActivityPresenter {
    public void retrieveSongListFromDB(SongListActivity activity);
    public ConnectionManager getConnectionManager();
    public void retrieveSongList(SongListActivity activity);
    public void deleteSong(long viewID, ArrayList<Song> songList,SongListArrayAdapter listAdapter);
    public void sortListByArtist(ArrayList<Song> songList,SongListArrayAdapter listAdapter, Editable text);
    public void sortListByTitle(ArrayList<Song> songList,SongListArrayAdapter listAdapter, Editable text);
    public void sortListByPrice(ArrayList<Song> songList,SongListArrayAdapter listAdapter, Editable text);
    public void reverserCurrentSort(ArrayList<Song> songList,SongListArrayAdapter listAdapter, Editable text);
    public void showDialog(AlertDialog dialog,AlertDialog.Builder builder);
}
