package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.android2droid.simpledevelop202001160940.mvp.base.model.BaseListBean;
import com.android2droid.simpledevelop202001160940.mvp.base.util.Debug;

import java.util.List;

/**
 * @author created by luokaixuan
 * @date 2019/12/11
 * 这个类是用来干嘛的
 */
public class RecyclerViewPlus<ITEM, BEAN extends BaseListBean<ITEM>> extends RecyclerViewLoadPre<ITEM, BEAN> {

    private RvPageScrollController mPageScrollController;

    public RvPageScrollController getPageScrollController() {
        return mPageScrollController;
    }

    public RecyclerViewPlus(Context context) {
        super(context);
    }

    @Override
    protected void updatePosition() {
        super.updatePosition();
        if (mPageScrollController != null) {
            Debug.d(TAG, "setLastVisiblePosition " + lastVisibleItemPosition);
            mPageScrollController.setLastVisiblePosition(lastVisibleItemPosition);
        }
    }

    /**
     * 添加滑动分页
     *
     * @param pageItemCount 每页条数
     */
//    public RvPageScrollController initScrollPageEvent(int pageItemCount, PageScrollStatus pageScrollStatus) {
//        if (getLayoutManager() == null) {
//            return initVerticalScrollPageEvent(pageItemCount, pageScrollStatus);
//        } else if (getLayoutManager() instanceof LinearLayoutManager) {
//            return initVerticalScrollPageEvent(pageItemCount, pageScrollStatus);
//        } else if (getLayoutManager() instanceof GridLayoutManager) {
//            Debug.d(TAG, "is GridLayoutManager ... ");
//            return initScrollPageEvent(pageItemCount, ((GridLayoutManager) getLayoutManager()).getOrientation(), pageScrollStatus);
//        } else {
//            return initVerticalScrollPageEvent(pageItemCount, pageScrollStatus);
//        }
//    }
    public RvPageScrollController initVerticalScrollPageEvent(int pageItemCount, RvPageScrollController.PageScrollStatus pageScrollStatus) {
        return initScrollPageEvent(pageItemCount, RecyclerView.VERTICAL, pageScrollStatus);
    }

    public RvPageScrollController initScrollPageEvent(int pageItemCount, int orientation, RvPageScrollController.PageScrollStatus pageScrollStatus) {
        Debug.d(TAG, "initScrollPageEvent " + orientation);
        if (orientation == RecyclerView.HORIZONTAL) {
            mPageScrollController = new RvPageHScrollController();
        } else {
            mPageScrollController = new RvPageVScrollController();
        }
        mPageScrollController.addRcvPageScrollListener(this, pageItemCount, pageScrollStatus);
        return mPageScrollController;
    }

    /**
     * 根据传入的位置更新当前在第几页。
     *
     * @param itemPosition
     */
    public void updateScrollPageEvent(int itemPosition) {
        if (mPageScrollController != null) {
            mPageScrollController.updateCurrentPageByPosition(itemPosition);
        }
    }

    /**
     * 重置最后的滚动到的位置。
     */
    public void resetLastScrollPosition() {
        if (mPageScrollController != null) {
            mPageScrollController.resetLastScrollPosition();
        }
    }

    public void scrollToCurrentPage() {
        if (mPageScrollController != null) {
            mPageScrollController.callScrollToCurrentPageWithDelay();
        }
    }

    private void pageScrollAdjust() {
        if (getPageScrollController() != null) {
            Debug.d(TAG, "pageScrollAdjustWithDelay() ... ");
            getPageScrollController().pageScrollAdjust();
        }
    }

    /**
     * 加载成功后，滚动位置的调整。
     */
    private void pageScrollAdjustWithDelay() {
        if (getPageScrollController() != null) {
            Debug.d(TAG, "pageScrollAdjustWithDelay() ... ");
            getPageScrollController().pageScrollAdjustWithDelay();
        }
    }

    @Override
    public void callbackLoadDataSuccess(List<ITEM> list) {
        super.callbackLoadDataSuccess(list);
        pageScrollAdjust();
    }

    @Override
    public void callbackLoadMoreList(List<ITEM> listOrg, List<ITEM> listMore, List<ITEM> listCurrent) {
        super.callbackLoadMoreList(listOrg, listMore, listCurrent);

        pageScrollAdjustWithDelay();
    }
}
