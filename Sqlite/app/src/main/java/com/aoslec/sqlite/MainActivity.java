package com.aoslec.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLData;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnUpdate, btnDelete,btnSelect;
    TextView tvResult;
    Memberinfo memberinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Memberinfo instance 생성 --> 하면 알아서 memberinfo내에 onCreate와 onUpgrade 구동 됨
        memberinfo = new Memberinfo(MainActivity.this);

        //연결
        btnInsert = findViewById(R.id.btn_insert);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        btnSelect = findViewById(R.id.btn_select);
        tvResult = findViewById(R.id.tv_result);

        //onclicklistener
        btnInsert.setOnClickListener(onClickListener);
        btnUpdate.setOnClickListener(onClickListener);
        btnDelete.setOnClickListener(onClickListener);
        btnSelect.setOnClickListener(onClickListener);

    }//onCreate

    View.OnClickListener onClickListener = new View.OnClickListener() {
        SQLiteDatabase DB;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_insert:
                    try{
                        //권한 주기
                        DB = memberinfo.getWritableDatabase();
                        String query = "INSERT INTO member(username, userid, passwd) VALUES ('홍길동','hkdong',1111)";
                        DB.execSQL(query);

                        memberinfo.close();
                        Toast.makeText(MainActivity.this, "Insert OK", Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        Toast.makeText(MainActivity.this, "Insert Error", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btn_update:
                    try{
                        //권한 주기
                        DB = memberinfo.getWritableDatabase();
                        String query = "UPDATE member SET username = '임꺽정' WHERE userid = 'hkdong'";
                        DB.execSQL(query);

                        memberinfo.close();
                        Toast.makeText(MainActivity.this, "Update OK", Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        Toast.makeText(MainActivity.this, "Update Error", Toast.LENGTH_SHORT).show();
                    }
                    break;
                    //select는 다르다!
                case R.id.btn_select:
                    try{
                        //권한 주기
                        DB = memberinfo.getReadableDatabase();
                        String query = "SELECT username, userid, passwd FROM member;";
                        Cursor cursor = DB.rawQuery(query,null);//해당 위치를 앎

                        //select는 데이터가 여러개!
                        //어레이 대신 쓸거야 버퍼로!
                        StringBuffer stringBuffer = new StringBuffer();

                        //rs.next 하듯이 하기위해 Cursor 만들기
                        while (cursor.moveToNext()) {
                            //data가 여러개 이면 해당 위치를 커서가 알고있음
                            String username = cursor.getString(0);//0번째
                            String userid = cursor.getString(1);
                            int passwd = cursor.getInt(2);

                            stringBuffer.append("username:" + username + ", userid :" + userid + ",passwd :" + passwd + "\n");
                        }
                            tvResult.setText(stringBuffer.toString());
                            //close 꼭!!
                            cursor.close();
                            memberinfo.close();
                            Toast.makeText(MainActivity.this, "Select Ok", Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        Toast.makeText(MainActivity.this, "Select Error", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btn_delete:
                    try{
                        //권한 주기
                        DB = memberinfo.getWritableDatabase();
                        //String query = "DELETE FROM member WHERE userid = 'hkdong'";
                        String query = "DELETE FROM member";
                        DB.execSQL(query);

                        memberinfo.close();
                        Toast.makeText(MainActivity.this, "Delete OK", Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        Toast.makeText(MainActivity.this, "Delete Error", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

        }
    };



}//MainActivity