package com.app.xeross.mynews.View;


import android.content.Context;
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
import com.app.xeross.mynews.Model.Utils.ApiCalls;
import com.app.xeross.mynews.R;

public class TopStoriesFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public TopStoriesFragment() {
    }

    // Fragment management
    public static TopStoriesFragment newInstance() {
        return (new TopStoriesFragment());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Fragment view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top_stories, container, false);

        mRecyclerView = view.findViewById(R.id.list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(new RecyclerViewAdapter(getActivity()));

        executeRequestHTTP(getContext());
        return view;
    }

    // Method for the network request
    private void executeRequestHTTP(Context context) {
        ApiCalls.requestTopStories(context, (RecyclerViewAdapter) mRecyclerView.getAdapter());
    }


}
