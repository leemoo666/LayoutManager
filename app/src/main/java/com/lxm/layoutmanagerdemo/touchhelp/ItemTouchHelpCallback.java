package com.lxm.layoutmanagerdemo.touchhelp;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

/**
 * Created by lixiaoming on 2018/6/28.
 */

public class ItemTouchHelpCallback extends ItemTouchHelper.Callback {

    private TouchHelpAdapter adapter;

    public ItemTouchHelpCallback(TouchHelpAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = ItemTouchHelper.LEFT;
        int dragFlag = ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        // 拖动暂不处理默认是0
        return makeMovementFlags(dragFlag, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.i("lxm","确定交换吗");
        adapter.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    /**
     * 侧滑删除后会回调的方法
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.i("lxm","确定删除吗");
        // 获取当前删除的位置
        int position = viewHolder.getAdapterPosition();
        adapter.onSwipe(position);
    }

    /**
     * 拖动选择状态改变回调
     */


    public interface OnItemCallbackListener {
        /**
         * @param fromPosition 起始位置
         * @param toPosition   移动的位置
         */
        void onMove(int fromPosition, int toPosition);

        void onSwipe(int position);
    }

}
