package com.robotandpencils.robotandpencilapp.services.impl;

import android.util.Log;
import android.widget.Toast;

import com.robotandpencils.robotandpencilapp.services.api.ProjectRepoService;

/**
 * Created by ronaldbjork on 4/21/17.
 */

public class ProjectRepoServiceManager implements ProjectRepoService{
    @Override
    public String retrieve(String account, String password,String projectName) {
        return "My Project " + projectName;
    }

    @Override
    public void save(String account, String password,String projectName, String data){
        Log.d("saving ", projectName);
    }
}
