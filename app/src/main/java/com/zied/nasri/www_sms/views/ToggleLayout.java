package com.zied.nasri.www_sms.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.zied.nasri.www_sms.R;
import com.zied.nasri.www_sms.Tools.ViewTools;
import com.zied.nasri.www_sms.listeners.IVisibilityChangeListener;

public class ToggleLayout extends LinearLayout {

    private SwitchMaterial switchMaterial;
    private FrameLayout frameLayout;
    private Context context;
    private String titleText;
    private View contentView;
    private IVisibilityChangeListener iVisibilityChangeListener;

    public ToggleLayout(Context context) {
        super(context);
    }

    public ToggleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ToggleLayout,0,0);
        this.titleText = a.getString(R.styleable.ToggleLayout_titleText);
        a.recycle();

    }

    public ToggleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ToggleLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if(contentView == null) {
            contentView = getChildAt(0);
            removeAllViews();
            this.initView();
        }
    }

    private void initView(){

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.toggle_layout_layout,this,true);

        switchMaterial = (SwitchMaterial)getChildAt(0);
        switchMaterial.setText(titleText);
        switchMaterial.setChecked(false);

        frameLayout = (FrameLayout)getChildAt(1);
        frameLayout.addView(contentView);
        frameLayout.setVisibility(GONE);

        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ViewTools.toggleVisibility(frameLayout,b);
                if(iVisibilityChangeListener != null)iVisibilityChangeListener.onVisibilityChangeListener(b);
            }
        });
    }

    public void setiVisibilityChangeListener(IVisibilityChangeListener iVisibilityChangeListener) {
        this.iVisibilityChangeListener = iVisibilityChangeListener;
    }
}
