package com.example.learndemo.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.text.TextUtils;

import com.example.learndemo.R;

public class SingleFragmentActivity extends BaseActivity {

    @Override
    protected void init() {
        if (getIntent() != null) {
            String className = getIntent().getStringExtra("fragment");
            if (!TextUtils.isEmpty(className)) {
                Fragment fragment = getSupportFragmentManager().getFragmentFactory().instantiate(getClassLoader(), className);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, fragment)
                        .commitNow();
                return;
            }
        }
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_fragment;
    }
}