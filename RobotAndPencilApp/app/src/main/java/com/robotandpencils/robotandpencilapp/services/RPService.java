package com.robotandpencils.robotandpencilapp.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.robotandpencils.robotandpencilapp.RPViewActivity;
import com.robotandpencils.robotandpencilapp.helpers.HelperAnnotation;
import com.robotandpencils.robotandpencilapp.helpers.HelperComment;
import com.robotandpencils.robotandpencilapp.model.Annotation;
import com.robotandpencils.robotandpencilapp.model.Comment;
import com.robotandpencils.robotandpencilapp.model.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ronaldbjork on 4/8/17.
 */

// NOTE THAT A INTENTSERVICE HANDLER RUNS IN A SEPARATE WORKER THREAD (NOT ON UI THREAD)

public class RPService extends IntentService {

    public static final String DATA= "data";
    public static final String ITEMS = "items";

    public static final String PROJECTNAME = "projectname";
    public static final String ACCOUNTNAME = "accountname";
    public static final String PASSWORD = "password";

    public static final String SAVE_ACTION = "saveaction";
    public static final String LOAD_ACTION = "loadaction";

    private static final String TYPE = "type";
    private static final String COMMENT_TYPE = "commenttype";
    private static final String ANNOTATION_TYPE = "annotationtype";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    // Use injection
    public RemoteStoreInterface remoteService;

    public RPService(String name) {

        super(name);
        //remoteService = some_remote_storage_service;
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle data = intent.getExtras();
        String action = intent.getAction();

        SharedPreferences preferences = getSharedPreferences("RPAPP", Context.MODE_PRIVATE);
        String accountName = preferences.getString(ACCOUNTNAME,"RPAPP");  // actually this probably come from user preference
        String password = preferences.getString(PASSWORD,"RPAPP"); // froom user preference local store
        String projectname = data.getString(PROJECTNAME);

        JSONArray dataJSON = new JSONArray();
        HelperComment helperComment = new HelperComment();
        HelperAnnotation helperAnnotation = new HelperAnnotation();

        if(action.equals(SAVE_ACTION)) {

            ArrayList<Comment> comments = Model.getInstance().getComments();
            ArrayList<Annotation> annotations = Model.getInstance().getAnnotations();

            for(Annotation a : annotations){
                JSONObject ann = helperAnnotation.serialize(a);
                try {
                    ann.put(TYPE, ANNOTATION_TYPE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dataJSON.put(ann);
            }
            for(Comment c : comments){
                JSONObject comment = helperComment.serialize(c);
                try {
                    comment.put(TYPE, COMMENT_TYPE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dataJSON.put(comment);
            }
            for (int i = 0; i < dataJSON.length(); i++) {
                try {
                    JSONObject dataitem = (JSONObject) dataJSON.get(i);
                    remoteService.save(accountName, password, projectname, dataitem.toString()); //  Synchronous call
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }else if(action.equals(LOAD_ACTION)){
            String loaddata = remoteService.retrieve(accountName,password,projectname);
            try {
                JSONObject projectJSON = new JSONObject(loaddata);

                dataJSON = projectJSON.getJSONArray(DATA);

                Model model = Model.getInstance();
                model.clear();
                for(int i=0;i<dataJSON.length();i++){
                    JSONObject jo = dataJSON.getJSONObject(i);
                    if(jo.getString(TYPE).equals(ANNOTATION_TYPE)) {
                        model.addAnnotation(helperAnnotation.deserialize(jo.toString()));
                    }else if(jo.getString(TYPE).equals(COMMENT_TYPE)){
                        model.addComment(helperComment.deserialize(jo.toString()));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Intent refreshIntent = new Intent(Model.MODEL_CHANGE);
            LocalBroadcastManager.getInstance(this).sendBroadcast(refreshIntent);
        }
    }
}
