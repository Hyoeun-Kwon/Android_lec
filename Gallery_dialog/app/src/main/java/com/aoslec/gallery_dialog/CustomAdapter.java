package com.aoslec.gallery_dialog;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class CustomAdapter extends BaseAdapter {



    Context context;
    int[] posterList ;

    public CustomAdapter(Context context, int[] posterList) {
        this.context = context;
        this.posterList = posterList;
    }

    //기본 생김
    @Override
    public int getCount() {
        return posterList.length;
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
        ImageView imageView;

            imageView = new ImageView(context);
            imageView.setLayoutParams(new Gallery.LayoutParams(250,300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,5,5,5);


        //각 포지션마다 넣어주기
        imageView.setImageResource(posterList[position]);

        //
        imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ImageView main_poster = main_poster.findViewById(R.id.main_poster);
                main_poster.setScaleType(ImageView.ScaleType.FIT_CENTER);
                main_poster.setImageResource(posterList[position]);
                return false;
            }
        });

        return imageView;
    }
}
