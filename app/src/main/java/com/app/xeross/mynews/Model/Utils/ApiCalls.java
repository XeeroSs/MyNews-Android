package com.app.xeross.mynews.Model.Utils;

import android.content.Context;
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
    public static void requestMostPopular(Context context, final RecyclerViewAdapter recyclerViewAdapter) {

        // WeakReference for avoid the memory leaks
        //final WeakReference<> cw = new WeakReference<>(callbacks);

        // Get Retrofit instance
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Articles> call = apiInterface.getMostPopular(API_KEY);

        // Network request execution
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

    public static void requestTopStories(Context context, final RecyclerViewAdapter recyclerViewAdapter) {

        // WeakReference for avoid the memory leaks
        //final WeakReference<> cw = new WeakReference<>(callbacks);

        // Get Retrofit instance
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Articles> call = apiInterface.getTopStories(API_KEY);

        // Network request execution
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
