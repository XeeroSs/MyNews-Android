package com.app.xeross.mynews;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageView;

import com.app.xeross.mynews.Model.Articles.ArticlesMost;
import com.app.xeross.mynews.Model.Articles.ArticlesSearch;
import com.app.xeross.mynews.Model.Articles.ArticlesTop;
import com.app.xeross.mynews.Utils.ApiClient;
import com.app.xeross.mynews.Utils.ApiInterface;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import retrofit2.Call;

import static com.app.xeross.mynews.Utils.Constants.API_KEY;

/**
 * Created by XeroSs on 07/03/2019.
 */
@RunWith(AndroidJUnit4.class)
public class ArticlesTest {

    private Context context;
    private ImageView image;

    @Test
    public void ArticleSize() throws Exception {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArticlesTop> calls = apiInterface.getTopStories("movies", API_KEY);
        Assert.assertNotEquals(0, calls.execute().body().getResults().size());
        Call<ArticlesTop> callss = apiInterface.getTopStories("technology", API_KEY);
        Assert.assertNotEquals(0, callss.execute().body().getResults().size());
        Call<ArticlesMost> callsss = apiInterface.getMostPopular(API_KEY);
        Assert.assertNotEquals(0, callsss.execute().body().getResults().size());
    }


    @Test
    public void ArticleURL() throws Exception {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArticlesMost> calls = apiInterface.getMostPopular(API_KEY);
        String url = calls.execute().body().getResults().get(0).getMedia().get(0).getMediaMetadata().get(0).getUrl();
        Boolean rt;
        if (url.startsWith("https://")) {
            rt = true;
        } else {
            rt = false;
        }

        Assert.assertTrue(rt);
    }

    @Test
    public void ArticleSearch() throws Exception {

        HashMap<String, String> query = new HashMap<>();

        query.put("q", "android");
        query.put("begin_date", "20190101");
        query.put("end_date", "20190310");
        query.put("fq", "Technology");

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArticlesSearch> calls = apiInterface.getArticles(query, API_KEY);

        Assert.assertNotEquals(0, calls.execute().body().getResponse().getDocs().size());

    }

    @Test
    public void ArticleImage() throws Exception {

        context = InstrumentationRegistry.getTargetContext();

        HashMap<String, String> query = new HashMap<>();

        query.put("q", "ios");
        query.put("begin_date", "20190101");
        query.put("end_date", "20190310");
        query.put("fq", "Technology");

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArticlesSearch> calls = apiInterface.getArticles(query, API_KEY);
        Boolean rt;

        String url = calls.execute().body().getResponse().getDocs().get(0).getMultimedia().get(0).getUrl();
        String img_url = context.getResources().getString(R.string.urlimage) + url;

        /*image = find;

        URL urls = new URL(img_url);
        Bitmap bmp;
        bmp = BitmapFactory.decodeStream(urls.openConnection().getInputStream());
        image.setImageBitmap(bmp);

        if (image.getDrawable() == null) {
            rt = true;
        } else {
            rt = false;
        }

        Assert.assertTrue(rt);*/
    }

}
