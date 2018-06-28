package com.lxm.layoutmanagerdemo.second;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by lixiaoming on 2018/6/27.
 */

public class PaddingItemDecoration extends RecyclerView.ItemDecoration {
    int padding = 20;

    private Paint paint;
    private Paint yellowPaint;
    private Paint greenPaint;

    public PaddingItemDecoration() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        yellowPaint = new Paint();
        yellowPaint.setColor(Color.YELLOW);
        greenPaint = new Paint();
        greenPaint.setColor(Color.GREEN);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //outRect就是你那个item条目的矩形
        outRect.left = padding;  //相当于 设置 left padding
        outRect.top = padding;   //相当于 设置 top padding
        outRect.right = padding; //相当于 设置 right padding
        outRect.bottom = padding;  //相当于 设置 bottom padding
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //得到列表所有的条目
        int childCount = parent.getChildCount();
        //得到条目的宽和高
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            //计算每一个条目的顶点和底部 float值
            float top = view.getTop() - padding;
            float bottom = view.getBottom() + padding;
            //重新绘制
            paint.setColor(Color.BLUE);
            c.drawRect(left, top, right, bottom, paint);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        Log.i("lxm","childCount = "+childCount);
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int pos = parent.getChildAdapterPosition(child);
            boolean isLeft = pos % 2 == 0;
            if (isLeft) {
                float left = 0;
                float right = child.getLeft() / 2;
                float top = child.getTop();
                float bottom = child.getBottom();

                c.drawRect(left, top, right, bottom, yellowPaint);
            } else {
                float left = child.getRight() + padding / 2;
                float right = child.getRight() + padding;
                float top = child.getTop();
                float bottom = child.getBottom();
                c.drawRect(left, top, right, bottom, greenPaint);
            }
        }
    }

}
