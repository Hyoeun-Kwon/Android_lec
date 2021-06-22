package com.aoslec.haezzo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HelperApplyAccountActivity extends AppCompatActivity {

    static public Activity activityAccount;

    Button buttonBack, buttonOK, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_apply_account);

        activityAccount = HelperApplyAccountActivity.this;

        buttonBack = findViewById(R.id.btn_helper_apply_account_back);
        buttonOK = findViewById(R.id.btn_helper_apply_account_ok);
        buttonCancel = findViewById(R.id.btn_helper_apply_account_cancel);

        buttonBack.setOnClickListener(onClickListener);
        buttonOK.setOnClickListener(onClickListener);
        buttonCancel.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_helper_apply_account_back:
                    finish();
                    break;
                case R.id.btn_helper_apply_account_ok:
                    Intent intent = new Intent(HelperApplyAccountActivity.this, HelperApplyIdCardActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_helper_apply_account_cancel:
                    finish();
                    break;
            }

        }
    };

}