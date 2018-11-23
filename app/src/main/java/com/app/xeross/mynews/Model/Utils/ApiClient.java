package com.app.xeross.mynews.Model.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XeroSs on 22/11/2018.
 */
public class ApiClient {

    public static String BASE_URL = "https://api.nytimes.com/";
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
