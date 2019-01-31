package com.app.xeross.mynews.Controller.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.xeross.mynews.Controller.Activity.SearchActivity;
import com.app.xeross.mynews.Model.Articles.ArticlesSearch;
import com.app.xeross.mynews.Model.Articles.ArticlesTop;
import com.app.xeross.mynews.Model.Utils.ApiCalls;
import com.app.xeross.mynews.Model.Utils.ApiClient;
import com.app.xeross.mynews.Model.Utils.ApiInterface;
import com.app.xeross.mynews.R;
import com.app.xeross.mynews.View.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.xeross.mynews.Model.Utils.Constants.API_KEY;
import static com.app.xeross.mynews.Model.Utils.Constants.SI;
import static com.app.xeross.mynews.Model.Utils.Constants.SP;

public class TopStoriesFragment extends Fragment {

    public ArrayList<ArticlesTop.Result> articles = new ArrayList<>();
    public ArrayList<ArticlesSearch.Doc> articlesSearch = new ArrayList<>();
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    @BindView(R.id.check_textview)
    TextView mTextview;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private SharedPreferences preferences;
    private HashMap<String, String> query;
    private String i = "#fff333";


    public TopStoriesFragment() {
    }

    // Fragment management
    public static TopStoriesFragment newInstance() {
        return (new TopStoriesFragment());
    }

    // Fragment view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top_stories, container, false);
        ButterKnife.bind(this, view);
        preferences = this.getActivity().getSharedPreferences(SP, Context.MODE_PRIVATE);
        this.loadColor();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(), articles, articlesSearch, null);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        executeRequestHTTP(apiInterface, getContext());
        mTextview.setVisibility(View.GONE);
        //this.confOnClickRecyclerView();
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    // Method for the network request
    private void executeRequestHTTP(ApiInterface apiInterface, Context context) {

        query = SearchActivity.loadResult(context);

        if (query.size() == 0) {
            ApiCalls.requestTop((RecyclerViewAdapter) mRecyclerView.getAdapter(), apiInterface.getTopStories("home", API_KEY));
            Toast.makeText(context, "Top", Toast.LENGTH_SHORT).show();
        } else {
            ApiCalls.requestSearch((RecyclerViewAdapter) mRecyclerView.getAdapter(), apiInterface.getArticles(query, API_KEY));
            Toast.makeText(context, "" + query, Toast.LENGTH_SHORT).show();
        }
    }

    // Get the position and the click an item
    /*private void confOnClickRecyclerView() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_top_stories)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        ArticlesTop.Doc result = mRecyclerViewAdapter.getPosition(position);
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra(WEBVIEW, result.getUrl());
                        String str = "#6666ff";
                        result.getResult().get(position).setColor(str);
                        saveColor(str);
                        getContext().startActivity(intent);
                    }
                });

    }*/

    private void saveColor(String color) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SI, color);
        editor.apply();
    }

    private String loadColor() {
        i = preferences.getString(SI, null);
        return i;
    }
}

