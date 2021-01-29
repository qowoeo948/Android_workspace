package com.koreait.actionbarapp.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koreait.actionbarapp.MainActivity;
import com.koreait.actionbarapp.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GalleryAdapter extends BaseAdapter {
    String TAG = this.getClass().getName();
    MainActivity mainActivity;
    ArrayList<Gallery> galleryList = new ArrayList<Gallery>();
    LayoutInflater layoutInflater;

    public GalleryAdapter(MainActivity mainActivity){
        this.mainActivity = mainActivity;   //인플레이터를 얻기위해
        layoutInflater=mainActivity.getLayoutInflater();
    }


    public int getCount() {
        return galleryList.size();
    }

    public Object getItem(int position) {
        return galleryList.get(position);
    }

    public long getItemId(int position) {
        Gallery gallery = galleryList.get(position);
        return gallery.getGallery_id();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;//미 메서드에서 최종 반환할 뷰
        if(convertView==null){   //레이아웃 뷰가 존재하지 않는다면, 인플레이션 시킴
            //false의 의미: 지정한 parent에 부착하지 않고, 인플레이션 대상 xml의 최상위를 반환
            view = layoutInflater.inflate(R.layout.item_gallery,parent,false);
        }else{  //이미 존재한다면, 기존 뷰 그대로 재사용
            view = convertView;
        }

        //리스트에 들어있는 position번쨰 Galley추출
        Gallery gallery = galleryList.get(position);

        ImageView img = view.findViewById(R.id.img);
        TextView t_title = view.findViewById(R.id.t_title);

        img.setImageBitmap(gallery.getBitmap());
        t_title.setText(gallery.getTitle());

        view.setOnClickListener(e->{
            Toast.makeText(mainActivity,gallery.getGallery_id()+"선택했어?",Toast.LENGTH_SHORT).show();
        });

        return view;

    }

}
