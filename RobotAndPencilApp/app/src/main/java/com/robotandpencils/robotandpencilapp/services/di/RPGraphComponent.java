package com.robotandpencils.robotandpencilapp.services.di;

import com.robotandpencils.robotandpencilapp.RPApplication;
import com.robotandpencils.robotandpencilapp.services.RPService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ronaldbjork on 4/21/17.
 */

@Singleton
@Component(modules = {RPModule.class})
public interface RPGraphComponent {

    void inject(RPService rpService);

    public static final class Initializer {
        private Initializer(){

        }
        public static RPGraphComponent init(RPApplication app){
            return DaggerRPGraphComponent.builder()
                    .rPModule(new RPModule(app))
                    .build();
        }
    }
}
