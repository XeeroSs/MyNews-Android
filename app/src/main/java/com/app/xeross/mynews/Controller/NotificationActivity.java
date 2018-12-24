package com.app.xeross.mynews.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.app.xeross.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    }

    // Toolbar configuration
    private void confToolbar() {
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void confSwitch(Switch aSwitch, final Context context) {
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                } else {
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

    private void confNotification(Context context) {
    }
}
