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

import com.app.xeross.mynews.Model.Articles.ArticlesTop;
import com.app.xeross.mynews.Model.Utils.ApiCalls;
import com.app.xeross.mynews.Model.Utils.ApiClient;
import com.app.xeross.mynews.Model.Utils.ApiInterface;
import com.app.xeross.mynews.R;
import com.app.xeross.mynews.View.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.xeross.mynews.Model.Utils.Constants.API_KEY;
import static com.app.xeross.mynews.Model.Utils.Constants.SI;
import static com.app.xeross.mynews.Model.Utils.Constants.SP;

public class MovieFragment extends Fragment {

    public ArrayList<ArticlesTop.Result> mItemsTop = new ArrayList<>();
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private String i = "#fff333";
    private SharedPreferences preferences;

    public MovieFragment() {
    }

    // Fragment management
    public static MovieFragment newInstance() {
        return (new MovieFragment());
    }

    // Fragment view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this, view);

        preferences = getActivity().getSharedPreferences(SP, Context.MODE_PRIVATE);
        this.loadColor();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(), mItemsTop, null, null);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        executeRequestHTTP(apiInterface);
        //this.confOnClickRecyclerView();
        return view;
    }

    // Method for the network request
    private void executeRequestHTTP(ApiInterface apiInterface) {
        ApiCalls.requestTop((RecyclerViewAdapter) mRecyclerView.getAdapter(), apiInterface.getTopStories("movies", API_KEY));
    }

    // Get the position and the click an item
    /*private void confOnClickRecyclerView() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_movie)
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

    @Override
    public void onStop() {
        super.onStop();
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

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
