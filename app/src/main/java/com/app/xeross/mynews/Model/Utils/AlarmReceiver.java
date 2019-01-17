package com.app.xeross.mynews.Model.Utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.app.xeross.mynews.Controller.Activity.MainActivity;

import static com.app.xeross.mynews.Model.Utils.Constants.NOTIFICATION_ID;

/**
 * Created by XeroSs on 13/01/2019.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            Intent i = new Intent(context, MainActivity.class);
            CharSequence name = "Notification Title";
            String description = "Notification Description";
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);
            NotificationChannel channel = new NotificationChannel("12", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "12")
                    .setContentTitle("Section: ")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(android.R.drawable.alert_dark_frame)
                    .setAutoCancel(true)
                    .setContentText("123")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            Notification notification = mBuilder.build();
            notificationManager.notify(NOTIFICATION_ID, notification);
        }

    }
}
