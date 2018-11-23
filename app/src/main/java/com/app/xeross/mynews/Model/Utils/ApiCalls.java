package com.app.xeross.mynews.Model.Utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.app.xeross.mynews.Model.Adapter.CustomAdapter;
import com.app.xeross.mynews.Model.MostPopular.ApiModelMostPopular;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by XeroSs on 15/11/2018.
 */

public class ApiCalls {

    // API_KEY
    private final static String API_KEY = "da4e42347e744f2cb790ff847b0aa6ec";

    // Method for the network request
    public static void request(Context context) {

        // WeakReference for avoid the memory leaks
        //final WeakReference<> cw = new WeakReference<>(callbacks);
        final CustomAdapter mAdapter = new CustomAdapter(context);


        // Get Retrofit instance
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiModelMostPopular> call = apiInterface.getMostPopular();

        // Network request execution
        call.enqueue(new Callback<ApiModelMostPopular>() {
            @Override
            public void onResponse(Call<ApiModelMostPopular> call, Response<ApiModelMostPopular> response) {
                if (response.isSuccessful()) {
                    mAdapter.updateAnswers(response.body());
                }
            }

            @Override
            public void onFailure(Call<ApiModelMostPopular> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

    // Get callbacks
    public interface Callbacks {

        void onResponse(@Nullable List<ApiModelMostPopular> mp);

        void onFailure();
    }
}
