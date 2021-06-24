package com.aoslec.haezzo.HelperApplyActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.aoslec.haezzo.R;
import com.aoslec.haezzo.ShareVar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelperApplyIdCardActivity extends AppCompatActivity {

    ShareVar shareVar = new ShareVar();

    public static String hIdCardImage;

    Button buttonBack, buttonOK, buttonCancel;

    ImageView iv_hidcardimage;

    private final int REQ_CODE_SELECT_IMAGE = 300; // Gallery Return Code
    private String img_path = null; // 최종 file name
    private String f_ext = null;    // 최종 file extension
    File tempSelectFile;

    String devicePath = Environment.getDataDirectory().getAbsolutePath() + "/data/com.aoslec.haezzo/";
    String urlAddr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_apply_id_card);

        ActivityCompat.requestPermissions(HelperApplyIdCardActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        ActivityCompat.requestPermissions(HelperApplyIdCardActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MODE_PRIVATE);

        iv_hidcardimage = findViewById(R.id.iv_hidcardimage);

        buttonBack = findViewById(R.id.btn_helper_apply_idcard_back);
        buttonOK = findViewById(R.id.btn_helper_apply_idcard_ok);
        buttonCancel = findViewById(R.id.btn_helper_apply_idcard_cancel);

        iv_hidcardimage.setOnClickListener(onClickListener);
        buttonBack.setOnClickListener(onClickListener);
        buttonOK.setOnClickListener(onClickListener);
        buttonCancel.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_hidcardimage:
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
                    break;
                case R.id.btn_helper_apply_idcard_back:
                    Intent intent2 = new Intent(HelperApplyIdCardActivity.this, HelperApplyAccountActivity.class);
                    startActivity(intent2);
                    finish();
                    break;
                case R.id.btn_helper_apply_idcard_ok:
                    Intent intent3 = new Intent(HelperApplyIdCardActivity.this, HelperApplyProfileImageActivity.class);
                    startActivity(intent3);
                    finish();
                    break;
                case R.id.btn_helper_apply_idcard_cancel:
                    finish();
//                    HelperApplyAccountActivity.activityAccount.finish();
                    break;
            }

        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQ_CODE_SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            try {
                //이미지의 URI를 얻어 경로값으로 반환.
                img_path = getImagePathToUri(data.getData());

                //이미지를 비트맵형식으로 반환
                Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                int height = image_bitmap.getHeight();
                int width = image_bitmap.getWidth();

                Bitmap image_bitmap_copy = null;


                //image_bitmap 으로 받아온 이미지의 사이즈를 임의적으로 조절함. width: 400 , height: 300
//                Bitmap image_bitmap_copy = Bitmap.createScaledBitmap(image_bitmap, 400, 300, true);
                while (width > 400){
                    image_bitmap_copy = Bitmap.createScaledBitmap(image_bitmap, 400, (height*400)/width, true);
                    height = image_bitmap_copy.getHeight();
                    width = image_bitmap_copy.getWidth();
                }
                iv_hidcardimage.setImageBitmap(image_bitmap_copy);

                // 파일 이름 및 경로 바꾸기(임시 저장, 경로는 임의로 지정 가능)
                String date = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
                hIdCardImage = date + "." + f_ext;
                tempSelectFile = new File(devicePath , hIdCardImage);
                OutputStream out = new FileOutputStream(tempSelectFile);
                image_bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

                // 임시 파일 경로로 위의 img_path 재정의
                img_path = devicePath + hIdCardImage;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String getImagePathToUri(Uri data) {

        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        //이미지의 경로 값
        String imgPath = cursor.getString(column_index);

        //이미지의 이름 값
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);

        // 확장자 명 저장
        f_ext = imgPath.substring(imgPath.length()-3, imgPath.length());

        return imgPath;
    }

}