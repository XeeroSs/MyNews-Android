package com.app.xeross.mynews.View.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.xeross.mynews.Controller.Fragment.MostPopularFragment;
import com.app.xeross.mynews.Controller.Fragment.MovieFragment;
import com.app.xeross.mynews.Controller.Fragment.TechnologyFragment;
import com.app.xeross.mynews.Controller.Fragment.TopStoriesFragment;

public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    // Number page
    @Override
    public int getCount() {
        return (4);
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
            case 3:
                return MovieFragment.newInstance();
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
                return "TECHNO";
            case 3:
                return "Movie";
            default:
                return null;
        }
    }
}

