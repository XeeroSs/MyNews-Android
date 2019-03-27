package com.app.xeross.mynews.Utils;

import android.util.Log;

import com.app.xeross.mynews.Model.Articles.ArticlesMost;
import com.app.xeross.mynews.Model.Articles.ArticlesSearch;
import com.app.xeross.mynews.Model.Articles.ArticlesTop;
import com.app.xeross.mynews.View.Adapter.RecyclerViewAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by XeroSs on 15/11/2018.
 */

public class ApiCalls {

    // Method for the network request
    public static void requestTop(final RecyclerViewAdapter recyclerViewAdapterMost, Call<ArticlesTop> calls) {
        calls.enqueue(new Callback<ArticlesTop>() {
            @Override
            public void onResponse(Call<ArticlesTop> call, Response<ArticlesTop> response) {
                if (response.isSuccessful()) {
                    recyclerViewAdapterMost.updateAnswersTop(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArticlesTop> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

    // Method for the network request
    public static void requestSearch(final RecyclerViewAdapter recyclerViewAdapterMost, Call<ArticlesSearch> calls) {
        calls.enqueue(new Callback<ArticlesSearch>() {
            @Override
            public void onResponse(Call<ArticlesSearch> call, Response<ArticlesSearch> response) {
                if (response.isSuccessful()) {

                    recyclerViewAdapterMost.updateAnswersSearch(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArticlesSearch> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

    // Method for the network request
    public static void requestMost(final RecyclerViewAdapter recyclerViewAdapterMost, Call<ArticlesMost> calls) {
        calls.enqueue(new Callback<ArticlesMost>() {
            @Override
            public void onResponse(Call<ArticlesMost> call, Response<ArticlesMost> response) {
                if (response.isSuccessful()) {
                    recyclerViewAdapterMost.updateAnswersMost(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArticlesMost> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

}
