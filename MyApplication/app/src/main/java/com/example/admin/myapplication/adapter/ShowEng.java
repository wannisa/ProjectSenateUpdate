package com.example.admin.myapplication.adapter;

/**
 * Created by Admin on 15/6/2559.
 */
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.myapplication.MainActivity;
import com.example.admin.myapplication.R;
import com.example.admin.myapplication.database.DatabaseHalper;
import com.example.admin.myapplication.detailactivity;
import com.example.admin.myapplication.mobel.vocabulary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 8/6/2559.
 */
public class ShowEng extends BaseAdapter {
    //list ในการเก็บข้อมูลของ MemberData
    private ArrayList<vocabulary> listData = new ArrayList<vocabulary>();

    //ตัวจัดการฐานข้อมูล
    private DatabaseHalper dbHelper;
    private SQLiteDatabase database;
    public Context mContext;
    private List<vocabulary> mVocabularyList;

    public ShowEng(Context mContext, List<vocabulary> mVocabularyList) {
        this.mContext = mContext;
        this.mVocabularyList = mVocabularyList;
    }

    public void registerDataSetObserver(DataSetObserver observer) {

    }


    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    //Method ดึงข้อมูลจาก SQLite
    public int getCount() {
        return mVocabularyList.size();
    }


    public Object getItem(int position) {
        return mVocabularyList.get(position);
    }


    public long getItemId(int position) {
        return mVocabularyList.get(position).getId();
    }


    public boolean hasStableIds() {
        return false;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.itemp2_listview, null);
        TextView ENG = (TextView) v.findViewById(R.id.thai);
        ENG.setText(mVocabularyList.get(position).getEngliesh());
        return v;

    }
}
