package com.aoslec.project_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private View loginButton, logoutButton;
    private TextView nickName;
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.login);
        logoutButton = findViewById(R.id.logout);
        nickName = findViewById(R.id.nickname);
        profileImage = findViewById(R.id.profile);

        //callback
        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>(){
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if(oAuthToken != null){
                    //TBD
                }
                if(throwable != null){
                    //TBD
                }
                updateKakaoLoginUi();
                return null;
            }
        };



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //카톡 설치 여부
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(MainActivity.this)){
                    UserApiClient.getInstance().loginWithKakaoTalk(MainActivity.this, callback);

                }else{
                    UserApiClient.getInstance().loginWithKakaoAccount(MainActivity.this,callback);
                }
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        updateKakaoLoginUi();
                        return null;
                    }
                });
            }
        });

        updateKakaoLoginUi();

    }//onCreate

    //로그인 아니면 로그인 버튼이 보이게
    //로그인이 되어있는지 확인이 되면 프로필 및 로그아웃 버튼 뜨게
    private void updateKakaoLoginUi(){
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                //login 되어있을때
                if(user != null){

                    Log.i(TAG, "invoke id=" + user.getId());
                    Log.i(TAG, "invoke email=" + user.getKakaoAccount().getEmail());
                    Log.i(TAG, "invoke nickname=" + user.getKakaoAccount().getProfile().getNickname());
                    Log.i(TAG, "invoke gender=" + user.getKakaoAccount().getGender());
                    Log.i(TAG, "invoke age=" + user.getKakaoAccount().getAgeRange());


                    nickName.setText(user.getKakaoAccount().getProfile().getNickname());
                    Glide.with(profileImage).load(user.getKakaoAccount().getProfile().getThumbnailImageUrl()).circleCrop().into(profileImage);
                    //user.getKakaoAccount().getProfile().getThumbnailImageUrl()).into(profilewImage);


                    //로그인 버튼은 없애고, 로그아웃버튼은 보이게 해라
                    loginButton.setVisibility(View.GONE);
                    logoutButton.setVisibility(View.VISIBLE);

                }else{

                    nickName.setText(null);
                    profileImage.setImageBitmap(null);
                    //로그인 안되어있으면 로그인버튼을 보이게하고, 로그아웃버튼은 없애라
                    loginButton.setVisibility(View.VISIBLE);
                    logoutButton.setVisibility(View.GONE);



                }




                return null;
            }
        });
    }//updateKakaoLogin

}//Main