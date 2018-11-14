package com.app.xeross.mynews.Model.Adapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by XeroSs on 08/11/2018.
 */
public interface ApiInterface {

    @GET("articlesearch.json")
    Call<List<ApiModelArticleSearch>> getActu();
}
