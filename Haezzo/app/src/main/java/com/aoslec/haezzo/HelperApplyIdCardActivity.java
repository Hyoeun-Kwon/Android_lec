package com.aoslec.haezzo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HelperApplyIdCardActivity extends AppCompatActivity {

    static public Activity activityIdCard;

    Button buttonBack, buttonOK, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_apply_id_card);

        activityIdCard = HelperApplyIdCardActivity.this;

        buttonBack = findViewById(R.id.btn_helper_apply_idcard_back);
        buttonOK = findViewById(R.id.btn_helper_apply_idcard_ok);
        buttonCancel = findViewById(R.id.btn_helper_apply_idcard_cancel);

        buttonBack.setOnClickListener(onClickListener);
        buttonOK.setOnClickListener(onClickListener);
        buttonCancel.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_helper_apply_idcard_back:
                    finish();
                    break;
                case R.id.btn_helper_apply_idcard_ok:
                    Intent intent = new Intent(HelperApplyIdCardActivity.this, HelperApplyProfileImageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_helper_apply_idcard_cancel:
                    finish();
                    HelperApplyAccountActivity.activityAccount.finish();
                    break;
            }

        }
    };

}