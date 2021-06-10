package com.aoslec.bottomsheetdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    BottomSheet bottomSheet;//그냥 전역변수로 선언하려고;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click시 밑에서 올라오는 거임
                //main에서 불러서 사용
                //main에 있는 버튼을 클릭하면
                bottomSheet = new BottomSheet();//class
                bottomSheet.show(getSupportFragmentManager(),bottomSheet.getTag());

            }
        });

    }//onCreate
}//