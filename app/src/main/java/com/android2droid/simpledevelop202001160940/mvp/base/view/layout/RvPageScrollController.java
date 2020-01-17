package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.android2droid.simpledevelop202001160940.mvp.base.util.Debug;
import com.android2droid.simpledevelop202001160940.mvp.base.util.MusicUtil;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;
import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_SETTLING;

/**
 * @author created by luokaixuan
 * @date 2019/7/27
 * 这个类是用来干嘛的
 */
public class RvPageScrollController {

    protected final String TAG = RvPageScrollController.class.getSimpleName();
    /**
     * 滑动到屏幕的 n 分之一就可以出发翻页了。
     */
    private static final float MIN_PAGE_SCROLL_DISTANCE_PROPORTION = 7;

    private static final int DEFAULT_LAST_SCROLL_POSITION = 0;

    private static final int UPDATE_POSITION_PRE_NEXT_BTN_INTERVAL = 200;

    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private int mPageItemCount;

    private int mLastScrollPosition;

    protected RecyclerViewPlus mRcv;

    private int mNewState;

    /**
     * 上次操作是否是触摸操作
     * 如果是触摸操作，则会让 setting 惯性滑动执行
     */
    private boolean mLastHandleIsTouched;

    private boolean mIsSettIng;
    private ScrollToPositionHandler mScrollToPositionHandler;
    private ScrollToPositionRunnable mScrollToPositionRunnable;

    private UpdatePositionPreMoreHandler mUpdatePositionPreMoreHandler;
    private UpdatePositionPreMoreRunnable mUpdatePositionPreMoreRunnable;

    /**
     * 触摸的延迟滑动
     * 这个值越小，触摸之后的自动滑动衔接越流畅。
     * 这个值越大，越不会受到多次触控导致不会滑动。
     */
    private int TOUCH_DELAY_MILLIS = 20;
    /**
     * 惯性滑动的延迟时间
     */
    private int SETTING_DELAY_MILLIS = 50;

    private float mMoveDx;
    private float mMoveDy;

    /**
     * 手指触摸滑动的距离
     * 如果过大，则不响应filling惯性滑动
     */
    /**
     * 数据变化后，需要重新滑动界面
     * 这个参数是数据变化后，延迟 n 时间再滑动界面
     */
    private static final int DATA_CHANGE_PAGE_SCROLL_DELAY_MILLIS = 500;

    /**
     * 手动切换下一页的延迟，为了快速点击。
     */
    private static final int PERSON_HAND_DELAY_MILLIS = 300;

    private int mLastVisiblePosition;

    public int getLastVisiblePosition() {
        return mLastVisiblePosition;
    }

    public void setLastVisiblePosition(int mLastVisiblePosition) {
        this.mLastVisiblePosition = mLastVisiblePosition;
    }

    public void setLastScrollPosition(int lastScrollPosition) {
        this.mLastScrollPosition = lastScrollPosition;
    }

    public int getPageItemCount() {
        return mPageItemCount;
    }

    public int getLastScrollPosition() {
        return mLastScrollPosition;
    }

    public interface PageScrollStatus {
        void onNoPrePage();

        void onNoNextPage();

        void hasPrePage();

        void hasNextPage();
    }

    private PageScrollStatus mPageScrollStatus;

    void addRcvPageScrollListener(RecyclerViewPlus rcv, int pageItemCount, PageScrollStatus pageScrollStatus) {
        mRcv = rcv;
        mPageItemCount = pageItemCount;
        mPageScrollStatus = pageScrollStatus;

        mScrollToPositionHandler = new ScrollToPositionHandler();
        mScrollToPositionRunnable = new ScrollToPositionRunnable();

        mUpdatePositionPreMoreHandler = new UpdatePositionPreMoreHandler();
        mUpdatePositionPreMoreRunnable = new UpdatePositionPreMoreRunnable();

        rcv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                //触摸监听一定要在 onInterceptTouchEvent 里面做
                //在这里无法监听到 ACTION_DOWN
                touchScrollPage(e);
            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                //这里的监听 ACTION_UP 会执行两次
                touchScrollPage(e);
                return false;
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        mRcv.setOnTouchListener((v, event) -> {
            touchScrollPage(event);
            return false;
        });

        mRcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mNewState = newState;
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                updatePageScroll(mNewState, dx, dy);
            }
        });

        //这个监听只有在filling惯性滑动结束的时候才会调
