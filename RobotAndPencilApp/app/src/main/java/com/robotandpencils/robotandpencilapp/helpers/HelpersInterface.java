package com.robotandpencils.robotandpencilapp.helpers;

import android.view.View;
import android.widget.EditText;

import com.robotandpencils.robotandpencilapp.views.RPView;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ronaldbjork on 4/8/17.
 */

public interface HelpersInterface {
    JSONObject serialize(Object data);
    Object deserialize(String data);
    String getType();
    void renderToView(View v, ArrayList<Object> itms);
    //ArrayList<Object> getItems(View v);
}
