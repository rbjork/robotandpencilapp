package com.robotandpencils.robotandpencilapp.services.di;

import android.app.Application;

import com.robotandpencils.robotandpencilapp.RPApplication;
import com.robotandpencils.robotandpencilapp.services.api.ProjectRepoService;
import com.robotandpencils.robotandpencilapp.services.impl.ProjectRepoServiceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ronaldbjork on 4/21/17.
 */

@Module
public class RPModule {
    RPApplication app;
    public RPModule(RPApplication application){
        app = application;
    }
    @Provides
    @Singleton
    protected Application provideApplication() {
        return app;
    }


    @Provides
    @Singleton
    ProjectRepoService projectRepoService(){ return new ProjectRepoServiceManager(); }
}
