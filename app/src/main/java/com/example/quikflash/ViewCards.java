package com.example.quikflash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ViewCards extends AppCompatActivity {

    Button correctBtn;
    Button wrongBtn;
    Button backBtn;
    int correct = 0;
    int wrong = 0;

    int cardID;
    int count;
    int makeCard(String question,String answer){
        LinearLayout linearLayout = new LinearLayout(ViewCards.this);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,500,0,0);

        TextView textView = new TextView(ViewCards.this);
        textView.setText(question);
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        textView.setTextSize(32);

        Button showAns = new Button(ViewCards.this);
        showAns.setText("Show Answer");

        showAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAns.setClickable(false);
                showAns.setText(answer);
            }
        });

        linearLayout.addView(textView,lp);
        linearLayout.addView(showAns);

        ConstraintLayout view = findViewById(R.id.studyCardsView);
        view.addView(linearLayout,lp);

        linearLayout.setId(LinearLayout.generateViewId());
        return linearLayout.getId();
    }

    public void nextCard(HashMap<Integer,Boolean> finished, ArrayList<String> questions, ArrayList<String> answers){

        // Remove old Question and answer
        ConstraintLayout layout = findViewById(R.id.studyCardsView);
        layout.removeView(findViewById(cardID));

        // Get new quesion and answer and add to the screen
        Random rand = new Random();
        Integer rand_int = rand.nextInt(questions.size());

        while(finished.getOrDefault(rand_int,false) == true){
            rand_int = rand.nextInt(questions.size());
        }

        count -= 1;
        finished.put(rand_int,true);
        cardID = makeCard(questions.get(rand_int),answers.get(rand_int));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cardID = -1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cards);

        Intent i = getIntent();
        int deck_id = i.getIntExtra("id",999);

        DBHelper db = new DBHelper(ViewCards.this);
        Cursor cards =  db.get_card(deck_id);

        ArrayList<String> questions = new ArrayList<>();
        ArrayList<String> answers = new ArrayList<>();
        ArrayList<String> category = new ArrayList<>();
        while(cards.moveToNext()){
            questions.add(cards.getString(1));
            answers.add(cards.getString(2));
            category.add(cards.getString(3));
        }

        count = questions.size()-1;
        HashMap<Integer, Boolean> finished = new HashMap<Integer, Boolean>();

        correctBtn = findViewById(R.id.correctBtn);
        wrongBtn = findViewById(R.id.wrongBtn);
        backBtn = findViewById(R.id.backBtn);


        correctBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correct += 1;
                // Once all the cards are over, it returns to the main menu
                if(count == -1){
                    Intent i = new Intent(ViewCards.this, FinishedActivity.class);
                    i.putExtra("correct",correct);
                    i.putExtra("wrong",wrong);
                    startActivity(i);
                    return;
                }

                nextCard(finished,questions,answers);
            }
        });

        wrongBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong +=1;
                // Once all the cards are over, it returns to the main menu
                if(count == -1){
                    Intent i = new Intent(ViewCards.this, FinishedActivity.class);
                    i.putExtra("correct",correct);
                    i.putExtra("wrong",wrong);
                    startActivity(i);
                    return;
                }

                nextCard(finished,questions,answers);
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewCards.this, MainActivity.class);
                startActivity(i);
            }
        });


        // Get the initial Card
        Random rand = new Random();
        Integer rand_int = rand.nextInt(questions.size());

        count -= 1;
        finished.put(rand_int,true);
        cardID = makeCard(questions.get(rand_int),answers.get(rand_int));
    }
}