//        mRcv.setOnFlingListener(new RecyclerView.OnFlingListener() {
//            @Override
//            public boolean onFling(int velocityX, int velocityY) {
//                onFilling(velocityX, velocityY);
//                return true;
//            }
//        });

        mRcv.getAdapter().registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (mRcv.getAdapter().getItemCount() == 0) {
                    resetLastScrollPosition();
                }
                //监听recycler数据的变化
//                scrollToCurrentPageWithDelay(mLastScrollPosition, DATA_CHANGE_PAGE_SCROLL_DELAY_MILLIS);
            }
        });

    }

    public void resetLastScrollPosition() {
        mLastScrollPosition = DEFAULT_LAST_SCROLL_POSITION;
    }

    public void updateCurrentPageByPosition(int itemPosition) {
        //根据item位置取整每页个数得到当前第几页。
        mLastScrollPosition = itemPosition;
//        mLastScrollPosition = (itemPosition / mPageItemCount);
        Debug.d(TAG, "updateCurrentPageByPosition mLastScrollPosition " + mLastScrollPosition);
    }

    public void updateCurrentPagePreNextWithDelay(int position) {
        mUpdatePositionPreMoreHandler.removeCallbacks(mUpdatePositionPreMoreRunnable);
        mUpdatePositionPreMoreRunnable.setPosition(position);
        mUpdatePositionPreMoreHandler.postDelayed(mUpdatePositionPreMoreRunnable, UPDATE_POSITION_PRE_NEXT_BTN_INTERVAL);
    }

    public void updateCurrentPagePreNext(int position) {
        updateCurrentPageByPosition(position);
        judgeHavePreNext(position);
    }

    float totalX;
    float totalY;

    private void touchScrollPage(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (startX == event.getX()) {
                    Debug.d(TAG, " startX == event.getX() ACTION_DOWN 执行了两次 。。。" + startX);
                } else {
                    startX = event.getX();
                    startY = event.getY();
                    Debug.d(TAG, "startX " + startX);
                }
                totalX = 0;
                totalY = 0;
                break;
            case MotionEvent.ACTION_UP:
                if (endX == event.getX()) {
                    Debug.d(TAG, " endX == event.getX() action_up 执行了两次 。。。");
                } else {
                    endX = event.getX();
                    endY = event.getY();
                    Debug.d(TAG, "endX " + endX);
                    if (mIsSettIng) {
                        Debug.d(TAG, "正在惯性滑动，不用监听手指抬起的事件了。。。");
                    } else {
                        Debug.d(TAG, "touchScrollPage(MotionEvent event) " + mLastScrollPosition);
                        mMoveDx = endX - startX;
                        mMoveDy = endY - startY;
                        scrollDistance(mMoveDx, mMoveDy, mLastScrollPosition);
                    }
                }
                mLastHandleIsTouched = true;
                break;
            default:
                break;
        }
    }

    private boolean mIsDragging;
    /**
     * 惯性滑动的总距离
     */
    private float mSettingTotalX;

    private void updatePageScroll(int newState, int dx, int dy) {
        switch (newState) {
            case SCROLL_STATE_IDLE:
                Debug.d(TAG, "SCROLL_STATE_IDLE " + SCROLL_STATE_IDLE);
                break;
            case SCROLL_STATE_DRAGGING:
                /*
                 * The RecyclerView is currently being dragged by outside input such as user touch input.
                 * @see #getScrollState()
                 */
                Debug.d(TAG, "SCROLL_STATE_DRAGGING " + " dx " + dx + " dy " + dy);
                mIsSettIng = false;
                mIsDragging = true;
                break;
            case SCROLL_STATE_SETTLING:
                /*
                 * The RecyclerView is currently animating to a final callbackSwitchPosition while not under
                 * outside control.
                 * @see #getScrollState()
                 */
//                Debug.d(TAG, "SCROLL_STATE_SETTLING " + " dx " + dx + " dy " + dy);
//                onFilling(dx, dy);
                if (mIsDragging) {
                    //开始了惯性滑动
                    mSettingTotalX = dx;
                } else {
                    //惯性滑动中。。。
                    mSettingTotalX += dx;
                }
                //判断惯性滑动的总距离是否大于一页了。
                if (Math.abs(mSettingTotalX) > getPageDistance()) {
//                    Debug.d(TAG, "滚动距离大于一页了，不能再滚动了");
                    //停止滚动
//                    stopScroll();
                }
                mIsDragging = false;
                break;
            default:
                break;
        }
    }

