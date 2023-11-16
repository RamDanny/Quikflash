package com.example.quikflash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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
            //name  =  name + " - ";

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            Button button = new Button(this);
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(20, 20, 20, 20);
            button.setLayoutParams(params);
            button.setText(name);
            button.setTextSize(28);
            button.setTextColor(Color.BLACK);
            button.setBackgroundColor(Color.rgb(32,152,174)) ;
            //setContentView(button, params);

            /*TextView textView = new TextView(this);
            textView.setText(name);
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(28);




            //Study Button
            Button button = new Button(this);
            button.setText("Study");*/
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent i = new Intent(StudyActivity.this, ViewCards.class);
                   i.putExtra("id",id);
                   startActivity(i);
                }
            });

            //linearLayout.addView(textView);
            linearLayout.addView(button);
            linearLayout.setGravity(Gravity.CENTER);

            LinearLayout view = findViewById(R.id.scrollView_layout);
            view.addView(linearLayout);

            count++;
        }

        if(count == 0){
            TextView textView = new TextView(this);
            textView.setText("Add Decks");
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(28);

            LinearLayout view = findViewById(R.id.scrollView_layout);
            view.addView(textView);
        }
    }
}