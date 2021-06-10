package com.aoslec.networkjson_student_recycle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {
    //1. extends RecycleAdapter
    //2. 정의 ( context, layout, Bean, inflater
    //private Context mContext = null;
    //private int layout = 0;
    private Context mContext = null;
    private int layout = 0;
    private ArrayList<JsonMember> data;
    private LayoutInflater inflater = null;

    //3.inflater를 제외하고 컨스트럭터 만들어주기
    public MemberAdapter(Context mContext, int layout, ArrayList<JsonMember> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        //4.인플레이터 추가
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //------------2.ViewHolder 만들기
    //class 안에 class , 제너릭으로 ViewHolder를 부름
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_code,tv_name,tv_dept,tv_phone;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_code = itemView.findViewById(R.id.tv_code);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_dept = itemView.findViewById(R.id.tv_dept);
            tv_phone = itemView.findViewById(R.id.tv_phone);

            //아까 클리커블, 포커서블 해놨음
            //셀모양 눌렀을때? 인가봄
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //뭘 클릭했는지 (현재 클릭한 위치)
                    int position = getAdapterPosition();
                    //snackbar 띄울거임
                    Snackbar.make(v,data.get(position).getCode(), Snackbar.LENGTH_LONG).setAction("",null).show();
                }
            });
        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder( MemberAdapter.ViewHolder holder, int position) {
        //배열이므로
        holder.tv_code.setText(data.get(position).getCode());

        holder.tv_name.setText(data.get(position).getName());
        holder.tv_dept.setText(data.get(position).getDept());
        holder.tv_phone.setText(data.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }



}//
