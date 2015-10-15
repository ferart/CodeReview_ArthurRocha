package com.intelematics.interview.dao.db;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Fernando on 14/10/2015.
 */
@Module
public class DaoModule {
    private DBManager dbManager;
    private Context context;

    public DaoModule(Context context,DBManager dbManager) {
        this.context=context;
        this.dbManager = dbManager;
    }

    @Provides
    @Singleton
    SongManager provideSongManagerImpl(){
        return new SongManagerImpl(context,dbManager);
    }
}
