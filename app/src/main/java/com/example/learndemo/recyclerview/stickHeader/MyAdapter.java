package com.example.learndemo.recyclerview.stickHeader;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemBean> mData = new ArrayList<>();

    public MyAdapter() {
        for (int i = 0; i < 40; i++) {
            if (i % 5 == 0) {
                ItemBean bean = new ItemBean();
                bean.setTitle(true);
                bean.setContent("title " + ((int) (i / 5)));
                bean.setHeader("title " + ((int) (i / 5)));
                mData.add(bean);
            }
            ItemBean bean = new ItemBean();
            bean.setIndex(i);
            bean.setTitle(false);
            bean.setContent("content " + i);
            bean.setHeader("title " + ((int) (i / 5)));
            mData.add(bean);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView view = new TextView(parent.getContext());
        if (viewType == 0) {
            view.setTextSize(16);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
            return new NormalViewHolder(view);
        } else {
            view.setTextColor(Color.RED);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            view.setGravity(Gravity.CENTER);
            return new TitleViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemBean bean = mData.get(position);
        if (getItemViewType(position) == 1) {
            TitleViewHolder th = (TitleViewHolder) holder;
            th.c.setText(bean.getContent());
        } else {
            NormalViewHolder nh = (NormalViewHolder) holder;
            nh.c.setText(bean.getContent());
        }
        holder.itemView.setTag(bean);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position).isTitle()) {
            return 1;
        }
        return 0;
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {

        TextView c;

        public NormalViewHolder(@NonNull View itemView) {
            super(itemView);
            c = (TextView) itemView;
        }
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView c;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            c = (TextView) itemView;
        }
    }
}
