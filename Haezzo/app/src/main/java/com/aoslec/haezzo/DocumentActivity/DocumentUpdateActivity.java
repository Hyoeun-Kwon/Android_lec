package com.aoslec.haezzo.DocumentActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aoslec.haezzo.LoginActivity.KakaoLoginActivity;
import com.aoslec.haezzo.NetworkTask.DocumentNetworkTask;
import com.aoslec.haezzo.R;

public class DocumentUpdateActivity extends AppCompatActivity {

    Button Dupdate_btnFurniture,Dupdate_btnElectronics,Dupdate_btnDupdate,Dupdate_btnDdelete;
    TextView Dupdate_tvDproducts,Dupdate_tvDtitle,Dupdate_tvDcontent,Dupdate_tvDdate,Dupdate_tvDtime,
            Dupdate_tvDplace,Dupdate_tvDmoney,Dupdate_tvDpay;

    //입력된 값을 받을 변수들
    String sDproduct, sDtitle, sDcontent, sDdate, sDtime, sDplace, sDmoney, sDpay;


    //IP받아오기
    String macIP = KakaoLoginActivity.macIP;

    //url받아오기

    String urlAddr = null;
    //수정 삭제 url 나누기
    String subUrl_update = null;
    String subUrl_delete = null;
    Intent intent = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Message", "onCreate : DocumentUpdate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_update);
        //url불러오기
        urlAddr = KakaoLoginActivity.urlAddr;
        //수정/ 삭제 jsp 연결
        subUrl_update = "documentUpdate.jsp?";
        subUrl_delete = "documentDelete.jsp?";

        //연결
        Dupdate_tvDproducts = findViewById(R.id.Dupdate_tvDproducts);
        Dupdate_tvDtitle = findViewById(R.id.Dupdate_tvDtitle);
        Dupdate_tvDcontent = findViewById(R.id.Dupdate_tvDcontent);
        Dupdate_tvDdate = findViewById(R.id.Dupdate_tvDdate);
        Dupdate_tvDtime = findViewById(R.id.Dupdate_tvDtime);
        Dupdate_tvDplace = findViewById(R.id.Dupdate_tvDplace);
        Dupdate_tvDmoney = findViewById(R.id.Dupdate_tvDmoney);
        Dupdate_tvDpay = findViewById(R.id.Dupdate_tvDpay);

        //화면에 intent로 넘겨온 값 넣기
        intent.getStringExtra("dnumber");
        Dupdate_tvDproducts.setText(intent.getStringExtra("dproduct"));
        Dupdate_tvDtitle.setText(intent.getStringExtra("dtitle"));
        Dupdate_tvDcontent.setText(intent.getStringExtra("dcontent"));
        Dupdate_tvDdate.setText(intent.getStringExtra("ddate"));
        Dupdate_tvDtime.setText(intent.getStringExtra("dtime"));
        Dupdate_tvDplace.setText(intent.getStringExtra("dplace"));
        Dupdate_tvDmoney.setText(intent.getStringExtra("dmoney"));
        Dupdate_tvDpay.setText(intent.getStringExtra("dpay"));

        //수정버튼
        Dupdate_btnDupdate = findViewById(R.id.Dupdate_btnDupdate);
        Dupdate_btnDupdate.setOnClickListener(onClickListener);

        //삭제버튼
        Dupdate_btnDdelete = findViewById(R.id.Dupdate_btnDdelete);
        Dupdate_btnDdelete.setOnClickListener(onClickListener);

    }//onCreate

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //입력되어있는 값 받기
            sDproduct = Dupdate_tvDproducts.getText().toString();
            sDtitle = Dupdate_tvDtitle.getText().toString();
            sDcontent = Dupdate_tvDcontent.getText().toString();
            sDdate = Dupdate_tvDdate.getText().toString();
            sDtime = Dupdate_tvDtime.getText().toString();
            sDplace = Dupdate_tvDplace.getText().toString();
            sDmoney = Dupdate_tvDmoney.getText().toString();
            sDpay = Dupdate_tvDpay.getText().toString();


            switch (v.getId()){
                case R.id.Dupdate_btnDupdate:
                    urlAddr = urlAddr + subUrl_update + "dproduct=" + sDproduct + "&dtitle=" + sDtitle + "&dcontent=" + sDcontent
                            + "&ddate=" + sDdate + "&dtime=" + sDtime + "&dplace=" + sDplace + "&dmoney=" + sDmoney + "&dpay=" + sDpay;
                    String result_up = connectUpdateData();
                    break;
                case R.id.Dupdate_btnDdelete:
                    urlAddr = urlAddr + subUrl_delete + "dtitle=" + sDtitle;
                    String result_del = connectDeleteData();
                    break;
            }


            finish();

        }
    };

    private String connectUpdateData(){
        Log.v("Message", "METHOD : connectUpdateData Start");
        String result_up = null;
        try {
            // NetworkTask 가져와서 일을 시킬 거다 (어디에?, 어느 주소받아서?, 어느역할이야?)
            DocumentNetworkTask documentNetworkTask = new DocumentNetworkTask(DocumentUpdateActivity.this, urlAddr, "update");
            Object obj = documentNetworkTask.execute().get();
            result_up = (String)obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.v("Message", "End of METHOD // result : " + result_up);
        return result_up;
    }//update

    private String connectDeleteData(){
        Log.v("Message", "METHOD : connectUpdateData Start");
        String result_del = null;
        try {
            // NetworkTask 가져와서 일을 시킬 거다 (어디에?, 어느 주소받아서?, 어느역할이야?)
            DocumentNetworkTask documentNetworkTask = new DocumentNetworkTask(DocumentUpdateActivity.this, urlAddr, "delete");
            Object obj = documentNetworkTask.execute().get();
            result_del = (String)obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.v("Message", "End of METHOD // result : " + result_del);
        return result_del;
    }//delete

}//------