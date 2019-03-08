package com.app.xeross.mynews;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * ApiTopStories local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    public ArrayList<String> items = new ArrayList<>();
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();

    @Test
    public void testArticle() throws Exception {

        list.add("df");
        list.add("df");

        items.addAll(list);

        list2.add("df");
        list2.add("df");
        list2.add("df");

        items.addAll(list2);

        Assert.assertEquals(items.size(), list.size() + list2.size());

    }
}