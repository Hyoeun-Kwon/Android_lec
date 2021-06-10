package com.aoslec.recyclercardtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //recyclerview만 정의해주면 됨
    //3단계
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter; //adapter에 데이터 함께 들어가있음

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recyclerview 연결
        recyclerView = findViewById(R.id.recycler_view);

        //layout manager 는 3개 중 쓰면 됨 (그리드,등등)
        //linearLayoutManager(context)
//        layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        layoutManager = new GridLayoutManager(this, 2);
        //recyclerview야 너는 리니어야! 알려주기
        recyclerView.setLayoutManager(layoutManager);

        //adapter연결
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        //!! 리사이클러뷰는 데이터와, 레이아웃메니저의 레이아웃만 변함

    }
}