package com.lxm.layoutmanagerdemo;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;

/**
 * Created by lixiaoming on 2018/6/22.
 */

public class PicLayoutManager extends RecyclerView.LayoutManager {

    //保存所有的Item的上下左右的偏移量信息
    private SparseArray<Rect> allItemFrames = new SparseArray<>();
    //记录Item是否出现过屏幕且还没有回收。true表示出现过屏幕上，并且还没被回收
    private SparseBooleanArray hasAttachedItems = new SparseBooleanArray();
    private int totalWidth;

    private int horizontalScrollOffset = 0;

    private Context context;

    public PicLayoutManager(Context context) {
        this.context = context;
        init();
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    private void init() {
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        totalWidth = 0;
        for (int i = 0; i < getItemCount(); i++) {
            // 遍历Recycler中保存的View取出来
            View view = recycler.getViewForPosition(i);
            childAndLayout(view, i);
            addView(view); // 因为刚刚进行了detach操作，所以现在可以重新添加
        }

        recycleAndFillItems(recycler, state);
    }

    private void childAndLayout(View view, int i) {
        measureChildWithMargins(view, 0, 0); // 通知测量view的margin值
        int width = getDecoratedMeasuredWidth(view); // 计算view实际大小，包括了ItemDecorator中设置的偏移量。
        int height = getDecoratedMeasuredHeight(view);

        Log.i("lxm", "width = " + width + "....height = " + height);
        Rect frame = allItemFrames.get(i);
        if (frame == null) {
            frame = new Rect();
        }

        if (i % 3 == 0) {
            frame.set(totalWidth, 0, totalWidth + width, height);
            totalWidth += width;
        } else if (i % 3 == 1) {
            frame.set(totalWidth, 0, totalWidth + width, height);
            totalWidth += width;
        } else if (i % 3 == 2) {
            totalWidth -= width;
            frame.set(totalWidth, height, totalWidth + width, height * 2);
            totalWidth += width;
        }
        // 将当前的Item的Rect边界数据保存
        allItemFrames.put(i, frame);
        // 由于已经调用了detachAndScrapAttachedViews，因此需要将当前的Item设置为未出现过
        hasAttachedItems.put(i, false);

    }


    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {

        detachAndScrapAttachedViews(recycler);
        //实际要滑动的距离
        int travel = dx;

        //如果滑动到最左部
        if (horizontalScrollOffset + dx < 0) {
            travel = -horizontalScrollOffset;
        } else if (horizontalScrollOffset + dx > totalWidth - getHorizontalSpace()) {//如果滑动到最底部
            travel = totalWidth - getHorizontalSpace() - horizontalScrollOffset;
        }

        horizontalScrollOffset += travel;

        // 平移容器内的item
        offsetChildrenHorizontal(-travel);
        recycleAndFillItems(recycler, state);
        return travel;
    }

    private int getHorizontalSpace() {
        return getWidth() - getPaddingRight() - getPaddingLeft();
    }

    private int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    /**
     * 回收不需要的Item，并且将需要显示的Item从缓存中取出
     */
    private void recycleAndFillItems(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.isPreLayout()) { // 跳过preLayout，preLayout主要用于支持动画
            return;
        }
        // 当前scroll offset状态下的显示区域
        Rect displayFrame = new Rect(horizontalScrollOffset, 0, horizontalScrollOffset + getHorizontalSpace(), getVerticalSpace());

        /**
         * 将滑出屏幕的Items回收到Recycle缓存中
         */
        Rect childFrame = new Rect();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            childFrame.left = getDecoratedLeft(child);
            childFrame.top = getDecoratedTop(child);
            childFrame.right = getDecoratedRight(child);
            childFrame.bottom = getDecoratedBottom(child);
            //如果Item没有在显示区域，就说明需要回收
            if (!Rect.intersects(displayFrame, childFrame)) {
                //回收掉滑出屏幕的View
                removeAndRecycleView(child, recycler);
                hasAttachedItems.put(i, false);
            }
        }

        //重新显示需要出现在屏幕的子View
        for (int i = 0; i < getItemCount(); i++) {
            if (Rect.intersects(displayFrame, allItemFrames.get(i))) {
                View scrap = recycler.getViewForPosition(i);
                measureChildWithMargins(scrap, 0, 0);
                addView(scrap);
                Rect frame = allItemFrames.get(i);
                //将这个item布局出来
                layoutDecoratedWithMargins(scrap, frame.left - horizontalScrollOffset, frame.top, frame.right - horizontalScrollOffset, frame.bottom);
                hasAttachedItems.put(i, true);
            }
        }
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        View view = recycler.getViewForPosition(0);
        if(view != null){
            measureChild(view, widthSpec, heightSpec);
            int measuredWidth = View.MeasureSpec.getSize(widthSpec);
            int measuredHeight = view.getMeasuredHeight();
            setMeasuredDimension(measuredWidth, measuredHeight);
        }
    }

}
