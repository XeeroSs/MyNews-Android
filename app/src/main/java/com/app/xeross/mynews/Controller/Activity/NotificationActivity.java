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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.app.xeross.mynews.Model.Utils.AlarmReceiver;
import com.app.xeross.mynews.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.xeross.mynews.Model.Utils.Constants.SP;
import static com.app.xeross.mynews.Model.Utils.Constants.SectionNot;

public class NotificationActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    @BindView(R.id.switchnotification)
    Switch mSwitch;
    @BindView(R.id.nchechbox_movie)
    CheckBox movie;
    @BindView(R.id.nchechbox_technology)
    CheckBox technology;
    @BindView(R.id.edittext_not_params)
    EditText editText;
    private AlarmManager alarmMgr;
    private Intent i;
    private PendingIntent pi;
    private ArrayList<String> sectionMap = new ArrayList<>();
    private SharedPreferences preferences;

    // -------------------------------------------------------------------------

    // HashMap recovery and values
    public static ArrayList<String> LoadSection(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(SectionNot, "");
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> sectionMap = gson.fromJson(json, type);
        if (sectionMap == null) {
            sectionMap = new ArrayList<>();
        }
        return sectionMap;
    }

    // -------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ButterKnife.bind(NotificationActivity.this);
        preferences = this.getSharedPreferences(SP, Context.MODE_PRIVATE);

        technology.setOnCheckedChangeListener(this);
        movie.setOnCheckedChangeListener(this);

        // Check if the CheckBox is checked or not
        movie.setChecked(loadBox("movie"));
        technology.setChecked(loadBox("technology"));

        editText.setText(editTextLoad());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // --------------------
        this.confToolbar();
        this.confSwitch();
        this.saveSection(sectionMap);
        // --------------------
    }

    // -------------------------------------------------------------------------


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


    // Check if an item is selected in the ToolBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Configuration de la Switch
    private void confSwitch() {

        // Switch Manager
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    // If no box is selected, the switch is disabled
                    if (!technology.isChecked() && !movie.isChecked()) {
                        Toast.makeText(NotificationActivity.this, "Aucune case sélectionnée", Toast.LENGTH_SHORT).show();
                        mSwitch.setChecked(false);

                        // Else the user can no longer interact with the checkboxes, as well as the editText. Activate the alarmManager
                    } else {
                        technology.setEnabled(false);
                        movie.setEnabled(false);
                        editText.setEnabled(false);
                        editTextSave(editText.getText().toString());
                        confAlarmManager();
                    }

                    // the AlarmManger is Disabled, and some elements can be interactive again
                } else {
                    stopNotification();
                    technology.setEnabled(true);
                    movie.setEnabled(true);
                    editText.setEnabled(true);
                }

                // Save Switch state
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switchkey", isChecked);
                editor.apply();
            }
        });

        // Load Switch state
        SharedPreferences preferences = getSharedPreferences(SP, 0);
        boolean sPreferences = preferences.getBoolean("switchkey", false);
        mSwitch.setChecked(sPreferences);
    }


    // Cancel AlarmManger
    private void stopNotification() {
        if (alarmMgr != null) {
            alarmMgr.cancel(pi);
        }
    }


    // Configuration de L'alarmManger
    private void confAlarmManager() {

        // Gets the instances of the AlarmManager class
        alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        i = new Intent(this, AlarmReceiver.class);
        pi = PendingIntent.getBroadcast(this, 0, i, 0);
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.currentThreadTimeMillis(), 1000 * 60 * 2, pi);
    }

    // Save in a SharedPreferences the hashMap that will contain the information chosen by the user
    private void saveSection(ArrayList<String> map) {

        map.clear();

        // If the edit text is not empty, we add in the hashMap
        if (editText.getText().toString().length() == 0) {
            map.add(editText.getText().toString());
        } else {
            map.add("");
        }

        // add "Technology" if the chechbox is checked
        if (technology.isChecked()) {
            map.add("technology");
        }

        // add "Movie" if the chechbox is checked
        if (movie.isChecked()) {
            map.add("movie");
        }

        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(map);
        editor.putString(SectionNot, json);
        editor.apply();

    }

    // CheckBox manager
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        // Save with the saveBox method the state of the check box
        switch (buttonView.getId()) {
            case R.id.nchechbox_movie:
                if (isChecked) {
                    saveBox(true, "movie");
                } else {
                    saveBox(false, "movie");
                }
                break;
            case R.id.nchechbox_technology:
                if (isChecked) {
                    saveBox(true, "technology");
                } else {
                    saveBox(false, "technology");
                }
                break;
        }
    }

    // Nous auvegardons dans un sharedprefense un boolean ainsi qu'une clé
    private void saveBox(boolean isChecked, String key) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, isChecked);
        editor.apply();
    }

    // Load the Box
    private boolean loadBox(String key) {
        boolean bol = preferences.getBoolean(key, false);
        return bol;
    }

    // Save the EditText
    private void editTextSave(String hint) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("hint", hint);
        editor.apply();
    }

    // Load the EditText
    private String editTextLoad() {
        String str = preferences.getString("hint", "");
        return str;
    }
}

