package com.app.xeross.mynews.Controller.Activity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.app.xeross.mynews.Model.Articles.ArticlesTop;
import com.app.xeross.mynews.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.xeross.mynews.Model.Utils.Constants.NOTIFICATION_ID;
import static com.app.xeross.mynews.Model.Utils.Constants.SP;

public class NotificationActivity extends AppCompatActivity {


    public ArrayList<ArticlesTop> mItems = new ArrayList<>();
    @BindView(R.id.switchnotification)
    Switch mSwitch;
    private NotificationManager notifManager;
    private RecyclerView mRecyclerView;
    private List<ArticlesTop.Result> article;
    private CharSequence name = "Notification Title";
    private String description = "Notification Description";

    // -------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ButterKnife.bind(NotificationActivity.this);
    }

    @Override
    protected void onResume() {
        // --------------------
        this.confToolbar();
        this.confSwitch(this);
        // --------------------
        super.onResume();
    }

    // Toolbar configuration
    private void confToolbar() {
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);

        if (this.getSupportActionBar() != null) {
            ActionBar actionBar = this.getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void confSwitch(final Context context) {
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //createNotification(NotificationActivity.this);
                } else {
                    stopNotification(NotificationActivity.this);
                }
                SharedPreferences preferences = getSharedPreferences(SP, 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switchkey", isChecked);
                editor.apply();
            }
        });

        SharedPreferences preferences = getSharedPreferences(SP, 0);
        boolean sPreferences = preferences.getBoolean("switchkey", false);
        mSwitch.setChecked(sPreferences);
    }

    public void stopNotification(Context context) {
        final NotificationManager mNotification = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (mNotification != null) {
            mNotification.cancel(NOTIFICATION_ID);
        }
    }
}
