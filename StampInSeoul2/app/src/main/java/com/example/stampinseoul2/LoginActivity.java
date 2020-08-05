package com.example.stampinseoul2;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.ApiErrorCode;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;
import com.kakao.usermgmt.LoginButton;


public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private LoginButton btn_kakao_login;
    private SessionCallback sessionCallback;
    public Long userId=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);

        btn_kakao_login = findViewById(R.id.btn_kakao_login);

        //++++++++++++++ 여기부터 카카오 +++++++++++++++

        sessionCallback = new SessionCallback(); //세션 초기화
        Session.getCurrentSession().addCallback(sessionCallback); //현재 세션에 콜백넣음
        Session.getCurrentSession().checkAndImplicitOpen(); // 자동으로 로그인됨.

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_kakao_login.performClick();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode,resultCode,data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {

            //유저의 정보를 받아오는 함수 이름
            UserManagement.getInstance().me(new MeV2ResponseCallback() {

                //로그인에 실패했을 경우
                @Override
                public void onFailure(ErrorResult errorResult) {
                    int result = errorResult.getErrorCode();

                    if(result == ApiErrorCode.CLIENT_ERROR_CODE) {
                        Toast.makeText(getApplicationContext(), "네트워크가 불안정합니다. 다시 시도하시기 바랍니다", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "로그인 도중 오류 발생 :" + errorResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                }//end of failure

                //로그인 중에 세션이 비정상적으로 닫혔을 때 띄워주기.
                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    Toast.makeText(getApplicationContext(), "세션이 닫혔습니다. 다시 시도하시기 바랍니다: " + errorResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
                //로그인에 성공했을 때 MeV2Response 객체 넘어오는데, 로그인한 유저의 정보를 담고 있는 중요객체.
                @Override
                public void onSuccess(MeV2Response result) {

                    try{
                        MainActivity.dbHelper = new DBHelper(getApplicationContext());
                        //헬퍼에 있는 데이터베이스를 읽어온다.
                        MainActivity.db = MainActivity.dbHelper.getWritableDatabase();

                        userId = result.getId();

                        String insertUserInfo = "INSERT or REPLACE INTO userTBL values('"
                                + result.getId() + "' , '"
                                + result.getNickname() + "' , '"
                                + result.getProfileImagePath() + "');";

                        MainActivity.db.execSQL(insertUserInfo);

                        //새로 로그인한 유저의 전옹 테이블 2개 생성
                        String createZzimTBL = "CREATE TABLE IF NOT EXISTS ZZIM_" + result.getId() + "("
                                + "title TEXT PRIMARY KEY,"
                                + "addr TEXT, "
                                + "MAPx REAL, "
                                + "mapY REAL, "
                                + "firstImage TEXT); ";

                            MainActivity.db.execSQL(createZzimTBL);

                        String createStampTBL = "CREATE TABLE IF NOT EXISTS STAMP_" + result.getId() + "("
                                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                                + "title TEXT, "
                                + "addr TEXT, "
                                + "mapX REAL, "
                                + "mapY REAL, "
                                + "firstImage TEXT, "
                                + "picture TEXT, "
                                + "content_pola TEXT, "
                                + "content_title TEXT, "
                                + "contents TEXT, "
                                + "complete INTEGER);";

                        MainActivity.db.execSQL(createStampTBL);

                    }catch(SQLiteConstraintException e) {

                    }

                    Log.d("TAG", userId.toString());

                    Intent intent = new Intent(getApplicationContext(),ThemeActivity.class);
                    intent.putExtra("name",result.getNickname()); //유저 닉네임
                    intent.putExtra("profile",result.getProfileImagePath()); //카카오톡 프로필 이미지
                    intent.putExtra("id",result.getId());

                    startActivity(intent);
                    finish();
                }
            });//end of 세션콜백
        }//end of open

        @Override
        public void onSessionOpenFailed(KakaoException e) {
            Toast.makeText(getApplicationContext(), "로그인 도중 오류가 발생했습니다. 인터넷 연결을 확인해주세요: "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}

