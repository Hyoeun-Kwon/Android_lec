package com.aoslec.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    //data
    private int[] data = {R.drawable.w1,R.drawable.w2,R.drawable.w3,R.drawable.w4,R.drawable.w5,R.drawable.w6,R.drawable.w7,
            R.drawable.w1,R.drawable.w2,R.drawable.w3,R.drawable.w4,R.drawable.w5,R.drawable.w6,R.drawable.w7,R.drawable.w1,R.drawable.w2,R.drawable.w3,R.drawable.w4,R.drawable.w5,R.drawable.w6,R.drawable.w7,
            R.drawable.w1,R.drawable.w2,R.drawable.w3,R.drawable.w4,R.drawable.w5,R.drawable.w6,R.drawable.w7,
            R.drawable.w1,R.drawable.w2,R.drawable.w3,R.drawable.w4,R.drawable.w5,R.drawable.w6,R.drawable.w7,
            R.drawable.w1,R.drawable.w2,R.drawable.w3,R.drawable.w4,R.drawable.w5,R.drawable.w6,R.drawable.w7};
    //adapter
    private CustomAdapter adapter = null;
    //view
    private GridView gridView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //화면띄울꺼니까 adapter에 대해
        adapter = new CustomAdapter(this,data);
        //view 연결
        gridView = findViewById(R.id.gv_01);
        //gridview에 adapter 넣어주기
        gridView.setAdapter(adapter);


    }
}