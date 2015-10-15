package com.intelematics.interview.business.net;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Fernando on 14/10/2015.
 */
@Module
public class BusinessModule {

    private Context context;

    public BusinessModule(Context context) {
        this.context = context;
    }
    @Provides
    @Singleton
    ConnectionManager provideConnectionManager(){
        return new ConnectionManagerImpl(context);
    }

}
