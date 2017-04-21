package com.robotandpencils.robotandpencilapp.services.api;

/**
 * Created by ronaldbjork on 4/21/17.
 */

public interface ProjectRepoService {
    public String retrieve(String account, String password, String projectName);
    public void save(String account, String password, String projectName, String data);
}
