package com.app.xeross.mynews.Model.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.app.xeross.mynews.Model.Utils.Constants.BASE_URL;

/**
 * Created by XeroSs on 22/11/2018.
 */
public class ApiClient {

    private static Retrofit ret;

    // Retrofit object used to run a network request
    public static Retrofit getClient() {
        ret = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return ret;
    }
}
