package com.intelematics.interview.dao.db;

import com.intelematics.interview.beans.models.Song;

import java.util.ArrayList;

/**
 * Created by Fernando on 14/10/2015.
 */
public interface SongManager {

    public  ArrayList<Song> getSongsList();
    public  void deleteSongsList();
    public void saveSongsList(ArrayList<Song> list);
    public  void saveSong(Song song);
    public  void saveCover(Song song, byte[] imageByteArray);
}
