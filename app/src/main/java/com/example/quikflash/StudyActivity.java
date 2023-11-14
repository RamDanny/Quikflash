package com.example.quikflash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class StudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        DBHelper db = new DBHelper(this);
        Cursor decks = db.get_decks();

        int count = 0;
        while(decks.moveToNext()){
            Integer id = decks.getInt(0);
            String name = decks.getString(1);

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            TextView textView = new TextView(this);
            textView.setText(name);
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
            textView.setTextSize(32);


            //Delete Button
            Button button = new Button(this);
            button.setText("Delete");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper db = new DBHelper(getApplicationContext());
                    db.delete_deck(id.toString());
                    finish();
                    startActivity(new Intent(getApplicationContext(), StudyActivity.class));
                }
            });

            linearLayout.addView(textView);
            linearLayout.addView(button);
            linearLayout.setGravity(Gravity.CENTER);

            LinearLayout view = findViewById(R.id.scrollView_layout);
            view.addView(linearLayout);

            count++;
        }
    }
}