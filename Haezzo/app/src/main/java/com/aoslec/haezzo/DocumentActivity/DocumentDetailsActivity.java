package com.aoslec.haezzo.DocumentActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.aoslec.haezzo.MainActivity;
import com.aoslec.haezzo.NetworkTask.DocumentNetworkTask;
import com.aoslec.haezzo.R;
import com.aoslec.haezzo.ShareVar;
import com.aoslec.haezzo.UserHelperActivity.MyHaezzoListActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DocumentDetailsActivity extends AppCompatActivity {

    String urlAddr = null;
    ArrayList<DocumentBean> documentBeans;
    String macIP = KakaoLoginActivity.macIP;

    Intent intent= null;
    String dnumber = null;

    //TextView 들
    TextView Ddetails_btnFurniture,Ddetails_btnElectronics,Ddetails_tvDproducts,Ddetails_tvDtitle, Ddetails_tvDcontent, Ddetails_tvDdate,Ddetails_tvDtime,
            Ddetails_tvDplace,Ddetails_tvDmoney,Ddetails_tvDpay;

    Button Ddetails_btnDmodify,Ddetails_btnDcomplete;
    ImageView Ddetails_ivDimage;

    //입력된 값을 받을 변수들
    String sDproduct, sDtitle, sDcontent, sDdate, sDtime, sDplace, sDmoney, sDpay, sDimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_details);
        Log.v("Message", "DocumentDetails_onCreate");
        //button연결
        Ddetails_btnDmodify = findViewById(R.id.Ddetails_btnDmodify);
        Ddetails_btnDmodify.setOnClickListener(onClickListener);

        Ddetails_btnDcomplete = findViewById(R.id.Ddetails_btnDcomplete);
        Ddetails_btnDcomplete.setOnClickListener(onClickListener);

        if(ShareVar.Document_dstatus.equals("대기중")){
            Ddetails_btnDmodify.setVisibility(View.VISIBLE);
        }
        if(ShareVar.Document_dstatus.equals("진행중")){
            Ddetails_btnDcomplete.setVisibility(View.VISIBLE);
        }

        //textView 연결
        Ddetails_tvDproducts = findViewById(R.id.Ddetails_tvDproducts);
        Ddetails_tvDtitle = findViewById(R.id.Ddetails_tvDtitle);
        Ddetails_tvDcontent = findViewById(R.id.Ddetails_tvDcontent);
        Ddetails_tvDdate = findViewById(R.id.Ddetails_tvDdate);
        Ddetails_tvDtime = findViewById(R.id.Ddetails_tvDtime);
        Ddetails_tvDplace = findViewById(R.id.Ddetails_tvDplace);
        Ddetails_tvDmoney = findViewById(R.id.Ddetails_tvDmoney);
        Ddetails_tvDpay = findViewById(R.id.Ddetails_tvDpay);

        //이미지뷰 연결
        Ddetails_ivDimage = findViewById(R.id.Ddetails_ivDimage);



    }//onCreate

    @Override//*********중요!!!
    protected void onResume() {
        super.onResume();
        showDetails();
    }

    private void connectGetData(){
        //검색하는 화면
        try {
            DocumentNetworkTask documentNetworkTask = new DocumentNetworkTask(DocumentDetailsActivity.this, urlAddr,"select");
            //networktask 가 구동이 되서
            Object obj = documentNetworkTask.execute().get();//프로그레스바 돌고, 데이터 가지러가고
            documentBeans = (ArrayList<DocumentBean>) obj;

        }catch(Exception e){
            e.printStackTrace();
        }

    }//connectGetdata

    //수정하기 버튼 눌렀을때 값 넘기기 ( 나중에 수정할 부분: 다른쪽이랑 연결 )
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.Ddetails_btnDmodify:
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
                    Toast.makeText(DocumentDetailsActivity.this, "수정화면으로 넘어갑니다.", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.Ddetails_btnDcomplete:
                    new AlertDialog.Builder(DocumentDetailsActivity.this)
                            .setIcon(R.mipmap.ic_launcher)
                            .setTitle("거래완료")
                            .setMessage("거래완료를 누르시면.. \n정말로 누르실껀가요?")
                            .setNegativeButton("아니오", null )
                            .setPositiveButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(DocumentDetailsActivity.this, MyHaezzoListActivity.class);
                                    dnumber = intent.getStringExtra("dnumber");
                                    intent.putExtra("dnumber",dnumber);
                                    //거래완료 누르면 dstatus가 거래완료! 상태로 변경 되어야 함
                                    startActivity(intent);
                                }
                            })
                            .show();
                    break;
            }

        } // onClick
    }; //onClickListener


    public void showDetails(){
        intent = getIntent();
        dnumber =intent.getStringExtra("dnumber");

        Log.v("Message", "Details_showDetail_dnumber = " + dnumber);

        //url불러와서 + select.jsp 넣기
        urlAddr = KakaoLoginActivity.urlAddr + "DocumentSelect.jsp?";
        connectGetData();

        Ddetails_tvDproducts.setText(documentBeans.get((Integer.parseInt(dnumber))-1).getDproduct());
        Ddetails_tvDtitle.setText(documentBeans.get((Integer.parseInt(dnumber))-1).getDtitle());
        Ddetails_tvDcontent.setText(documentBeans.get((Integer.parseInt(dnumber))-1).getDcontent());
        Ddetails_tvDdate.setText(documentBeans.get((Integer.parseInt(dnumber))-1).getDdate());
        Ddetails_tvDtime.setText(documentBeans.get((Integer.parseInt(dnumber))-1).getDtime());
        Ddetails_tvDplace.setText(documentBeans.get((Integer.parseInt(dnumber))-1).getDplace());
        Ddetails_tvDmoney.setText(documentBeans.get((Integer.parseInt(dnumber))-1).getDmoney());
        Ddetails_tvDpay.setText(documentBeans.get((Integer.parseInt(dnumber))-1).getDpay());

        Glide.with(this)
                .load(KakaoLoginActivity.urlAddr + documentBeans.get((Integer.parseInt(dnumber))-1).getDimage())
                .into(Ddetails_ivDimage);

    }//showDetails

}//---------