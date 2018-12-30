package com.app.xeross.mynews.Controller.Activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.app.xeross.mynews.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.edittext_search_params)
    EditText mEditTextSearch;
    @BindView(R.id.chechbox_arts)
    CheckBox mArts;
    @BindView(R.id.chechbox_politics)
    CheckBox mPolitics;
    @BindView(R.id.chechbox_business)
    CheckBox mBusiness;
    @BindView(R.id.chechbox_sports)
    CheckBox mSports;
    @BindView(R.id.chechbox_entrepreneurs)
    CheckBox mEntrepreneurs;
    @BindView(R.id.chechbox_travel)
    CheckBox mTravel;
    @BindView(R.id.buttun_search_params)
    Button mButtonSearch;
    @BindView(R.id.date_from)
    EditText mDateFrom;
    @BindView(R.id.date_to)
    EditText mDateTo;
    private Calendar calendar;
    private int year, month, day;

    // -------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(SearchActivity.this);

        // -------------------------
        this.confToolbar();
        this.confCalendar(mDateFrom, mDateTo);
        // -------------------------
    }

    @Override
    protected void onResume() {

        // -------------------------
        this.confOnClickListerner();
        // -------------------------

        super.onResume();
    }

    // -------------------------------------------------------------------------

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

                String[] datefromm = mDateFrom.getText().toString().split("/");
                String[] datetoo = mDateTo.getText().toString().split("/");

                Toast.makeText(SearchActivity.this, String.valueOf("From "
                        + datefromm[0] + datefromm[1] + datefromm[2] + " to "
                        + datetoo[0] + datetoo[1] + datetoo[2]), Toast.LENGTH_SHORT).show();

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
                        editText.setText(days + "/" + months + "/" + years);
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

    private void confCheckBox(CheckBox checkBox) {
        checkBox.isChecked();
    }
}