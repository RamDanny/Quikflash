package com.example.quikflash;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.database.Cursor;

public class DBHelper_d extends SQLiteOpenHelper {
    public DBHelper_d(@Nullable Context context) {
        super(context, "Deck.db", null, 1);
    }
@Override

    public void onCreate(SQLiteDatabase db)
{

db.execSQL("");
}


}


















