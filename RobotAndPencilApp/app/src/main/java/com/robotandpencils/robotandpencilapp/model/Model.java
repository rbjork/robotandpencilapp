package com.robotandpencils.robotandpencilapp.model;

import java.util.ArrayList;

/**
 * Created by ronaldbjork on 4/9/17.
 */

public class Model {
    public static final String MODEL_CHANGE = "modelchange";
    public static Model model;
    public static Model getInstance(){
        if(model == null)model = new Model();
        return model;
    }

    public void save(ArrayList<Comment> comments, ArrayList<Annotation> annotations){
        this.comments = comments;
        this.annotations = annotations;
    }

    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<Annotation> annotations = new ArrayList<>();

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<Annotation> getAnnotations() {
        return annotations;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }
    public void addAnnotation(Annotation annotation){
        annotations.add(annotation);
    }

    // .. remove methods


    public void clear(){
        Model.model = new Model();
    }
}
