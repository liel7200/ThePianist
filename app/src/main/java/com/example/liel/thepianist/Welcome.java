package com.example.liel.thepianist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Welcome extends AppCompatActivity
{
    private static int SlashTimeOut=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        notification();
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent moveMain= new Intent(Welcome.this, MainActivity.class);
                startActivity(moveMain);
                finish();
            }
        }, SlashTimeOut);
    }
    public void notification()
    {
        int icon = R.drawable.note_icon;
        long when = System.currentTimeMillis();
        String title = "Lets play!";
        String ticker = "ticker";
        //phase 2
        Intent intent = new Intent(Welcome.this, Welcome.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(Welcome.this, 0,intent , 0);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //phase 3
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        Notification notification = builder.setContentIntent(pendingIntent)
                .setSmallIcon(icon).setTicker(ticker).setWhen(when)
                .setAutoCancel(true).setContentTitle(title).
                        setSmallIcon(R.drawable.note_icon)
                .setContentText("you can learn and play for fun!").build();
        notificationManager.notify(1, notification);
    }
}
