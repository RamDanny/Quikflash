package com.example.quikflash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);


        DBHelper db = new DBHelper(getApplicationContext());
        Boolean check_insert = db.insert_deck("test");

        if(check_insert == true)
        {
            Intent j = new Intent(InsertActivity.this, MainActivity.class);
            startActivity(j);
        }
    }


}