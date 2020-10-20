package com.example.learndemo.recyclerview.bottomScale;

import android.graphics.Rect;
import android.view.View;

import com.example.learndemo.base.BaseActivity;
import com.example.learndemo.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BottomScaleActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void init() {
        recyclerView = f(R.id.recycler_view);
        recyclerView.setAdapter(new MyAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {
                View bg = view.findViewById(R.id.bg);
                if (bg != null) {
                    bg.setScaleY(1);
                    bg.setScaleX(1);
                }
            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                View bg = view.findViewById(R.id.bg);
                if (bg != null) {
                    bg.setScaleY(1);
                    bg.setScaleX(1);
                }
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                /**
                 * 复原其他的不需要缩放的View，避免因为快速滑动导致部分View缩放出现问题
                 */
                for (int i = 0; i < recyclerView.getChildCount() - 1; i++) {
                    View c = recyclerView.getChildAt(i);
                    View bg = c.findViewById(R.id.bg);
                    if (bg != null) {
                        bg.setScaleX(1);
                        bg.setScaleY(1);
                    }
                }
                /**
                 * 只给最后一个View设置缩放，因为做后一个就是可见的最后一个
                 */
                View c = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
                Rect rect = new Rect();
                c.getGlobalVisibleRect(rect);
                float ratio = 0.9f + ((float) rect.height() / c.getHeight()) * 0.1f;
                ratio = ratio < 0.9 ? 0.9f : ratio;
                ratio = ratio > 1 ? 1 : ratio;
                View bg = c.findViewById(R.id.bg);
                if (bg != null) {
                    bg.setScaleX(ratio);
                    bg.setScaleY(ratio);
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bottom_scale;
    }
}
