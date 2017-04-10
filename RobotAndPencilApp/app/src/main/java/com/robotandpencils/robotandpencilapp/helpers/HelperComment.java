package com.robotandpencils.robotandpencilapp.helpers;

import android.view.View;
import android.widget.EditText;

import com.robotandpencils.robotandpencilapp.model.Comment;
import com.robotandpencils.robotandpencilapp.views.RPView;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ronaldbjork on 4/8/17.
 */

public class HelperComment implements HelpersInterface {
    @Override
    public JSONObject serialize(Object data) {
        Comment comment = (Comment)data;
        return null;
    }

    @Override
    public Comment deserialize(String data) {
        return null;
    }


    public String getType(){
        return "COMMENT";
    }

    @Override
    public void renderToView(View v, ArrayList<Object> items) {

    }

    public ArrayList<Comment> getItemsFromView(CommentFeatureView view){
        return null;
    }

    public interface CommentFeatureView{
        void renderComment(Comment comment);
        ArrayList<Comment> getComments();
    }
}
