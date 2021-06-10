package com.aoslec.alertdialogue_he;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bt_app_close;
    Button bt_progressbar_show, bt_progressbar_hide;
    //progress 레퍼런스 가져오기
    ProgressBar pg_download;

    //horizontal progress bar
    Button bt_progressbar_horizontal;
    ProgressBar pg_horizontal;
    TextView tv_progress_value;

    //변수 만들기 : 작업이 얼만큼 진행되었는지 표시해주기 위해
    private int progressBarValue;
    //외부에서 데이터
    private int downloadDataSize;
    //핸들러 구현
    private Handler progressBarHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //app close 버튼 초기화 및 연결
        bt_app_close = (Button)findViewById(R.id.bt_app_close);
        bt_app_close.setOnClickListener(onClickListener);

        //progress bar button 초기화
        bt_progressbar_show = findViewById(R.id.bt_progress_show);
        bt_progressbar_hide = findViewById(R.id.bt_progress_hide);
        //progress bar(show,hide) listener에 연결
        bt_progressbar_show.setOnClickListener(onClickListener);
        bt_progressbar_hide.setOnClickListener(onClickListener);

        //progress bar 초기화
        pg_download = findViewById(R.id.pg_download);

        //horizontal progress bar 초기화
        bt_progressbar_horizontal = findViewById(R.id.bt_progressbar_horizontal);
        bt_progressbar_horizontal.setOnClickListener(onClickListener);
        pg_horizontal = findViewById(R.id.pg_horizontal);
        tv_progress_value =findViewById(R.id.tv_progress_value);

        //변수 2개 초기화
        progressBarValue = 0;
        downloadDataSize = 0;

        //핸들러 초기화를 위해 객체 생성
        progressBarHandler = new Handler();

    }//onCreate

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          switch (v.getId()){
              case R.id.bt_app_close:
                  //alertdialog에 builder를 내장하고 있음
                  new AlertDialog.Builder(MainActivity.this)
                          .setIcon(R.mipmap.ic_launcher)
                          .setTitle(getResources().getString(R.string.dialog_title_str_app_close))
                          .setMessage(getResources().getString(R.string.dialog_msg_str_app_close))
                          .setNeutralButton(getResources().getString(R.string.dialog_btn_str_cancel),null)
                          .setNegativeButton(getResources().getString(R.string.dialog_btn_str_no), new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                  Toast.makeText(MainActivity.this, getResources().getString(R.string.dialog_toast_str_cancel),Toast.LENGTH_SHORT).show();
                              }
                          })
                          .setPositiveButton(getResources().getString(R.string.dialog_btn_str_yes), new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                  finish();
                              }
                          })
                          .show();
                  break;

                  //progress bar
              case R.id.bt_progress_show:
                  pg_download.setVisibility(View.VISIBLE);
                  break;

              case R.id.bt_progress_hide:
                  pg_download.setVisibility(View.INVISIBLE);
                  break;

                  //1. 안보이다가 보이게 처리 //쓰레드 이용
              case R.id.bt_progressbar_horizontal:
                  pg_horizontal.setVisibility(View.VISIBLE);
                  tv_progress_value.setVisibility(View.VISIBLE);

                  //프로그레스 바 처음 0
                  progressBarValue = 0;
                  pg_horizontal.setProgress(progressBarValue);

                  //쓰레드 이용 : 실제로 프로그레스가 네트워크 가져오듯이 보이기 위해?
                  new Thread(new Runnable() {
                      @Override
                      public void run() {
                          while(progressBarValue<100){
                              //100보다 작으면 계속 돌거임!
                              progressBarValue = doMyTask();
                              //조금 늦게 진행되라고
                              try {
                                  Thread.sleep(100);
                              }catch (InterruptedException e){
                                  e.printStackTrace();
                              }

                              //handler 구현
                              progressBarHandler.post(new Runnable() {
                                  @Override
                                  public void run() {
                                      pg_horizontal.setProgress(progressBarValue);
                                      tv_progress_value.setText(String.valueOf(progressBarValue));
                                  }
                              });//post

                          }//while

                          if(progressBarValue >= 100){
                             try {
                                 //2초 정도 머물다가 (사라짐)
                                 Thread.sleep(2000);
                             }catch (InterruptedException e) {
                                 e.printStackTrace();
                             }
                             //사라짐
                             pg_horizontal.setVisibility(View.INVISIBLE);
                             tv_progress_value.setVisibility(View.INVISIBLE);

                          }
                      }//run
                  }).start();

                  break;

          }//switch

        }//onClick
    };//listener

    //doMyTask 메소드 (가상저장소) : 실제 네트워크 통해 외부에서 데이터 가져오는 것 구현될 곳
    public int doMyTask(){
        //download데이터사이즈가 100보다 작으면 사이즈를 다시 0으로 만들어주고
        if(downloadDataSize >= 100) downloadDataSize = 0;
        //else 아니라면 계속해서 1씩 사이즈를 늘려준다.
        downloadDataSize++;
        return downloadDataSize;
    }//doMyTask


}//Main