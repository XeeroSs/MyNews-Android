package com.app.xeross.mynews.Model.Utils;

import com.app.xeross.mynews.Model.MostPopular.ApiModelMostPopular;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by XeroSs on 08/11/2018.
 */
public interface ApiInterface {

    // REST request on the URL complement
    @GET("svc/mostpopular/v2/mostviewed/arts/7.json?api-key=da4e42347e744f2cb790ff847b0aa6ec")
    Call<ApiModelMostPopular> getMostPopular();
}