//    private void onFilling(float dx, float dy) {
//        Debug.d(TAG, "SCROLL_STATE_SETTLING " + " dx " + dx + " dy " + dy);
//        //上次是触摸操作
//        //如果上次不是触摸操作，不能响应filling.
//        //因为自动滑动也会触发filling，会导致无限循环的 filling 。
//        if (mLastHandleIsTouched) {
//            mLastHandleIsTouched = false;
//            if (Math.abs(mMoveDx) > getCanScrollPageDistance()) {
//                //如果触摸移动的距离大于了n
//                //则不相应惯性滑动了。
//                //不需要惯性滑动到下一页。
//                Debug.d(TAG, "如果触摸移动的距离大于 " + getCanScrollPageDistance() + " 则不响应惯性滑动 。。。 ");
//                stopScroll();
//            } else {
//                Debug.d(TAG, "执行触摸滑动之后的翻页或者是触摸之后的惯性滑动的翻页 ... ");
//                if (dx < 0) {
//                    mIsSettIng = true;
//                    settingScrollToPrePage(mLastScrollPosition);
//                    Debug.d(TAG, "SCROLL_STATE_SETTLING (dx < 0)");
//                } else if (dx == 0) {
//                    Debug.d(TAG, "SCROLL_STATE_SETTLING (dx == 0)");
//                    settingScrollToCurrentPage(mLastScrollPosition);
//                } else {
//                    Debug.d(TAG, "SCROLL_STATE_SETTLING scrollToNextPage(mLastScrollPosition); " + mLastScrollPosition);
//                    mIsSettIng = true;
//                    settingScrollToNextPage(mLastScrollPosition);
//                }
//            }
//        } else {
//            Debug.d(TAG, "上次是惯性滑动，这次也是惯性滑动，就不做滚动了，会滚动多页 。。。");
//        }
//    }

    protected void scrollDistance(float dx, float dy, int lastScrollPosition) {
        //这里不直接调用滚动到下一页逻辑，需要到子类调用滚动到下一页逻辑
    }

    void scrollDistance(float scrollDistance, int lastScrollPosition) {
        Debug.d(TAG, "scrollDistance " + scrollDistance);
        Debug.d(TAG, "getHeight() MIN_PAGE_SCROLL_DISTANCE_PROPORTION " + getCanScrollPageDistance());

        int itemCount = mRcv.getLayoutManager().getItemCount();
        if (scrollDistance < 0) {
            if (Math.abs(scrollDistance) > getCanScrollPageDistance()) {
                //向右翻页
                if (itemCount > lastScrollPosition) {
                    //如果总条数大于上次翻页的条数 + 需要翻页的条数，就向后翻页
                    Debug.d(TAG, "itemCount > lastScrollPosition 如果总条数大于上次翻页的条数 + 需要翻页的条数，就向后翻页");
                    touchScrollToNextPage(lastScrollPosition);
                } else if (itemCount == mPageItemCount) {
                    //如果总条数等于上次翻页的条数 + 需要翻页的条数，就不翻页
                    //不翻页，重新定位到当前上次的位置
                    Debug.d(TAG, "itemCount == mPageItemCount 如果总条数等于上次翻页的条数 + 需要翻页的条数，就不翻页");
                    touchScrollToNextPage(lastScrollPosition);
                } else {
                    //如果总条数小于上次翻页的条数 + 需要翻页的条数，就不翻页
                    //不翻页，重新定位到当前上次的位置
                    Debug.d(TAG, "如果总条数小于上次翻页的条数 + 需要翻页的条数，就不翻页 ");
                    touchScrollToCurrentPage(lastScrollPosition);
                }
            } else {
                //不翻页，重新定位到当前定位的位置
                Debug.d(TAG, "不翻页，重新定位到当前定位的位置111111111 ");
                touchScrollToCurrentPage(lastScrollPosition);
            }
        } else {
            if (scrollDistance == 0) {
                Debug.d(TAG, "scrollDistance == 0");
                touchScrollToCurrentPage(lastScrollPosition);
            } else if (Math.abs(scrollDistance) > getCanScrollPageDistance()) {
                //向左翻页
                if (lastScrollPosition - mPageItemCount > 0) {
                    //如果上次定位的位置，减去需要翻页的条数还大于0，就向前翻页
                    Debug.d(TAG, "lastScrollPosition - mPageItemCount > 0 如果上次定位的位置，减去需要翻页的条数还大于0，就向前翻页");
                    touchScrollToPrePage(lastScrollPosition);
                } else if (lastScrollPosition - mPageItemCount == 0) {
                    //如果上次定位的位置，减去需要翻页的条数等于0，就不翻页
                    //不翻页，重新定位到当前上次的位置
                    Debug.d(TAG, "lastScrollPosition - mPageItemCount == 0 如果上次定位的位置，减去需要翻页的条数等于0，就不翻页");
                    touchScrollToPrePage(lastScrollPosition);
                } else {
                    //如果上次定位的位置，减去需要翻页的条数小于0，就不翻页
                    //不翻页，重新定位到当前上次的位置
                    Debug.d(TAG, "如果上次定位的位置，减去需要翻页的条数小于0，就不翻页 ");
                    touchScrollToCurrentPage(lastScrollPosition);
                }
            } else {
                //不翻页，重新定位到当前定位的位置
                Debug.d(TAG, "不翻页，重新定位到当前定位的位置2222222 ");
                touchScrollToCurrentPage(lastScrollPosition);
            }
        }
    }

    protected void touchScrollToPrePage(int position) {
        scrollToPrePage(position, TOUCH_DELAY_MILLIS);
    }

    protected void touchScrollToCurrentPage(int position) {
        scrollToCurrentPageWithDelay(position, TOUCH_DELAY_MILLIS);
    }

    protected void touchScrollToNextPage(int position) {
        scrollToNextPage(position, TOUCH_DELAY_MILLIS);
    }

    private void settingScrollToPrePage(int position) {
        scrollToPrePage(position, SETTING_DELAY_MILLIS);
    }

    private void settingScrollToCurrentPage(int position) {
        scrollToCurrentPageWithDelay(position, SETTING_DELAY_MILLIS);
    }

    private void settingScrollToNextPage(int position) {
        scrollToNextPage(position, SETTING_DELAY_MILLIS);
    }

    public void callScrollToPrePageWithDelay() {
        scrollToPrePage(mLastScrollPosition, PERSON_HAND_DELAY_MILLIS);
    }

    public void callScrollToCurrentPageWithDelay() {
        scrollToCurrentPageWithDelay(mLastScrollPosition, PERSON_HAND_DELAY_MILLIS);
    }

    public void pageScrollAdjustWithDelay() {
        Debug.d(TAG, "pageScrollAdjust ");
        if (isNeedDataChangeScrollAdjust()) {
            Debug.d(TAG, "pageScrollAdjust isNeedDataChangeScrollAdjust() ");
            callScrollToCurrentPageWithDelay();
        } else {
            Debug.d(TAG, "pageScrollAdjust is not Need DataChangeScrollAdjust() ");
        }
    }

    /**
     * 列表刷新后，调整滚动到的页数。
     */
    private boolean isNeedDataChangeScrollAdjust;

    public boolean isNeedDataChangeScrollAdjust() {
        return isNeedDataChangeScrollAdjust;
    }

    public void setNeedDataChangeScrollAdjust(boolean needDataChangeScrollAdjust) {
        isNeedDataChangeScrollAdjust = needDataChangeScrollAdjust;
    }

    public void pageScrollAdjust() {
        Debug.d(TAG, "pageScrollAdjust ");
        if (isNeedDataChangeScrollAdjust()) {
            Debug.d(TAG, "pageScrollAdjust isNeedDataChangeScrollAdjust() ");
            callScrollToCurrentPage();
        } else {
            Debug.d(TAG, "pageScrollAdjust is not Need DataChangeScrollAdjust() ");
        }
    }

    public void callScrollToCurrentPage() {
        smoothScrollToPosition(mLastScrollPosition);
    }

    public void callScrollToNextPageWithDelay() {
        scrollToNextPage(mLastScrollPosition, PERSON_HAND_DELAY_MILLIS);
    }

    public void personHandScrollToPrePageWithDelay() {
        scrollToPrePage(mLastScrollPosition, MusicUtil.getDelayTime(PERSON_HAND_DELAY_MILLIS));
    }

    public void personHandScrollToCurrentPageWithDelay() {
        scrollToCurrentPageWithDelay(mLastScrollPosition, MusicUtil.getDelayTime(PERSON_HAND_DELAY_MILLIS));
    }

    public void personHandScrollToNextPageWithDelay() {
        scrollToNextPage(mLastScrollPosition, MusicUtil.getDelayTime(PERSON_HAND_DELAY_MILLIS));
    }

    private void scrollToPrePage(int lastScrollPosition, long delayTime) {
        //需要滚动到的位置
        int needScrollToPosition = lastScrollPosition - mPageItemCount;
        if (judgeCanScrollToPrePage(needScrollToPosition)) {
            lastScrollPosition = 0;
//            isNoPrePage();
            Debug.d(TAG, "不能再向上一页了。");
        } else {
            lastScrollPosition = needScrollToPosition;
//            hasPrePage();
            Debug.d(TAG, "还能再向上一页。");
        }
        Debug.d(TAG, "scrollToPrePage ... " + lastScrollPosition);
        scrollToPositionWithDelay(lastScrollPosition, delayTime);
    }

    private void scrollToCurrentPageWithDelay(int lastScrollPosition, long delayTime) {
        Debug.d(TAG, "scrollToCurrentPageWithDelay ... " + lastScrollPosition);
        scrollToPositionWithDelay(lastScrollPosition, delayTime);
    }

    private void scrollToNextPage(int lastScrollPosition, long delayTime) {
        int needScrollToPosition = lastScrollPosition + mPageItemCount;
        if (judgeCanScrollToNextPage(needScrollToPosition)) {
            //需要滚动的位置小于总条数。
            lastScrollPosition = needScrollToPosition;
//            hasNextPage();
            Debug.d(TAG, "scrollToNextPage 还能向下一页翻。");
        } else {
//            isNoNextPage();
            Debug.d(TAG, "scrollToNextPage 不能再向下一页翻。");
        }

        scrollToPositionWithDelay(lastScrollPosition, delayTime);
        Debug.d(TAG, "scrollToNextPage ... " + lastScrollPosition);
    }

    private boolean judgeCanScrollToPrePage(int position) {
        Debug.d(TAG, "judgeCanScrollToPrePage position " + position);
        return position <= 0;
    }

    private boolean judgeCanScrollToNextPage(int position) {
        int countValueFromOne = mRcv.getLayoutManager().getItemCount() - 1;
        Debug.d(TAG, "judgeCanScrollToNextPage position " + position + " ? mRcv.getLayoutManager().getItemCount() - 1 = " + countValueFromOne);
        Debug.d(TAG, "从现在需要滚动到的位置开始计算，是否能够滚动到下一页，现在滚动到的位置等于总条数-1，还有下一页，能够滚动到下一页 。。。");
        return position < countValueFromOne;
    }

    private float getCanScrollPageDistance() {
        return getPageDistance() / MIN_PAGE_SCROLL_DISTANCE_PROPORTION;
    }

    protected float getPageDistance() {
        return 0;
    }

    /**
     * 停止 RecyclerView 的滚动
     */
    private void stopScroll() {
        mRcv.stopScroll();
    }

    /**
     * 在滑动监听结束后
     * 必须要添加延迟
     * 才能正确的滚动
     * 否则系统不会执行滚动操作
     *
     * @param position 滚动的位置。
     */
    public void scrollToPositionWithDelay(int position, long delayTime) {
        Debug.d(TAG, "scrollToPositionWithDelay " + position);
        //这个移除在数据量多的时候会无效
//        mScrollToPositionHandler.removeCallbacks(mScrollToPositionRunnable);
        mScrollToPositionHandler.removeCallbacksAndMessages(null);
        mScrollToPositionRunnable.setPosition(position);
        Debug.d(TAG, "mScrollToPositionRunnable.setPosition(callbackSwitchPosition) -> " + position);
        mScrollToPositionHandler.postDelayed(mScrollToPositionRunnable, delayTime);
    }

    /**
     * 判断是否还能够滑动到上一页，下一页
     *
     * @param position 滑动到的位置
     */
    private void judgeHavePreNext(int position) {
        judgeHavePrePage(position);
        judgeHaveNextPage(position);
    }

    private boolean judgeHavePrePage(int position) {
        Debug.d(TAG, "judgeCanScrollToPreNextPage");
        if (position <= 0) {
            //如果滑动到的位置等于0，则没有上一页
            Debug.d(TAG, "judgeHavePrePage position " + position + " <= 0");
            Debug.d(TAG, "滑动到的位置等于0，则没有上一页");
            isNoPrePage();
            return false;
        } else {
            //如果滑动到的位置大于0，则有上一页
            Debug.d(TAG, "judgeHavePrePage position " + position + " > 0");
            Debug.d(TAG, "滑动到的位置大于0，则有上一页");
            hasPrePage();
            return true;
        }
    }

    private boolean judgeHaveNextPage(int position) {
        int itemCount = mRcv.getLayoutManager().getItemCount();
        Debug.d(TAG, " position " + position + " mPageItemCount " + mPageItemCount + " itemCount " + itemCount);
        int positionCount = itemCount - 1;
        Debug.d(TAG, "positionCount " + positionCount);
        if (position + mPageItemCount >= positionCount) {
            //如果滑动到的位置加上每页的条数，大于等于总条数，则没有下一页
            Debug.d(TAG, "judgeHaveNextPage position1 " + position + " mPageItemCount " + mPageItemCount + " >= positionCount " + positionCount);
            Debug.d(TAG, "滑动到的位置加上每页的条数，大于等于总条数，则没有下一页");
            isNoNextPage();
            return false;
        } else {
            //如果滑动到的位置加上每页的条数，小于等于总条数，则有下一页
            Debug.d(TAG, "judgeHaveNextPage position2 " + position + " mPageItemCount " + mPageItemCount + " >= positionCount " + positionCount);
            Debug.d(TAG, "如果滑动到的位置加上每页的条数，小于等于总条数，则有下一页");
            hasNextPage();
            return true;
        }
    }

    public void isNoPrePage() {
        if (mPageScrollStatus != null) {
            mPageScrollStatus.onNoPrePage();
            Debug.d(TAG, "judgeCanScrollToPreNextPage isNoPrePage");
        }
    }

    public void hasPrePage() {
        if (mPageScrollStatus != null) {
            mPageScrollStatus.hasPrePage();
            Debug.d(TAG, "judgeCanScrollToPreNextPage hasPrePage");
        }
    }

    public void isNoNextPage() {
        if (mPageScrollStatus != null) {
            mPageScrollStatus.onNoNextPage();
            Debug.d(TAG, "judgeCanScrollToPreNextPage isNoNextPage");
        }
    }

    public void hasNextPage() {
        if (mPageScrollStatus != null) {
            mPageScrollStatus.hasNextPage();
            Debug.d(TAG, "judgeCanScrollToPreNextPage hasNextPage");
        }
    }

    private static class ScrollToPositionHandler extends Handler {

    }

    private class ScrollToPositionRunnable implements Runnable {
        int position;

        public void setPosition(int position) {
            this.position = position;
        }

        @Override
        public void run() {
            smoothScrollToPosition(position);
        }
    }

    private static class UpdatePositionPreMoreHandler extends Handler {

    }

    private class UpdatePositionPreMoreRunnable implements Runnable {
        int position;

        public void setPosition(int position) {
            this.position = position;
        }

        @Override
        public void run() {
            updateCurrentPagePreNext(position);
        }
    }

    private void smoothScrollToPosition(int position) {
        Debug.d(TAG, "smoothScrollToPosition callbackSwitchPosition " + position);
//        mRcv.getLayoutManager().smoothScrollToPosition(mRcv, new RecyclerView.State(), callbackSwitchPosition);
        TopSmoothScroller topSmoothScroller = new TopSmoothScroller(mRcv.getContext());
        if (position <= RecyclerView.NO_POSITION) {
            Debug.d(TAG, "position == RecyclerView.NO_POSITION " + RecyclerView.NO_POSITION);
        } else {
            topSmoothScroller.setTargetPosition(position);
            mRcv.getLayoutManager().startSmoothScroll(topSmoothScroller);
            setLastScrollPosition(position);
            judgeHavePreNext(mLastScrollPosition);
        }
    }

    public int getScollYDistance(RecyclerView mRcv) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) mRcv.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }

    /**
     * @author created by luokaixuan
     * @date 2019/7/30
     * 这个类是RecyclerView用来自动滚动到指定item 的顶部
     */
    public static class TopSmoothScroller extends LinearSmoothScroller {

        TopSmoothScroller(Context context) {
            super(context);
        }

        @Override
        protected int getHorizontalSnapPreference() {
            return SNAP_TO_START;//具体见源码注释
        }

        @Override
        protected int getVerticalSnapPreference() {
            return SNAP_TO_START;//具体见源码注释
        }

        // 返回：滑过1px时经历的时间(ms)。
        @Override
        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return 100f / displayMetrics.densityDpi;
        }
    }
}
