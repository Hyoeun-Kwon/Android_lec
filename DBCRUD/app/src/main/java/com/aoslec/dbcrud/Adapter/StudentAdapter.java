package com.aoslec.dbcrud.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aoslec.dbcrud.Bean.Student;
import com.aoslec.dbcrud.R;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {
    private Context mContext = null; //어디서 불렀는지
    private int layout =0;
    private ArrayList<Student> data =null; //data
    private LayoutInflater inflater = null ;

    //생성자
    public StudentAdapter(Context mContext, int layout, ArrayList<Student> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getCode();//빈에서 받는 코드
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(this.layout,parent,false);
        //custom layout 필요 --> 만들자
        TextView tv_code = convertView.findViewById(R.id.tv_code);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_dept = convertView.findViewById(R.id.tv_dept);
        TextView tv_phone = convertView.findViewById(R.id.tv_phone);

        //화면에 쓸것 --> 한줄 만든 것임
        tv_code.setText("학번 : " + data.get(position).getCode());
        tv_name.setText("성명 : " + data.get(position).getName());
        tv_dept.setText("전공 : " + data.get(position).getDept());
        tv_phone.setText("번호 : " + data.get(position).getPhone());


        return convertView;
    }
}
