package com.aoslec.seekbar_he;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ChangedPackages;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //레퍼런스 변수 만들기
    SeekBar sb_brightness;
    TextView tv_brightness;

    RatingBar ratingBar;
    TextView tv_rating;
    Button bt_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb_brightness = findViewById(R.id.sb_lighting);
        sb_brightness.setOnSeekBarChangeListener(onSeekBarChangeListener);

        tv_brightness = findViewById(R.id.tv_brightness);

        ratingBar = findViewById(R.id.rb_rating);
        ratingBar.setOnRatingBarChangeListener(onRatingBarChangeListener);

        tv_rating = findViewById(R.id.tv_rating);
        tv_rating.setText(String.valueOf(ratingBar.getRating()));

        bt_submit = findViewById(R.id.bt_submit);
        bt_submit.setOnClickListener(onClickListener);
    }//onCreate

    SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        //2번째 매개변수가 progress 였는데 i 로 변경 , 3 fromUser -> b
        
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            Log.i("Changed--->", String.valueOf(i));

            if(i<10){
                i=10;
                sb_brightness.setProgress(i);
            }else if(i>100){
                i=100;
            }//else if

            //key Point param객체 쓰면 window 관련 속성 다 가져올수 있음
            //실제 화면 밝기 조절이 되는 부분
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.screenBrightness =(float)i/100;
            getWindow().setAttributes(params);

            tv_brightness.setText(String.valueOf(i));
        }//onProgressChanged

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.i("StartTracking--->",String.valueOf(seekBar.getProgress()));

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.i("StopTracking--->",String.valueOf(seekBar.getProgress()));
        }
    }; //seekbar listener

    RatingBar.OnRatingBarChangeListener onRatingBarChangeListener = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            Log.i("RatingChanged--->",String.valueOf(v));
            tv_rating.setText(String.valueOf(v));
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_submit:
                    float resultF = Float.parseFloat(tv_rating.getText().toString());
                    String resultS = "NEUTRAL";
                    if(resultF>4.0f){
                        resultS = "BEST";
                    }else if(resultF>3.0f){
                        resultS = "LIKE IT";
                    }else if(resultF>2.0f){
                        resultS = "NEUTRAL";
                    }else if (resultF>1.0f){
                        resultS = "DISLIKE";
                    }else if (resultF>0.0f){
                        resultS = "WORST";
                    }

                    Toast.makeText(MainActivity.this, resultS, Toast.LENGTH_LONG).show();
                    break;
            }//switch

        }
    };//onClickListener


}//Main