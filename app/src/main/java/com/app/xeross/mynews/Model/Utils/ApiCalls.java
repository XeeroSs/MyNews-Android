package com.app.xeross.mynews.Model.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.app.xeross.mynews.Model.Articles.Articles;
import com.app.xeross.mynews.View.Adapter.RecyclerViewAdapterMost;
import com.app.xeross.mynews.View.Adapter.RecyclerViewAdapterTop;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.xeross.mynews.Model.Utils.Constants.API_KEY;

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


    public static void request(final Context context) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Articles> calls = apiInterface.getTopStories("movies", API_KEY);
        calls.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                if (response.isSuccessful()) {
                    // int article = response.body().;
                    // if (article != 0) {
                    // createNotification(context, article);
                    // }
                    Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

}
