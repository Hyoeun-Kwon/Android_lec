package com.aoslec.haezzo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Button main_btnHaezzo,main_btnHaezulgae;
    BottomNavigationView main_bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent 값 넘겨오기
        Intent intent = getIntent();

        //해쭤 버튼 누르면 헬퍼보이는 리스트로 넘어가기
        main_btnHaezzo = findViewById(R.id.main_btnHaezzo);
        main_btnHaezzo.setOnClickListener(onClickListener);

        //해줄게 버튼 누르면 얼러트 뜨기
        main_btnHaezulgae = findViewById(R.id.main_btnHaezulgae);
        main_btnHaezulgae.setOnClickListener(onClickListener);

        //바틈 네비게이션 뷰 눌렀을때
        main_bottomNavigationView = (BottomNavigationView)findViewById(R.id.main_bottom_navigation);
        main_bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }//onCreate

//    BottomNavigationView.OnNavigationItemReselectedListener naviItemSelectedListener = new BottomNavigationView.OnNavigationItemReselectedListener() {
//        @Override
//        public void onNavigationItemReselected(@NonNull  MenuItem item) {
//            switch (item.getItemId()){
//                case R.
//            }
//        }
//    };
private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
        = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
               // mTextMessage.setText(R.string.title_home);
                startActivity(new Intent(getApplicationContext(), HaezzoListActivity.class));
                finish();
                overridePendingTransition(0,0);
                return true;
//            case R.id.navigation_list:
//                startActivity(new Intent(getApplicationContext(), UseActivity.class));
//                finish();
//                overridePendingTransition(0,0);
//                return true;
            case R.id.navigation_mypage:
                startActivity(new Intent(getApplicationContext(), MypageActivity.class));
                finish();
                overridePendingTransition(0,0);
                return true;
        }
        return false;
    }
};


    //해쭤 버튼 누르기
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.main_btnHaezzo:
                    Intent intent = new Intent(MainActivity.this,HaezzoListActivity.class);
                    startActivity(intent);
                    break;
                case R.id.main_btnHaezulgae:
                    new AlertDialog.Builder(MainActivity.this)
                            .setIcon(R.mipmap.ic_launcher)
                            .setTitle("헬퍼 등록")
                            .setMessage("'해줄게' 이용하시려면 \n 헬퍼가 되야 해요. \n 등록하시겠어요?")
                            .setNegativeButton("아니오", null )
                            .setPositiveButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                   Intent intent2 = new Intent(MainActivity.this, HaezulgaeListActivity.class);
                                   startActivity(intent2);
                                }
                            })
                            .show();
            }
        }
    };



}//