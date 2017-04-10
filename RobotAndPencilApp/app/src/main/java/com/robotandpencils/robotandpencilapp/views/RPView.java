package com.robotandpencils.robotandpencilapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.robotandpencils.robotandpencilapp.helpers.HelperAnnotation;
import com.robotandpencils.robotandpencilapp.helpers.HelperComment;
import com.robotandpencils.robotandpencilapp.model.Annotation;
import com.robotandpencils.robotandpencilapp.model.Comment;

import java.util.ArrayList;

/**
 * Created by ronaldbjork on 4/8/17.
 */

public class RPView extends EditText implements HelperAnnotation.AnnotationFeatureView {


    public RPView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void refreshView(){

    }

    @Override
    public void renderAnnotation(Annotation ann) {

    }

    @Override
    public ArrayList<Annotation> getAnnotations() {
        return null;
    }


}
