package com.aoslec.networkjson_student_p;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MemberAdapter extends BaseAdapter {
    //1. extends BaseAdapter
    //2. 정의 ( context, layout, Bean, inflater
    private Context mContext = null;
    private int layout = 0;
    private ArrayList<JsonMember> data = null;
    private LayoutInflater inflater = null;

    //3.inflater를 제외하고 컨스트럭터 만들어주기
    public MemberAdapter(Context mContext, int layout, ArrayList<JsonMember> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        //4.인플레이터 추가
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //5. BaseAdapter를 implement
    @Override
    public int getCount() {
        return data.size(); //6.data size
    }

    @Override
    public Object getItem(int position) {
        //return data.get(position).getId;
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //7.view 작업
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //매개 변수에 따라서 layout은 이거고, 뷰는 부모를 따르고, 뷰그룹은 false
        convertView = inflater.inflate(this.layout, parent,false);

        //custom_layout으로 만들어 둔 걸 가져올거야! (커스텀 뷰는 한 셀 )
        TextView tv_code = convertView.findViewById(R.id.tv_code);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_dept = convertView.findViewById(R.id.tv_dept);
        TextView tv_phone = convertView.findViewById(R.id.tv_phone);

        //여기에 어레이리스트에 있는거 넣어주기 (진짜 bean에서 가져온걸 이 레이아웃에 넣기!)
        tv_code.setText("Code :" + data.get(position).getCode());
        tv_name.setText("Name :" + data.get(position).getName());
        tv_dept.setText("Dept :" + data.get(position).getDept());
        tv_phone.setText("Phone :" + data.get(position).getPhone());

        //색상 주기
        if((position % 2) == 1){
            convertView.setBackgroundColor(0xA4C3FF);
        }else{
            convertView.setBackgroundColor(0xF9FFFF);
        }

        return convertView;
    }//get View





}//
