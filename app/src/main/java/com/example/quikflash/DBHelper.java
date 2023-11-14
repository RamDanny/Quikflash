package com.example.quikflash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Random;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context,"QuikFlash.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE deck(deck_id INT PRIMARY KEY, deck_name TEXT UNIQUE)");
        db.execSQL("CREATE TABLE cards(card_id INT PRIMARY KEY,card_question TEXT, card_answer, category TEXT, card_deck_id INT, FOREIGN KEY (card_deck_id) REFERENCES deck(deck_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert_deck(String name){
        Random rand = new Random();
        int rand_int = rand.nextInt(32000);

        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("deck_id",rand_int);
        contentValue.put("deck_name",name);
        long result = db.insert("deck",null,contentValue);
        if(result == -1)
            return false;
        return true;
    }
    public Cursor get_decks(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor record = db.rawQuery("SELECT * FROM deck",null);
        return record;
    }
    public void delete_deck(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int c = db.delete("deck","deck_id=?",new String[]{id});
        if(c == -1)
                System.out.println("Error");
        else
            System.out.println("Deleted");
    }
}
