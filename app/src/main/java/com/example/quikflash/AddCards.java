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

            // Question
            TextView questionView = new TextView(this);
            questionView.setText(question);
            questionView.setGravity(Gravity.CENTER);
            questionView.setTextColor(Color.BLACK);
            questionView.setTypeface(questionView.getTypeface(), Typeface.BOLD);
            questionView.setTextSize(14);

            // Seperator
            TextView seperatorView = new TextView(this);
            seperatorView.setText(" - ");
            seperatorView.setGravity(Gravity.CENTER);
            seperatorView.setTextColor(Color.BLACK);
            seperatorView.setTypeface(seperatorView.getTypeface(), Typeface.BOLD);
            seperatorView.setTextSize(14);

            // Answer
            TextView answerView = new TextView(this);
            answerView.setText(answer);
            answerView.setGravity(Gravity.CENTER);
            answerView.setTextColor(Color.BLACK);
            answerView.setTypeface(answerView.getTypeface(), Typeface.BOLD);
            answerView.setTextSize(14);

            linearLayout.addView(questionView);
            linearLayout.addView(seperatorView);
            linearLayout.addView(answerView);
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

                startActivity(new Intent(getApplicationContext(), ViewCards.class));
            }
        });

        Button back = findViewById(R.id.backToModifyBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddCards.this, ModifyActivity.class);
                startActivity(i);
            }
        });

    }
}