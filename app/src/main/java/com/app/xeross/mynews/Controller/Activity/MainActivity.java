package com.app.xeross.mynews.Controller.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.app.xeross.mynews.Controller.Fragment.MostPopularFragment;
import com.app.xeross.mynews.Controller.Fragment.MovieFragment;
import com.app.xeross.mynews.Controller.Fragment.TechnologyFragment;
import com.app.xeross.mynews.Controller.Fragment.TopStoriesFragment;
import com.app.xeross.mynews.R;
import com.app.xeross.mynews.View.Adapter.PageAdapter;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Identify each fragment with a number
    private static final int FRAGMENT_NEWS = 0;
    private static final int FRAGMENT_PROFILE = 1;
    private static final int FRAGMENT_PARAMS = 2;
    private static final int FRAGMENT_DS = 3;
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    // Declare fragment handled by Navigation Drawer
    private Fragment fragmentNews;
    private Fragment fragmentProfile;
    private Fragment fragmentParams;
    private Fragment fragmentsd;


    // -------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ------------------
        this.confMenu();
        // ------------------
    }

    // -------------------------------------------------------------------------

    // Toolbar creation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }

    // Clicks management of the toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_activity_main_params:
                this.confNotification();
                return true;
            case R.id.menu_activity_main_search:
                this.confSearch();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle Navigation Item Click
        int id = item.getItemId();

        switch (id) {
            case R.id.activity_main_drawer_not:
                this.confNotification();
                break;
            case R.id.activity_main_drawer_search:
                this.confSearch();
                break;
            case R.id.activity_main_drawer_most:
                this.showFragment(FRAGMENT_NEWS);
                break;
            case R.id.activity_main_drawer_top:
                this.showFragment(FRAGMENT_PROFILE);
                break;
            case R.id.activity_main_drawer_techno:
                this.showFragment(FRAGMENT_PARAMS);
                break;
            case R.id.activity_main_drawer_movie:
                this.showFragment(FRAGMENT_DS);
                break;
            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    // -------------------------------------------------------------------------

    // Menu configuration
    private void confMenu() {

        //ViewPager
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()) {
        });

        //TabLayout
        TabLayout tabs = findViewById(R.id.activity_main_tabs);
        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_FIXED);

        //ToolBar
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);

        // Configure Drawer Layout
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.no, R.string.nc);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Configure NavigationView
        this.navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    // Activity change - NotificationActivity
    private void confNotification() {
        Intent i = new Intent(MainActivity.this, NotificationActivity.class);
        this.startActivity(i);
    }

    // Activity change - SearchActivity
    private void confSearch() {
        Intent i = new Intent(MainActivity.this, SearchActivity.class);
        this.startActivity(i);
    }

    private void showFragment(int fragmentIdentifier) {
        switch (fragmentIdentifier) {
            case FRAGMENT_NEWS:
                this.showMostFragment();
                break;
            case FRAGMENT_PROFILE:
                this.showTopFragment();
                break;
            case FRAGMENT_PARAMS:
                this.showTechnoFragment();
                break;
            case FRAGMENT_DS:
                this.showMovieFragment();
                break;
            default:
                break;
        }
    }

    // Create each fragment page and show it
    private void showTopFragment() {
        if (this.fragmentNews == null) this.fragmentNews = TopStoriesFragment.newInstance();
        this.startFragment(this.fragmentNews);
    }

    private void showMostFragment() {
        if (this.fragmentParams == null) this.fragmentParams = MostPopularFragment.newInstance();
        this.startFragment(this.fragmentParams);
    }

    private void showTechnoFragment() {
        if (this.fragmentProfile == null) this.fragmentProfile = TechnologyFragment.newInstance();
        this.startFragment(this.fragmentProfile);
    }

    private void showMovieFragment() {
        if (this.fragmentsd == null) this.fragmentsd = MovieFragment.newInstance();
        this.startFragment(this.fragmentsd);
    }

    // Generic method that will replace and show a fragment inside the MainActivity Frame Layout
    private void startFragment(Fragment fragment) {
        if (!fragment.isVisible()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_frame_layout, fragment).commit();
        }
    }

}
