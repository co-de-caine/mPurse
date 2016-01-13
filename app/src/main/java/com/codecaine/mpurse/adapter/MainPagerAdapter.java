package com.codecaine.mpurse.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.codecaine.mpurse.fragment.AddFragment;
import com.codecaine.mpurse.fragment.RequestFragment;
import com.codecaine.mpurse.fragment.SendFragment;

/**
 * Created by Deepankar on 12-01-2016.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        if (position == 0)
            frag = new SendFragment();
        if (position == 1)
            frag = new RequestFragment();
        if (position == 2)
            frag = new AddFragment();
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0)
            return "Send";
        if (position == 1)
            return "Request";
        if (position == 2)
            return "Add";
        return null;
    }
}