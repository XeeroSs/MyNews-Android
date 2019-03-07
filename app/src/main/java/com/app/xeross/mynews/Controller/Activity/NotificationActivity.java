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
    private Calendar calendar;
    private PendingIntent pi;
    private ArrayList<String> sectionMap = new ArrayList<>();
    private SharedPreferences preferences;

    // -------------------------------------------------------------------------

    // Cette méthode sers a charger le HashMap avec ses valeurs sauvegarder au paravrent
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

        // Relier à la méthode "onCheckedChanged" et permet de gérer les checkBox en temps réel
        technology.setOnCheckedChangeListener(this);
        movie.setOnCheckedChangeListener(this);

        // Verifie si la chckbox est coché ou non
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


    // Méthode relier au bouton retour en arrière
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

        // Permet la switchs en temps réel
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // Si l'utilisateur active la switch
                if (isChecked) {

                    // Nous vérifions si au moins une checkbox est séléctionner sinon nous désactivons la switchs
                    if (!technology.isChecked() && !movie.isChecked()) {
                        Toast.makeText(NotificationActivity.this, "Aucune case sélectionnée", Toast.LENGTH_SHORT).show();
                        mSwitch.setChecked(false);

                        // Sinon nous désactivons les élément de l'écran et nous démarons la classe "confAlarmManger"
                    } else {
                        technology.setEnabled(false);
                        movie.setEnabled(false);
                        editText.setEnabled(false);
                        editTextSave(editText.getText().toString());
                        confAlarmManager();
                    }

                    // Si la switch n'est pas activer, nous activons les élément à l'écran et démarons la classe "stopNotification"
                } else {
                    stopNotification();
                    technology.setEnabled(true);
                    movie.setEnabled(true);
                    editText.setEnabled(true);
                }

                // Nous sauvegardons l'état de notre switch en boolean
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switchkey", isChecked);
                editor.apply();
            }
        });

        // Quand l'activité et démarrer nous chargons l'état de notre switch grâce a un sharedpreferense
        SharedPreferences preferences = getSharedPreferences(SP, 0);
        boolean sPreferences = preferences.getBoolean("switchkey", false);
        mSwitch.setChecked(sPreferences);
    }


    // Nous annulons l'alarm manager
    private void stopNotification() {
        if (alarmMgr != null) {
            alarmMgr.cancel(pi);
        }
    }


    // Configuration de L'alarmManger
    private void confAlarmManager() {

        // Gets the instances of the AlarmManager class
        alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        //  ici on choisis qu'elle classe va etre notre Receiver
        i = new Intent(this, AlarmReceiver.class);
        pi = PendingIntent.getBroadcast(this, 0, i, 0);
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.currentThreadTimeMillis(), 5 * 60 * 1000, pi);
    }

    // Nous sauvegardons un HashMap quyi va contenir les information pour les notification
    private void saveSection(ArrayList<String> map) {

        map.clear();

        // Si l'edit text n'est pas vide, nous l'ajoutons dans l'hashMap
        if (editText.getText().toString().length() == 0) {
            map.add(editText.getText().toString());
        } else {
            map.add("");
        }

        if (technology.isChecked()) {
            map.add("technology");
        }

        if (movie.isChecked()) {
            map.add("movie");
        }

        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(map);
        editor.putString(SectionNot, json);
        editor.apply();

    }

    // Gestionnaire de la checkbox
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        // Nous vérfions l'id du checbox et nous sauvegardonc grâce à la méthode saveBox l'état de la checkbox
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

    // Nous chargons un boolean grâce au string dans les parzmètre de la méthode
    private boolean loadBox(String key) {
        boolean bol = preferences.getBoolean(key, false);
        return bol;
    }

    private void editTextSave(String hint) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("hint", hint);
        editor.apply();
    }

    private String editTextLoad() {
        String str = preferences.getString("hint", "");
        return str;
    }
}

