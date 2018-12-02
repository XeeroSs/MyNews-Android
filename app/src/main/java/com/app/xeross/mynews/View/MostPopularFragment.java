package com.app.xeross.mynews.View;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.xeross.mynews.Model.Adapter.RecyclerViewAdapter;
import com.app.xeross.mynews.Model.Articles.Result;
import com.app.xeross.mynews.Model.Utils.ApiCalls;
import com.app.xeross.mynews.Model.Utils.ApiClient;
import com.app.xeross.mynews.Model.Utils.ApiInterface;
import com.app.xeross.mynews.Model.Utils.ItemClickSupport;
import com.app.xeross.mynews.Model.Utils.WebViewActivity;
import com.app.xeross.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.xeross.mynews.Model.Utils.Constants.API_KEY;
import static com.app.xeross.mynews.Model.Utils.Constants.WEBVIEW;

public class MostPopularFragment extends Fragment {

    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    public MostPopularFragment() {
    }

    // Fragment management
    public static MostPopularFragment newInstance() {
        return (new MostPopularFragment());
    }

    // Fragment view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_most_popular, container, false);
        ButterKnife.bind(this, view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        executeRequestHTTP();
        //this.confOnClickRecyclerView();
        return view;
    }

    // Method for the network request
    private void executeRequestHTTP() {
        ApiCalls.requestHTTPMostStories((RecyclerViewAdapter) mRecyclerView.getAdapter());
    }

    // Get the position and the click an item
    /*private void confOnClickRecyclerView() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_most_popular)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Result result = mRecyclerViewAdapter.getPosition(position);
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra(WEBVIEW, result.getUrl());
                        getContext().startActivity(intent);
                    }
                });

    }*/

}
