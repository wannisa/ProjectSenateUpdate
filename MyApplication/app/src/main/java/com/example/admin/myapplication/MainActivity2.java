package com.example.admin.myapplication;

/**
 * Created by Admin on 16/6/2559.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.example.admin.myapplication.adapter.ListEng;
import com.example.admin.myapplication.adapter.ListVocabularyAdapter;
import com.example.admin.myapplication.database.DatabaseHalper;
import com.example.admin.myapplication.mobel.vocabulary;

public class MainActivity2 extends Activity {
    private Switch E;
    private ListView lvocabulary;
    private ListEng adapter;
    private List<vocabulary> mVocabularyList;
    private DatabaseHalper mDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        E = (Switch) findViewById(R.id.switch2);
        E.setChecked(true);
//        E.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//
//                if(E.isChecked() == false){
//                    Intent intent1 = new Intent(MainActivity2.this , MainActivity.class);
//                    startActivity(intent1);
//                }
//                else{
//                    Intent intent1 = new Intent(MainActivity2.this , MainActivity.class);
//                    startActivity(intent1);
//                }
//            }
//        });
        E.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if(E.isChecked() == false){
                    Intent intent1 = new Intent(MainActivity2.this , MainActivity.class);
                    startActivity(intent1);
                }
                else if(E.isChecked() == true){}
            }
        });

        lvocabulary = (ListView)findViewById(R.id.listView_vocab2);
        mDBHelper = new DatabaseHalper(this);
        File database = getApplicationContext().getDatabasePath(DatabaseHalper.DBNAME);
        if(false == database.exists()){
            mDBHelper.getReadableDatabase();
            if(coppyDatabase(this)){
                Toast.makeText(this, "Coppy Database Succes",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Coppy Data Errer", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        mVocabularyList = mDBHelper.getListVocabulary2();
        adapter = new ListEng(this,mVocabularyList);
        lvocabulary.setAdapter(adapter);
        final ImageButton btn1 = (ImageButton) findViewById(R.id.search);
        // Perform action on click
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),detailactivity.class);
                startActivity(intent);
            }
        });

    }


    public boolean coppyDatabase(Context context){
        try{
            InputStream inputStream = context.getAssets().open(DatabaseHalper.DBNAME);
            String outFileName = DatabaseHalper.DBLOCATION + DatabaseHalper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while((length = inputStream.read(buff))>0){
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.v("MainActivity2","DB copied");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
