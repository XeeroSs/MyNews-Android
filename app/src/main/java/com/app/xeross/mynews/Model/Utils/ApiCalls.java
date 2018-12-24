package com.app.xeross.mynews.Model.Utils;

import android.util.Log;

import com.app.xeross.mynews.Model.Adapter.RecyclerViewAdapterMost;
import com.app.xeross.mynews.Model.Adapter.RecyclerViewAdapterTop;
import com.app.xeross.mynews.Model.Articles.Articles;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by XeroSs on 15/11/2018.
 */

public class ApiCalls {

    // Method for the network request
    public static void requestHTTP(final RecyclerViewAdapterMost recyclerViewAdapterMost, Call<Articles> calls) {
        calls.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                if (response.isSuccessful()) {
                    recyclerViewAdapterMost.updateAnswers(response.body());
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

    public static void requestHTTPTop(final RecyclerViewAdapterTop recyclerViewAdapterMost, Call<Articles> calls) {
        calls.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                if (response.isSuccessful()) {
                    recyclerViewAdapterMost.updateAnswers(response.body());
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

}
