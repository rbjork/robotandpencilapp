package com.robotandpencils.robotandpencilapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.robotandpencils.robotandpencilapp.helpers.HelperComment;
import com.robotandpencils.robotandpencilapp.model.Comment;

import java.util.ArrayList;



/**
 * Created by ronaldbjork on 4/9/17.
 */

public class RPCommentsList extends ListView implements HelperComment.CommentFeatureView {


    public RPCommentsList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void renderComment(Comment comment) {

    }

    @Override
    public ArrayList<Comment> getComments() {
        return null;
    }
}
