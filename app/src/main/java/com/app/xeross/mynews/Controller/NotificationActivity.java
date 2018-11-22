package com.app.xeross.mynews.Controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.xeross.mynews.Model.Utils.ApiCalls;
import com.app.xeross.mynews.Model.MostPopular.ApiModelMostPopular;
import com.app.xeross.mynews.R;

import java.util.List;

public class NotificationActivity extends AppCompatActivity implements ApiCalls.Callbacks {

    private Button mButton;
    private TextView mTextView;

    // -------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        // --------------------
        this.confId();
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

    // -------------------------------------------------------------------------

    // User's clicks
    public void confonClick() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execute();
            }
        });
    }

    // Referencing graphic elements
    public void confId() {
        mButton = findViewById(R.id.testbutton);
        mTextView = findViewById(R.id.testTextView);
    }

    // Toolbar configuration
    private void confToolbar() {
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    // -------------------------------------------------------------------------

    private void execute() {
        this.startUpdate();
        ApiCalls.request(this);
    }

    @Override
    public void onResponse(@Nullable List<ApiModelMostPopular> mostPopulars) {

        if (mostPopulars != null) {
            update(mostPopulars);
        }
    }

    @Override
    public void onFailure() {
        this.stopUpdate("Error");
    }

    private void update(List<ApiModelMostPopular> aid) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ApiModelMostPopular ai : aid) {
            stringBuilder.append("-" + ai.getResults() + "\n");
        }
        stopUpdate(stringBuilder.toString());
    }

    private void startUpdate() {
        this.mTextView.setText("Chargement...");
    }

    private void stopUpdate(String response) {
        this.mTextView.setText(response);
    }
}

// new NetworkAsyncTask(this).execute("https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=da4e42347e744f2cb790ff847b0aa6ec");