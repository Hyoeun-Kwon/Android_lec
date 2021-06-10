package com.aoslec.dbcrud.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aoslec.dbcrud.R;

public class MainActivity extends AppCompatActivity {

    EditText edtIP;
    Button insertBtn, updateBtn, deleteBtn, selectAllBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListener();
    }//onCreate

    //findviewbyid, clicklistener
    private void addListener(){
        edtIP = findViewById(R.id.edt_ip);
        insertBtn = findViewById(R.id.btn_insert);
        updateBtn = findViewById(R.id.btn_update);
        deleteBtn = findViewById(R.id.btn_delete);
        selectAllBtn = findViewById(R.id.btn_selectA);

        //listener
        insertBtn.setOnClickListener(onClickListener);
        updateBtn.setOnClickListener(onClickListener);
        deleteBtn.setOnClickListener(onClickListener);
        selectAllBtn.setOnClickListener(onClickListener);

    }

        //이동시 ip address 가져가야함
    View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempIp = edtIP.getText().toString();
                Intent intent = null;

                switch (v.getId()){
                    case R.id.btn_insert:
                        intent = new Intent(MainActivity.this, InsertActivity.class);
                        intent.putExtra("macIP", tempIp);
                        startActivity(intent);
                        break;
                    case R.id.btn_update:
                        Toast.makeText(MainActivity.this, "검색 후 버튼을 짧게 누르면 ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn_delete:
                        Toast.makeText(MainActivity.this, "검색 후 버튼을 길게 누르면", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn_selectA:
                        intent = new Intent(MainActivity.this, SelectAllActivity.class);
                        intent.putExtra("macIP", tempIp);
                        startActivity(intent);
                        break;
                }
            }
        };
}//Main