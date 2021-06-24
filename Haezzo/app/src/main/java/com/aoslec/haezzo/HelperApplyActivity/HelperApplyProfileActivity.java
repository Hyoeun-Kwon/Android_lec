package com.aoslec.haezzo.HelperApplyActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.aoslec.haezzo.R;
import com.aoslec.haezzo.ShareVar;

public class HelperApplyProfileActivity extends AppCompatActivity {

    ShareVar shareVar = new ShareVar();

    public static String hSelf;
    public static String hGaGa;

    EditText et_hself;

    Button buttonBack, buttonOK, buttonCancel;

    ArrayAdapter<CharSequence> adpater_gaga = null;
    Spinner sp_gaga = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_apply_profile);

        et_hself = findViewById(R.id.et_hself);

        buttonBack = findViewById(R.id.btn_helper_apply_profile_back);
        buttonOK = findViewById(R.id.btn_helper_apply_profile_ok);
        buttonCancel = findViewById(R.id.btn_helper_apply_profile_cancel);

        adpater_gaga = ArrayAdapter.createFromResource(this, R.array.gaga,
                android.R.layout.simple_spinner_dropdown_item);
        sp_gaga = findViewById(R.id.sp_gaga);
        sp_gaga.setAdapter(adpater_gaga);

        buttonBack.setOnClickListener(onClickListener);
        buttonOK.setOnClickListener(onClickListener);
        buttonCancel.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_helper_apply_profile_back:
                    Intent intent = new Intent(HelperApplyProfileActivity.this, HelperApplyProfileImageActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.btn_helper_apply_profile_ok:
//                    Intent intent2 = new Intent(HelperApplyProfileActivity.this, HelperApplyProfileCheckActivity.class);
//                    startActivity(intent2);
//                    finish();
                    hSelf = et_hself.getText().toString();
                    hGaGa = sp_gaga.getSelectedItem().toString();
                    Log.v("ShareVar", hSelf + ", " + hGaGa);
                    Intent intent2 = new Intent(HelperApplyProfileActivity.this, HelperApplyFinalActivity.class);
                    intent2.putExtra("hSelf", hSelf);
                    intent2.putExtra("hGaGa", hGaGa);
                    startActivity(intent2);
                    finish();
                    break;
                case R.id.btn_helper_apply_profile_cancel:
                    finish();
                    break;
            }

        }
    };

}