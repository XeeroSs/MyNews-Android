package com.app.xeross.mynews.Controller.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.xeross.mynews.View.Adapter.RecyclerViewAdapterMost;
import com.app.xeross.mynews.Model.Articles.Articles;
import com.app.xeross.mynews.Model.Utils.ApiCalls;
import com.app.xeross.mynews.Model.Utils.ApiClient;
import com.app.xeross.mynews.Model.Utils.ApiInterface;
import com.app.xeross.mynews.Model.Utils.ItemClickSupport;
import com.app.xeross.mynews.Controller.Activity.WebViewActivity;
import com.app.xeross.mynews.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.xeross.mynews.Model.Utils.Constants.API_KEY;
import static com.app.xeross.mynews.Model.Utils.Constants.SI;
import static com.app.xeross.mynews.Model.Utils.Constants.SP;
import static com.app.xeross.mynews.Model.Utils.Constants.WEBVIEW;

public class MostPopularFragment extends Fragment {

    public ArrayList<Articles.Result> mItems = new ArrayList<>();
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    private RecyclerViewAdapterMost mRecyclerViewAdapterMost;
    private SharedPreferences preferences;

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

        preferences = getActivity().getSharedPreferences(SP, Context.MODE_PRIVATE);
        this.loadColor(getActivity());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewAdapterMost = new RecyclerViewAdapterMost(getActivity(), mItems);
        mRecyclerView.setAdapter(mRecyclerViewAdapterMost);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        executeRequestHTTP(apiInterface);

        this.confOnClickRecyclerView();
        return view;
    }

    // Method for the network request
    private void executeRequestHTTP(ApiInterface apiInterface) {
        ApiCalls.requestHTTP((RecyclerViewAdapterMost) mRecyclerView.getAdapter(), apiInterface.getMostPopular(API_KEY));
    }

    // Get the position and the click an item
    private void confOnClickRecyclerView() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_most_popular)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Articles.Result result = mRecyclerViewAdapterMost.getPosition(position);
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra(WEBVIEW, result.getUrl());
                        result.setColor("#6666ff");
                        saveColor(getActivity());
                        getContext().startActivity(intent);
                    }
                });

    }

    private void saveColor(Context context) {
        SharedPreferences.Editor edit = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mItems);
        edit.putString(SI, json);
        edit.apply();
    }

    private void loadColor(Context context) {
        Gson gson = new Gson();
        String json = preferences.getString(SI, null);
        Type type = new TypeToken<ArrayList<Articles.Result>>() {
        }.getType();
        mItems = gson.fromJson(json, type);
        if (mItems == null) {
            mItems = new ArrayList<>();
        }
    }
}
