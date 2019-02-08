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

import com.app.xeross.mynews.Controller.Activity.MainActivity;
import com.app.xeross.mynews.Model.Articles.ArticlesTop;
import com.app.xeross.mynews.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.xeross.mynews.Model.Utils.Constants.API_KEY;
import static com.app.xeross.mynews.Model.Utils.Constants.SI;
import static com.app.xeross.mynews.Model.Utils.Constants.SP;

/**
 * Created by XeroSs on 13/01/2019.
 */
public class AlarmReceiver extends BroadcastReceiver {

    private CharSequence name = "Notification Title";
    private String description = "Notification Description";
    private ApiInterface apiInterface;
    private Call<ArticlesTop> calls;
    private String i;
    private SharedPreferences preferences;

    @Override
    public void onReceive(final Context context, Intent intent) {
        preferences = context.getSharedPreferences(SP, Context.MODE_PRIVATE);
        i = preferences.getString(SI, "");
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        calls = apiInterface.getTopStories("technology", API_KEY);
        calls.enqueue(new Callback<ArticlesTop>() {
            @Override
            public void onResponse(Call<ArticlesTop> call, Response<ArticlesTop> response) {
                if (response.isSuccessful()) {
                    String str = response.body().getResults().get(0).getTitle();
                    // if ((str.equalsIgnoreCase(i))) {
                    // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    Intent i = new Intent(context, MainActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);
                    NotificationChannel channel = new NotificationChannel("12", name, importance);
                    channel.setDescription(description);
                    NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "12")
                            .setContentTitle("Section: " + response.body().getResults().get(0).getSection())
                            .setContentIntent(pendingIntent)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setAutoCancel(true)
                            .setContentText(str)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                    Notification notification = mBuilder.build();
                    notificationManager.notify(0, notification);
                    //}

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(SI, str);
                    editor.apply();
                    //}

                }
            }

            @Override
            public void onFailure(Call<ArticlesTop> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }
}