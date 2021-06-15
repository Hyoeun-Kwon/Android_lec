package com.aoslec.he_diary.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aoslec.he_diary.Activity.DiaryUpdateActivity;
import com.aoslec.he_diary.Bean.Diary;
import com.aoslec.he_diary.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context mContext = null; //어디서 불렀는지
    private int layout =0;
    private ArrayList<Diary> data =null; //data

    //생성자
    public ListAdapter(Context mContext, int layout, ArrayList<Diary> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
    }


    //------------2.ViewHolder 만들기
    //class 안에 class , 제너릭으로 ViewHolder를 부름
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView list_tvdate,list_tvtitle,list_tvdetail;
        public WebView list_tvstatus;


        public ViewHolder(View itemView) {
            super(itemView);
            list_tvdate = itemView.findViewById(R.id.list_tvdate);
            list_tvtitle = itemView.findViewById(R.id.list_tvtitle);
            list_tvdetail = itemView.findViewById(R.id.list_tvdetail);
            list_tvstatus = itemView.findViewById(R.id.list_tvstatus);


////            아까 클리커블, 포커서블 해놨음
////            셀모양 눌렀을때? 인가봄
//            itemView.setOnClickListener(new View.OnClickListener() {
//                Intent intent = null;
//                @Override
//                public void onClick(View v) {
//                    //뭘 클릭했는지 (현재 클릭한 위치)
//                    int position = getAdapterPosition();
//                    intent.putExtra("date", data.get(position).getDate());
//                    intent.putExtra("title", data.get(position).getTitle());
//                    intent.putExtra("detail", data.get(position).getDetail());
//                    intent.putExtra("status", data.get(position).getStatus());
//                    intent = Intent(v.getContext(), DiaryUpdateActivity.class);
//
//                    //snackbar 띄울거임
//                    Snackbar.make(v,data.get(position).getTitle(), Snackbar.LENGTH_LONG).setAction("누르셨습니다.",null).show();
//                }
//            });
        }
    }

    //뷰홀더 만들기
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }
    //뷰홀더 사용
    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {

        //배열이므로
       // holder.wv_img.loadData(htmlData(data.get(position).getImg()),"text/html","UTF-8");
        holder.list_tvdate.setText(data.get(position).getDate());
        holder.list_tvtitle.setText(data.get(position).getTitle());
        holder.list_tvdetail.setText(data.get(position).getDetail());
        holder.list_tvstatus.loadData(htmlData(data.get(position).getStatus()),"text/html","UTF-8");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),DiaryUpdateActivity.class);
                    intent.putExtra("date", data.get(position).getDate());
                    intent.putExtra("title", data.get(position).getTitle());
                    intent.putExtra("detail", data.get(position).getDetail());
                    intent.putExtra("status", data.get(position).getStatus());
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //htmlData
    private String htmlData(String image){
        String content =
                "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"+
                        "<html><head>"+
                        "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf8\"/>"+
                        "<head><body>"+
                        "<img src=\"http://192.168.35.20:8080/test/";
        content += image + "\" alt=\"오늘의 사진\" width=\"100%\" height=\"100%\"></body></html>";
        return content;
    }

}//
