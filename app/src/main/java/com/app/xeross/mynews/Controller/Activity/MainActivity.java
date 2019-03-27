package com.app.xeross.mynews.Controller.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.app.xeross.mynews.R;
import com.app.xeross.mynews.View.Adapter.PageAdapter;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_MOST = 0;
    private static final int FRAGMENT_TOP = 1;
    private static final int FRAGMENT_TECHNO = 2;
    private static final int FRAGMENT_MOVIE = 3;
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ViewPager pager;


    // -------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ------------------
        this.confUI();
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
        // Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // Manager for the DrawerLayout
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
                this.showFragment(FRAGMENT_MOST);
                break;
            case R.id.activity_main_drawer_top:
                this.showFragment(FRAGMENT_TOP);
                break;
            case R.id.activity_main_drawer_techno:
                this.showFragment(FRAGMENT_TECHNO);
                break;
            case R.id.activity_main_drawer_movie:
                this.showFragment(FRAGMENT_MOVIE);
                break;
            default:
                break;
        }

        // Close the drawerLayout
        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    // -------------------------------------------------------------------------

    // Menu configuration
    private void confUI() {

        //ViewPager
        pager = findViewById(R.id.activity_main_viewpager);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()) {
        });

        //TabLayout
        TabLayout tabs = findViewById(R.id.activity_main_tabs);
        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_FIXED);

        //ToolBar
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);

        // DrawerLayout
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.no, R.string.nc);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // NavigationView
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

    // Search the fragment's id, then the display
    private void showFragment(int fragmentId) {
        switch (fragmentId) {
            case FRAGMENT_MOST:
                pager.setCurrentItem(1);
                break;
            case FRAGMENT_TOP:
                pager.setCurrentItem(0);
                break;
            case FRAGMENT_TECHNO:
                pager.setCurrentItem(2);
                break;
            case FRAGMENT_MOVIE:
                pager.setCurrentItem(3);
                break;
            default:
                break;
        }
    }

}
