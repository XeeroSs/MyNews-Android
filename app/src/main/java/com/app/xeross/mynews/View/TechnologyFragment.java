package com.app.xeross.mynews.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

public class TechnologyFragment extends Fragment {

    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    public TechnologyFragment() {
    }

    public static TechnologyFragment newInstance() {
        return (new TechnologyFragment());
    }

    // Fragment management
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_technology, container, false);
        ButterKnife.bind(this, view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        executeRequestHTTP(apiInterface);
        this.confOnClickRecyclerView();
        return view;
    }

    // Method for the network request
    private void executeRequestHTTP(ApiInterface apiInterface) {
        ApiCalls.requestHTTPTopStories((RecyclerViewAdapter) mRecyclerView.getAdapter(), apiInterface.getTechnology(API_KEY));
    }

    // Get the position and the click an item
    private void confOnClickRecyclerView() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_technology)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Result result = mRecyclerViewAdapter.getPosition(position);
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra(WEBVIEW, result.getUrl());
                        getContext().startActivity(intent);
                    }
                });

    }

}
