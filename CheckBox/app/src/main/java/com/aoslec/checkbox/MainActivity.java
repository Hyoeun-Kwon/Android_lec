package com.aoslec.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox cb1, cb2, cb3, cb4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("체크박스");

        cb1 = findViewById(R.id.cb_01);
        cb2 = findViewById(R.id.cb_02);
        cb3 = findViewById(R.id.cb_03);
        cb4 = findViewById(R.id.cb_04);

        cb1.setOnCheckedChangeListener(checkChangeListener);
        cb2.setOnCheckedChangeListener(checkChangeListener);
        cb3.setOnCheckedChangeListener(checkChangeListener);
        cb4.setOnCheckedChangeListener(checkChangeListener);


    }//onCreate

    //compoundButton 시
    CompoundButton.OnCheckedChangeListener checkChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //화면에 toast로 보여줄 거임 (체크 된만큼 )
            String str = "";
            String[] kind = {"운동", "요리", "독서", "여행"};
            if(cb1.isChecked() == false){
                kind[0]="";
            }
            if(cb2.isChecked()== false) {
                kind[1] = "";

            }//if
            if(cb3.isChecked()==false){
                kind[2] = "";
            }

            if(cb4.isChecked()==false){
                kind[3]="";
            }

            for(int i=0; i<kind.length; i++){
                str += kind[i];
            }
            if(kind[0] == "" && kind[1]=="" && kind[2]=="" && kind[3]=="" ){
                Toast.makeText(MainActivity.this, "아무것도 선택되지 않았습니다.", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(MainActivity.this, str+" is checked", Toast.LENGTH_SHORT).show();

            }
        }
    };

}//MainActivity