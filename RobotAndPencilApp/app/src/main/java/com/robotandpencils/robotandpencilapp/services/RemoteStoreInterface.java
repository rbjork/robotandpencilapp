package com.robotandpencils.robotandpencilapp.services;

/**
 * Created by ronaldbjork on 4/8/17.
 */

public interface RemoteStoreInterface {
    void save(String accountname, String password, String projectname, String data);
    String retrieve(String accountname, String password, String projectname);
}
