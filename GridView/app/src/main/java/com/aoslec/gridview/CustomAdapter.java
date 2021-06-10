package com.aoslec.gridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class CustomAdapter extends BaseAdapter {


    private Context mContext;
    private int[] data;

    //생성자 만들기
    public CustomAdapter(Context mContext, int[] data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
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
        //convertview 땅 작업
        if(convertView == null){
            //mContext는 위의 선언한 것임
          imageView = new ImageView(mContext);
          imageView.setLayoutParams(new GridView.LayoutParams(50,50));
          imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
          imageView.setPadding(5,5,5,5);
        }else{
            imageView = (ImageView) convertView;

        }
            imageView.setImageResource(data[position]);

        return imageView;
    }
}
