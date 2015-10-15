package com.intelematics.interview.business.net;

import android.util.JsonReader;

import com.intelematics.interview.beans.models.Song;
import com.intelematics.interview.dao.db.SongManager;

import org.apache.http.util.ByteArrayBuffer;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Fernando on 14/10/2015.
 */
public interface ConnectionManager {

   public boolean isNetworkAvailable();
    public JsonReader requestJson();
    public InputStream request();
    public void closeConnection();
    public ByteArrayBuffer requestImage(URL urlCover);
    void  getCover(Song song);
    public SongManager getSongManager();

}
