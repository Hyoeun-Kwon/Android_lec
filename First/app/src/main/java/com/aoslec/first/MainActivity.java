package com.aoslec.first;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
                .setSmallIcon(R.drawable.cat)
                .setContentTitle("알림제목")
                .setContentText("알림 내용 !! ")
                //진동으로 알림 오기 소리를 원하면 : default_sound
                .setDefaults(Notification.DEFAULT_VIBRATE)
                //우선순위
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                //푸시 알림창을 사용자가 터치했을 때 자동으로 사라지는 가에 대한 내용
                //true면 사라지고, false면 유지
                .setAutoCancel(true);

              //  .setContentIntent(mPendingIntent);

        //알림 보내기
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify(0,mBuilder.build());

    };//



}