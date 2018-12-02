package com.app.xeross.mynews.Model.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.xeross.mynews.View.MostPopularFragment;
import com.app.xeross.mynews.View.TechnologyFragment;
import com.app.xeross.mynews.View.TopStoriesFragment;

public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    // Number page
    @Override
    public int getCount() {
        return (3);
    }

    // Gets fragment instance
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TopStoriesFragment.newInstance();
            case 1:
                return MostPopularFragment.newInstance();
            case 2:
                return TechnologyFragment.newInstance();
            default:
                return null;
        }
    }

    // Gets page title
    @Override
    public CharSequence getPageTitle(int pos) {
        switch (pos) {
            case 0:
                return "TOP STORIES";
            case 1:
                return "MOST POPULAR";
            case 2:
                return "TECHNOLOGY";
            default:
                return null;
        }
    }
}

