package com.robotandpencils.robotandpencilapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.robotandpencils.robotandpencilapp.helpers.HelperAnnotation;
import com.robotandpencils.robotandpencilapp.helpers.HelperComment;
import com.robotandpencils.robotandpencilapp.model.Annotation;
import com.robotandpencils.robotandpencilapp.model.Comment;
import com.robotandpencils.robotandpencilapp.model.Model;
import com.robotandpencils.robotandpencilapp.services.RPService;
import com.robotandpencils.robotandpencilapp.views.RPCommentsList;
import com.robotandpencils.robotandpencilapp.views.RPView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


// View Controller for RPView
public class RPViewActivity extends AppCompatActivity {

    public static final String REFRESH_VIEW = "refreshview";

    private RPView rpView;
    private RPCommentsList rpCommentsList;
    private HelperComment helperComment = new HelperComment();
    private HelperAnnotation helperAnnotation = new HelperAnnotation();
    private String projectName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpview);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(Model.MODEL_CHANGE));
        rpView = (RPView)findViewById(R.id.rpview);
        rpCommentsList = (RPCommentsList)findViewById(R.id.rpcommentlist);
    }

    public void saveClick(View v) throws JSONException {

        ArrayList<Comment> comments = helperComment.getItemsFromView(rpCommentsList);
        ArrayList<Annotation> annotations = helperAnnotation.getItemsFromView(rpView);

        Model.getInstance().save(comments,annotations);

        if(projectName == null){
            promptForProjectName();
            return;
        }

        Bundle databundle = new Bundle();
        databundle.putString(RPService.PROJECTNAME, "someproject");

        Intent intent = new Intent(this, RPService.class);
        intent.putExtra(RPService.DATA,databundle);
        intent.setAction(RPService.SAVE_ACTION);
        startService(intent);
    }

    public void load(String projectName){
        Bundle databundle = new Bundle();
        databundle.putString(RPService.PROJECTNAME, projectName );
        Intent intent = new Intent(this, RPService.class);
        intent.putExtra(RPService.DATA,databundle);
        intent.setAction(RPService.LOAD_ACTION);
        startService(intent);
    }

    public void loadClick(View v){
        if(projectName == null){
            promptForProjectName();
            return;
        }
        load(projectName);
    }


    private void promptForProjectName(){

    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String projectname = intent.getStringExtra(RPService.PROJECTNAME);
            if(projectname != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        projectName = projectname;
                        rpView.refreshView();
                    }
                });
            }
        }
    };


}
