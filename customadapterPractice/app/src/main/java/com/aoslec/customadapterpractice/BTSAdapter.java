package com.aoslec.customadapterpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BTSAdapter extends BaseAdapter {

    //field
    private Context mContext = null;
    private int layout = 0;
    private ArrayList<BTS> data = null;
    private LayoutInflater inflater = null;

    //생성자

    public BTSAdapter(Context mContext, int layout, ArrayList<BTS> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // 자동 생성
    @Override
    public int getCount() {
       //data size
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        //item?
        return data.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(this.layout, parent, false);

        }
        //연
        TextView tv_btsName = convertView.findViewById(R.id.tv_btsName);
        ImageView iv_face = convertView.findViewById(R.id.iv_face);
        TextView tv_age = convertView.findViewById(R.id.tv_age);

        //data 넣기
        tv_btsName.setText(data.get(position).getName()+" ");
        iv_face.setImageResource(data.get(position).getIcon());
        tv_age.setText(data.get(position).getAge());

        if(position % 2 == 1) {
            convertView.setBackgroundColor(0x50D1B2FF);

        }else{
            convertView.setBackgroundColor(0x20D1B2FF);
        }

        return convertView;


    }//
}//
