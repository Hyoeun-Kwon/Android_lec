package com.aoslec.quiz8click_fontsize;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView tvHello;
    Button btnSmallFont, btnLargeFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSmallFont = findViewById(R.id.btnSmallFont);
        btnLargeFont = findViewById(R.id.btnLargeFont);
        tvHello = findViewById(R.id.tvHello);

        btnSmallFont.setOnClickListener(onClickListener);
        btnLargeFont.setOnClickListener(onClickListener);

    }//

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int num = (int) tvHello.getTextSize();
            switch (v.getId()){
                case R.id.btnSmallFont:
                    num = num-3;
                    tvHello.setTextSize(TypedValue.COMPLEX_UNIT_PX,num);
                    break;

                case R.id.btnLargeFont:
                    num = num+3;
                    tvHello.setTextSize(TypedValue.COMPLEX_UNIT_PX,num);
                    break;
            }//switch

        }
    };


}//