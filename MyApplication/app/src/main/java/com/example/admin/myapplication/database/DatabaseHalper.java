package com.example.admin.myapplication.database;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.mobel.vocabulary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Admin on 10/6/2559.
 */
public class DatabaseHalper extends SQLiteOpenHelper{
    public static final String DBNAME = "vocab.db";
    public static final String DBLOCATION = "/data/data/com.example.admin.myapplication/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabate;

    public DatabaseHalper(Context context){
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void OpenDatabase(){
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabate != null && mDatabate.isOpen()){
            return;
        }
        mDatabate = SQLiteDatabase.openDatabase(dbPath, null ,SQLiteDatabase.OPEN_READONLY);
    }
    public void closeDatabase(){
        if(mDatabate != null) {
            mDatabate.close();
        }
    }

        public List<vocabulary> getListVocabulary(){
            vocabulary vocabulary = null;
            List<vocabulary> vocabularyList = new ArrayList<>();
            OpenDatabase();
            Cursor cursor = mDatabate.rawQuery("SELECT * FROM VOCABULARY ORDER BY THAI ASC", null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                vocabulary = new vocabulary(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                vocabularyList.add(vocabulary);
                cursor.moveToNext();
            }
            cursor.close();
            closeDatabase();
            return vocabularyList;
        }
    public List<vocabulary> getListVocabulary2(){
        vocabulary vocabulary = null;
        List<vocabulary> vocabularyList = new ArrayList<>();
        OpenDatabase();
        Cursor cursor = mDatabate.rawQuery("SELECT * FROM VOCABULARY ORDER BY ENGLISH ASC", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            vocabulary = new vocabulary(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            vocabularyList.add(vocabulary);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return vocabularyList;
    }

    public List<vocabulary> showdetail(int id, String ENG, String THAI){
        int ID = id;
        vocabulary vocabulary = null;
        List<vocabulary> vocabularyList = new ArrayList<>();
        OpenDatabase();
        Cursor cursor = mDatabate.rawQuery("SELECT * FROM VOCABULARY"
                + " WHERE ID = "+ ID , null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            vocabulary = new vocabulary(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            vocabularyList.add(vocabulary);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return vocabularyList;
    }
    public List<vocabulary> SearchEditText(String d){

        String DATA = d;

        vocabulary vocabulary = null;
        List<vocabulary> vocabularyList = new ArrayList<>();
        OpenDatabase();
        Cursor cursor = mDatabate.rawQuery("SELECT * FROM VOCABULARY"
                + " WHERE  thai like'"+ DATA +"%' OR english like '"+ DATA +"%'ORDER BY thai AND english ASC", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            vocabulary = new vocabulary(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            vocabularyList.add(vocabulary);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return vocabularyList;
    }
}

