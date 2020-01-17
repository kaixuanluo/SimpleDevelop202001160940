package com.android2droid.simpledevelop202001160940.mvp.base.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android2droid.simpledevelop202001160940.R;
import com.android2droid.simpledevelop202001160940.mvp.ClientApplication;


public class MyDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int mOrientation = VERTICAL_LIST;
    private int mLeft, mTop, mRight, mBottom;

    private boolean isShowLastDivider = false;

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    public MyDecoration(Context context, int left, int top, int right, int bottom) {
        this(context);
        mLeft = left;
        mTop = top;
        mRight = right;
        mBottom = bottom;
    }


    public MyDecoration(Context context) {
        mDivider = ClientApplication.getApplication().getResources().getDrawable(R.drawable.divider);
        setOrientation(VERTICAL_LIST);
    }

    public void setDividerDrawable(Context context, int dividerDrawableId) {
        mDivider = ClientApplication.getApplication().getResources().getDrawable(dividerDrawableId);
    }

    public void setShowLastDivider(boolean showLastDivider) {
        isShowLastDivider = showLastDivider;
    }

    //设置屏幕的方向
    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == HORIZONTAL_LIST) {
            drawVerticalLine(c, parent, state);
        } else {
            drawHorizontalLine(c, parent, state);
        }
    }

    //画横线, 这里的parent其实是显示在屏幕显示的这部分
    public void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft() + mLeft;
        int right = parent.getWidth() - parent.getPaddingRight() - mRight;
        int childCount = parent.getChildCount();

        if (!isShowLastDivider) {
            childCount -= 1;
        }

        for (int i = 0; i < childCount; i++) {//最后一个item不画分割线
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin + mTop / 2;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
            //Log.d("wnw", left + " " + top + " "+right+"   "+bottom+" "+i);
        }
    }

    //画竖线
    public void drawVerticalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    //由于Divider也有长宽高，每一个Item需要向下或者向右偏移
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //outRect.bottom = 30;
        if (parent.getChildPosition(view) != 0) {
            outRect.top = mTop;
        }


        /*if (mOrientation == HORIZONTAL_LIST) {
            //画横线，就是往下偏移一个分割线的高度
            outRect.set(10, 0, 10, mDivider.getIntrinsicHeight());
        } else {
            //画竖线，就是往右偏移一个分割线的宽度
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }*/
    }
}
