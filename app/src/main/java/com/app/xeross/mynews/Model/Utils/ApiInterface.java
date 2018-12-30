package com.app.xeross.mynews.Model.Utils;

import com.app.xeross.mynews.Model.Articles.ApiArticleSearch;
import com.app.xeross.mynews.Model.Articles.Articles;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by XeroSs on 08/11/2018.
 */
public interface ApiInterface {

    // REST request on the URL complement
    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json")
    Call<Articles> getMostPopular(@Query("api-key") String apikey);

    @GET("svc/search/v2/articlesearch.json?facet_field=day_of_week")
    Call<ApiArticleSearch> getArticleSearch(@Path("fq") String fq,
                                            @Path("begin_date") Integer begin_date,
                                            @Path("end_date") Integer end_date,
                                            @Query("api-key") String apikey);

    @GET("svc/topstories/v2/{section}.json")
    Call<Articles> getTopStories(@Path("section") String section,
                                 @Query("api-key") String apikey);
}
