package com.aoslec.haezzo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.aoslec.haezzo.DocumentActivity.HaezulgaeListActivity;
import com.aoslec.haezzo.DocumentActivity.WriteDocumentActivity;
import com.aoslec.haezzo.HelperApplyActivity.HelperApplyIntroActivity;
import com.aoslec.haezzo.LoginActivity.GpsTracker;
import com.aoslec.haezzo.UserHelperActivity.HelperListActivity;
import com.aoslec.haezzo.UserHelperActivity.MyHaezzoListActivity;
import com.aoslec.haezzo.UserHelperActivity.MypageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String strNick, strProfileImg, strEmail, strGender, strAgeRange, strAddress;
    Button main_writeDocument;
    ImageButton main_btnHaezzo, main_btnHaezulgae;

    BottomNavigationView main_bottomNavigationView;

    // --------------GPS 사용을 위한 코드 6월 18일
    private GpsTracker gpsTracker;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ----------GPS 현재 위치 찾기, 6월 18일
        if (!checkLocationServicesStatus()){
            showDialogForLocationServiceSetting();
        }else {
            checkRunTimePermission();
        }

//        final TextView tv_address = findViewById(R.id.tv_address); // 좌표 출력
//        final TextView tv_location = findViewById(R.id.tv_location); // 좌표 출력

        gpsTracker = new GpsTracker(MainActivity.this);

        double latitude = gpsTracker.getLatitude();
        double longitude = gpsTracker.getLongitude();

        String address = getCurrentAddress(latitude, longitude);
        // 전역 변수 strAddress에 현재 주소 값 입력
        strAddress = address;

//        tv_address.setText(address);
//        tv_location.setText("위도 : " + latitude + " 경도 : " + longitude);


        // ----------카카오 지도 api, 현재 위치 마커 찍기 6월 18일
//        MapView mapView = new MapView(this);
//        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
//
//        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.clayout_map);
//        mapViewContainer.addView(mapView);
//
//        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
//        //mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true); // 좌표
//        mapView.setZoomLevel(5,true); // 줌 레벨
//
//        // 마커 찍는 부분
//        MapPOIItem customMarker = new MapPOIItem();
//        customMarker.setItemName("나!"); // 마커 이름
//        customMarker.setTag(0);
//        customMarker.setMapPoint(mapPoint);
//        customMarker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본 제공 마커 모양
//        customMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커 클릭 시, 기본 제공 RedPin 마커 모양
//        mapView.addPOIItem(customMarker);

        //Intent 값 넘겨오기
        Intent intent = getIntent();
        strNick = intent.getStringExtra("nickname");
        strEmail = intent.getStringExtra("email");
        strProfileImg = intent.getStringExtra("profileImg");
        strGender = intent.getStringExtra("gender");
        strAgeRange = intent.getStringExtra("agerange");


        //해쭤 버튼 누르면 헬퍼보이는 리스트로 넘어가기
        main_btnHaezzo = findViewById(R.id.main_btnHaezzo);
        main_btnHaezzo.setOnClickListener(onClickListener);

        //해줄게 버튼 누르면 얼러트 뜨기
        main_btnHaezulgae = findViewById(R.id.main_btnHaezulgae);
        main_btnHaezulgae.setOnClickListener(onClickListener);

        //요청하기 버튼 눌렀을 때
        main_writeDocument = findViewById(R.id.main_writeDocument);
        main_writeDocument.setOnClickListener(onClickListener);

        //바틈 네비게이션 뷰 눌렀을때
        main_bottomNavigationView = (BottomNavigationView)findViewById(R.id.main_bottom_navigation);
        main_bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }//onCreate

    //    -------------- GPS 기능 위한 메소드 들
    void checkRunTimePermission(){
        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음



        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(MainActivity.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    } // checkRunTimePermission

    public String getCurrentAddress( double latitude, double longitude) {

        // GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";
        }

        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";
        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString()+"\n";

    } // getCurrentAddress

    // GPS 활성화를 위한 메소드
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    } // showDialogForLocationServiceSetting

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    } // onActivityResult

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    } // checkLocationServicesStatus



    // 바텀 네비게이션
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    // mTextMessage.setText(R.string.title_home);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;

                case R.id.navigation_list:
                    startActivity(new Intent(getApplicationContext(), MyHaezzoListActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;

                case R.id.navigation_mypage:
                    Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                    intent.putExtra("nickname", strNick);
                    intent.putExtra("email",strEmail);
                    intent.putExtra("profileImg",strProfileImg);
                    intent.putExtra("gender", strGender);
                    intent.putExtra("agerange", strAgeRange);
                    intent.putExtra("address", strAddress);
                    startActivity(intent);

                    finish();
                    overridePendingTransition(0,0);
                    return true;

                //                startActivity(new Intent(getApplicationContext(), MypageActivity.class));
                //                finish();
                //                overridePendingTransition(0,0);
                //                return true;
            }
            return false;
        }
    };


    //해쭤 버튼 클릭 시
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.main_btnHaezzo:
                    Intent intent = new Intent(MainActivity.this, HelperListActivity.class);
                    startActivity(intent);
                    break;
                case R.id.main_btnHaezulgae:
                    new AlertDialog.Builder(MainActivity.this)
                            .setIcon(R.mipmap.ic_launcher)
                            .setTitle("헬퍼 등록")
                            .setMessage("'해줄게' 이용하시려면 \n 헬퍼가 되야 해요. \n 등록하시겠어요?")
                            .setNegativeButton("아니오", null )
                            .setPositiveButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                  Intent intent2 = new Intent(MainActivity.this, HaezulgaeListActivity.class);
                                  startActivity(intent2);

                                  //Intent intent2 = new Intent(MainActivity.this, HelperApplyIntroActivity.class);
                                  //startActivity(intent2);
                                }
                            })
                            .show();
                    break;
                case  R.id.main_writeDocument:
                    Intent intent3 = new Intent(MainActivity.this, WriteDocumentActivity.class);
                    startActivity(intent3);
            }
        }
    };



}//