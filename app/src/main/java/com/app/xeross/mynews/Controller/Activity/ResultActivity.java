package com.app.xeross.mynews.Controller.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.app.xeross.mynews.Model.Articles.ArticlesSearch;
import com.app.xeross.mynews.R;
import com.app.xeross.mynews.Utils.ApiCalls;
import com.app.xeross.mynews.Utils.ApiClient;
import com.app.xeross.mynews.Utils.ApiInterface;
import com.app.xeross.mynews.Utils.ItemClickSupport;
import com.app.xeross.mynews.View.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.xeross.mynews.Utils.Constants.API_KEY;
import static com.app.xeross.mynews.Utils.Constants.WEBVIEW;

public class ResultActivity extends AppCompatActivity {

    public ArrayList<ArticlesSearch.Doc> articlesSearch = new ArrayList<>();
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private ArrayList<String> query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ButterKnife.bind(this);
        this.confToolbar();

        // RecyclerView Configuration
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewAdapter = new RecyclerViewAdapter(this, null, articlesSearch, null);
        mRecyclerViewAdapter.clearSearchList();
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        this.executeRequestHTTP(apiInterface, this);
        this.confOnClickRecyclerView();
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

    // Request HTTP
    private void executeRequestHTTP(ApiInterface apiInterface, Context context) {
        query = SearchActivity.loadResult(context);

        HashMap<String, String> hm = new HashMap<>();
        hm.put("q", query.get(0));
        hm.put("begin_date", query.get(1));
        hm.put("end_date", query.get(2));

        if (query.contains("arts")) {
            hm.put("fq", "Arts");
            test(apiInterface, hm);
        }
        if (query.contains("business")) {
            hm.put("fq", "Business");
            test(apiInterface, hm);
        }
        if (query.contains("politics")) {
            hm.put("fq", "Politics");
            test(apiInterface, hm);
        }
        if (query.contains("entrepreneurs")) {
            hm.put("fq", "Entrepreneurs");
            test(apiInterface, hm);
        }
        if (query.contains("sports")) {
            hm.put("fq", "Sports");
            test(apiInterface, hm);
        }
        if (query.contains("travel")) {
            hm.put("fq", "Travel");
            test(apiInterface, hm);
        }

        mRecyclerViewAdapter.notifyDataSetChanged();

        if (articlesSearch.isEmpty()) {
            Toast.makeText(context, "Aucun résultat, veuillez réessayer", Toast.LENGTH_SHORT).show();
        }
    }

    private void test(ApiInterface apiInterface, HashMap<String, String> hm) {
        ApiCalls.requestSearch((RecyclerViewAdapter) mRecyclerView.getAdapter(), apiInterface.getArticles(hm, API_KEY));
        articlesSearch.addAll(((RecyclerViewAdapter) mRecyclerView.getAdapter()).itemsSearch());
    }

    private void confOnClickRecyclerView() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_top_stories)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        ArticlesSearch.Doc result = mRecyclerViewAdapter.getPosition(position);
                        Intent intent = new Intent(ResultActivity.this, WebViewActivity.class);
                        intent.putExtra(WEBVIEW, result.getWebUrl());
                        startActivity(intent);
                    }
                });
    }
}
