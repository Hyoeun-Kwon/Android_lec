package com.aoslec.he_diary.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.aoslec.he_diary.NetworkTask.NetworkTask;
import com.aoslec.he_diary.NetworkTask.NetworkTask2;
import com.aoslec.he_diary.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiaryActivity extends AppCompatActivity {
    String urlAddr= null;
    String sdate, stitle, sdetail, myIP;
    public static String sstatus = null;

    TextView tv_diary_date;
    EditText edt_diary_detail, edt_diary_title;
    Button btn_dairy_insert,btn_diaryimg_insert;
    //WebView web_diary_status;
    ImageView Imageview_diary_status = null;

    //private final int GET_GALLERY_IMAGE = 200;
    final static String TAG = "DirayActivity";
    private final int REQ_CODE_SELECT_IMAGE = 300; // Gallery Return Code
    private String img_path = null; // ?????? file name
    private String f_ext = null;    // ?????? file extension
    File tempSelectFile;
    String devicePath = Environment.getDataDirectory().getAbsolutePath() + "/data/com.aoslec.he_diary.Activity/";
    String urlAddr_img = "http://192.168.35.20:8080/test/multipartRequest.jsp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        //??????????????? ??????(Media) ?????? ?????? ??????
        ActivityCompat.requestPermissions(DiaryActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);


        edt_diary_title = findViewById(R.id.edt_diary_title);
        edt_diary_detail = findViewById(R.id.edt_diary_detail);
        //status??????
        //web_diary_status = findViewById(R.id.web_diary_status);
        Imageview_diary_status = findViewById(R.id.imageview_diary_status);

        btn_dairy_insert = findViewById(R.id.new_diary_insert);
        btn_dairy_insert.setOnClickListener(onClickListener);

        //image????????? ??????
        btn_diaryimg_insert = findViewById(R.id.new_diaryimg_insert);
        btn_diaryimg_insert.setEnabled(false);
        Imageview_diary_status.setOnClickListener(onimageClickListener);
        //btn_diaryimg_insert.setOnClickListener(onimageClickListener);

//        btn_diaryimg_insert = (Button)findViewById(R.id.new_diaryimg_insert);
//        btn_diaryimg_insert.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType("image/*");
//                startActivityForResult(intent, GET_GALLERY_IMAGE);
//
//            }
//        });




    }//onCreate

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();

        myIP = intent.getStringExtra("myIP");
        Log.v("Message","Diary_getmyIP:"+ myIP);
        urlAddr = "http://" + myIP + ":8080/test/diaryInsertReturn.jsp?";

        tv_diary_date = findViewById(R.id.tv_diary_date);
        tv_diary_date.setText(intent.getStringExtra("date"));

        sstatus = intent.getStringExtra("status");
        //web_diary_status.loadData(htmlData(sstatus), "text/html", "UTF-8");
        Log.v("Message", "Selection Web: " + sstatus);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sdate = tv_diary_date.getText().toString();
            stitle = edt_diary_title.getText().toString();
            sdetail = edt_diary_detail.getText().toString();
            //sstatus = edt_diary_status.getText().toString();

            urlAddr = urlAddr + "date=" + sdate + "&title=" + stitle + "&detail=" + sdetail + "&status=" + sstatus;

            String result = connectInsertData();
