package com.example.kolys.homework2;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView chooesen_time_tv;
    public EditText time_et;
    public Button start_btn;
    public Button stop_btn;
    public AlarmManager alarmManager;
    public BroadcastReceiver mReceiver;
    public PendingIntent pendingIntent;
    public final String CHANNEL_ID = "notify chanel";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chooesen_time_tv = findViewById(R.id.tv_choosen_time);
        time_et = findViewById(R.id.et_time_in_sec);
        start_btn = findViewById(R.id.btn_start);
        stop_btn = findViewById(R.id.btn_stop);
        start_btn.setOnClickListener(this);
        stop_btn.setOnClickListener(this);
        RegisterAlarmBroadcast(this);
        CreateNotificationChannel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                if (!time_et.getText().toString().isEmpty()) {
                    chooesen_time_tv.setText(time_et.getText().toString());
                    int time = Integer.parseInt(time_et.getText().toString());
                    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + time, pendingIntent);
                } else {
                    Toast.makeText(this, "Enter time!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_stop:
                alarmManager.cancel(pendingIntent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    private void RegisterAlarmBroadcast(Context context) {
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Alarm time has been reached", Toast.LENGTH_LONG).show();
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(context, CHANNEL_ID)
                                .setSmallIcon(R.drawable.notification_icon)
                                .setContentTitle("Notification")
                                .setContentText("Wake up, Grab a brash and put a little makeup!");
                Intent intentTL = new Intent(context, WakeUpActivity.class);
                intentTL.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                PendingIntent pIntent = PendingIntent.getActivity(
                        context,
                        0,
                        intentTL,
                        PendingIntent.FLAG_CANCEL_CURRENT);
                builder.setContentIntent(pIntent);
                Notification notification = builder.build();
                notificationManager.notify(1, notification);
            }
        };
        registerReceiver(mReceiver, new IntentFilter("sample"));
        pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent("sample"), 0);
        alarmManager = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));
    }

    private void CreateNotificationChannel() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("My channel description");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
