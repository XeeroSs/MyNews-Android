package com.app.xeross.mynews.Controller.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import com.app.xeross.mynews.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.xeross.mynews.Model.Utils.Constants.SP;
import static com.app.xeross.mynews.Model.Utils.Constants.cHASHMAP;

public class SearchActivity extends AppCompatActivity {

    private static HashMap<String, String> query = new HashMap<>();
    @BindView(R.id.edittext_search_params)
    EditText mEditTextSearch;
    @BindView(R.id.buttun_search_params)
    Button mButtonSearch;
    @BindView(R.id.date_from)
    EditText mDateFrom;
    @BindView(R.id.date_to)
    EditText mDateTo;
    @BindView(R.id.chechbox_technology)
    CheckBox technology;
    @BindView(R.id.chechbox_movie)
    CheckBox movie;
    private Calendar calendar;
    private int year, month, day;

    // -------------------------------------------------------------------------

    public SearchActivity() {
    }

    public static HashMap<String, String> loadResult(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(cHASHMAP, null);
        Type type = new TypeToken<HashMap<String, String>>() {
        }.getType();
        HashMap<String, String> query = gson.fromJson(json, type);
        if (query == null) {
            query = new HashMap<>();
        }
        return query;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(SearchActivity.this);

        // -------------------------
        this.confToolbar();
        this.confCalendar(mDateFrom, mDateTo);
        mButtonSearch.setEnabled(false);
        // -------------------------
    }

    // -------------------------------------------------------------------------

    @Override
    protected void onResume() {

        // -------------------------
        this.confOnClickListerner();
        this.confEditText();
        // -------------------------

        super.onResume();
    }

    // Toolbar configuration
    private void confToolbar() {
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
        // Backtrack button
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

    // User's clicks
    private void confOnClickListerner() {
        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = mEditTextSearch.getText().toString();
                String[] datefromm = mDateFrom.getText().toString().split("/");
                String[] datetoo = mDateTo.getText().toString().split("/");

                saveResult(datefromm, datetoo, s, query);
                Intent i = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(i);

            }
        });

        mDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confDate(mDateFrom);
            }
        });
        mDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confDate(mDateTo);
            }
        });


    }

    // Creation the DatePickerDialog and alter edittext
    private void confDate(final EditText editText) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int years, int months, int days) {
                        months += 1;
                        String z1 = "";
                        String z2 = "";
                        if (months < 9) z2 = "0";
                        if (days < 9) z1 = "0";
                        editText.setText(z1 + days + "/" + z2 + months + "/" + years);
                    }
                }, year, month, day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        datePickerDialog.show();
    }

    // Configuration the Calendar and retrieve the current time
    private void confCalendar(EditText editTextFrom, EditText editTextTo) {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());
        editTextFrom.setText(date);
        editTextTo.setText(date);
    }

    private void confEditText() {
        if (technology.isChecked()) {
            movie.setChecked(false);
        }
        if (movie.isChecked()) {
            technology.setChecked(false);
        }
        mEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mButtonSearch.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void saveResult(String datefrom[], String dateto[], String s, HashMap<String, String> query) {
        query.put("q", s);
        query.put("begin_date", datefrom[2] + datefrom[1] + datefrom[0]);
        query.put("end_date", dateto[2] + dateto[1] + dateto[0]);
        if (technology.isChecked()) { query.put("fq", "Technology"); }
        if (movie.isChecked()) { query.put("fq", "Movie"); }
        if (!movie.isChecked() && !technology.isChecked()) { query.remove("fq"); }

        SharedPreferences sharedPreferences = getSharedPreferences(SP, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(query);
        editor.putString(cHASHMAP, json);
        editor.apply();
    }
}
