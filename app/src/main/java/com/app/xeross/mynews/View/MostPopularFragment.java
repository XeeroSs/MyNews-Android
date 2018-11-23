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

public class MostPopularFragment extends Fragment {

    public MostPopularFragment() {
    }

    // Fragment management
    public static MostPopularFragment newInstance() {
        return (new MostPopularFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView mRecyclerView = new RecyclerView(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new CustomAdapter(getContext()));
        mRecyclerView.setHasFixedSize(true);
        execute(getContext());

        return mRecyclerView;
    }

    private void execute(Context context) {
        ApiCalls.request(context);
    }

}
