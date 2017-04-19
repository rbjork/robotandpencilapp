package com.robotandpencils.robotandpencilapp.helpers;

import android.view.View;
import android.widget.EditText;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robotandpencils.robotandpencilapp.model.Annotation;
import com.robotandpencils.robotandpencilapp.model.Comment;
import com.robotandpencils.robotandpencilapp.views.RPView;

import org.json.JSONObject;

import java.io.IOException;
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
    public Comment deserialize(String data) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Comment comment = mapper.readValue(data, new TypeReference<Comment>(){});
        return comment;
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
