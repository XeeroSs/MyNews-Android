package com.app.xeross.mynews;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * ApiTopStories local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    public ArrayList<String> items = new ArrayList<>();
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    @Mock
    private ImageView image;
    @Mock
    private Context context;
    private String URL_STRING = "https://static01.nyt.com/images/2019/01/07/business/07jproose1-print/merlin_148355052_6b940b42-fb52-4140-9f45-1b00fbeca604-articleLarge.jpg";

    @Test
    public void test() throws Exception {

        list.add("df");
        list.add("df");

        items.addAll(list);

        list2.add("df");
        list2.add("df");
        list2.add("df");

        items.addAll(list2);

        Assert.assertEquals(items.size(), list.size() + list2.size());

    }

    @Test
    public void testCalendar() throws Exception {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        month += 1;
        String z1 = "";
        String z2 = "";
        if (month < 9) z2 = "0";
        if (day < 9) z1 = "0";
        String str = z1 + day + "/" + z2 + month + "/" + year;

        String[] date = str.toString().split("/");

        Assert.assertEquals("20190313", date[2] + date[1] + date[0]);
    }

}