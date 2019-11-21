package com.project.notification_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_notify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel =
                    new NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("channel description");
            notificationChannel.enableLights(true); notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        btn_notify = findViewById(R.id.btn_notify);
        btn_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap mLargeIconForNotify = BitmapFactory.decodeResource(getResources(), R.drawable.image0);

                PendingIntent mPendingIntent = PendingIntent.getActivity(MainActivity.this, 0,
                        new Intent(getApplicationContext(), MainActivity.class),
                        PendingIntent.FLAG_CANCEL_CURRENT);

                Notification.Builder mBuilder = new Notification.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.image0)
                        .setContentTitle("알림!")
                        .setContentText("Notification On")
                        .setDefaults(Notification.DEFAULT_VIBRATE)
                        .setLargeIcon(mLargeIconForNotify)
                        .setPriority(Notification.PRIORITY_DEFAULT)
                        .setAutoCancel(true)
                        .setContentIntent(mPendingIntent);

                NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotificationManager.notify(0, mBuilder.build());
            }
        });

    }
}