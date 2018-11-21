package com.app.xeross.mynews.Model.Adapter;

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

    Retrofit ret = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    /*@GET("articlesearch.json")
    Call<List<ApiModelArticleSearch>> getActu();*/

    @GET("svc/mostpopular/v2/mostviewed/arts/7.json?api-key=")
    Call<List<ApiModelMostPopular>> getMostPopular(@Query("api_key") String apikey);
}
