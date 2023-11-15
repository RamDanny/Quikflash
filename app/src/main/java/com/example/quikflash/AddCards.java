package com.example.quikflash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddCards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cards);


        Intent i = getIntent();

        int deck_id = i.getIntExtra("id",999);

        DBHelper db = new DBHelper(this);
        Cursor cards = db.get_card(deck_id);

        while(cards.moveToNext())
        {

            String question = cards.getString(1);
            String answer = cards.getString(2);

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            TextView textView = new TextView(this);
            textView.setText(question);
            TextView textView1 = new TextView(this);
            textView.setGravity(Gravity.CENTER);
            textView1.setText(answer);
            textView1.setGravity(Gravity.CENTER);
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
            textView.setTextSize(32);
            textView1.setTypeface(textView1.getTypeface(), Typeface.BOLD);
            textView1.setTextSize(32);

            linearLayout.addView(textView);
            linearLayout.addView(textView1);
            linearLayout.setGravity(Gravity.CENTER);
            LinearLayout view = findViewById(R.id.listcards);
            view.addView(linearLayout);

        }

        EditText ques = findViewById(R.id.ques);
        EditText ans = findViewById(R.id.ans);
        EditText category = findViewById(R.id.category);

        Button add = findViewById(R.id.addbtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(getApplicationContext());
                Boolean check_add=  db.add_card( ques.getText().toString(), ans.getText().toString(), category.getText().toString(),deck_id );
                if(check_add == true)
                {
                    Toast.makeText(getApplicationContext(),"Added card!",Toast.LENGTH_SHORT).show();
                }

                startActivity(new Intent(getApplicationContext(), AddCards.class));
            }
        });






    }
}