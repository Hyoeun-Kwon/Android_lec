package com.aoslec.putextra;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final static int RValue = 0;//암호같은거임
    TextView textView = null;
    Button button = null;
    Button button1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_main);
        button = findViewById(R.id.btn_main);
        button1 = findViewById(R.id.btn_main1);

        //listener
        button.setOnClickListener(onClickListener);
        button1.setOnClickListener(onClickListener);

    }//onCreate

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.btn_main:
                    intent = new Intent(MainActivity.this, SecondActivity.class);

                    //data 주기 intent 버스에 사람 태우기
                    intent.putExtra("userid", "root"); //jsp 에 getparameter와 같은 거임
                    intent.putExtra("passwd", 1111);
                    startActivity(intent);
                    break;

                case R.id.btn_main1:
                    //third로 보내기
                    intent = new Intent(MainActivity.this,ThirdActivity.class);
                    intent.putExtra("userid", "admin");
                    intent.putExtra("passwd",2222);

                    //다시 값 가져오기!? (3에서 받아올떄)
                    //상호작용 //페이지가 샥 사라질때 없어진(없어졌지만) 값 알고있어야함
                    //빠지면서 정보줌 ( 화면이 계속 겹쳐 있으므로 )
                    //jsp 는 계속 화면을 불러왔었음
                    startActivityForResult(intent,RValue);//이걸 쓰면 onActivityResult 나오게
                default:
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //return code가 1, 2 따라 설정할수 있음
        //requestCode는 third 에서의 returnValue임
        switch (resultCode){
            case MainActivity.RValue://request code에 걸리면 안되는거 아니야?(third1 이고 여기는 0일때)-
                //result는 third에서 intent로 보낼때 result라는 이름으로 intent에 넣엇
                //data는 intent를 받은 매개변수임
                textView.setText("Return Value :" + data.getStringExtra("result"));
                break;
            default:
                break;
        }
    }
}//Main