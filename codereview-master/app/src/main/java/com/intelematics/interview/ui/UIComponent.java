package com.intelematics.interview.ui;

import javax.inject.Singleton;

import com.intelematics.interview.SongListActivity;
import com.intelematics.interview.ui.presenter.PresenterModule;
import dagger.Component;

/**
 * Created by Fernando on 14/10/2015.
 */
@Singleton
@Component(modules = {PresenterModule.class})
public interface UIComponent {


    void inject(SongListActivity songListActivity);

}
