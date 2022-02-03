package com.zied.nasri.www_sms.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.zied.nasri.www_sms.R;
import com.zied.nasri.www_sms.Tools.ViewTools;
import com.zied.nasri.www_sms.adapters.LockFragmentsPagerAdapter;
import com.zied.nasri.www_sms.listeners.IWWWFragmentListener;

public class SendSmsFragment extends Fragment {

    public class XCore implements IWWWFragmentListener {
        @Override
        public void onStateChange(int id, boolean state) {

            BadgeDrawable badgeDrawable = xview.tabLayout.getTabAt(id).getOrCreateBadge();
            badgeDrawable.setVisible(state);
            xview.lockUnlockView(this.isLocked());
        }

        public boolean isLocked(){

            return xview.whoFragment.isLocked() || xview.whenFragment.isLocked() || xview.whereFragment.isLocked();
        }
    }

    public class XModel {

    }

    public class XView {

        ImageView lockUnlockImageView;
        TabLayout tabLayout;
        ViewPager viewPager;
        LockFragmentsPagerAdapter lockFragmentsPagerAdapter;
        RelativeLayout lockLayout;
        WhoFragment whoFragment;
        WhenFragment whenFragment;
        WhereFragment whereFragment;

        private void init(android.view.View v){

            lockLayout = v.findViewById(R.id.activity_view_lock_layout);
            lockUnlockImageView = v.findViewById(R.id.activity_view_lock);
            tabLayout = v.findViewById(R.id.tabs);
            viewPager = v.findViewById(R.id.pager);
            tabLayout.setupWithViewPager(viewPager);

            whoFragment = new WhoFragment(xcore);
            whenFragment = new WhenFragment(xcore);
            whereFragment = new WhereFragment(xcore);

            lockFragmentsPagerAdapter = new LockFragmentsPagerAdapter(getActivity().getSupportFragmentManager(),0);
            lockFragmentsPagerAdapter.addFragment(whoFragment, "Who?");
            lockFragmentsPagerAdapter.addFragment(whenFragment, "When?");
            lockFragmentsPagerAdapter.addFragment(whereFragment, "Where?");
            viewPager.setAdapter(lockFragmentsPagerAdapter);

            tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_visibility_24);
            tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_access_time_24);
            tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_where_to_vote_24);

            ViewTools.toggleVisibilityOnClick(lockUnlockImageView, lockLayout);

        }

        private void lockUnlockView(boolean locked){
            this.lockUnlockImageView.setImageResource(locked ? R.drawable.ic_baseline_lock_24 : R.drawable.ic_baseline_lock_open_24);
        }

        private void bind(XModel model){

        }

    }

    public XCore xcore = new XCore();
    public XModel xmodel = new XModel();
    public XView xview = new XView();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_send_sms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        xview.init(view);
    }
}