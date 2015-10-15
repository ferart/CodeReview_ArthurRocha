package com.intelematics.interview.business.net;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.util.ByteArrayBuffer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.JsonReader;

import com.intelematics.interview.ApplicationManager;
import com.intelematics.interview.beans.models.Song;
import com.intelematics.interview.dao.db.SongManager;
import com.intelematics.interview.dao.db.SongManagerImpl;

import javax.inject.Inject;


/**
 *
 */
public class ConnectionManagerImpl implements ConnectionManager{
	@Inject
	SongManager songManager ;

	private HttpURLConnection httpConnection = null;
	private URL url = null;
	private InputStream is = null;
	private JsonReader jsonReader = null;
	
	private Context context;
	
	
	public ConnectionManagerImpl(Context context){
		this.context = context;
		songManager=((ApplicationManager)context.getApplicationContext()).getDaoComponent().injectSongManager();
		
		try {
			url = new URL("https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=25");
			
		} catch (MalformedURLException e) {
		}
	}

	@Override
	public boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		return (networkInfo != null && networkInfo.isConnected());
	}

	@Override
	public JsonReader requestJson(){
		try {
			jsonReader = new JsonReader(new InputStreamReader(request(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
		}
		
		return jsonReader;
	}
	@Override
	public InputStream request(){

	    try {
	        httpConnection = (HttpURLConnection) url.openConnection();

	        int responseCode = httpConnection.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            is = httpConnection.getInputStream();
	            
	        }
	        
	    } catch (Exception ex) {
	    }
	    
	    return is;
	}
	@Override
	public void closeConnection(){
	    try{
	    	if(is != null){
	    		is.close();
	    	}
	    	if(httpConnection != null){
	    		httpConnection.disconnect();
	    	}
		} catch(Exception e){
		}
	}

	@Override
	public ByteArrayBuffer requestImage(URL urlCover){
		HttpURLConnection httpConnection = null;
		ByteArrayBuffer baf = new ByteArrayBuffer(1024);
		BufferedInputStream bis = null;

		if(!isNetworkAvailable()){
			return null;
		}
		
	    try {
	        httpConnection = (HttpURLConnection) urlCover.openConnection();
	        
	        int responseCode = httpConnection.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	        	bis = new BufferedInputStream(httpConnection.getInputStream(), 1024);

				int current = 0;
				while ((current = bis.read()) != -1) {
					baf.append((byte) current);
				}
	            
	        } 
	        
	    } catch (Exception ex) {

	    }
	    return baf;
	}


	@Override
	public void getCover(Song song){
		if(song.getCover() == null){

			byte[] imageByteArray = new byte[0];
			try {
				imageByteArray = requestImage(new URL(song.getCoverURL())).buffer();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			ByteArrayInputStream imageStream = new ByteArrayInputStream(imageByteArray);
			Bitmap cover = BitmapFactory.decodeStream(imageStream);
			song.setCover(cover);


			songManager.saveCover(song, imageByteArray);
		}
	}

	@Override
	public SongManager getSongManager() {
		return songManager;
	}
}
