package com.app.xeross.mynews.Controller.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.app.xeross.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.xeross.mynews.Model.Utils.Constants.NOTIFICATION_ID;
import static com.app.xeross.mynews.Model.Utils.Constants.SP;

public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.nchechbox_arts)
    CheckBox mArts;
    @BindView(R.id.nchechbox_politics)
    CheckBox mPolitics;
    @BindView(R.id.nchechbox_business)
    CheckBox mBusiness;
    @BindView(R.id.nchechbox_sports)
    CheckBox mSports;
    @BindView(R.id.nchechbox_entrepreneurs)
    CheckBox mEntrepreneurs;
    @BindView(R.id.nchechbox_travel)
    CheckBox mTravel;
    @BindView(R.id.switchnotification)
    Switch mSwitch;
    @BindView(R.id.buttontextnotification)
    Button mButton;

    // -------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ButterKnife.bind(NotificationActivity.this);

        // --------------------
        this.confSwitch(mSwitch, this);
        // --------------------
    }

    @Override
    protected void onResume() {
        // --------------------
        this.confonClick();
        this.confToolbar();
        // --------------------
        super.onResume();
    }

    // User's clicks
    public void confonClick() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NotificationActivity.this, "Test", Toast.LENGTH_SHORT).show();
                createNotification();
            }
        });
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

    private void confSwitch(Switch aSwitch, final Context context) {
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                }
                SharedPreferences preferences = getSharedPreferences(SP, 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switchkey", isChecked);
                editor.apply();
            }
        });

        SharedPreferences preferences = getSharedPreferences(SP, 0);
        boolean silent = preferences.getBoolean("switchkey", false);
        aSwitch.setChecked(silent);
    }

    private final void createNotification() {

        final NotificationManager mNotification = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(this, "0")
                .setWhen(System.currentTimeMillis())
                .setTicker("test")
                .setSmallIcon(R.drawable.date_from)
                .setContentTitle("test")
                .setContentText("test");

        mNotification.notify(NOTIFICATION_ID, builder.build());
    }

    private final void stopNotification() {
        final NotificationManager mNotification = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotification.cancel(NOTIFICATION_ID);
    }
}
