package com.aoslec.recyclercardtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private String[] titles = {"Monday 월", "Tuesday 화", "Wednesdat 수", "Thursday 목", "Friday 금", "Saturday 토", "Sunday 일"};
    private String[] details = {"월요일 기상정보", "화요일 기상정보", "수요일 기상정보", "목요일 기상정보", "금요일 기상정보", "토요일 기상정보", "일요일 기상정보"};
    private int[] images = {R.drawable.w1, R.drawable.w2, R.drawable.w3, R.drawable.w4, R.drawable.w5, R.drawable.w6, R.drawable.w7, };

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemDetail = itemView.findViewById(R.id.item_detail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 현재 클릭한 위치 알려주는 역할
                    int position = getAdapterPosition();
                    Snackbar.make(v, details[position], Snackbar.LENGTH_LONG).setAction("", null).show();


                } // onClick
            }); // setOnclick

        } // Constructor - ViewHolder
    } // ViewHolder

    // 실제 화면에 띄우는 것


    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 뷰홀더를 어디에 올릴지                                                 // Parameter를 parent로 써서 그런거임! ViewGroup이라고 보면 좋다.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }//onCreateViewHolder

    // 스크롤 내려서 없어진 값을 다시 밑에서 재사용한다!
    // ItemCount를 가지고 판단해서 위에 '월' 내용이 없어지면 그걸 가지고 '금'을 만드는 개념.
    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        holder.itemTitle.setText(titles[position]);
        holder.itemDetail.setText(details[position]);
        holder.itemImage.setImageResource(images[position]);

    }//onBind

    // 전체가 몇개인지 알려줘야함.
    @Override
    public int getItemCount() {
        return titles.length;
    }
}