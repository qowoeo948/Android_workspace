package com.koreait.mapproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    SupportMapFragment mapFragment;
    GoogleMap googleMap;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //XML에 명시한 맵 프레그먼트 얻기
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        handler = new Handler(Looper.getMainLooper()){
            //쓰레드로 반영하고 싶은 UI 작업은 핸들러가 대신해준다!
            public void handleMessage(@NonNull Message message) {
                MarkerOptions options = (MarkerOptions)message.getData().get("options");
                googleMap.addMarker(options);  //맵에 옵션 추가
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(options.getPosition()));  //카메라 시점을 지정한 위치로 이동
            }
        };
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        Thread thread = new Thread(){
            @Override
            public void run() {
                setMarker();
            }
        };
        thread.start();
    }

    public void setMarker(){
        // Add a marker in Sydney and move the camera
        LatLng pos = new LatLng(37.504206, 127.048003);
        MarkerOptions options = new MarkerOptions();
        options.position(pos);

        URL url = null;
        Bitmap bitmap = null;
        try {
            url = new URL("https://cdn2.iconfinder.com/data/icons/bitsies/128/FlagYellow-128.png");
            bitmap = BitmapFactory.decodeStream(url.openStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
        options.icon(bitmapDescriptor);

        //핸들러에게 부탁
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putParcelable("options",options);
        message.setData(bundle);
        handler.sendMessage(message);

    }
}