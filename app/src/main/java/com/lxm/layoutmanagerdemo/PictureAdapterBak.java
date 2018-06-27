package com.lxm.layoutmanagerdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by lixiaoming on 2018/6/22.
 */

public class PictureAdapterBak extends RecyclerView.Adapter<PictureAdapterBak.ViewHolder> {

    private Context context;
    private ArrayList<String> picUrls = new ArrayList<>();

    public PictureAdapterBak(Context context) {
        this.context = context;
    }

    public void setPicUrls(ArrayList<String> data) {
        this.picUrls = data;
        notifyDataSetChanged();
    }

    private static final int TYPE_WIDTHER = 1;
    private static final int TYPE_NORMAL = 2;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        if (viewType == TYPE_WIDTHER) {
            v = LayoutInflater.from(context).inflate(R.layout.item_big, parent, false);
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        }
        // 实例化展示的view
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 0) {
            return TYPE_WIDTHER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("lxm", "click = " + position);
            }
        });

        // 绑定数据
        Glide.with(context).load(picUrls.get(position)).into(holder.ivPicture);
    }

    @Override
    public int getItemCount() {
        return picUrls == null ? 0 : picUrls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPicture;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.ivPicture);
        }
    }

}
