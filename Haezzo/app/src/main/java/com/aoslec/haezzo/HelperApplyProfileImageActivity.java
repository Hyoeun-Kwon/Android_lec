package com.aoslec.haezzo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HelperApplyProfileImageActivity extends AppCompatActivity {

    static public Activity activityProfileImage;

    Button buttonBack, buttonOK, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_apply_profile_image);

        activityProfileImage = HelperApplyProfileImageActivity.this;

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
                case R.id.btn_helper_apply_profileimage_back:
                    finish();
                    break;
                case R.id.btn_helper_apply_profileimage_ok:
                    Intent intent = new Intent(HelperApplyProfileImageActivity.this, HelperApplyProfileActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_helper_apply_profileimage_cancel:
                    finish();
                    HelperApplyIdCardActivity.activityIdCard.finish();
                    HelperApplyAccountActivity.activityAccount.finish();
                    break;
            }

        }
    };

}