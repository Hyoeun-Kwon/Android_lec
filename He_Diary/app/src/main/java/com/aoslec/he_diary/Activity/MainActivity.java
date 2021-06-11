package com.aoslec.he_diary.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

import com.aoslec.he_diary.DiaryActivity;
import com.aoslec.he_diary.R;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btn_logout;
    CalendarView calendarView;
    String myIP = null;
    Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //intent로 ip값 받아오기
        Intent intent = getIntent();
        myIP = intent.getStringExtra("myIP");

        //캘린더
        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDayClickListener(dayClick);

        //로그아웃 작업
        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그아웃 하기
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                //finish();//앱 끄기
            }
        });

    }//onCreate

    OnDayClickListener dayClick = new OnDayClickListener() {
        @Override
        public void onDayClick(EventDay eventDay) {
            Calendar clickedDay = eventDay.getCalendar();

            int nYear = clickedDay.get(Calendar.YEAR);
            int nMonth = clickedDay.get(Calendar.MONTH)+1;
            int nDay = clickedDay.get(Calendar.DAY_OF_MONTH);

            String year = Integer.toString(nYear);
            String month = Integer.toString(nMonth);
            String day = Integer.toString(nDay);
            String date = year + "년 " + month + "월 " + day + "일";

            Toast.makeText(MainActivity.this, "선택한 날짜 : "+date, Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
            intent.putExtra("date", date);
            intent.putExtra("myIP", myIP);
            startActivity(intent);
        }
    };


}//Main