package com.aoslec.haezzo.DocumentActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aoslec.haezzo.Bean.DocumentBean;
import com.aoslec.haezzo.LoginActivity.KakaoLoginActivity;
import com.aoslec.haezzo.NetworkTask.DocumentNetworkTask;
import com.aoslec.haezzo.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HaezulgaeDocumentDetailsActivity extends AppCompatActivity {

    String urlAddr = null;
    ArrayList<DocumentBean> documentBeans;
    String macIP = KakaoLoginActivity.macIP;

    Intent intent= null;
    String dnumber = null;

    //TextView 들
    TextView HDdetails_btnFurniture,HDdetails_btnElectronics,HDdetails_tvDproducts,HDdetails_tvDtitle, HDdetails_tvDcontent, HDdetails_tvDdate,HDdetails_tvDtime,
            HDdetails_tvDplace,HDdetails_tvDmoney,HDdetails_tvDpay;

    Button HDdetails_btnDapply;
    ImageView HDdetails_ivDimage;

    //입력된 값을 받을 변수들
    String sDproduct, sDtitle, sDcontent, sDdate, sDtime, sDplace, sDmoney, sDpay, sDimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haezulgae_document_details);
        Log.v("Message", "HaezulgaeDetails_onCreate");

        //textView 연결
        HDdetails_tvDproducts = findViewById(R.id.HDdetails_tvDproducts);
        HDdetails_tvDtitle = findViewById(R.id.HDdetails_tvDtitle);
        HDdetails_tvDcontent = findViewById(R.id.HDdetails_tvDcontent);
        HDdetails_tvDdate = findViewById(R.id.HDdetails_tvDdate);
        HDdetails_tvDtime = findViewById(R.id.HDdetails_tvDtime);
        HDdetails_tvDplace = findViewById(R.id.HDdetails_tvDplace);
        HDdetails_tvDmoney = findViewById(R.id.HDdetails_tvDmoney);
        HDdetails_tvDpay = findViewById(R.id.HDdetails_tvDpay);

        //이미지뷰 연결
        HDdetails_ivDimage = findViewById(R.id.HDdetails_ivDimage);

        //button연결
        HDdetails_btnDapply = findViewById(R.id.HDdetails_btnDapply);
        HDdetails_btnDapply.setOnClickListener(onClickListener);

    }//onCreate

    @Override//*********중요!!!
    protected void onResume() {
        super.onResume();
        showDetails();
    }

    private void connectGetData(){
        //검색하는 화면
        try {
            DocumentNetworkTask documentNetworkTask = new DocumentNetworkTask(HaezulgaeDocumentDetailsActivity.this, urlAddr,"select");
            //networktask 가 구동이 되서
            Object obj = documentNetworkTask.execute().get();//프로그레스바 돌고, 데이터 가지러가고
            documentBeans = (ArrayList<DocumentBean>) obj;

        }catch(Exception e){
            e.printStackTrace();
        }

    }//connectGetdata

    //지원하기 버튼 눌렀을때 값 넘기기 ( 나중에 수정할 부분: 다른쪽이랑 연결 )
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //화면에 입력된 값 가져오기
            sDproduct = HDdetails_tvDproducts.getText().toString();
            sDtitle = HDdetails_tvDtitle.getText().toString();
            sDcontent = HDdetails_tvDcontent.getText().toString();
            sDdate = HDdetails_tvDdate.getText().toString();
            sDtime = HDdetails_tvDtime.getText().toString();
            sDplace = HDdetails_tvDplace.getText().toString();
            sDmoney = HDdetails_tvDmoney.getText().toString();
            sDpay = HDdetails_tvDpay.getText().toString();

            //입력받은 값 intent에 넣어서 값 넘기기
            intent = new Intent(HaezulgaeDocumentDetailsActivity.this, DocumentUpdateActivity.class);
            intent.putExtra("dnumber",dnumber);
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
            Toast.makeText(HaezulgaeDocumentDetailsActivity.this, "지원이 완료되었습니다.", Toast.LENGTH_SHORT).show();
        } // onClick
    }; //onClickListener


    public void showDetails(){
        intent = getIntent();
        dnumber =intent.getStringExtra("dnumber");

        Log.v("Message", "Details_showDetail_dnumber = " + dnumber);

        //url불러와서 + select.jsp 넣기
        urlAddr = KakaoLoginActivity.urlAddr + "haezulgaeDocumentSelectList.jsp?";
        connectGetData();

        HDdetails_tvDproducts.setText(documentBeans.get((Integer.parseInt(dnumber))-1).getDproduct());
        HDdetails_tvDtitle.setText(documentBeans.get((Integer.parseInt(dnumber))-1).getDtitle());
        HDdetails_tvDcontent.setText(documentBeans.get((Integer.parseInt(dnumber))-1).getDcontent());
        HDdetails_tvDdate.setText(documentBeans.get((Integer.parseInt(dnumber))-1).getDdate());
        HDdetails_tvDtime.setText(documentBeans.get((Integer.parseInt(dnumber))-1).getDtime());
        HDdetails_tvDplace.setText(documentBeans.get((Integer.parseInt(dnumber))-1).getDplace());
        HDdetails_tvDmoney.setText(documentBeans.get((Integer.parseInt(dnumber))-1).getDmoney());
        HDdetails_tvDpay.setText(documentBeans.get((Integer.parseInt(dnumber))-1).getDpay());

        Glide.with(this)
                .load(KakaoLoginActivity.urlAddr + documentBeans.get((Integer.parseInt(dnumber))-1).getDimage())
                .into(HDdetails_ivDimage);

    }//showDetails

}//------