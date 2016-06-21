package com.example.admin.myapplication.adapter;

/**
 * Created by Admin on 16/6/2559.
 */
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.detailactivity;
import com.example.admin.myapplication.mobel.vocabulary;

import java.util.List;

/**
 * Created by Admin on 10/6/2559.
 */
public class ListEng extends BaseAdapter{
    private Context mContext;
    private List<vocabulary> mVocabularyList;
    public ListEng(Context mContext, List<vocabulary> mVocabularyList) {
        this.mContext = mContext;
        this.mVocabularyList = mVocabularyList;
    }

    @Override
    public int getCount() {
        return mVocabularyList.size();
    }

    @Override
    public Object getItem(int position) {
        return mVocabularyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mVocabularyList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.itemp_listview, null);
        TextView thai = (TextView)v.findViewById(R.id.thai);
        TextView eng = (TextView)v.findViewById(R.id.eng);

        final Integer id = (mVocabularyList.get(position).getId());
        final String sd = String.valueOf(id);
        final String Thai = (mVocabularyList.get(position).getThai());
        final String Eng = (mVocabularyList.get(position).getEngliesh());

        eng.setText(mVocabularyList.get(position).getThai());
        thai.setText(mVocabularyList.get(position).getEngliesh());
        ImageButton clickaction = (ImageButton)v.findViewById(R.id.clickaction);
        clickaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext.getApplicationContext(), detailactivity.class);
                intent.putExtra("ID", sd);
                intent.putExtra("THAI", Thai);
                intent.putExtra("ENGLIESH", Eng);
                mContext.startActivity(intent);
            }
        });
        return v;
    }

}
