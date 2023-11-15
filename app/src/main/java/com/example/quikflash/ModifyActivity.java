package com.example.quikflash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ModifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        DBHelper db = new DBHelper(this);
        Cursor decks = db.get_decks();

        int count = 0;
        while(decks.moveToNext()){
            Integer id = decks.getInt(0);
            String name = decks.getString(1);

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.weight = 1;

            TextView textView = new TextView(this);
            textView.setText(name);
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
            textView.setTextColor(Color.BLACK);
            textView.setMinWidth(500);
            textView.setTextSize(32);

            // Add cards
            Button addBtn = new Button(this);
            addBtn.setText("Add Cards");
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ModifyActivity.this,AddCards.class);
                    i.putExtra("id",id);
                    startActivity(i);
                }
            });

            //Delete Button
            Button deleteBtn = new Button(this);
            deleteBtn.setText("Delete");
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper db = new DBHelper(getApplicationContext());
                    db.delete_deck(id.toString());
                    finish();
                    startActivity(new Intent(getApplicationContext(), ModifyActivity.class));
                }
            });

            linearLayout.addView(textView,lp);
            linearLayout.addView(deleteBtn,lp);
            linearLayout.addView(addBtn,lp);
            linearLayout.setGravity(Gravity.CENTER);

            LinearLayout view = findViewById(R.id.scrollView2);
            view.addView(linearLayout);

            count++;
        }
    }
}