package com.aoslec.haezzo.DocumentActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.aoslec.haezzo.LoginActivity.KakaoLoginActivity;
import com.aoslec.haezzo.R;

public class DocumentUpdateActivity extends AppCompatActivity {

    Button Dupdate_btnFurniture,Dupdate_btnElectronics,Dupdate_btnDupdate,Dupdate_btnDdelete;
    TextView Dupdate_Dproducts,Dupdate_tvDtitle,Dupdate_tvDcontent,Dupdate_tvDdate,Dupdate_tvDtime,
            Dupdate_tvDplace,Dupdate_tvDmoney,Dupdate_tvDpay;

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
        //url
        urlAddr = KakaoLoginActivity.urlAddr;

        subUrl_update = "diaryUpdateReturn.jsp?";
        subUrl_delete = "diaryDeleteReturn.jsp?";


    }//onCreate






}//------