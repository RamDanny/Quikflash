package com.example.quikflash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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