package com.koreait.permissionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    int REQUEST_CODE = 1;   //요청시 사용할 코드 규칙(개발자가 정한다)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void turnOn(View view){
        //나 아닌 다른앱을 호출
        //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivity(intent);

        //현재 앱에서 카메라 켜기


        //유저에게 권한 수락요청
        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.CAMERA
        },REQUEST_CODE);
    }

    //grantResults 배열의 길이가 0이 아닐 때 사용자가 수락한 것으로 본다.
    @Override
    //권한 승인 팝업에서 사용자가 선택한 정보가 있을 떄, 아래의 콜백메서드가 호출됨.
    //따라서 개발자는 이 메서드를 총해 수락, 거절에 대한 처리를 할 수 있다.
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE){   //내가 요청시 보냈던 코드가 다시 왔다면..(즉, 내 요청에 대한 응답이라면)
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){   //카메라 권한을 승인했다면
                //go ahead 하고자 했던 작업을 그대로 진행..
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("승인 결과");
                builder.setMessage("승인해주셔서 감사합니다");
                builder.setPositiveButton("예",null);
                builder.setNegativeButton("아니오",null);
                builder.create().show();

            }else{  //거절한 경우, 이 앱을 더 이상 사용할 수 없음을 알려주자
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("승인 결과");
                builder.setMessage("거절하시면 앱사용을 할 수 없습니다.");
                builder.setPositiveButton("예",null);
                builder.setNegativeButton("아니오",null);
                builder.create().show();

            }
        }
    }
}