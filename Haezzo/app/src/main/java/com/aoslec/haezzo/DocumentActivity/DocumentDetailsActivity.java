package com.aoslec.haezzo.DocumentActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.aoslec.haezzo.Adapter.HelperListAdapter;
import com.aoslec.haezzo.Bean.DocumentBean;
import com.aoslec.haezzo.Bean.HelperListBean;
import com.aoslec.haezzo.LoginActivity.KakaoLoginActivity;
import com.aoslec.haezzo.NetworkTask.DocumentNetworkTask;
import com.aoslec.haezzo.NetworkTask.HelperNetworkTask;
import com.aoslec.haezzo.R;
import com.aoslec.haezzo.UserHelperActivity.HelperListActivity;

import java.util.ArrayList;

public class DocumentDetailsActivity extends AppCompatActivity {

    String urlAddr = null;
    ArrayList<DocumentBean> documentBeans;
    String macIP = KakaoLoginActivity.macIP;
    //spinner작업
    Spinner Dproducts;
    ArrayAdapter<CharSequence> adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_details);
        //url불러와서 + select.jsp 넣기
        urlAddr = KakaoLoginActivity.urlAddr + "documentSelectList.jsp?";

        adapter = ArrayAdapter.createFromResource(this, R.array.dproduct_category,
                android.R.layout.simple_spinner_dropdown_item);

        //spinner 연결
        Dproducts = findViewById(R.id.Ddetails_sDproducts);
        Dproducts.setAdapter(adapter);

    }//onCreate

    @Override//*********중요!!!
    protected void onResume() {
        super.onResume();
        connectGetData();
    }

    private void connectGetData(){
        //검색하는 화면
        try {
            DocumentNetworkTask documentNetworkTask = new DocumentNetworkTask(DocumentDetailsActivity.this, urlAddr,"select");
            //networktask 가 구동이 되서
            Object obj = documentNetworkTask.execute().get();//프로그레스바 돌고, 데이터 가지러가고
            Log.v("Message","DocumentActivity_networkTask" + documentNetworkTask);
            documentBeans = (ArrayList<DocumentBean>) obj;
            Log.v("Message","DocumentActivity_DocumentBeans" + documentBeans);

        }catch(Exception e){
            e.printStackTrace();
        }

    }//connectGetdata






}//------