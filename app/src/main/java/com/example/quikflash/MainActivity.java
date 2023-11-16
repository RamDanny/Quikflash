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
import android.widget.LinearLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.modifyDeck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ModifyActivity.class);
                startActivity(i);
            }
        });


        NotificationChannel channel = new NotificationChannel("Notify", "Chn name", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

        LinearLayout enableReminderButton = findViewById(R.id.notify);

        enableReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NotifyActivity.class);
                startActivity(i);
            }
        });
    }



    public void onStudyClick(View view) {
        Intent intent = new Intent(this, StudyActivity.class);
        startActivity(intent);

    }

    public void onAddClick(View view) {
        Intent intent = new Intent(this, InsertActivity.class);
        startActivity(intent);

    }


}