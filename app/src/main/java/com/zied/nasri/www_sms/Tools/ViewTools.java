package com.zied.nasri.www_sms.Tools;

import android.animation.Animator;
import android.view.View;
import android.view.animation.Animation;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ViewTools {

    public static void toggleVisibility(View view, boolean b){

        if(!b){
            view.setVisibility(GONE);
            return;
        }
        view.setVisibility(VISIBLE);
    }

    public static void toggleVisibilityOnClick(View clickView, View targetView){

        clickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int visibility = targetView.getVisibility() == VISIBLE ? GONE : VISIBLE;
                targetView.setVisibility(visibility);
        }});
    }
}
