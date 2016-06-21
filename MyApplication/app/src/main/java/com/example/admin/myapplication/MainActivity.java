package com.example.admin.myapplication;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
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

import com.example.admin.myapplication.adapter.ListVocabularyAdapter;
import com.example.admin.myapplication.adapter.ShowVocabularyAdapter;
import com.example.admin.myapplication.database.DatabaseHalper;
import com.example.admin.myapplication.mobel.vocabulary;

public class MainActivity extends Activity {
    private Switch T;
    private ImageButton put;
    private EditText Search;
    private ListView lvocabulary;
    private ListVocabularyAdapter adapter;
    private List<vocabulary> mVocabularyList;
    private DatabaseHalper mDBHelper;
//    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Search = (EditText)findViewById(R.id.search);


//        Search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(Search.length() != 0){
//                    mVocabularyList = mDBHelper.SearchEditText(data);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
            put = (ImageButton) findViewById(R.id.search_button);
        // Perform action on click

            put.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(Search!= null) {
                    Intent intent = new Intent(getApplicationContext(), Search.class);
                    intent.putExtra("Data", Search.getText().toString());
                    startActivity(intent);
                }
            }
        });


        T = (Switch) findViewById(R.id.switch1);
        T.setChecked(false);
        T.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if(T.isChecked() == true){
                    Intent intent1 = new Intent(getApplicationContext() , MainActivity2.class);
                    startActivity(intent1);
                }
                else if(T.isChecked() == false) {}
            }
        });

        lvocabulary = (ListView) findViewById(R.id.listView_vocab);
        mDBHelper = new DatabaseHalper(this);
        File database = getApplicationContext().getDatabasePath(DatabaseHalper.DBNAME);
        if (false == database.exists()) {
            mDBHelper.getReadableDatabase();
            if (coppyDatabase(this)) {
                Toast.makeText(this, "Coppy Database Succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Coppy Data Errer", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        mVocabularyList = mDBHelper.getListVocabulary();
        adapter = new ListVocabularyAdapter(this, mVocabularyList);
        lvocabulary.setAdapter(adapter);
    }

    public boolean coppyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DatabaseHalper.DBNAME);
            String outFileName = DatabaseHalper.DBLOCATION + DatabaseHalper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.v("MainActivity", "DB copied");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}


