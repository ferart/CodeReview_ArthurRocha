package com.intelematics.interview.ui.presenter;

import android.content.Context;

import com.intelematics.interview.business.net.ConnectionManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Fernando on 14/10/2015.
 */
@Module
public class PresenterModule {

    private Context context;
    private ConnectionManager connectionManager;

    public PresenterModule(Context context,ConnectionManager connectionManager) {
        this.context = context;
        this.connectionManager=connectionManager;
    }

    @Provides
    @Singleton
    SongListActivityPresenter provideSongListPresenter(){
        return new SongListActivityPresenterImpl(context,connectionManager);
    }
}
