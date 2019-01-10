package com.app.xeross.mynews.Controller.Activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.app.xeross.mynews.Model.Articles.Articles;
import com.app.xeross.mynews.Model.Utils.ApiCalls;
import com.app.xeross.mynews.R;
import com.app.xeross.mynews.View.Adapter.RecyclerViewAdapterMost;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.xeross.mynews.Model.Utils.Constants.NOTIFICATION_ID;
import static com.app.xeross.mynews.Model.Utils.Constants.SP;

public class NotificationActivity extends AppCompatActivity {


    public ArrayList<Articles> mItems = new ArrayList<>();
    @BindView(R.id.switchnotification)
    Switch mSwitch;
    private NotificationManager notifManager;
    private RecyclerViewAdapterMost mRecyclerViewAdapterMost;
    private RecyclerView mRecyclerView;
    private List<Articles.Result> article;
    private CharSequence name = "Notification Title";
    private String description = "Notification Description";

    // -------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ButterKnife.bind(NotificationActivity.this);
        mRecyclerViewAdapterMost = new RecyclerViewAdapterMost();
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
                    createNotification(NotificationActivity.this);
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

    public void createNotification(Context context) {
        ApiCalls.request(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            Intent i = new Intent(context, MainActivity.class);
            CharSequence name = "Notification Title";
            String description = "Notification Description";
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);
            NotificationChannel channel = new NotificationChannel("12", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "12")
                    .setContentTitle("Section: ")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(android.R.drawable.alert_dark_frame)
                    .setAutoCancel(true)
                    .setContentText("123")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            Notification notification = mBuilder.build();
            notificationManager.notify(NOTIFICATION_ID, notification);
        }
    }

    public void stopNotification(Context context) {
        final NotificationManager mNotification = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (mNotification != null) {
            mNotification.cancel(NOTIFICATION_ID);
        }
    }
}
