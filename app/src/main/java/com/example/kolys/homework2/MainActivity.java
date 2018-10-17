package com.example.kolys.homework2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView tv_chooesen_time;
    public EditText time_et;
    public Button start_btn;
    public Button stop_btn;
    public AlarmManager alarmManager;
    public BroadcastReceiver mReceiver;
    public PendingIntent pendingIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_chooesen_time = findViewById(R.id.tv_choosen_time);
        time_et = findViewById(R.id.et_time_in_sec);
        start_btn = findViewById(R.id.btn_start);
        stop_btn = findViewById(R.id.btn_stop);
        start_btn.setOnClickListener(this);
        stop_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, TimeNotificationReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);

        switch (v.getId()) {
            case R.id.btn_start:
                if (!time_et.getText().toString().isEmpty()) {
                    tv_chooesen_time.setText(time_et.getText().toString());
                    int time = Integer.parseInt(time_et.getText().toString());
                    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + time * 1000, pendingIntent);
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

}