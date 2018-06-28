package com.lxm.layoutmanagerdemo.sticky;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxm.layoutmanagerdemo.R;

import java.util.ArrayList;

/**
 * Created by lixiaoming on 2018/6/22.
 */

public class StickyAdapter extends RecyclerView.Adapter<StickyAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> picUrls = new ArrayList<>();

    public StickyAdapter(Context context) {
        this.context = context;
    }

    public void setPicUrls(ArrayList<String> data) {
        this.picUrls = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        // 实例化展示的view
        // 实例化viewholder
        v = LayoutInflater.from(context).inflate(R.layout.item_second, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.text.setText(picUrls.get(position));

    }

    @Override
    public int getItemCount() {
        return picUrls == null ? 0 : picUrls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }
    }

}
