package com.app.xeross.mynews.Model.Utils;

import com.app.xeross.mynews.Model.ArticleSearch.ApiArticleSearch;
import com.app.xeross.mynews.Model.MostPopular.Articles;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by XeroSs on 08/11/2018.
 */
public interface ApiInterface {

    // REST request on the URL complement
    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json")
    Call<Articles> getMostPopular(@Query("api-key") String apikey);

    @GET("svc/search/v2/articlesearch.json")
    Call<ApiArticleSearch> getArticleSearch(@Query("api-key") String apikey);

    @GET("svc/topstories/v2/home.json")
    Call<Articles> getTopStories(@Query("api-key") String apikey);
}
