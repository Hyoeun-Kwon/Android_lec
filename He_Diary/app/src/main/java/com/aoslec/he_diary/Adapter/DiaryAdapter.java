package com.aoslec.he_diary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.aoslec.he_diary.Bean.Diary;
import com.aoslec.he_diary.R;

import java.util.ArrayList;

public class DiaryAdapter extends BaseAdapter {

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<Diary> data = null;
    private LayoutInflater inflater = null;


    public DiaryAdapter(Context mContext, int layout, ArrayList<Diary> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    // # # # # # 중요 # # # # #
    @Override
    public Object getItem(int position) {
        return data.get(position).getDate();
    }

    @Override
    public long getItemId(int position) {
        // 여기서 주는게 진짜 position -> getItem ( position ) 으로 들어감.
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(this.layout, parent, false);

        TextView tv_date = convertView.findViewById(R.id.tv_diary_date);
        EditText edt_diary_title = convertView.findViewById(R.id.edt_diary_title);
        EditText edt_diary_detail = convertView.findViewById(R.id.edt_diary_detail);

        return convertView;
    }





}
