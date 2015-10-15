package com.intelematics.interview.business;

import com.intelematics.interview.business.net.BusinessModule;
import com.intelematics.interview.business.net.ConnectionManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Fernando on 14/10/2015.
 */
@Singleton
@Component(modules = {BusinessModule.class})
public interface BusinessComponent {
    ConnectionManager injectConnectionManager();
}
