package com.example.learndemo.bottomScale;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learndemo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    int[] res = new int[]{R.drawable.img1, R.drawable.img2};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_scale_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(String.valueOf(position));
        holder.bg.setImageResource(res[position % 2]);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView bg;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bg = itemView.findViewById(R.id.bg);
            title = itemView.findViewById(R.id.title);
        }
    }

}
