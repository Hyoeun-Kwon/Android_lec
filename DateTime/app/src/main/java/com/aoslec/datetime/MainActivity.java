package com.aoslec.datetime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    //선언(필요한 변수들) : 위젯 이름들 -> 위에 선언하자
    //android widget 사용하자 --> 얘네들은 다 클라스임 (그래서 다 대문자로 시작)
    Chronometer chrono;
    Button btnStart, btnEnd;
    RadioButton rdoCal, rdoTime;
    CalendarView calView;
    TimePicker tPicker;
    TextView tvYear, tvMonth, tvDay, tvHour, tvMinute;

        //전역변수 : 다른 메소드에서 사용하려고 !
    int selectYear, selectMonth, selectDay;

    //onCreate Method : 제일 처음 시작하고 시작안한다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //appBar 의 제목을 바꾸자 ( 그 아래는 appBody 라고 함)
        setTitle("시간 예약");

        //버튼 연결
        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);

        //chronometer 연결
        chrono = findViewById(R.id.chronometer1);

        //radioButton 2개
        rdoCal = findViewById(R.id.rdoCal);
        rdoTime = findViewById(R.id.rdoTime);

        //Framelayout의 2개 위젯
        tPicker = findViewById(R.id.timePicker1);
        calView = findViewById(R.id.calendarView1);

        //TextView 중에서 년, 월, 일, 시 , 분
        tvYear = findViewById(R.id.tvYear);
        tvMonth = findViewById(R.id.tvMonth);
        tvDay = findViewById(R.id.tvDay);
        tvHour = findViewById(R.id.tvHour);
        tvMinute = findViewById(R.id.tvMinute);

        //처음에는 2개를 안보이게 설정 (달력, 타임피커)
        tPicker.setVisibility(View.INVISIBLE);
        calView.setVisibility(View.INVISIBLE);

        //RadioButton-> 버튼 클릭에 따라 보이는 화면 다르게 하기
        //group 일 경우는 change ~ 였지만, 버튼은 그냥 clicklistener
        rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tPicker.setVisibility(View.INVISIBLE);
                calView.setVisibility(View.VISIBLE);

            }
        });
        //RadioButton-> 버튼 클릭에 따라 보이는 화면 다르게 하기
        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tPicker.setVisibility(View.VISIBLE);
                calView.setVisibility(View.INVISIBLE);
            }
        });

        //타이머 설정
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chrono.setBase(SystemClock.elapsedRealtime()); //현재시간
               // chrono.setBase(SystemClock.currentThreadTimeMillis()); // 시작 안눌러도 돌아가고 있네?
                chrono.start();
                //예약시작 누르면 글자 색이 빨강색으로 변함 (타이머가 돌고있는 동안 빨간색)
                chrono.setTextColor(Color.RED);

            }
        });

        //캘린더 선택
        //date가 바뀌는 것
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //위의 매개변수 참조
                selectYear = year;
                //month는 0부터 시작함
                selectMonth = month +1;
                selectDay = dayOfMonth;
            }
        });

        // 예약완료 버튼 눌렀을 때 -> textview에 날짜를 찍어줘야 함
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono.stop();
                //멈췄을때는 파랑색으로 변경
                chrono.setTextColor(Color.BLUE);

                //selectYear,selectMonth,selectDay는 int로 변수 선언 해놨음
                //그리고 그 값을 날짜 선택시 값이 들어오게 해둠
                //그 들어온 값을 textview에 찍음
                tvYear.setText(Integer.toString(selectYear));
                tvMonth.setText(Integer.toString(selectMonth));
                tvDay.setText(Integer.toString(selectDay));

                //Picker에서 선택한 시간 과 분
                //시간하고 분은 피커타임이 가지고 있음 (얘네는 listener 필요없음)
                //getCurrentHour/ getCurrentMinute 으로 사용 가능
                tvHour.setText(Integer.toString(tPicker.getCurrentHour()));
                tvMinute.setText(Integer.toString(tPicker.getCurrentMinute()));
            }
        });


    }//onCreate
}//MainActivity