package com.intelematics.interview;

import android.app.Application;

import com.intelematics.interview.business.BusinessComponent;
import com.intelematics.interview.business.DaggerBusinessComponent;
import com.intelematics.interview.business.net.BusinessModule;
import com.intelematics.interview.business.net.ConnectionManagerImpl;
import com.intelematics.interview.dao.DaggerDaoComponent;
import com.intelematics.interview.dao.DaoComponent;
import com.intelematics.interview.dao.db.DBManager;
import com.intelematics.interview.dao.db.DaoModule;
import com.intelematics.interview.ui.DaggerUIComponent;
import com.intelematics.interview.ui.UIComponent;
import com.intelematics.interview.ui.presenter.PresenterModule;


import timber.log.Timber;

/**
 * Created by Arthur Rocha on 01/10/2015.
 * custom application class
 * it handles the components that inject dependencies
 */

public class ApplicationManager extends Application {

    //components
    private UIComponent uiComponent;
    private BusinessComponent businessComponent;
    private DaoComponent daoComponent;


    @Override
    public void onCreate() {
        super.onCreate();


       //logging only on debug mode
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        //creates the components for the presenters and business
        daoComponent= DaggerDaoComponent.builder().daoModule(new DaoModule(this,new DBManager(this))).build();
        businessComponent= DaggerBusinessComponent.builder().businessModule(new BusinessModule(this)).build();
        uiComponent= DaggerUIComponent.builder().presenterModule(new PresenterModule(this, new ConnectionManagerImpl(this))).build();




    }

    public UIComponent getUiComponent() {
        return uiComponent;
    }

    public BusinessComponent getBusinessComponent() {
        return businessComponent;
    }

    public DaoComponent getDaoComponent() {
        return daoComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
