package com.aoslec.haezzo.DocumentActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

    Intent intent= null;

    //TextView 들
    TextView Ddetails_tvDproducts,Ddetails_tvDtitle, Ddetails_tvDcontent, Ddetails_tvDdate,Ddetails_tvDtime,
            Ddetails_tvDplace,Ddetails_tvDmoney,Ddetails_tvDpay;

    Button Ddetails_btnDapply;

    //입력된 값을 받을 변수들
    String sDproduct, sDtitle, sDcontent, sDdate, sDtime, sDplace, sDmoney, sDpay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_details);
        //url불러와서 + select.jsp 넣기
        urlAddr = KakaoLoginActivity.urlAddr + "documentSelectList.jsp?";

        //textView 연결
        Ddetails_tvDproducts = findViewById(R.id.Ddetails_tvDproducts);
        Ddetails_tvDtitle = findViewById(R.id.Ddetails_tvDtitle);
        Ddetails_tvDcontent = findViewById(R.id.Ddetails_tvDcontent);
        Ddetails_tvDdate = findViewById(R.id.Ddetails_tvDdate);
        Ddetails_tvDtime = findViewById(R.id.Ddetails_tvDtime);
        Ddetails_tvDplace = findViewById(R.id.Ddetails_tvDplace);
        Ddetails_tvDmoney = findViewById(R.id.Ddetails_tvDmoney);
        Ddetails_tvDpay = findViewById(R.id.Ddetails_tvDpay);

        //button연결
        Ddetails_btnDapply = findViewById(R.id.Ddetails_btnDapply);
        Ddetails_btnDapply.setOnClickListener(onClickListener);


    }//onCreate

    @Override//*********중요!!!
    protected void onResume() {
        super.onResume();

        connectGetData();

        Ddetails_tvDproducts.setText(documentBeans.get(0).getDproduct());
        Ddetails_tvDtitle.setText(documentBeans.get(0).getDtitle());
        Ddetails_tvDcontent.setText(documentBeans.get(0).getDcontent());
        Ddetails_tvDdate.setText(documentBeans.get(0).getDdate());
        Ddetails_tvDtime.setText(documentBeans.get(0).getDtime());
        Ddetails_tvDplace.setText(documentBeans.get(0).getDplace());
        Ddetails_tvDmoney.setText(documentBeans.get(0).getDmoney());
        Ddetails_tvDpay.setText(documentBeans.get(0).getDpay());

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


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //화면에 입력된 값 가져오기
            sDproduct = Ddetails_tvDproducts.getText().toString();
            sDtitle = Ddetails_tvDtitle.getText().toString();
            sDcontent = Ddetails_tvDcontent.getText().toString();
            sDdate = Ddetails_tvDdate.getText().toString();
            sDtime = Ddetails_tvDtime.getText().toString();
            sDplace = Ddetails_tvDplace.getText().toString();
            sDmoney = Ddetails_tvDmoney.getText().toString();
            sDpay = Ddetails_tvDpay.getText().toString();

            //입력받은 값 intent에 넣어서 값 넘기기
            intent = new Intent(DocumentDetailsActivity.this, DocumentUpdateActivity.class);
            intent.putExtra("dproduct",sDproduct);
            intent.putExtra("dtitle",sDtitle);
            intent.putExtra("dcontent",sDcontent);
            intent.putExtra("ddate",sDdate);
            intent.putExtra("dtime",sDtime);
            intent.putExtra("dplace",sDplace);
            intent.putExtra("dmoney",sDmoney);
            intent.putExtra("dpay",sDpay);
            startActivity(intent);

            //토스트 띄우기
            Toast.makeText(DocumentDetailsActivity.this, "지원이 완료되었습니다.", Toast.LENGTH_SHORT).show();
        }
    };



}//------