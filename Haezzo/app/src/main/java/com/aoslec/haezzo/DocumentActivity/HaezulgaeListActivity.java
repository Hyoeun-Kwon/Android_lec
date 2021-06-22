package com.aoslec.haezzo.DocumentActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.aoslec.haezzo.Adapter.HaezulgaeListAdapter;
import com.aoslec.haezzo.Bean.HaezulgaeListBean;
import com.aoslec.haezzo.LoginActivity.KakaoLoginActivity;
import com.aoslec.haezzo.NetworkTask.HaezulgaeListNetworkTask;
import com.aoslec.haezzo.R;

import java.util.ArrayList;

public class HaezulgaeListActivity extends AppCompatActivity {

    String urlAddr = null;
    ArrayList<HaezulgaeListBean> haezulgaeListBeans;
    HaezulgaeListAdapter haezulgaeListAdapter;

    String macIP = KakaoLoginActivity.macIP;

    RecyclerView recyclerView = null;
    RecyclerView.LayoutManager layoutManager = null;

    //지원하기 버튼
    Button Dapply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Message","HaezulgaeListActivity_onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haezulgae_list);

        urlAddr = "http://"+ macIP + ":8080/test/Haezzo/documentSelectList.jsp?";

        recyclerView = findViewById(R.id.lv_haezulgaeList);
        layoutManager = new LinearLayoutManager(HaezulgaeListActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        Log.v("Message","TEST1");

        //지원하기 버튼
        Dapply = findViewById(R.id.haezulgaeList_btnDapply);
        Dapply.setOnClickListener(onClickListener);

    } // onCreate

    //화면이 뜰때: resume, 다시 와도 resume 부터 실행.
    @Override
    protected void onResume() {
        super.onResume();
        //수정된 정보를 부르기 위해
        connectGetData();
    }
    private void connectGetData(){
        try {
            HaezulgaeListNetworkTask haezulgaeListNetworkTask = new HaezulgaeListNetworkTask(HaezulgaeListActivity.this, urlAddr, "select");
            Object obj = haezulgaeListNetworkTask.execute().get();
            Log.v("Message","HelperListActivity_networkTask" + haezulgaeListNetworkTask);
            haezulgaeListBeans = (ArrayList<HaezulgaeListBean>) obj;
            Log.v("Message","HelperListActivity_helperListBeans" + haezulgaeListBeans);

            haezulgaeListAdapter = new HaezulgaeListAdapter(HaezulgaeListActivity.this, R.layout.haezulgae_custom_layout, haezulgaeListBeans);
            recyclerView.setAdapter(haezulgaeListAdapter);



        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //지원하기 버튼
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.haezulgaeList_btnDapply:
                    Intent intent = new Intent(HaezulgaeListActivity.this, DocumentDetailsActivity.class);
                    startActivity(intent);
            }
        }
    };

}//-------