package com.study.boardapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//보여질 데이터가 단순한 텍스트 뿐이라면, ArrayAdapter를 사용하면 된다.
//하지만 ListView의 각 아이템에 들어갈 데이터 형태는 대부분 복잡하고 복합적으로
//이루어져 있기 때문에, 개발자가 정의한 UI를 반영하려면 어댑터를 재정의하면 된다.
// 참고) 안드로이드의 Adapter는 자바 Swing의 TableModel과 그 역할이 완전 같다!!
public class BoardAdapter extends BaseAdapter {
    String TAG = this.getClass().getName();
    MainActivity mainActivity;
    List<String> data = new ArrayList<String>();

    LayoutInflater layoutInflater; //xml을 자바 객체화시켜주는 객체


    public BoardAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        layoutInflater  = mainActivity.getLayoutInflater();

        data.add("사과");
        data.add("바나나");
        data.add("딸기");
        data.add("오렌지");
        data.add("파인애플");
        data.add("멜론");
        data.add("레몬");
        data.add("귤");
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /*
    * 아래의 getView메서드는 getCount()만큼 호출된다
    * 호출하면서, 리스트뷰의 각 칸(아이템)을 차지하게 될 View를 반환해간다!!
    * 파라미터 1) position은 getCount()만큼 getView메서드가 호출될 때 자동증가 되는 값
    * 파라미터 2)
    * */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView란? 리스트뷰의 각 아이템을 차지하는 뷰가 새롭게 태어날떄는 convertView(기존뷰)
        //값이 존재하지 않는다.
        //하지만 일단 태어나고, 화면에서 가려진 후 다시 등장할 때는 새로운 아이템이 아니라 기존의 아이템
        //이므로, 자신이 부여받았던 뷰 주소가 convertView에 실려져있다.
        //따라서 개발자는 화면에 가려졌다가 다시 보여지는 아이템들에 대해서는 굳이 새로운 뷰를 new하지말고
        //기존에 아이템 본인이 가지고 있었던 기존뷰(old view)를 재사용하는 것이 메모리 효율상 좋다.

        View view = null;
        if(convertView==null){   //학생증 발급이 필요한 애들은 ( 즉 새롭게 태어난 애들..)

            //여긴 예시로 공부목적상 해본거
//            TextView tv = new TextView(mainActivity);
//            tv.setText(data.get(position));
//            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,300);
//            tv.setLayoutParams(params);
//            view = tv;


            //여기서부터 제대로 구현
            //여기서 리스트뷰에 보여질 하나의 아이템을 이룰 복합 뷰를 생성한다!!(xml로 부터 인플레이션 시키자!!)
            //false라고 해야, parent인 ListView가 아닌, 인플레이션 대상 xml의 최상위 뷰그룹이 반환된다. 즉 아이템의 최상위
            View parentView = layoutInflater.inflate(R.layout.board_item,parent,false);
            Log.d(TAG,"parentView is "+parentView);

            view = parentView;
        }else{  //이미 학생증 발급받은 애는... 지가 가지고 있었던 학생증 아이디 대입
            view = convertView;
        }

        Log.d(TAG,data.get(position)+"의 들어갈 뷰는 "+view+", parent는 "+parent);
        Log.d(TAG,data.get(position)+"의 convertView는 "+convertView);


        return view;
    }
}
