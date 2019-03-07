package com.app.xeross.mynews;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.app.xeross.mynews.Model.Articles.ArticlesTop;
import com.app.xeross.mynews.Model.Utils.ApiClient;
import com.app.xeross.mynews.Model.Utils.ApiInterface;

import org.junit.Test;
import org.junit.runner.RunWith;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.xeross.mynews.Model.Utils.Constants.API_KEY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by XeroSs on 07/03/2019.
 */
@RunWith(AndroidJUnit4.class)
public class ArticlesTest {

    @Test
    public void testArticle() throws Exception {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArticlesTop> calls = apiInterface.getTopStories("movies", API_KEY);

        calls.enqueue(new Callback<ArticlesTop>() {
            @Override
            public void onResponse(Call<ArticlesTop> call, Response<ArticlesTop> response) {
            }

            @Override
            public void onFailure(Call<ArticlesTop> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

}
