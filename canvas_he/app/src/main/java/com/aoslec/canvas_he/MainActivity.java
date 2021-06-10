package com.aoslec.canvas_he;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout ll_top, ll_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ll_top = findViewById(R.id.ll_top);
        ll_bottom = findViewById(R.id.ll_bottom);

        //top
        //bitmap 객체 만듬 (사이즈, 종이의 종류)
        //bitmap은 캔버스 객체 안으로 넣어야함
        Bitmap myBitmap = Bitmap.createBitmap(500,500,Bitmap.Config.ARGB_8888);
        //canvas 객체 만듬
        Canvas myCanvas = new Canvas(myBitmap);
        myCanvas.drawColor(getResources().getColor(R.color.colorWhite));

        //그리기 위해
        Paint myPaint = new Paint();
        myPaint.setColor(getResources().getColor(R.color.colorGreen));
        myPaint.setStrokeWidth(5.0f);

        //그려주기
        myCanvas.drawLine(0,0,250,250,myPaint);


        //위의 작업까지는 실제로 화면에그려지지 않음
        //view를 이용해 화면에 보여줌
        ImageView myImageView = new ImageView(MainActivity.this);
        myImageView.setImageBitmap(myBitmap);
        ll_top.addView(myImageView);

        //bottom
        //나만의 view
        MyView myView = new MyView(MainActivity.this);
        ll_bottom.addView(myView);





    }//onCreate
}//Main