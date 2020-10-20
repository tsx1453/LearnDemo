package com.example.learndemo.recyclerview.zhihuAd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.learndemo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemBean> mData = new ArrayList<>();

    public MyAdapter() {
        for (int i = 0; i < 20; i++) {
            ItemBean itemBean = new ItemBean(i);
            if (i == 6) {
                itemBean.setAd(true);
            }
            mData.add(itemBean);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new AdViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.zhihu_item_layout, parent, false));
        }
        return new NormalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.zhihu_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemBean itemBean = mData.get(position);
        if (getItemViewType(position) == 1) {
            AdViewHolder ah = (AdViewHolder) holder;
            ah.title.setText(itemBean.getTitle());
            ah.bg.setRes(R.drawable.img1, R.drawable.img2);
            ah.bg.setTag(itemBean);
        } else {
            NormalViewHolder nh = (NormalViewHolder) holder;
            nh.title.setText(itemBean.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position).isAd()) {
            return 1;
        }
        return 0;
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {

        AdShowView bg;
        TextView title;

        NormalViewHolder(@NonNull View itemView) {
            super(itemView);
            bg = itemView.findViewById(R.id.background);
            title = itemView.findViewById(R.id.title);
        }
    }

    class AdViewHolder extends RecyclerView.ViewHolder {

        AdShowView bg;
        TextView title;

        AdViewHolder(@NonNull View itemView) {
            super(itemView);
            bg = itemView.findViewById(R.id.background);
            title = itemView.findViewById(R.id.title);
        }
    }

}
