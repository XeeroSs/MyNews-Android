package com.app.xeross.mynews.Model.Utils;

import com.app.xeross.mynews.Model.Articles.ArticlesMost;
import com.app.xeross.mynews.Model.Articles.ArticlesSearch;
import com.app.xeross.mynews.Model.Articles.ArticlesTop;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by XeroSs on 08/11/2018.
 */
public interface ApiInterface {

    // REST request on the URL complement
    @GET("svc/mostpopular/v2/viewed/7.json")
    Call<ArticlesMost> getMostPopular(@Query("api-key") String apikey);

    @GET("svc/topstories/v2/{section}.json")
    Call<ArticlesTop> getTopStories(@Path("section") String section,
                                    @Query("api-key") String apikey);

    @GET("svc/search/v2/articlesearch.json")
    Call<ArticlesSearch> getArticles(@QueryMap Map<String, String> query,
                                     @Query("api-key") String apikey);
}
