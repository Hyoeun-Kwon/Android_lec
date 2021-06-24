package com.aoslec.haezzo.HelperApplyActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aoslec.haezzo.NetworkTask.HelperApplyDataNetworkTask;
import com.aoslec.haezzo.NetworkTask.HelperApplyImageNetworkTask;
import com.aoslec.haezzo.R;
import com.aoslec.haezzo.ShareVar;

import java.io.File;

public class HelperApplyFinalActivity extends AppCompatActivity {

    ShareVar shareVar = new ShareVar();

    TextView tv_hself, tv_hgaga;

    Button buttonBack, buttonOK, buttonCancel;

    String hAccountImage = ShareVar.hAccountImage;
    String hName = ShareVar.hName;
    String hBank = ShareVar.hBank;
    String hAccount = ShareVar.hAccount;
    String hIdCardImage = ShareVar.hIdCardImage;
    String hProfileImage = ShareVar.hProfileImage;
    String hSelf = ShareVar.hSelf;
    String hGaGa = ShareVar.hGaGa;

    String hSelf2, hGaGa2;

    String urlAddr = null;

    String devicePath = Environment.getDataDirectory().getAbsolutePath() + "/data/com.aoslec.haezzo/";

    String img_path1 = devicePath + hAccountImage;
    String img_path2 = devicePath + hIdCardImage;
    String img_path3 = devicePath + hProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_apply_final);

        tv_hself = findViewById(R.id.tv_hself);
        tv_hgaga = findViewById(R.id.tv_hgaga);

        Intent intent = getIntent();
        hSelf2 = intent.getExtras().getString("hSelf");
        hGaGa2 = intent.getExtras().getString("hGaGa");

        tv_hself.setText(hSelf2);
        tv_hgaga.setText(hGaGa2);

        buttonBack = findViewById(R.id.btn_helper_apply_final_back);
        buttonOK = findViewById(R.id.btn_helper_apply_final_ok);
        buttonCancel = findViewById(R.id.btn_helper_apply_final_cancel);

        buttonBack.setOnClickListener(onClickListener);
        buttonOK.setOnClickListener(onClickListener);
        buttonCancel.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_helper_apply_final_back:
                    Intent intent = new Intent(HelperApplyFinalActivity.this, HelperApplyProfileActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.btn_helper_apply_final_ok:
                    urlAddr = "http://"+ shareVar.macIP + ":8080/test/jsp/helperMultipartRequest.jsp";
                    HelperApplyImageNetworkTask networkTask1 = new HelperApplyImageNetworkTask(HelperApplyFinalActivity.this, urlAddr, img_path1, null);
//                    HelperApplyImageNetworkTask networkTask2 = new HelperApplyImageNetworkTask(HelperApplyFinalActivity.this, urlAddr, img_path2, null);
//                    HelperApplyImageNetworkTask networkTask3 = new HelperApplyImageNetworkTask(HelperApplyFinalActivity.this, urlAddr, img_path3, null);
                    try {
                        Integer result1 = networkTask1.execute(100).get();

                        connectInsertData(hAccountImage);
//                        Integer result2 = networkTask2.execute(100).get();
//                        Integer result3 = networkTask3.execute(100).get();
                        switch (result1) {
                            case 1:
//                                connectInsertData(hAccountImage);
//                                connectInsertData(hIdCardImage);
//                                connectInsertData(hProfileImage);

                                Toast.makeText(HelperApplyFinalActivity.this, "Success!", Toast.LENGTH_SHORT).show();

                                File file1 = new File(img_path1);
//                                File file2 = new File(img_path2);
//                                File file3 = new File(img_path3);
                                file1.delete();
                                break;
                            case 0:
                                Toast.makeText(HelperApplyFinalActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                break;

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finish();
                    break;
                case R.id.btn_helper_apply_final_cancel:
                    finish();
                    break;
            }

        }
    };

    private String connectInsertData(String image){
        String result = null;
        try{
            urlAddr = "http://"+ shareVar.macIP + ":8080/test/Haezzo/helperInsert.jsp?haccountimage=" + image;
            HelperApplyDataNetworkTask networkTask1 = new HelperApplyDataNetworkTask(HelperApplyFinalActivity.this, urlAddr);
            Object obj = networkTask1.execute().get();
            result = (String)obj;

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

} // ----