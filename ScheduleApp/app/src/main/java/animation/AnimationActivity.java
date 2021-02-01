package animation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.koreait.scheduleapp.R;
import com.koreait.scheduleapp.ScheduleActivity;

import java.util.Set;

public class AnimationActivity extends AppCompatActivity {
    ViewPager viewPager;
    MyPagerAdapter adapter;
    LayoutInflater layoutInflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        viewPager = findViewById(R.id.viewPager);

        adapter = new MyPagerAdapter(getSupportFragmentManager(),0);

        //back 버튼 추가
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        layoutInflater = this.getLayoutInflater();


        //뷰페이지와 어댑터 연결
        viewPager.setAdapter(adapter);

    }

    //back버튼에 이벤트 연결
    @Override
    public boolean onSupportNavigateUp() {
        //현재 액티비티는 스택에서 제거
        this.finish();//현재 액티비티는 스택에서 제거!!
        return true;
    }


}