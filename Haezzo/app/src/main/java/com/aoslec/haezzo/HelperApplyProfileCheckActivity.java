package com.aoslec.haezzo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HelperApplyProfileCheckActivity extends AppCompatActivity {

    static public Activity activityProfileCheck;

    Button buttonBack, buttonOK, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_apply_profile_check);

        activityProfileCheck = HelperApplyProfileCheckActivity.this;

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
                case R.id.btn_helper_apply_profilecheck_back:
                    finish();
                    break;
                case R.id.btn_helper_apply_profilecheck_ok:
                    finish();
                    HelperApplyProfileActivity.activityProfile.finish();
                    HelperApplyProfileImageActivity.activityProfileImage.finish();
                    HelperApplyIdCardActivity.activityIdCard.finish();
                    HelperApplyAccountActivity.activityAccount.finish();

                    break;
                case R.id.btn_helper_apply_profilecheck_cancel:
                    finish();
                    HelperApplyProfileActivity.activityProfile.finish();
                    HelperApplyProfileImageActivity.activityProfileImage.finish();
                    HelperApplyIdCardActivity.activityIdCard.finish();
                    HelperApplyAccountActivity.activityAccount.finish();
                    break;
            }

        }
    };

}