package com.aoslec.networkjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //ip : 본인
    String urlAddr = "http://192.168.211.130:8080/test/json_members.json";
    Button button;
    ListView listView;
    ArrayList<JsonMember> members;
    MemberAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn_network_con);
        listView = findViewById(R.id.lv_members);

        button.setOnClickListener(onClickListener);

    }//onCreate

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_network_con:
                    try{
                        //1. networktask에서 data 가져오기
                        //context와 주소로 (생성되면서 뱅글뱅글 돌겠지!: 실질적 돌아가는거 백그라운드)
                        NetworkTask networkTask = new NetworkTask(MainActivity.this, urlAddr);
                        //백그라운드의 리턴값은 object 임
                        Object obj = networkTask.execute().get();
                        //get 인 이유 특정 메소드를 가져올수 없음 (async 이므로 동시에 진행 되고 있으므로)
                        //obj에 데이터가 들어와있을 거임
                        //memgers로 리턴
                        members = (ArrayList<JsonMember>) obj;

                        //화면에 띄우자 : Adapter
                        //adapter 생성자는 context, layout, data를 매개변수로 받음
                        //layout은 우리가 만든 custom layout
                        //data는 networktask 통해 members에 있음!
                        adapter = new MemberAdapter(MainActivity.this, R.layout.custom_layout, members);
                        //이제 이 그림을 listview에 넣자
                        listView.setAdapter(adapter);
                        //button 글씨를 바꿔보자
                        button.setText("Results");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
            }
        }
    };


}//Main