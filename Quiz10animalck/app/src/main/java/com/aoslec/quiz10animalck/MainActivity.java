package com.aoslec.quiz10animalck;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox cb_strat;
//    LinearLayout radio_layout;
//    FrameLayout f_layout;
    RadioGroup rg_animalCheck;
    RadioButton rb_dog, rb_cat, rb_rabbit;
    Button btn_Ok;
//    ImageView img_dog, img_cat, img_rabbit;
    TextView text1, text2;
    ImageView img_pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //위젯을 변수에 대입
        //radio_layout = findViewById(R.id.radio_layout);
        // f_layout = findViewById(R.id.f_layout);
        cb_strat = findViewById(R.id.cb_start); //쌤은 ChkAgree
        rg_animalCheck = findViewById(R.id.rg_animalCheck);
        rb_dog = findViewById(R.id.rb_dog);
        rb_cat = findViewById(R.id.rb_cat);
        rb_rabbit = findViewById(R.id.rb_rabbit);
        btn_Ok = findViewById(R.id.btn_Ok);
        text1 = findViewById(R.id.Text1);
        text2 = findViewById(R.id.Text2);
        img_pet = findViewById(R.id.img_pet);


        //시작함 체크박스의 체크가 변경되면...
        cb_strat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cb_strat.isChecked()==true){
                    text2.setVisibility(View.VISIBLE);
                    rg_animalCheck.setVisibility(View.VISIBLE);
                    btn_Ok.setVisibility(View.VISIBLE);
                    img_pet.setVisibility(View.VISIBLE);
                }else{
                    text2.setVisibility(View.INVISIBLE);
                    rg_animalCheck.setVisibility(View.INVISIBLE);
                    btn_Ok.setVisibility(View.INVISIBLE);
                    img_pet.setVisibility(View.INVISIBLE);
                }//else
            }
        });//setOnCheckedChangeListener

        //버튼을 클릭하면 .....

        btn_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rg_animalCheck.getCheckedRadioButtonId()){
                    case R.id.rb_dog:
                        img_pet.setImageResource(R.drawable.dog);
                        break;
                    case R.id.rb_cat:
                        img_pet.setImageResource(R.drawable.cat);
                        break;
                    case R.id.rb_rabbit:
                        img_pet.setImageResource(R.drawable.rabbit);
                        break;
                        //체크 아무것도 안하고 선택완료 버튼 눌렀을 경우
                    default:
                        //화면이 여러개 떠 있을 경우 mainactivity.this 사용 불가
                        //이럴 경우 getApplicationContext 사용 ---> 현재 가동되고 있는 어플리케이션 아이디, 위치를 가지고 있는게 context 이다.
                        Toast.makeText(getApplicationContext(), "동물을 선택해 주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });




//        cb_strat.setOnCheckedChangeListener(cbcheckedChangeListener);
//        btn_select.setOnClickListener(btnClickListener);
//
    }//onCreate
//
//    CompoundButton.OnCheckedChangeListener cbcheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//            if(cb_strat.isChecked()==true){
//                radio_layout.setVisibility(radio_layout.VISIBLE);
//            }else{
//                radio_layout.setVisibility(radio_layout.INVISIBLE);
//                f_layout.setVisibility(f_layout.INVISIBLE);
//            }
//        }
//    };//Listener
//
//    View.OnClickListener btnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            f_layout.setVisibility(v.VISIBLE);
//            if(rb_dog.isChecked()==true){
//                img_dog.setVisibility(v.VISIBLE);
//                img_cat.setVisibility(v.INVISIBLE);
//            }
//        }
//    };



}//MainActivity