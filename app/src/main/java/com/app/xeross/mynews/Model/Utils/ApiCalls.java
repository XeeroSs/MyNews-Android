package com.app.xeross.mynews.Model.Utils;

import android.util.Log;

import com.app.xeross.mynews.Model.Adapter.RecyclerViewAdapter;
import com.app.xeross.mynews.Model.Articles.Articles;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.xeross.mynews.Model.Utils.Constants.API_KEY;

/**
 * Created by XeroSs on 15/11/2018.
 */

public class ApiCalls {

    // Method for the network request
    public static void requestHTTPTopStories(final RecyclerViewAdapter recyclerViewAdapter, Call<Articles> calls) {
        calls.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                if (response.isSuccessful()) {
                    recyclerViewAdapter.updateAnswers(response.body());
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

    public static void requestHTTPMostStories(final RecyclerViewAdapter recyclerViewAdapter) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Articles> call = apiInterface.getMostPopular(API_KEY);

        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                if (response.isSuccessful()) {
                    recyclerViewAdapter.updateAnswers(response.body());
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }
}