//            if(result.equals("1")){
//                // ????????? ?????? ( 1??? ??????????????? ?????? jsp ?????? ?????? ??? ??? ????????? ?????? ?????????. )
//                Toast.makeText(DiaryActivity.this, sdate+"??? ?????????????????????.", Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(DiaryActivity.this, "????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
//            }
//            // back ????????? ?????? ?????? ????????? ?????? (= ?????? ???????????? ???????????? ??????)
            Toast.makeText(DiaryActivity.this, "??????????????? ?????????????????????.", Toast.LENGTH_SHORT).show();

            finish();
        }
    };

    private String connectInsertData(){
        String result = null;
        try {
            // NetworkTask ???????????? ?????? ?????? ?????? (??????????, ?????? ????????????????, ???????????????????)
            NetworkTask networkTask = new NetworkTask(DiaryActivity.this, urlAddr, "insert");
            Object obj = networkTask.execute().get();
            result = (String)obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

//    public String htmlData(String sstatus) {
//
//        String content =
//                "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" +
//                        "<html><head>" +
//                        "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf8\"/>" +
//                        "<head><body><center>" +
//                        //"<img src=\""+myIP+":8080/test/";
//                        "<img src=\"http://192.168.35.20:8080/test/";
//        content += sstatus + "\" alt=\"????????? ??????\" style=\"width: auto; height: 100%;\"></center></body></html>";
//        return content;
//
//    }

    View.OnClickListener onimageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                //////////////////////////////////////////////////////////////////////////////////////////////
                //
                //          Photo App.?????? ??????
                //
                //////////////////////////////////////////////////////////////////////////////////////////////
                case R.id.imageview_diary_status:
                    //Intent??? ?????? ???????????? ??????
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
                    break;
                //////////////////////////////////////////////////////////////////////////////////////////////
                //
                //           Upload
                //
                //////////////////////////////////////////////////////////////////////////////////////////////
                case R.id.new_diaryimg_insert:
                    NetworkTask2 networkTask2 = new NetworkTask2(DiaryActivity.this, Imageview_diary_status, img_path, urlAddr_img);
                    //////////////////////////////////////////////////////////////////////////////////////////////
                    //
                    //              NetworkTask Class??? doInBackground Method??? ???????????? ????????????.
                    //
                    //////////////////////////////////////////////////////////////////////////////////////////////
                    try {
                        Integer result = networkTask2.execute(100).get();
                        //////////////////////////////////////////////////////////////////////////////////////////////
                        //
                        //              doInBackground??? ??????????????? Toast??????
                        //
                        //////////////////////////////////////////////////////////////////////////////////////////////
                        switch (result){
                            case 1:
                                Toast.makeText(DiaryActivity.this, "Success!", Toast.LENGTH_SHORT).show();

                                //////////////////////////////////////////////////////////////////////////////////////////////
                                //
                                //              Device??? ????????? ?????? ?????? ??????
                                //
                                //////////////////////////////////////////////////////////////////////////////////////////////
                                File file = new File(img_path);
                                file.delete();
                                break;
                            case 0:
                                Toast.makeText(DiaryActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        //////////////////////////////////////////////////////////////////////////////////////////////
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //                   Photo App.?????? Image ????????? ????????????
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.v(TAG, "Data :" + String.valueOf(data));

        if (requestCode == REQ_CODE_SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            try {
                //???????????? URI??? ?????? ??????????????? ??????.
                img_path = getImagePathToUri(data.getData());
                Log.v(TAG, "image path :" + img_path);
                Log.v(TAG, "Data :" +String.valueOf(data.getData()));

                //???????????? ????????????????????? ??????
                Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

                //image_bitmap ?????? ????????? ???????????? ???????????? ??????????????? ?????????. width: 400 , height: 300
                Bitmap image_bitmap_copy = Bitmap.createScaledBitmap(image_bitmap, 400, 300, true);
                Imageview_diary_status.setImageBitmap(image_bitmap_copy);

                // ?????? ?????? ??? ?????? ?????????(?????? ??????, ????????? ????????? ?????? ??????)
                String date = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
                String imageName = date + "." + f_ext;
                tempSelectFile = new File(devicePath , imageName);
                OutputStream out = new FileOutputStream(tempSelectFile);
                image_bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

                // ?????? ?????? ????????? ?????? img_path ?????????
                img_path = devicePath + imageName;
                Log.v(TAG,"fileName :" + img_path);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //              ???????????? ????????? ???????????? ????????? ?????????
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String getImagePathToUri(Uri data) {

        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        //???????????? ?????? ???
        String imgPath = cursor.getString(column_index);
        Log.v(TAG, "Image Path :" + imgPath);

        //???????????? ?????? ???
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);

        // ????????? ??? ??????
        f_ext = imgPath.substring(imgPath.length()-3, imgPath.length());

        return imgPath;
    }



}//Main