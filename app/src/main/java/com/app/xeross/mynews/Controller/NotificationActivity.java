package com.app.xeross.mynews.Controller;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.app.xeross.mynews.R;

public class NotificationActivity extends AppCompatActivity {

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
}

// new NetworkAsyncTask(this).execute("https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=da4e42347e744f2cb790ff847b0aa6ec");