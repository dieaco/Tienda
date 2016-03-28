package com.ciego.tienda;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.renderscript.Script;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Ciego on 27/12/2014.
 */
public class BroadcastSelectStore extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("TAG", "en onReceive");
        Toast.makeText(context, "En onReceive", Toast.LENGTH_SHORT).show();

        notification(context);

    }

    private void  notification(Context context){
        Intent intent = new Intent(context, Store.class);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 123, intent, 0);

        PendingIntent pendingIntentCancel =
                PendingIntent.getActivity(context, 123, new Intent(), 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.badge_newbie);
        builder.setTicker("Ya seleccionaste la tienda");
        builder.setWhen(System.currentTimeMillis());
        builder.setContentTitle("Store");
        builder.setContentText("La tienda number one =)");
        builder.setContentInfo("Tienda");
        builder.setContentIntent(pendingIntent);
        builder.setDeleteIntent(pendingIntentCancel);

        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        /////
        NotificationManager notificationManager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(12, notification);
    }

    private void  notificationWithList(Context context){
        Intent intent = new Intent(context, Store.class);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 123, intent, 0);

        PendingIntent pendingIntentCancel =
                PendingIntent.getActivity(context, 123, new Intent(), 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.badge_newbie);
        builder.setTicker("Ya seleccionaste la tienda");
        builder.setWhen(System.currentTimeMillis());
        builder.setContentTitle("Store");
        builder.setContentText("La tienda number one =)");
        builder.setContentInfo("Tienda");

        NotificationCompat.InboxStyle notification = new NotificationCompat.InboxStyle(builder);
        notification.addLine("Correo 1");
        notification.addLine("Correo 2");
        notification.addLine("Correo 3");


    }

    private void  notificationWithBigImage(Context context){
        Intent intent = new Intent(context, Store.class);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 123, intent, 0);

        PendingIntent pendingIntentCancel =
                PendingIntent.getActivity(context, 123, new Intent(), 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.badge_newbie);
        builder.setTicker("Ya seleccionaste la tienda");
        builder.setWhen(System.currentTimeMillis());
        builder.setContentTitle("Store");
        builder.setContentText("La tienda number one =)");
        builder.setContentInfo("Tienda");

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.badge_squeaky_clean);

        NotificationCompat.BigPictureStyle notification = new NotificationCompat.BigPictureStyle(builder);
        notification.bigPicture(bitmap);
        notification.bigLargeIcon(bitmap);
        notification.setBigContentTitle("Notification title");
        notification.setSummaryText("Resúmen de la Notificación");
    }

}
