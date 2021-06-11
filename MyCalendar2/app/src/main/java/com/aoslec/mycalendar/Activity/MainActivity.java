package com.aoslec.mycalendar.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.aoslec.mycalendar.R;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Calendar;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {


    CalendarView calendarView;
    private boolean InetAddressUtils;

    String macIP = "192.168.35.155";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Message", macIP);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDayClickListener(dayClick);

    } // onCreate

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
            intent.putExtra("macIP", macIP);
            startActivity(intent);
        }
    };




} //Main