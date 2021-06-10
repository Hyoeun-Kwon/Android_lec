package com.aoslec.networkjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MemberAdapter extends BaseAdapter {
    //1. extends BaseAdapter

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<JsonMember> data = null;
    private LayoutInflater inflater = null;


    //constructor
    public MemberAdapter(Context mContext, int layout, ArrayList<JsonMember> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        //inflater를 컨스트럭터 할때 안잡는 이유는 타입때문임
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getId();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //custom_layout의 이름을 알고있는 애가 됨 (아래 명령어를 통해)
        convertView = inflater.inflate(this.layout, parent, false);
        //셀 하나 연결
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_age = convertView.findViewById(R.id.tv_age);
        TextView tv_hobbies = convertView.findViewById(R.id.tv_hobbies);
        TextView tv_no = convertView.findViewById(R.id.tv_no);
        TextView tv_id = convertView.findViewById(R.id.tv_id);

        //여기에 어레이리스트에 있는거 넣어주기
        tv_name.setText("Name :" + data.get(position).getName());
        tv_age.setText("Age :" + data.get(position).getAge());
        tv_hobbies.setText("Hobby :" + data.get(position).getHobbies());
        tv_no.setText("No :" + data.get(position).getNo());
        tv_id.setText("ID :" + data.get(position).getId());

        //색상 주기
        if((position % 2) == 1){
            convertView.setBackgroundColor(0x5000ff00);
        }else{
            convertView.setBackgroundColor(0x500000ff);
        }
        return convertView;
    }

}//End...
