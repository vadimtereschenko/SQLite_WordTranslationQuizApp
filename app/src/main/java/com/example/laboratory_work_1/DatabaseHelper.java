package com.example.laboratory_work_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Laboratory_work_1";
    private static final String TABLE_NAME = "Dictionary";
    private static final String COLUMN_WORD_ID ="wordID";
    private static final String COLUMN_WORD ="word";
    private static final String COLUMN_TRANSLATION = "translation";


    /* Конструктор суперкласса */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_WORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_WORD + " TEXT NOT NULL UNIQUE,"
                + COLUMN_TRANSLATION + " TEXT NOT NULL UNIQUE" + ")";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addWord(String word, String translation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_WORD, word);
        values.put(COLUMN_TRANSLATION, translation);
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public ArrayList<CoupleWords> getAllWords() {

        ArrayList<CoupleWords> wordsList = new ArrayList<CoupleWords>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CoupleWords words = new CoupleWords();
                words.setWordID(Integer.parseInt(cursor.getString(0)));
                words.setWord(cursor.getString(1));
                words.setTranslation(cursor.getString(2));
                wordsList.add(words);
            } while (cursor.moveToNext());
        }

        return wordsList;
    }
}
