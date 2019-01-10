package com.app.xeross.mynews.Controller.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.app.xeross.mynews.View.Adapter.PageAdapter;
import com.app.xeross.mynews.View.Adapter.RecyclerViewAdapterMost;
import com.app.xeross.mynews.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    private RecyclerViewAdapterMost mRecyclerViewAdapterMost;

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

    // -------------------------------------------------------------------------


    @Override
    protected void onPause() {
        super.onPause();
    }

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

}
