package com.aoslec.fragment_he;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    //초기화
    LinearLayout ll_imgs;
    LinearLayout ll_btns;
    Button bt_male, bt_female;

    //fragment 사용을 위한 객체가 있음!
    FragmentManager fragmentManager; //android fragment app  --->얘는 관리만 해줌
    FragmentTransaction fragmentTransaction; // 실제 수행하는 애  ---> 한번 사용하면 사이클이 끝나므로 다시 불러야함
    FragmentFeMale fragmentFeMale;
    FragmentMale fragmentMale;
    //--> Management는 한번! tracsaction은 여러번!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager(); //시스템에서 가져옴
        fragmentMale = new FragmentMale();
        fragmentFeMale = new FragmentFeMale();


        ll_imgs = findViewById(R.id.ll_imgs);
        ll_btns = findViewById(R.id.ll_bnts);
        bt_female = findViewById(R.id.bt_female);
        bt_male = findViewById(R.id.bt_male);

        bt_male.setOnClickListener(null);
        bt_female.setOnClickListener(null);


    }//onCreate

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.bt_male:
                    //click 메소드 안에서 tansaction 이 움직이면 되겟다
                    fragmentTransaction = fragmentManager.beginTransaction();//가져올때 쓰는 명령
                    fragmentTransaction.replace(R.id.ll_imgs,fragmentMale).commitAllowingStateLoss();//화면을 변경해라
                    //ll_img 에다가 fragmentMale로 바꿔줘!
                    //이렇게 썼다고 바로 바뀌는건 아님--- commit 이 아직 안된 상태
                    break;

                case R.id.bt_female:
                    fragmentTransaction = fragmentManager.beginTransaction();//가져올때 쓰는 명령
                    fragmentTransaction.replace(R.id.ll_imgs,fragmentFeMale).commitAllowingStateLoss();
                    break;
            }
        }
    };


}