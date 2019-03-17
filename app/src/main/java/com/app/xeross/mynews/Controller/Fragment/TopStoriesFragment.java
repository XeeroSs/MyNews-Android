package com.app.xeross.mynews.Controller.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.xeross.mynews.Controller.Activity.WebViewActivity;
import com.app.xeross.mynews.Model.Articles.ArticlesTop;
import com.app.xeross.mynews.Model.Utils.ApiCalls;
import com.app.xeross.mynews.Model.Utils.ApiClient;
import com.app.xeross.mynews.Model.Utils.ApiInterface;
import com.app.xeross.mynews.Model.Utils.ItemClickSupport;
import com.app.xeross.mynews.R;
import com.app.xeross.mynews.View.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.xeross.mynews.Model.Utils.Constants.API_KEY;
import static com.app.xeross.mynews.Model.Utils.Constants.WEBVIEW;

public class TopStoriesFragment extends Fragment {

    ArrayList<ArticlesTop.Result> articles = new ArrayList<>();
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;


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

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(), articles, null, null);
        mRecyclerViewAdapter.clearTopList();
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        executeRequestHTTP(apiInterface);
        this.confOnClickRecyclerView();
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    // Method for the network request
    private void executeRequestHTTP(ApiInterface apiInterface) {
        ApiCalls.requestTop((RecyclerViewAdapter) mRecyclerView.getAdapter(), apiInterface.getTopStories("movies", API_KEY));
        articles.addAll(((RecyclerViewAdapter) mRecyclerView.getAdapter()).items());
        ApiCalls.requestTop((RecyclerViewAdapter) mRecyclerView.getAdapter(), apiInterface.getTopStories("technology", API_KEY));
        articles.addAll(((RecyclerViewAdapter) mRecyclerView.getAdapter()).items());
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    // Get the position and the click an item
    private void confOnClickRecyclerView() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_top_stories)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        ArticlesTop.Result result = mRecyclerViewAdapter.getPositionTop(position);
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra(WEBVIEW, result.getUrl());
                        getContext().startActivity(intent);
                    }
                });

    }
}

