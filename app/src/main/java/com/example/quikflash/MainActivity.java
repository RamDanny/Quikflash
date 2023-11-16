package com.example.quikflash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


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

        Button enableReminderButton = findViewById(R.id.reminderButton);

        enableReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "Notify");
                builder.setContentTitle("Studyyy!!");
                builder.setContentText("pls dont ignore!");
                builder.setSmallIcon(R.drawable.ic_notification);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                managerCompat.notify(1, builder.build());
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