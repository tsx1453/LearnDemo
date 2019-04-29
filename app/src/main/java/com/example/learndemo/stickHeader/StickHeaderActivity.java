package com.example.learndemo.stickHeader;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.learndemo.BaseActivity;
import com.example.learndemo.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StickHeaderActivity extends BaseActivity {

    FrameLayout headerContainer;
    TextView headerView;
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    @Override
    protected void init() {
        headerContainer = f(R.id.header_container);
        recyclerView = f(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        headerView = (TextView) createHeaderView();
        headerContainer.addView(headerView);
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                /**
                 * 根据固定位置的Header的底部的View的数据来设置其对应的Header信息
                 */
                View v = recyclerView.findChildViewUnder(recyclerView.getWidth() >> 1, 0);
                if (v != null && v.getTag() instanceof ItemBean) {
                    ItemBean bean = (ItemBean) v.getTag();
                    headerView.setText(bean.getHeader());
                }
                /**
                 * 根据Header的bottom的底部的View来判断是否需要改变位置
                 */
                View v2 = recyclerView.findChildViewUnder(headerView.getMeasuredWidth() / 2, headerView.getMeasuredHeight());
                if (v2 != null && v2.getTag() instanceof ItemBean) {
                    ItemBean bean = (ItemBean) v2.getTag();
                    if (bean.isTitle()) {
                        int delta = v2.getTop() - headerView.getHeight();
                        if (v2.getTop() > 0) {
                            headerView.setTranslationY(delta);
                        } else {
                            headerView.setTranslationY(0);
                            headerView.setTop(0);
                        }
                    } else {
                        headerView.setTranslationY(0);
                    }
                }
                if (headerView.getTranslationY() > 0) {
                    headerView.setTranslationY(0);
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_stick_header;
    }

    private View createHeaderView() {
        TextView textView = new TextView(this);
        textView.setTextColor(Color.RED);
        textView.setBackgroundColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return textView;
    }
}
