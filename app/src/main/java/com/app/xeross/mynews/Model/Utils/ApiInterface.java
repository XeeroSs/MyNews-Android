package com.app.xeross.mynews.Model.Utils;

import com.app.xeross.mynews.Model.MostPopular.ApiModelMostPopular;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by XeroSs on 08/11/2018.
 */
public interface ApiInterface {

    // Retrofit object used to run a network request
    Retrofit ret = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // REST request on the URL complement
    @GET("svc/mostpopular/v2/mostviewed/arts/7.json?api-key=")
    Call<List<ApiModelMostPopular>> getMostPopular(@Query("api_key") String apikey);
}
