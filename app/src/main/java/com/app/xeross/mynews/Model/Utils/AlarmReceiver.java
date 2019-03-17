package com.app.xeross.mynews.Model.Utils;

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
import android.widget.Toast;

import com.app.xeross.mynews.Controller.Activity.MainActivity;
import com.app.xeross.mynews.Controller.Activity.NotificationActivity;
import com.app.xeross.mynews.Model.Articles.ArticlesSearch;
import com.app.xeross.mynews.R;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.xeross.mynews.Model.Utils.Constants.API_KEY;
import static com.app.xeross.mynews.Model.Utils.Constants.SP;

/**
 * Created by XeroSs on 13/01/2019.
 */
public class AlarmReceiver extends BroadcastReceiver {

    private CharSequence name = "Notification Title";
    private String description = "Notification Description";
    private ApiInterface apiInterface;
    private Call<ArticlesSearch> calls;
    private String t, m;
    private SharedPreferences preferences;

    @Override
    public void onReceive(final Context context, Intent intent) {

        // ArrayList holding user registered information
        ArrayList<String> a = NotificationActivity.LoadSection(context);
        preferences = context.getSharedPreferences(SP, Context.MODE_PRIVATE);

        if (a.contains("movie")) {
            // Create a new HashMap that will contain the information for the notification
            HashMap<String, String> mapM = new HashMap<>();
            if (!a.get(0).contains("")) mapM.put("q", a.get(0));
            mapM.put("fq", "movie");
            request(context, mapM);
        }
        if (a.contains("technology")) {
            // Create a new HashMap that will contain the information for the notification
            HashMap<String, String> mapT = new HashMap<>();
            if (!a.get(0).contains("")) mapT.put("q", a.get(0));
            mapT.put("fq", "technology");
            request(context, mapT);
        }
    }

    // Request HTTP
    private void request(final Context context, final HashMap<String, String> values) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        calls = apiInterface.getArticles(values, API_KEY);

        //
        calls.enqueue(new Callback<ArticlesSearch>() {
            @Override
            public void onResponse(Call<ArticlesSearch> call, Response<ArticlesSearch> response) {
                if (response.isSuccessful()) {

                    String str = response.body().getResponse().getDocs().get(0).getHeadline().getMain().toString();

                    // Gets the description of the last articles to notify
                    t = preferences.getString("technologyKey", "");
                    m = preferences.getString("movieKey", "");

                    if (values.containsKey("fq") && values.containsValue("movie")) {
                        // Check if the description of the present article is the same as the one saved previously,
                        // if it is not the case, save the new description, and send the notification to the user
                        if (!(str.equalsIgnoreCase(m))) {
                            notificaton(context, str, "Movie");
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("movieKey", str);
                            editor.apply();
                        }
                    }

                    if (values.containsKey("fq") && values.containsValue("technology")) {
                        if (!(str.equalsIgnoreCase(t))) {
                            notificaton(context, str, "Technology");
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("technologyKey", str);
                            editor.apply();
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<ArticlesSearch> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

    // Notification configuration
    private void notificaton(Context context, String str, String section) {
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);
        NotificationChannel channel = new NotificationChannel("12", name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "12")
                .setContentTitle("Section: " + section)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true)
                .setContentText(str)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Send Notification
        Notification notification = mBuilder.build();
        notificationManager.notify(0, notification);
    }
}