package com.app.xeross.mynews.Model.Utils;

import android.support.annotation.Nullable;
import android.util.Log;

import com.app.xeross.mynews.Model.MostPopular.ApiModelMostPopular;

import java.lang.ref.WeakReference;
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
    public static void request(Callbacks callbacks) {

        // WeakReference for avoid the memory leaks
        final WeakReference<Callbacks> cw = new WeakReference<>(callbacks);

        // Get Retrofit instance
        ApiInterface apiInterface = ApiInterface.ret.create(ApiInterface.class);
        Call<List<ApiModelMostPopular>> call = apiInterface.getMostPopular(API_KEY);

        // Network request execution
        call.enqueue(new Callback<List<ApiModelMostPopular>>() {
            @Override
            public void onResponse(Call<List<ApiModelMostPopular>> call, Response<List<ApiModelMostPopular>> response) {
                if (cw.get() != null)
                    cw.get().onResponse(response.body());
                Log.i("Reussi", "onResponse");
            }

            @Override
            public void onFailure(Call<List<ApiModelMostPopular>> call, Throwable t) {
                if (cw.get() != null) cw.get().onFailure();
                Log.i("non Reussi", String.valueOf(t));
            }
        });
    }

    // Get callbacks
    public interface Callbacks {

        void onResponse(@Nullable List<ApiModelMostPopular> mp);

        void onFailure();
    }
}
