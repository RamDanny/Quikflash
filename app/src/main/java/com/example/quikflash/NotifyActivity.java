package com.example.quikflash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class NotifyActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    TimePicker timePicker;
    ToggleButton toggleBtn;
    Button home;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        timePicker = findViewById(R.id.timePicker);
        home = findViewById(R.id.notificationReturnHome);
        toggleBtn = findViewById(R.id.toggleNotification);
        timer = new Timer();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NotifyActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        toggleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setNotification(isChecked);
            }
        });

    }

    public void setNotification(boolean isChecked) {
        if (isChecked == true) {
            Calendar alarmTime = Calendar.getInstance();
            alarmTime.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
            alarmTime.set(Calendar.MINUTE, timePicker.getMinute());
            alarmTime.set(Calendar.SECOND, 0);



            timer.scheduleAtFixedRate(new AlarmTask(), calculateDelay(alarmTime.getTime()), 24 * 60 * 60 * 1000);
            Toast.makeText(NotifyActivity.this,"Notification Set",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(NotifyActivity.this,"Notification Cancelled",Toast.LENGTH_SHORT).show();
            timer.cancel();
        }
    }

    private static long calculateDelay(Date alarmTime) {
        // Calculate the delay until the first alarm trigger
        long currentTime = System.currentTimeMillis();
        long alarmTimeMillis = alarmTime.getTime();

        // If the specified time has already passed for today, schedule it for the same time tomorrow
        if (currentTime > alarmTimeMillis) {
            alarmTimeMillis += 24 * 60 * 60 * 1000;
        }

        return alarmTimeMillis - currentTime;
    }
    class AlarmTask extends TimerTask {
        //@Override
        public void run() {
            // This method will be called when the alarm triggers
            System.out.println("Alarm triggered at: " + new Date());
            // Add your alarm logic here

            NotificationChannel channel = new NotificationChannel("Notify", "Chn name", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(NotifyActivity.this, "Notify");
            builder.setContentTitle("Studyyy!!");
            builder.setContentText("pls dont ignore!");
            builder.setSmallIcon(R.drawable.ic_notification);
            builder.setAutoCancel(true);

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(NotifyActivity.this);
            if (ActivityCompat.checkSelfPermission(NotifyActivity.this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            managerCompat.notify(1, builder.build());

        }
    }
}