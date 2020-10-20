package com.example.learndemo;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learndemo.base.BaseActivity;
import com.example.learndemo.base.SingleFragmentActivity;
import com.example.learndemo.recyclerview.bottomScale.BottomScaleActivity;
import com.example.learndemo.recyclerview.stickHeader.StickHeaderActivity;
import com.example.learndemo.recyclerview.zhihuAd.ZhiHuAdActivity;
import com.example.learndemo.virtual_display.VirtualDisplayDemo;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private List<HomeItem> itemList = Arrays.asList(
            new HomeItem("仿知乎广告", ZhiHuAdActivity.class, "根据滚动状态双广告切换"),
            new HomeItem("分组吸顶", StickHeaderActivity.class, "RecyclerView分组Header吸顶实现"),
            new HomeItem("底部Item动画", BottomScaleActivity.class, "根据滚动状态缩放最后一个View"),
            new HomeItem("VirtualDisplay", "VirtualDisplay基本使用", VirtualDisplayDemo.class)
    );

    @Override
    protected void init() {
        recyclerView = f(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(itemList));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    static class HomeItem {
        public String title;
        public String description;
        public Class<? extends Fragment> fragmentClass;
        public Class<? extends Activity> activityClass;

        public HomeItem() {
        }

        public HomeItem(String title, Class<? extends Fragment> fragmentClass) {
            this.title = title;
            this.fragmentClass = fragmentClass;
        }

        public HomeItem(String title, String description, Class<? extends Fragment> fragmentClass) {
            this.title = title;
            this.description = description;
            this.fragmentClass = fragmentClass;
        }

        public HomeItem(String title, Class<? extends Activity> activityClass, String description) {
            this.title = title;
            this.description = description;
            this.activityClass = activityClass;
        }

        public HomeItem activity(Class<? extends Activity> clazz) {
            this.activityClass = clazz;
            return this;
        }

        public HomeItem fragment(Class<? extends Fragment> clazz) {
            this.fragmentClass = clazz;
            return this;
        }
    }

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
        private List<HomeItem> items;
        private LayoutInflater inflater;

        public Adapter(List<HomeItem> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (inflater == null) {
                inflater = LayoutInflater.from(parent.getContext());
            }
            return new ViewHolder(inflater.inflate(R.layout.home_item_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            HomeItem item = items.get(position);
            holder.title.setText(item.title);
            if (TextUtils.isEmpty(item.description)) {
                holder.desc.setVisibility(View.VISIBLE);
            } else {
                holder.desc.setVisibility(View.VISIBLE);
                holder.desc.setText(item.description);
            }
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView title;
            TextView desc;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title);
                desc = itemView.findViewById(R.id.desc);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                HomeItem item = items.get(getAdapterPosition());
                Intent intent;
                if (item.activityClass != null) {
                    intent = new Intent(MainActivity.this, item.activityClass);
                } else {
                    intent = new Intent(MainActivity.this, SingleFragmentActivity.class);
                }
                if (item.fragmentClass != null) {
                    intent.putExtra("fragment", item.fragmentClass.getName());
                }
                intent.putExtra("title", item.title);
                startActivity(intent);
            }
        }
    }


}
