package com.robotandpencils.robotandpencilapp.helpers;

import android.view.View;
import android.widget.EditText;

import com.robotandpencils.robotandpencilapp.model.Annotation;
import com.robotandpencils.robotandpencilapp.views.RPView;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ronaldbjork on 4/8/17.
 */

public class HelperAnnotation implements HelpersInterface {

    @Override
    public JSONObject serialize(Object annotation) {
        Annotation ann = (Annotation)annotation;
        // write methods to get properties and contruct JSON String
        return null;
    }

    @Override
    public Annotation deserialize(String data) {
        return null;
    }

    @Override
    public String getType() {
        return "ANNOTATION";
    }

    @Override
    public void renderToView(View view, ArrayList<Object> items) {
        AnnotationFeatureView anview = (AnnotationFeatureView)view;
        for(Object itm : items){
            anview.renderAnnotation((Annotation)itm);
        }
    }

    public ArrayList<Annotation> getItemsFromView(AnnotationFeatureView view){
        return view.getAnnotations();
    }

    public interface AnnotationFeatureView{
        void renderAnnotation(Annotation ann);
        ArrayList<Annotation> getAnnotations();
    }
}
