package com.intelematics.interview.dao;

import com.intelematics.interview.dao.db.DaoModule;
import com.intelematics.interview.dao.db.SongManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Fernando on 14/10/2015.
 */
@Singleton
@Component(modules = {DaoModule.class})
public interface DaoComponent {
   SongManager injectSongManager();
}
