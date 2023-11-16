package com.example.quikflash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinishedActivity extends AppCompatActivity {

    TextView correctView;
    TextView wrongView;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);

        Intent i = getIntent();
        Integer correct = i.getIntExtra("correct",0);
        Integer wrong = i.getIntExtra("wrong",0);


        correctView = findViewById(R.id.correctCount);
        wrongView = findViewById(R.id.wrongCount);


        correctView.setText(correct.toString());
        wrongView.setText(wrong.toString());

        home = findViewById(R.id.finishedActivityReturnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FinishedActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}