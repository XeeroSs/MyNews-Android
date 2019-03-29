package com.app.xeross.mynews.Controller.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.app.xeross.mynews.R;
import com.app.xeross.mynews.Utils.AlarmReceiver;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.xeross.mynews.Utils.Constants.SP;
import static com.app.xeross.mynews.Utils.Constants.SectionNot;

public class NotificationActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    @BindView(R.id.switchnotification)
    Switch mSwitch;
    @BindView(R.id.nchechbox_arts)
    CheckBox arts;
    @BindView(R.id.nchechbox_business)
    CheckBox business;
    @BindView(R.id.nchechbox_politics)
    CheckBox politics;
    @BindView(R.id.nchechbox_entrepreneurs)
    CheckBox entrepreneurs;
    @BindView(R.id.nchechbox_travel)
    CheckBox travel;
    @BindView(R.id.nchechbox_sports)
    CheckBox sports;
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

        checkbox();

        editText.setText(editTextLoad());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // --------------------
        this.confToolbar();
        this.confSwitch();
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

    private void userI(boolean bool) {
        arts.setEnabled(bool);
        politics.setEnabled(bool);
        entrepreneurs.setEnabled(bool);
        sports.setEnabled(bool);
        travel.setEnabled(bool);
        business.setEnabled(bool);
        editText.setEnabled(bool);
    }

    // Configuration de la Switch
    private void confSwitch() {

        // Switch Manager
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    // If no box is selected, the switch is disabled
                    if (!arts.isChecked() && !business.isChecked() &&
                            !entrepreneurs.isChecked() && !travel.isChecked() &&
                            !sports.isChecked() && !politics.isChecked()) {
                        Toast.makeText(NotificationActivity.this, "Aucune case sélectionnée", Toast.LENGTH_SHORT).show();
                        mSwitch.setChecked(false);

                        // Else the user can no longer interact with the checkboxes, as well as the editText. Activate the alarmManager
                    } else {
                        userI(false);
                        editTextSave(editText.getText().toString());
                        saveSection(sectionMap);
                        confAlarmManager();
                    }

                    // the AlarmManger is Disabled, and some elements can be interactive again
                } else {
                    stopNotification();
                    userI(true);
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
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        // Gets the instances of the AlarmManager class
        alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        i = new Intent(this, AlarmReceiver.class);
        pi = PendingIntent.getBroadcast(this, 0, i, 0);

        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);
    }

    // Save in a SharedPreferences the hashMap that will contain the information chosen by the user
    private void saveSection(ArrayList<String> map) {

        map.clear();

        // If the edit text is not empty, we add in the hashMap
        map.add(editText.getText().toString());

        // add "Technology" if the chechbox is checked
        boxCheck(arts, map);
        boxCheck(politics, map);
        boxCheck(entrepreneurs, map);
        boxCheck(business, map);
        boxCheck(sports, map);
        boxCheck(travel, map);

        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(map);
        editor.putString(SectionNot, json);
        editor.apply();

    }

    private void boxCheck(CheckBox section, ArrayList<String> map) {
        if (section.isChecked()) {
            map.add(section.getText().toString());
        }
    }

    // CheckBox manager
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        // Save with the saveBox method the state of the check box
        switch (buttonView.getId()) {
            case R.id.nchechbox_arts:
                if (isChecked) {
                    saveBox(true, "artsKey");
                } else {
                    saveBox(false, "artsKey");
                }
                break;
            case R.id.nchechbox_business:
                if (isChecked) {
                    saveBox(true, "businessKey");
                } else {
                    saveBox(false, "businessKey");
                }
                break;
            case R.id.nchechbox_entrepreneurs:
                if (isChecked) {
                    saveBox(true, "entrepreneursKey");
                } else {
                    saveBox(false, "entrepreneursKey");
                }
                break;
            case R.id.nchechbox_travel:
                if (isChecked) {
                    saveBox(true, "travelKey");
                } else {
                    saveBox(false, "travelKey");
                }
                break;
            case R.id.nchechbox_politics:
                if (isChecked) {
                    saveBox(true, "politicsKey");
                } else {
                    saveBox(false, "politicsKey");
                }
                break;
            case R.id.nchechbox_sports:
                if (isChecked) {
                    saveBox(true, "sportsKey");
                } else {
                    saveBox(false, "sportsKey");
                }
                break;
        }
    }

    private void checkbox() {
        politics.setOnCheckedChangeListener(this);
        sports.setOnCheckedChangeListener(this);
        travel.setOnCheckedChangeListener(this);
        entrepreneurs.setOnCheckedChangeListener(this);
        business.setOnCheckedChangeListener(this);
        arts.setOnCheckedChangeListener(this);

        // Check if the CheckBox is checked or not
        politics.setChecked(loadBox("politicsKey"));
        sports.setChecked(loadBox("sportsKey"));
        travel.setChecked(loadBox("travelKey"));
        entrepreneurs.setChecked(loadBox("entrepreneursKey"));
        business.setChecked(loadBox("businessKey"));
        arts.setChecked(loadBox("artsKey"));
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

