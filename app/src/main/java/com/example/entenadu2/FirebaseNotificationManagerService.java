package com.example.entenadu2;

import android.app.NotificationManager;

import com.example.entenadu2.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class FirebaseNotificationManagerService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Show_notifications(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

    }
    public void Show_notifications(String tittle,String msg)
    {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"Mynotifications")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(tittle)
                .setContentText(msg)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(999,builder.build());
    }
}
