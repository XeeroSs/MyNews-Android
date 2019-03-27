package com.app.xeross.mynews.Utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.app.xeross.mynews.Controller.Activity.NotificationActivity;
import com.app.xeross.mynews.Controller.Activity.WebViewActivity;
import com.app.xeross.mynews.Model.Articles.ArticlesTop;
import com.app.xeross.mynews.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.xeross.mynews.Utils.Constants.API_KEY;
import static com.app.xeross.mynews.Utils.Constants.SP;
import static com.app.xeross.mynews.Utils.Constants.WEBVIEW;

/**
 * Created by XeroSs on 13/01/2019.
 */
public class AlarmReceiver extends BroadcastReceiver {

    private CharSequence name = "Notification Title";
    private String description = "Notification Description";
    private ApiInterface apiInterface;
    private Call<ArticlesTop> calls;
    private String t, a, b, p, o, s;
    private SharedPreferences preferences;

    @Override
    public void onReceive(final Context context, Intent intent) {

        // ArrayList holding user registered information
        ArrayList<String> a = NotificationActivity.LoadSection(context);
        preferences = context.getSharedPreferences(SP, Context.MODE_PRIVATE);

        if (a.contains("Arts")) {
            request(context, "arts", a.get(0));
        }
        if (a.contains("Business")) {
            request(context, "business", a.get(0));
        }
        if (a.contains("Politics")) {
            request(context, "politics", a.get(0));
        }
        if (a.contains("Entrepreneurs")) {
            request(context, "opinion", a.get(0));
        }
        if (a.contains("Sports")) {
            request(context, "sports", a.get(0));
        }
        if (a.contains("Travel")) {
            request(context, "travel", a.get(0));
        }
    }

    // Request HTTP
    private void request(final Context context, final String section, final String edittext) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        calls = apiInterface.getTopStories(section, API_KEY);

        calls.enqueue(new Callback<ArticlesTop>() {
            @Override
            public void onResponse(Call<ArticlesTop> call, Response<ArticlesTop> response) {
                if (response.isSuccessful()) {

                    String str = response.body().getResults().get(0).getTitle();
                    String url = response.body().getResults().get(0).getUrl();

                    // Gets the description of the last articles to notify
                    a = preferences.getString("arts", "");
                    b = preferences.getString("business", "");
                    p = preferences.getString("politics", "");
                    o = preferences.getString("opinion", "");
                    s = preferences.getString("sports", "");
                    t = preferences.getString("travel", "");

                    if (section.equals("arts")) {
                        section(section, str, a, edittext, context, url);
                    }

                    if (section.equals("business")) {
                        section(section, str, b, edittext, context, url);
                    }
                    if (section.equals("politics")) {
                        section(section, str, p, edittext, context, url);
                    }
                    if (section.equals("opinion")) {
                        section(section, str, o, edittext, context, url);
                    }
                    if (section.equals("sports")) {
                        section(section, str, s, edittext, context, url);
                    }
                    if (section.equals("travel")) {
                        section(section, str, t, edittext, context, url);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArticlesTop> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

    private void section(String section, String str, String str2, String editText, Context context, String url) {
        // Check if the description of the present article is the same as the one saved previously,
        // if it is not the case, save the new description, and send the notification to the user
        if (!(str.equalsIgnoreCase(str2))) {
            if (str.contains(editText)) {
                notificaton(context, str, section, url);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(section, str);
                editor.apply();
            }
        }
    }


    // Notification configuration
    private void notificaton(Context context, String str, String section, String url) {
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        Intent i = new Intent(context, WebViewActivity.class);
        i.putExtra(WEBVIEW, url);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);
        NotificationChannel channel = new NotificationChannel("12", name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "45")
                .setContentTitle("Section: " + section)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true)
                .setContentText(str)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Send Notification
        Notification notification = mBuilder.build();
        notificationManager.notify(0, notification);
        saveIntNot(loadIntNot() + 1);
    }

    private void saveIntNot(int i) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("IntNot", i);
        editor.apply();
    }

    // Load the EditText
    private int loadIntNot() {
        int i = preferences.getInt("IntNot", 0);
        return i;
    }
}