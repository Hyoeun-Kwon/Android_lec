package com.aoslec.recyclercardtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    //------------1.data 만들기
    private String[] titles = {"Monday 월","Tuesday 화","Wednesday 수", "Thursday 목", "Friday 금", "Saturday 토", "Sunday 일"};
    //title 아래 내용
    private String[] details ={"월요일 기상정보,화요일 기상정보,수요일 기상정보,목요일 기상정보,금요일 기상정보,토요일 기상정보,일요일 기상정보"};
    //image
    private int[] images = {R.drawable.w1,R.drawable.w2,R.drawable.w3,R.drawable.w4,R.drawable.w5,R.drawable.w6,R.drawable.w7};
    //---------------- data 끝

    //------------2.ViewHolder 만들기
    //class 안에 class , 제너릭으로 ViewHolder를 부름
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;
        //하나의 셀모양 세팅해주는게 뷰홀더임
        public ViewHolder(View itemView){
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemDetail = itemView.findViewById(R.id.item_detail);

            //아까 클리커블, 포커서블 해놨음
            //셀모양 눌렀을때? 인가봄
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //뭘 클릭했는지 (현재 클릭한 위치)
                    int position = getAdapterPosition();
                    //snackbar 띄울거임
                    Snackbar.make(v,details[position], Snackbar.LENGTH_LONG).setAction("",null).show();
                }
            });
        }
    }


    @Override
    //초기값 만들어주는 뷰홀더
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 뷰홀더를 어디에 올릴지
        // Parameter를 parent로 써서 그런거임! ViewGroup이라고 보면 좋다.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    //스크롤뷰 쭉 올리면 없어지는 뷰를 재사용 (갯수와 화면크기 가지고 판단함)
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        //배열이므로
        holder.itemTitle.setText(titles[position]);
        holder.itemDetail.setText(details[position]);
        holder.itemImage.setImageResource(images[position]);
    }

        //전체가 몇개야
    @Override
    public int getItemCount() {
        return titles.length;
    }
}//
