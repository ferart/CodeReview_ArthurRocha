package com.intelematics.interview.ui.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;

import com.intelematics.interview.SongListActivity;
import com.intelematics.interview.beans.models.Song;
import com.intelematics.interview.business.net.ConnectionManager;
import com.intelematics.interview.business.tasks.DownloadSongListTask;
import com.intelematics.interview.business.tasks.ReadDBSongListTask;
import com.intelematics.interview.ui.adapters.SongListArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.inject.Inject;

/**
 * Created by Fernando on 14/10/2015.
 */
public class SongListActivityPresenterImpl implements SongListActivityPresenter {

    private final ConnectionManager connectionManager;
    private final Context context;

     @Inject
     public SongListActivityPresenterImpl(Context context,ConnectionManager connectionManager){
         this.context=context;
         this.connectionManager=connectionManager;

     }


    @Override
    public void retrieveSongListFromDB(SongListActivity activity) {
        ReadDBSongListTask fetchTask = new ReadDBSongListTask(activity);
        fetchTask.execute();

    }

    @Override
    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    @Override
    public void retrieveSongList(SongListActivity activity) {
        DownloadSongListTask fetchTask = new DownloadSongListTask(activity);
        fetchTask.execute();
    }

    @Override
    public void deleteSong(long viewID, ArrayList<Song> songList, SongListArrayAdapter listAdapter) {
        songList.remove((int) viewID);
        listAdapter.updateList(songList);
    }

    @Override
    public void sortListByArtist(ArrayList<Song> songList, SongListArrayAdapter listAdapter, Editable text) {
        Collections.sort(songList, new Comparator<Song>() {
            public int compare(Song song0, Song song1) {
                return song0.getArtist().compareTo(song1.getArtist());
            }
        });
        listAdapter.updateList(songList, text);
    }

    @Override
    public void sortListByTitle(ArrayList<Song> songList, SongListArrayAdapter listAdapter, Editable text) {
        Collections.sort(songList, new Comparator<Song>() {
            public int compare(Song song0, Song song1) {
                return song0.getTitle().compareTo(song1.getTitle());
            }
        });
        listAdapter.updateList(songList, text);
    }

    @Override
    public void sortListByPrice(ArrayList<Song> songList, SongListArrayAdapter listAdapter, Editable text) {

        Collections.sort(songList, new Comparator<Song>() {
            public int compare(Song song0, Song song1) {
                int priceCompare = ((Double)song0.getPrice()).compareTo((Double)song1.getPrice());
                if(priceCompare != 0){
                    return priceCompare;
                } else{
                    return song0.getTitle().compareTo(song1.getTitle());
                }
            }
        });
        listAdapter.updateList(songList, text);
    }

    @Override
    public void reverserCurrentSort(ArrayList<Song> songList, SongListArrayAdapter listAdapter, Editable text) {
        Collections.reverse(songList);
        listAdapter.updateList(songList,text);
    }

    @Override
    public void showDialog(AlertDialog dialog,AlertDialog.Builder builder) {
        dialog = builder.create();
        dialog.show();
    }
}
