package com.aoslec.haezzo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

public class KakaoLoginActivity extends AppCompatActivity {

    private ISessionCallback mSessionCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_login);
        mSessionCallback = new ISessionCallback() {
            @Override
            public void onSessionOpened() {
                // 로그인 요청
                UserManagement.getInstance().me(new MeV2ResponseCallback() {

                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        // 로그인 실패
                        Toast.makeText(KakaoLoginActivity.this, "로그인 도중에 오류가 발생 했습니다. ", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        // 세선이 닫혔을 때
                        Toast.makeText(KakaoLoginActivity.this, "세션이 닫혔습니다. 다시 시도해 주세요", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(MeV2Response result) { // 로그인 성공 시, 정보를 result에 담아서 줌
                        // 로그인 성공
                        Intent intent = new Intent(KakaoLoginActivity.this, SubActivity.class);
                        intent.putExtra("name", result.getKakaoAccount().getProfile().getNickname());
                        intent.putExtra("profileImg", result.getKakaoAccount().getProfile().getProfileImageUrl());
                        intent.putExtra("email", result.getKakaoAccount().getEmail());
                        startActivity(intent);

                        // 연결 성공 시 로그로 이름, 프로필 사진, 이메일 표시
                        Log.v("name ", result.getKakaoAccount().getProfile().getNickname()); // 이름 로그로 표시
                        Log.v("profile_img ", result.getKakaoAccount().getProfile().getProfileImageUrl()); // 프사 로그로 표시
                        Log.v("email ", result.getKakaoAccount().getEmail()); // 이메일 로그로 표시
                        Toast.makeText(KakaoLoginActivity.this, "환영 합니다 !", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onSessionOpenFailed(KakaoException exception) {
                Toast.makeText(KakaoLoginActivity.this, "onSessionOpenFailed", Toast.LENGTH_SHORT).show();
            }
        };

        Session.getCurrentSession().addCallback(mSessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();
//      getAppKeyHash();
    }//onCreate


//    카카오 로그인 시 키(해시)를 얻는 메소드 이다.
//    private void getAppKeyHash(){
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures){
//                MessageDigest md;
//                md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String something = new String(Base64.encode(md.digest(), 0));
//                Log.e("Hash key", something); // 오류 로그 형식으로 해쉬키를 찍어낸다.
//            }
//        }catch (Exception e){
//            Log.e("name not found", e.toString());
//        }
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(mSessionCallback);
    }
}//Main