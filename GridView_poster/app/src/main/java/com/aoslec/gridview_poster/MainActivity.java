package com.aoslec.gridview_poster;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("그리드뷰 영화 포스터");
        GridView gv = findViewById(R.id.gridView1);
        MyGridAdapter gridAdapter = new MyGridAdapter(this);
        gv.setAdapter(gridAdapter);


    }//onCreate

    //adapter만들기
    public class MyGridAdapter extends BaseAdapter{
        Context context;
        int[] posterID = {R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,R.drawable.mov05,
                R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,R.drawable.mov10,
                R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,R.drawable.mov05,
                R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,R.drawable.mov10};


         public MyGridAdapter(Context context){
            this.context = context;
         }

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(200,300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,5,5,5);

            imageView.setImageResource(posterID[position]);

            //(오후수업)1.버튼 리스너 여기에!
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //클릭시 다이얼로그 띄울것임
                    //다이얼로그의 그림을 만들자 ---> 2.xml 만들기 -dialog.xml
                    //그림을 화면에 띄우기 위해 inflate 사용
                    View dialogView = View.inflate(MainActivity.this, R.layout.dialog, null);

                    //얼러트 다이얼로그
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this); //틀
                    ImageView ivPoster = dialogView.findViewById(R.id.ivPoster);//변수 연결
                    ivPoster.setImageResource(posterID[position]); //이미지 넣기

                    //alert 내용
                    dlg.setTitle("   >>> 포스터 <<<");
                    dlg.setView(dialogView);//dialog.xml 가져온 값
                    dlg.setNegativeButton("닫기",null);//null대신 리스너 줘서 뭔가 해도 됨
                    dlg.show();


                }
            });




            return imageView;
        }
    }



}//Main