package com.example.quikflash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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