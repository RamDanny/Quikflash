package com.example.quikflash;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        EditText dname = (EditText)findViewById(R.id.addDName);

        Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dnametxt = dname.getText().toString();
                DBHelper_d db = new DBHelper_d(getApplicationContext());
                Boolean check = db.insert(dnametxt);

                if(check){
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);
           }

        }

        });
    }




}