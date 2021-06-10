package com.aoslec.networkjson_student_re_webview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class BeanAdapter extends RecyclerView.Adapter<BeanAdapter.ViewHolder> {

    private Context context = null;
    private int layout = 0;
    private ArrayList<JsonBean> data= null;


    public BeanAdapter(Context context, ArrayList<JsonBean> data, int layout) {
        this.context = context;
        this.data = data;
        this.layout = layout;
    }

    //------------2.ViewHolder 만들기
    //class 안에 class , 제너릭으로 ViewHolder를 부름
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_name,tv_info;
        public WebView wv_img;

        public ViewHolder(View itemView) {
            super(itemView);
            wv_img = itemView.findViewById(R.id.wv_img);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_info = itemView.findViewById(R.id.tv_info);


//            //아까 클리커블, 포커서블 해놨음
//            //셀모양 눌렀을때? 인가봄
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //뭘 클릭했는지 (현재 클릭한 위치)
//                    int position = getAdapterPosition();
//                    //snackbar 띄울거임
//                    Snackbar.make(v,data.get(position).getName(), Snackbar.LENGTH_LONG).setAction("",null).show();
//                }
//            });
        }
    }

    //뷰홀더 만들기
    @Override
    public BeanAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }
    //뷰홀더 사용
    @Override
    public void onBindViewHolder(BeanAdapter.ViewHolder holder, int position) {
        //배열이므로
        holder.wv_img.loadData(htmlData(data.get(position).getImg()),"text/html","UTF-8");
        holder.tv_name.setText(data.get(position).getName());
        holder.tv_info.setText(data.get(position).getInfo());
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
                "<img src=\"http://192.168.35.230:8080/test/";
        content += image + "\" alt=\"강아지\" width=\"100%\" height=\"100%\"></body></html>";
        return content;
    }


}//Main
