package com.zied.nasri.www_sms.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zied.nasri.www_sms.R;
import com.zied.nasri.www_sms.listeners.IWWWFragmentListener;
import com.zied.nasri.www_sms.views.ToggleLayout;

public class WhereFragment extends BaseFragment {

    private ToggleLayout toggleLayout;

    @Override
    public int getFragmentId() {
        return 2;
    }

    public WhereFragment(IWWWFragmentListener iwwwFragmentListener) {
        super(iwwwFragmentListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_where, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toggleLayout = view.findViewById(R.id.fragment_where_layout);
        toggleLayout.setiVisibilityChangeListener(this);
    }


}