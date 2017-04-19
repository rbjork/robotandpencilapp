package com.robotandpencils.robotandpencilapp.services;

import android.util.Log;

/**
 * Created by ronaldbjork on 4/19/17.
 */

public class DummyStorageService implements RemoteStoreInterface {

    private static final String TAG = DummyStorageService.class.getName();

    @Override
    public void save(String accountname, String password, String projectname, String data) {
        Log.d(TAG,"save to dummy storage");
    }

    @Override
    public String retrieve(String accountname, String password, String projectname) {
        Log.d(TAG,"retrieve to dummy storage");
        return "{project:dummy}";
    }


}
