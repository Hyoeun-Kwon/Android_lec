package com.aoslec.he_diary.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;

import com.aoslec.he_diary.Bean.Diary;
import com.aoslec.he_diary.NetworkTask.NetworkTask;
import com.aoslec.he_diary.R;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnMypage, btnSelectAll;
    CalendarView calendarView;
    String myIP = null;
    String date = null;

    // 수정 삭제용
    String urlAddr = null;
    String dTitle = null;
    String dDetail = null;
    String dStatus = null;
    ArrayList<Diary> diaries;

    //BottomSheet
    BottomSheet bottomSheet;

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

        //bottomsheet로 로그인 버튼 띄우기
        bottomSheet = new BottomSheet();//class
        bottomSheet.show(getSupportFragmentManager(),bottomSheet.getTag());

        //Mypage
        btnMypage = findViewById(R.id.btn_mypage);
        btnMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MypageActivity.class);
                intent.putExtra("myIP",myIP);
                startActivity(intent);
            }
        });



//        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);

    }//onCreate

    @Override
    protected void onResume() {
        super.onResume();
    }

    OnDayClickListener dayClick = new OnDayClickListener() {
        @Override
        public void onDayClick(EventDay eventDay) {
            Intent intent = null;

            Log.v("Message", "onDayClick start");
            Calendar clickedDay = eventDay.getCalendar();

            int nYear = clickedDay.get(Calendar.YEAR);
            int nMonth = clickedDay.get(Calendar.MONTH)+1;
            int nDay = clickedDay.get(Calendar.DAY_OF_MONTH);

            String year = Integer.toString(nYear);
            String month = Integer.toString(nMonth);
            String day = Integer.toString(nDay);
            date = year + "년" + month + "월" + day + "일";
            Log.v("Message", "Selected day : " + date);
            // Init
            dTitle = null;
            dDetail = null;

            // 해당 날짜에 값이 있나 체크.
            emptyCheck();

            Log.v("Message", "Title : " + dTitle + "  /  Detail : " + dDetail + "Status"+dStatus);

            // 수정 / 삭제 페이지로 이동
            if(dTitle !=null && dDetail !=null){
                Log.v("Message", "Type A");
                Toast.makeText(MainActivity.this, "선택한 날짜 : " + date, Toast.LENGTH_SHORT).show();

                intent = new Intent(MainActivity.this, DiaryUpdateActivity.class);
                intent.putExtra("date", date);
                intent.putExtra("title", dTitle);
                intent.putExtra("detail", dDetail);
                intent.putExtra("status", dStatus);
                intent.putExtra("myIP", myIP);
                startActivity(intent);

            }else {
                // 신규 작성페이지로 이동.
                Log.v("Message", "Type B");
                Toast.makeText(MainActivity.this, "선택한 날짜 : " + date, Toast.LENGTH_SHORT).show();

                intent = new Intent(MainActivity.this, DiaryActivity.class);
                intent.putExtra("date", date);
                intent.putExtra("myIP", myIP);
                startActivity(intent);
            }
        }
    };//dayClick

    public void emptyCheck(){
        Log.v("Message", "METHOD : emptyCheck");
        try {
            urlAddr = "http://" + myIP + ":8080/test/EmptyCheckReturn.jsp?date=" + date;
            Log.v("Message", "  - emptyCheck Url : " + urlAddr);

            NetworkTask networkTask = new NetworkTask(MainActivity.this, urlAddr, "select");
            Object obj = networkTask.execute().get();

            diaries = (ArrayList<Diary>) obj;
            dTitle = diaries.get(0).getTitle();
            dDetail = diaries.get(0).getDetail();
            dStatus = diaries.get(0).getStatus();
            Log.v("Message", "  - emptyCheck _ dDetail : " +dDetail);

        }catch (Exception e){
            e.printStackTrace();
        }



    }






    //list Click -> 목록 보여지는 부분
    View.OnClickListener listClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            intent.putExtra("myIP", myIP);
            startActivity(intent);
        }
    };//listClick


}//Main