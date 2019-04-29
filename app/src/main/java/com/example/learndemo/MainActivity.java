package com.example.learndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.learndemo.bottomScale.BottomScaleActivity;
import com.example.learndemo.stickHeader.StickHeaderActivity;
import com.example.learndemo.zhihuAd.ZhiHuAdActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void init() {
        f(R.id.zhihu_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ZhiHuAdActivity.class));
            }
        });
        f(R.id.list_stick_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StickHeaderActivity.class));
            }
        });
        f(R.id.list_bottom_scale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BottomScaleActivity.class));
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
