package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import com.android2droid.simpledevelop202001160940.mvp.base.util.Debug;

/**
 * @author created by luokaixuan
 * @date 2019/10/12
 * 这个类是用来干嘛的
 */
public class RvPageVScrollController extends RvPageScrollController {

    @Override
    protected float getPageDistance() {
        return mRcv.getHeight();
    }

    @Override
    protected void scrollDistance(float dx, float dy, int lastScrollPosition) {
        super.scrollDistance(dy, lastScrollPosition);
    }

    @Override
    protected void touchScrollToPrePage(int position) {
//        super.touchScrollToPrePage(position);
    }

    @Override
    protected void touchScrollToCurrentPage(int position) {
//        super.touchScrollToCurrentPage(position);
    }

    @Override
    protected void touchScrollToNextPage(int position) {
//        super.touchScrollToNextPage(position);
    }

    @Override
    public void setLastVisiblePosition(int mLastVisiblePosition) {
        super.setLastVisiblePosition(mLastVisiblePosition);
        Debug.d(TAG, "mLastVisiblePosition " + mLastVisiblePosition);
        //设置最后可见的条目为滚动到的条目
        int currentScrollToPosition;
        int headersCount = mRcv.getBaseHFAdapter().getHeadersCount();
        Debug.d(TAG, "headersCount " + headersCount);
        //position 的最大值
        int itemCount = mRcv.getLayoutManager().getItemCount();
        Debug.d(TAG, "itemCount " + itemCount);
        int positionCount = itemCount - 1;
        Debug.d(TAG, "positionCount " + positionCount);
        if (mLastVisiblePosition <= getPageItemCount()) {
            //在首页，可见的条数小于每页的条数。
            //设置当页的第一条的位置
            currentScrollToPosition = 0;
        } else {
            if (mLastVisiblePosition == itemCount - 1) {
                currentScrollToPosition = mLastVisiblePosition - (getPageItemCount() - 1);
            } else {
                //可见的条数大于每页的条数
                //设置当页的第一条的位置
                //翻页后最后可见位置比实际位置大1.
                mLastVisiblePosition = mLastVisiblePosition - 1;
                currentScrollToPosition = mLastVisiblePosition - (getPageItemCount() - 1);
            }
        }
        updateCurrentPagePreNextWithDelay(currentScrollToPosition);
    }

    /**
     * 最后一页显示的时候，
     * 点击下一页，不会有滚动监听回调。
     *
     * @param lastScrollPosition 记录滚动到的位置。
     */
    @Override
    public void setLastScrollPosition(int lastScrollPosition) {
        if (mRcv.getLayoutManager().getItemCount() - 1 == getLastVisiblePosition()) {
            //滚动到底部了。设置当页的第一条的位置
            //设置滚动到最后一条
            lastScrollPosition = (getLastVisiblePosition() - 1) - (getPageItemCount() - 1);
        }
        super.setLastScrollPosition(lastScrollPosition);
    }
}
