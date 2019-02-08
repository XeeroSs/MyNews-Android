package com.app.xeross.mynews.Controller.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.app.xeross.mynews.Model.Articles.ArticlesTop;
import com.app.xeross.mynews.Model.Utils.AlarmReceiver;
import com.app.xeross.mynews.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.xeross.mynews.Model.Utils.Constants.SP;

public class NotificationActivity extends AppCompatActivity {


    @BindView(R.id.switchnotification)
    Switch mSwitch;
    private AlarmManager alarmMgr;
    private Intent i;
    private PendingIntent pi;

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
        this.confSwitch();
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

    private void confSwitch() {
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    confAlarmManager();
                } else {
                    stopNotification();
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

    private void stopNotification() {
        if (alarmMgr != null) {
            alarmMgr.cancel(pi);
        }
    }

    private void confAlarmManager() {
        alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        i = new Intent(this, AlarmReceiver.class);
        pi = PendingIntent.getBroadcast(this, 0, i, 0);
        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(),      1, pi);
    }
}
