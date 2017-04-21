package com.robotandpencils.robotandpencilapp.views;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.robotandpencils.robotandpencilapp.R;

/**
 * Created by ronaldbjork on 4/18/17.
 */

public class ProjectNameDialog extends DialogFragment {

    public interface NoticeDialogListener {
        public void onDialogProjectNameSave(String projectName);
    }

    NoticeDialogListener mListener;
    EditText projectText;

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        Activity activity = getActivity();
        mListener = (NoticeDialogListener) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mListener = (NoticeDialogListener) getActivity();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_project,null);
        projectText = (EditText)v.findViewById(R.id.projectNameText);
        builder.setView(v)
            .setPositiveButton(R.string.save,new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ProjectNameDialog pd = ProjectNameDialog.this;
                    mListener.onDialogProjectNameSave(projectText.getText().toString());
                }
            })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ProjectNameDialog.this.getDialog().cancel();
                }
            });
        return builder.create();
    }
}
