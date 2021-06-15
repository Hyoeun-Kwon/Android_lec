package com.aoslec.he_diary.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.aoslec.he_diary.Adapter.ListAdapter;
import com.aoslec.he_diary.Bean.Diary;
import com.aoslec.he_diary.NetworkTask.NetworkTask;
import com.aoslec.he_diary.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    String urlAddr = null;//만들어줄 어드레스
    String myIP; //가져온 어드레스
    ArrayList<Diary> diaries;
    ListAdapter adapter;

    RecyclerView recyclerView = null;
    RecyclerView.LayoutManager layoutManager= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Message","listActivity_onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //listView 초기화
        recyclerView = findViewById(R.id.rv_lists);
        //linearLayoutManager(context)
        layoutManager = new LinearLayoutManager(ListActivity.this);
        //layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        //recyclerview야 너는 리니어야! 알려주기
        recyclerView.setLayoutManager(layoutManager);

        //ip와, data 부르기
        Intent intent = getIntent();
        myIP = intent.getStringExtra("myIP");
        urlAddr = "http://"+ myIP + ":8080/test/diarySelectList.jsp?";
        Log.v("Message","listActivity_urlAddr"+urlAddr);

        //리스트 뷰에서 학번 1 -> 수정 -- finish 없어지고 다시 리스트뷰 화면으로 넘어와서 그 화면에서 바뀐 장면 보여줘야 함
        //아래에 있는 뷰에 정보를 줘야함
        //화면이 뜰때: resume , 다음화면에 있다가 다시 와도 resume 부터 한다.
        //data 갱신에는 resume에 있음 !!!


    }//onCreate
    @Override//*********중요!!!
    protected void onResume() {
        Log.v("Message","listActivity_onResume");

        super.onResume();
        //수정된 정보를 부르기 위해
        //맨처음에도 실행, 위에 뭐가 얹혀졌다가 사라져도 실행 되기 위하여
        connectGetData();
    }

    private void connectGetData(){
        //검색하는 화면
        try {
            NetworkTask networkTask = new NetworkTask(ListActivity.this, urlAddr,"select");
            Log.v("Message","listActivity_networkTask" + networkTask);

            //networktask 가 구동이 되서
            Object obj = networkTask.execute().get();//프로그레스바 돌고, 데이터 가지러가고
            diaries = (ArrayList<Diary>) obj;//결과

            adapter = new ListAdapter(ListActivity.this, R.layout.custom_layout, diaries);
            recyclerView.setAdapter((RecyclerView.Adapter) adapter);


        }catch(Exception e){
            e.printStackTrace();
        }
    }//connectGetdata end




}//main