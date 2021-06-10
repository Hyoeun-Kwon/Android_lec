package com.aoslec.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("갤러리 영화 포스터");
        Gallery gallery = findViewById(R.id.gallery1);
        MyGalleryAdapter myGalleryAdapter = new MyGalleryAdapter(this);
        gallery.setAdapter(myGalleryAdapter);

    }




    public class MyGalleryAdapter extends BaseAdapter{

        Context context;
        int [] posterID = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05};

        public MyGalleryAdapter(Context context) {
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
            //context는 Context 클래스인듯!?
            //화면을 나타내는 단위가 activity
            //컨텍스트는 컴퓨터 내부에서 볼 때,
                   // - 프로세서 안에 있는 레지스터, 플래그 등의 현재 값/상태들의 집합을 말함
            ImageView imageView = new ImageView(context);
            //layout 속성값 변경할때는 layoutParam을 꼭 써야함
            imageView.setLayoutParams(new Gallery.LayoutParams(200, 300)); //이런 레이아웃을 쓸거야
            //스케일이란 이미지를 이미지뷰에서 사용할때 이미지의 크기나 위치를 가기다른 크기의 사진을 효율적으로 보여주기 위해 사용하는 속성
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);//
            imageView.setPadding(5,5,5,5);

            //실제 이미지 넣기 (각 포지션 마다 넣어주기)
            imageView.setImageResource(posterID[position]);


            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ImageView ivPoster = findViewById(R.id.ivPoster);
                    ivPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    ivPoster.setImageResource(posterID[position]);

                    return false;
                }
            });

            return imageView;
        }
    }


} // Main