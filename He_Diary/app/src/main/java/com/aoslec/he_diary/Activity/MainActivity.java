package com.aoslec.he_diary.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.aoslec.he_diary.R;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnLogout, btnSelectAll;
    CalendarView calendarView;
    String myIP = null;
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

        //내 모든 기록 보기
        btnSelectAll = findViewById(R.id.btn_selectAll);
        btnSelectAll.setOnClickListener(listClick);

        //로그아웃 작업
        btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
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
    };//dayClick

    View.OnClickListener listClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            intent.putExtra("myIP", myIP);
            startActivity(intent);
        }
    };//listClick


}//Main