package com.app.xeross.mynews;

import android.util.Log;

import com.app.xeross.mynews.Model.Articles.ArticlesTop;
import com.app.xeross.mynews.Model.Utils.ApiClient;
import com.app.xeross.mynews.Model.Utils.ApiInterface;

import org.junit.Assert;
import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.xeross.mynews.Model.Utils.Constants.API_KEY;
import static org.junit.Assert.*;

/**
 * ApiTopStories local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testArticle() throws Exception {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArticlesTop> calls = apiInterface.getTopStories("movies", API_KEY);

        calls.enqueue(new Callback<ArticlesTop>() {
            @Override
            public void onResponse(Call<ArticlesTop> call, Response<ArticlesTop> response) {
                    assertEquals(20, 31);
            }

            @Override
            public void onFailure(Call<ArticlesTop> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }
}