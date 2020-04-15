package com.example.entenadu2;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationManage {
    String tittle;
    String content;
    Context context;
    Class noteClass;
    private String CHANEL_ID = "simple_notification";
    private final int NOTIFICATION_ID = 01;
    public NotificationManage(String tittle, String content, Context context) {
        this.tittle = tittle;
        this.content = content;
        this.context = context;
        this.noteClass=noteClass;
            NotificationCompat.Builder builder=new NotificationCompat.Builder(context,"Mynotifications")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(tittle)
                    .setContentText(content)
                    .setAutoCancel(true);
            NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
            notificationManagerCompat.notify(999,builder.build());
    }

    public NotificationManage() {
        //constructer needed
    }
}
