package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class ScaleRecyclerView extends RecyclerView {

    private int mSelectedPosition = 0;

    public ScaleRecyclerView(Context context) {
        super(context);
        init();
    }

    public ScaleRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScaleRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        //启用子视图排序功能
        setChildrenDrawingOrderEnabled(true);
    }

    @Override
    public void onDraw(Canvas c) {
        mSelectedPosition = getChildAdapterPosition(getFocusedChild());
        super.onDraw(c);
    }

    @Override
    public boolean isInTouchMode() {
        if (Build.VERSION.SDK_INT == 19) {
            return !(hasFocus() && !super.isInTouchMode());
        }
        return super.isInTouchMode();
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        int position = mSelectedPosition;
        if (position < 0) {
            return i;
        } else {
            if (i == childCount - 1) {
                if (position > i) {
                    position = i;
                }
                return position;
            }
            if (i == position) {
                return childCount - 1;
            }
        }
        return i;
    }
}
