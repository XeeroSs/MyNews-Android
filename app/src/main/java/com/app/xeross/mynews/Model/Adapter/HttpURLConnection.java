package com.app.xeross.mynews.Model.Adapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by XeroSs on 08/11/2018.
 */
public class HttpURLConnection {

    public static String startHttpRequest(String urlString) {

        StringBuilder stringBuilder = new StringBuilder();

        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.connect();
            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (MalformedURLException exception) {

        } catch (IOException exception) {

        } catch (Exception e) {

        }

        return stringBuilder.toString();
    }

}
