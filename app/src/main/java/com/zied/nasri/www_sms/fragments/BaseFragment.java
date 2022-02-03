package com.zied.nasri.www_sms.fragments;

import androidx.fragment.app.Fragment;

import com.zied.nasri.www_sms.listeners.IVisibilityChangeListener;
import com.zied.nasri.www_sms.listeners.IWWWFragmentListener;

public abstract class BaseFragment extends Fragment implements IVisibilityChangeListener {

    protected IWWWFragmentListener iwwwFragmentListener;
    protected boolean isLocked;

    public BaseFragment(IWWWFragmentListener iwwwFragmentListener){
        super();
        this.iwwwFragmentListener = iwwwFragmentListener;
    }

    public abstract int getFragmentId();

    public boolean isLocked(){return isLocked;}

    @Override
    public void onVisibilityChangeListener(boolean visibility) {
        this.isLocked = visibility;
        if(this.iwwwFragmentListener != null)iwwwFragmentListener.onStateChange(getFragmentId(),visibility);
    }
}
