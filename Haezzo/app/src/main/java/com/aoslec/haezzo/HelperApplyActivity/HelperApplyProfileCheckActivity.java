package com.aoslec.haezzo.HelperApplyActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.aoslec.haezzo.R;

public class HelperApplyProfileCheckActivity extends AppCompatActivity {

    Button buttonBack, buttonOK, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_apply_profile_check);

        buttonBack = findViewById(R.id.btn_helper_apply_profilecheck_back);
        buttonOK = findViewById(R.id.btn_helper_apply_profilecheck_ok);
        buttonCancel = findViewById(R.id.btn_helper_apply_profilecheck_cancel);

        buttonBack.setOnClickListener(onClickListener);
        buttonOK.setOnClickListener(onClickListener);
        buttonCancel.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_helper_apply_profilecheck_back:
                    Intent intent = new Intent(HelperApplyProfileCheckActivity.this, HelperApplyProfileActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.btn_helper_apply_profilecheck_ok:
                    finish();
                    break;
                case R.id.btn_helper_apply_profilecheck_cancel:
                    finish();
                    break;
            }

        }
    };

}