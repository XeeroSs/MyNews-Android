package com.app.xeross.mynews.View;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.xeross.mynews.Model.Adapter.CustomAdapter;
import com.app.xeross.mynews.Model.Utils.ApiCalls;
import com.app.xeross.mynews.R;

public class MostPopularFragment extends Fragment {

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
        RecyclerView mRecyclerView = view.findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new CustomAdapter(getContext()));
        executeRequestHTTP(getContext());

        return mRecyclerView;
    }

    // Method for the network request
    private void executeRequestHTTP(Context context) {
        ApiCalls.request(context);
    }

}
