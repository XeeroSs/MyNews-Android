package com.app.xeross.mynews.Controller;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import com.app.xeross.mynews.R;

public class SearchActivity extends AppCompatActivity {

    private EditText mEditTextSearch;
    private DatePicker mDatePicker;
    private CheckBox mArts, mPolitics, mBusiness, mSports, mEntrepreneurs, mTravel;
    private Button mButtonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // -------------------------
        this.confIdentification();
        this.confToolbar();
        this.confOnClickListerner();
        // -------------------------
    }

    private void confToolbar() {
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void confIdentification() {
        mEditTextSearch = findViewById(R.id.edittext_search_params);
        mArts = findViewById(R.id.chechbox_arts);
        mPolitics = findViewById(R.id.chechbox_politics);
        mBusiness = findViewById(R.id.chechbox_business);
        mSports = findViewById(R.id.chechbox_sports);
        mEntrepreneurs = findViewById(R.id.chechbox_entrepreneurs);
        mTravel = findViewById(R.id.chechbox_travel);
        mButtonSearch = findViewById(R.id.buttun_search_params);
    }

    private void confOnClickListerner() {
        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
