package com.example.learndemo.recyclerview.zhihuAd;

import android.graphics.Rect;
import android.view.View;

import com.example.learndemo.base.BaseActivity;
import com.example.learndemo.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ZhiHuAdActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void init() {
        recyclerView = f(R.id.recycler_view);
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /**
         * 核心部分，找出当前展示的View中是Ad的那个，根据其在屏幕的位置来设置对应动画的缩放（或者说展示）比例
         */
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Rect rect = new Rect();
                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    View child = recyclerView.getChildAt(i).findViewById(R.id.background);
                    if (child != null && child.getTag() instanceof ItemBean && child instanceof AdShowView) {
                        ItemBean bean = (ItemBean) child.getTag();
                        if (bean.isAd()) {
                            AdShowView liv = (AdShowView) child;
                            // 获取View在屏幕中的位置
                            recyclerView.getGlobalVisibleRect(rect);
                            int rtop = rect.top;
                            int rheight = rect.height();
                            child.getGlobalVisibleRect(rect);
                            int ctop = rect.top;
                            int cheight = rect.height();
                            if (ctop > rtop && cheight < child.getHeight()) {
                                liv.setRatio(0f);
                            } else if (cheight < child.getHeight()) {
                                liv.setRatio(1f);
                            } else {
                                int th = rheight - child.getHeight();
                                int lh = ctop - rtop;
                                liv.setRatio(1 - (float) lh / th);
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhi_hu_ad;
    }
}
