package com.aoslec.haezzo.DocumentActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.aoslec.haezzo.LoginActivity.KakaoLoginActivity;
import com.aoslec.haezzo.NetworkTask.DocumentNetworkTask;
import com.aoslec.haezzo.R;

public class WriteDocumentActivity extends AppCompatActivity {

    String urlAddr = null;
    Button btnFurniture, btnElectronics, btnWrite;
    EditText etTitle, etContent, etLocation, etMoney;
    DatePicker dpDate;
    TimePicker tpTime;

    //작업용
    String dtitle, dcontent, dmoney;

    //임시, usernumber
    String unumber = "1";

    //------image 업로드, 지급수단, product 제외하고 진행 -----//

    //IP받아오기
    String macIP = KakaoLoginActivity.macIP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_document);
        Log.v("macIP",macIP);
        urlAddr = KakaoLoginActivity.urlAddr + "documentInsert.jsp?";

        //연결
        etTitle = findViewById(R.id.wtire_etTitle);
        etContent = findViewById(R.id.write_etContent);
        etMoney = findViewById(R.id.write_etMoney);

        //입력 자릿수 제한
        etTitle.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        etMoney.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});

        //버튼
        btnWrite = findViewById(R.id.write_btnWrite);
        btnWrite.setOnClickListener(onClickListener);

    }//onCreate

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dtitle = etTitle.getText().toString();
            dcontent = etContent.getText().toString();
            dmoney = etMoney.getText().toString();

            //get방식 url
            urlAddr = urlAddr + "dtitle=" + dtitle +
                    "&dcontent=" + dcontent + "&dmoney=" +dmoney + "&unumber=" + unumber;

            //urlAddr는 전역변수라 아무 메소드에서 쓸 수 있음
            String result = connectInsertData();//여기에 return값 줄거임

            Toast.makeText(WriteDocumentActivity.this, "입력되었습니다", Toast.LENGTH_SHORT).show();

        }
    };

    private String connectInsertData(){
        String result = null;
        try{
            //여기서 networktask
            //insertactivity에서 부른거야, 나는 ip주소 줄게 그리고     hb insert 할거야
            DocumentNetworkTask documentNetworkTask = new DocumentNetworkTask(WriteDocumentActivity.this, urlAddr,"insert");
            //jsp통해서 받아온 return 값 -> object
            Object obj = documentNetworkTask.execute().get();
            result = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;//잘끝났으면 1 아니면 다른값 넘길 거임
    }//connectInsertData



}//---DocumentWrite