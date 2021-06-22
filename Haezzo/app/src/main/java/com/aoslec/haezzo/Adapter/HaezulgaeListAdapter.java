package com.aoslec.haezzo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aoslec.haezzo.Bean.HaezulgaeListBean;
import com.aoslec.haezzo.Bean.HelperListBean;
import com.aoslec.haezzo.R;

import java.util.ArrayList;

public class HaezulgaeListAdapter extends RecyclerView.Adapter<HaezulgaeListAdapter.ViewHolder>{

    public static String macIP = "192.168.35.241";
    private Context mContext = null; //어디서 불렀는지
    private int layout =0;
    private ArrayList<HaezulgaeListBean> data =null; //data
    private LayoutInflater inflater = null ;

    //생성자
    public HaezulgaeListAdapter(Context mContext, int layout, ArrayList<HaezulgaeListBean> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        //4.인플레이터 추가
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    //------------2.ViewHolder 만들기
    //class 안에 class , 제너릭으로 ViewHolder를 부름
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView list_tvdate, HaezulgaeList_tvDgaga, HaezulgaeList_tvDproduct, HaezulgaeList_tvDtitle,
                HaezulgaeList_tvDdate, HaezulgaeList_tvDplace, HaezulgaeList_tvDmoney;
        public WebView HaezulgaeList_wvImage;

        public Button haezulgaeList_btnDapply;


        public ViewHolder(View itemView) {
            super(itemView);
            HaezulgaeList_tvDproduct = itemView.findViewById(R.id.haezulgaeList_tvDproduct);
            HaezulgaeList_tvDtitle = itemView.findViewById(R.id.haezulgaeList_tvDtitle);
            HaezulgaeList_tvDdate = itemView.findViewById(R.id.haezulgaeList_tvDdate);
            HaezulgaeList_tvDplace = itemView.findViewById(R.id.haezulgaeList_tvDplace);
            HaezulgaeList_tvDmoney = itemView.findViewById(R.id.haezulgaeList_tvDmoney);
            HaezulgaeList_wvImage = itemView.findViewById(R.id.haezulgaeList_wvImage);
            //버튼 추가
           //haezulgaeList_btnDapply = itemView.findViewById(R.id.haezulgaeList_btnDapply);

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.haezulgae_custom_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    //뷰홀더 사용
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //배열이므로
        // holder.wv_img.loadData(htmlData(data.get(position).getImg()),"text/html","UTF-8");
        holder.HaezulgaeList_tvDproduct.setText(data.get(position).getDproduct());
        holder.HaezulgaeList_tvDtitle.setText(data.get(position).getDtitle());
        holder.HaezulgaeList_tvDdate.setText(data.get(position).getDdate());
        holder.HaezulgaeList_tvDplace.setText(data.get(position).getDplace());
        holder.HaezulgaeList_tvDmoney.setText(data.get(position).getDmoney());
        holder.HaezulgaeList_wvImage.loadData(htmlData(data.get(position).getDimage()),"text/html","UTF-8");


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(),DiaryUpdateActivity.class);
//                intent.putExtra("date", data.get(position).getDate());
//                intent.putExtra("title", data.get(position).getTitle());
//                intent.putExtra("detail", data.get(position).getDetail());
//                intent.putExtra("status", data.get(position).getStatus());
//                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
//                v.getContext().startActivity(intent);
//            }
//        });



    }//viewholder

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
                        "<img src=\"http://" + macIP + ":8080/test/Haezzo/";
        content += image + "\" alt=\"게시물 사진\" width=\"100%\" height=\"100%\"></body></html>";
        return content;
    }


}
