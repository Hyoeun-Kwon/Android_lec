package com.aoslec.haezzo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aoslec.haezzo.Bean.Students;
import com.aoslec.haezzo.R;

import java.util.ArrayList;

public class StudentsAdapter extends BaseAdapter {
    private Context mContext = null;
    private  int layout = 0;
    private ArrayList<Students> data = null;
    private LayoutInflater inflater = null;

    public StudentsAdapter(Context mContext, int layout, ArrayList<Students> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getScode();
    }

    @Override
    public long getItemId(int position) {
        //getItemId -> getItem
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(this.layout, parent, false);
        TextView tv_code = convertView.findViewById(R.id.tv_code_list);
        TextView tv_name = convertView.findViewById(R.id.tv_name_list);
        TextView tv_dept = convertView.findViewById(R.id.tv_dept_list);
        TextView tv_phone = convertView.findViewById(R.id.tv_phone_list);

        tv_code.setText("학번 : "+ data.get(position).getScode());
        tv_name.setText("이름 : "+ data.get(position).getSname());
        tv_dept.setText("전공 : "+ data.get(position).getSdept());
        tv_phone.setText("전화번호 : "+ data.get(position).getSphone());

        return convertView;
    }
}
