package com.robotandpencils.robotandpencilapp;

import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.robotandpencils.robotandpencilapp.helpers.HelperAnnotation;
import com.robotandpencils.robotandpencilapp.helpers.HelperComment;
import com.robotandpencils.robotandpencilapp.model.Annotation;
import com.robotandpencils.robotandpencilapp.model.Comment;
import com.robotandpencils.robotandpencilapp.model.Model;
import com.robotandpencils.robotandpencilapp.services.RPService;
import com.robotandpencils.robotandpencilapp.views.ProjectNameDialog;
import com.robotandpencils.robotandpencilapp.views.RPCommentsList;
import com.robotandpencils.robotandpencilapp.views.RPView;

import org.json.JSONException;

import java.util.ArrayList;



// View Controller for RPView
public class RPViewActivity extends AppCompatActivity implements ProjectNameDialog.NoticeDialogListener {
    public static final String TAG = RPViewActivity.class.getName();

    public static final String REFRESH_VIEW = "refreshview";

    private RPView rpView;
    private RPCommentsList rpCommentsList;
    private HelperComment helperComment = new HelperComment();
    private HelperAnnotation helperAnnotation = new HelperAnnotation();
    private String projectName = null;
    private Tracker mTracker;
    Toolbar rpToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpview);

        rpToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        setSupportActionBar(rpToolbar);

        RPApplication application = (RPApplication) getApplication();
        mTracker = application.getDefaultTracker();

        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(Model.MODEL_CHANGE));
        rpView = (RPView)findViewById(R.id.rpview);
        rpCommentsList = (RPCommentsList)findViewById(R.id.rpcommentlist);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        rpToolbar.setTitle(R.string.app_name);
        rpToolbar.setSubtitle("Challenge");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionsbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_save:
                break;
            case R.id.action_load:
                if(projectName == null){
                    promptForProjectName();
                }else {
                    load(projectName);
                }
                break;
            default:
                return false;
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTracker.setScreenName("onResume~" + TAG);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    public void saveClick(View v) throws JSONException {
        save();
    }

    private void save(){

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


    @Override
    public void onDialogProjectNameSave(String projectName) {

        this.projectName = projectName;
    }

    private void promptForProjectName(){
        ProjectNameDialog dialog = new ProjectNameDialog();
        dialog.show(getFragmentManager(),"Project Name");
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
