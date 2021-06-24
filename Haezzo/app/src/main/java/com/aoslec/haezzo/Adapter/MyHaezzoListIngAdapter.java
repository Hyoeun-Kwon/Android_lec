package com.aoslec.haezzo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aoslec.haezzo.Bean.MyHaezzoListBean;
import com.aoslec.haezzo.LoginActivity.KakaoLoginActivity;
import com.aoslec.haezzo.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyHaezzoListIngAdapter extends RecyclerView.Adapter<MyHaezzoListIngAdapter.ViewHolder>{

    private Context mContext = null;
    private int layout =0;
    private ArrayList<MyHaezzoListBean> data =null;
    private LayoutInflater inflater = null ;



    //IP
    public static String macIP = "192.168.36.130";

    Integer strdimage = null;

    //생성자
    public MyHaezzoListIngAdapter(Context mContext, int layout, ArrayList<MyHaezzoListBean> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
    }


    //------------2.ViewHolder 만들기
    //class 안에 class , 제너릭으로 ViewHolder를 부름
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView myhaezzoList_tvDproduct,myhaezzoList_tvUnickname,myhaezzoList_tvDdate,myhaezzoList_tvDplace,
                myhaezzoList_tvDstatus,myhaezzoList_tvDmoney;
        public ImageView myhaezzoList_ivDimg;



        public ViewHolder(View itemView) {
            super(itemView);
            myhaezzoList_ivDimg = itemView.findViewById(R.id.myhaezzoList_ivDimg);
            myhaezzoList_tvDproduct = itemView.findViewById(R.id.myhaezzoList_tvDproduct);
            myhaezzoList_tvUnickname = itemView.findViewById(R.id.myhaezzoList_tvUnickname);
            myhaezzoList_tvDdate = itemView.findViewById(R.id.myhaezzoList_tvDdate);
            myhaezzoList_tvDplace = itemView.findViewById(R.id.myhaezzoList_tvDplace);
            myhaezzoList_tvDstatus = itemView.findViewById(R.id.myhaezzoList_tvDstatus);
            myhaezzoList_tvDmoney = itemView.findViewById(R.id.myhaezzoList_tvDmoney);




            // ----> 화면 클릭시 넘어가기
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickposition = getAdapterPosition();
                    if (clickposition != RecyclerView.NO_POSITION){
                        if (mListener !=null){
                            mListener.onItemClick(v, clickposition);
                        }
                    }
                }
            }); // setOnClickListener


            //dnumber..?

//            //아까 클리커블, 포커서블 해놨음
//            //셀모양 눌렀을때? 인가봄
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //뭘 클릭했는지 (현재 클릭한 위치)
//                    int position = getAdapterPosition();
//                    //snackbar 띄울거임
//                    Snackbar.make(v,data.get(position).getDnumber(), Snackbar.LENGTH_LONG).setAction("이용 내역입니다.",null).show();
//                }
//            });
        }
    }


    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.myhaezzolist_custom_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyHaezzoListIngAdapter.ViewHolder holder, int position) {
        holder.myhaezzoList_tvDproduct.setText(data.get(position).getDproduct());
        holder.myhaezzoList_tvUnickname.setText(data.get(position).getUnickname());
        holder.myhaezzoList_tvDdate.setText(data.get(position).getDdate());
        holder.myhaezzoList_tvDplace.setText(data.get(position).getDplace());
        holder.myhaezzoList_tvDstatus.setText(data.get(position).getDstatus());
        holder.myhaezzoList_tvDmoney.setText(data.get(position).getDmoney());

        Glide.with(mContext)
                .load(KakaoLoginActivity.urlAddr + data.get(position).getDimage())
                .into(holder.myhaezzoList_ivDimg);

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

}//----
