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
    PendingIntent pendingIntent;
    TimePicker timePicker;
    ToggleButton toggleBtn;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        timePicker = findViewById(R.id.timePicker);
        home = findViewById(R.id.notificationReturnHome);
        toggleBtn = findViewById(R.id.toggleNotification);

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
        long time;
        if (isChecked == true) {
            /*Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());

            Intent intent = new Intent(this, Notifier.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_MUTABLE);

            time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
            if (System.currentTimeMillis() > time) {
                if (Calendar.AM_PM == 0)
                    time = time + (1000 * 60 * 60 * 12);
                else
                    time = time + (1000 * 60 * 60 * 24);
            }

            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (time * 1000), pendingIntent);
            Toast.makeText(NotifyActivity.this,"Notification Set",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(NotifyActivity.this,"Notification Cancelled",Toast.LENGTH_SHORT).show();
            alarmManager.cancel(pendingIntent);
        }*/
            Calendar alarmTime = Calendar.getInstance();
            alarmTime.set(Calendar.HOUR_OF_DAY, 12);
            alarmTime.set(Calendar.MINUTE, 42);
            alarmTime.set(Calendar.SECOND, 0);

            Timer timer = new Timer();

            // Schedule the alarm to run every day at the specified time
            timer.scheduleAtFixedRate(new AlarmTask(), calculateDelay(alarmTime.getTime()), 24 * 60 * 60 * 1000);

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