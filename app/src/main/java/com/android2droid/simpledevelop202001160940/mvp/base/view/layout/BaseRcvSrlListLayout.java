package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.android2droid.simpledevelop202001160940.mvp.base.model.BaseListBean;
import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseListStatus;
import com.didi365.miudrive.navi.ClientApplication;
import com.didi365.miudrive.navi.R;
import com.didi365.miudrive.navi.focus.configure.FocusStateConfigure;
import com.didi365.miudrive.navi.focus.listener.OnRvChangeListener;
import com.didi365.miudrive.navi.focus.view.layoutmanager.FocusGridLayoutManager;
import com.didi365.miudrive.navi.focus.view.layoutmanager.FocusLinearLayoutManager;
import com.didi365.miudrive.navi.main.music.MusicConstants;
import com.didi365.miudrive.navi.main.music.base.model.BaseListBean;
import com.didi365.miudrive.navi.main.music.base.status.BaseListStatus;
import com.didi365.miudrive.navi.main.music.util.MusicViewBean;
import com.didi365.miudrive.navi.main.navigation.MyDecoration;
import com.didi365.miudrive.navi.utils.Debug;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

/**
 * Created by luokaixuan
 * Created Date 2017/12/25.
 * Description
 * RecyclerView 和 SmartRefreshLayout
 *
 * @author luokaixuan
 */

public abstract class BaseRcvSrlListLayout<ITEM, BEAN extends BaseListBean<ITEM>
        , P extends BaseListStatus.BasePListStatus<ITEM, BEAN>
        , HOLDER extends RecyclerView.ViewHolder>
        extends BaseSrlListLayout<ITEM, BEAN, P>
        implements BaseListStatus.BaseVListStatus<ITEM, BEAN> {

    protected RecyclerViewPlus<ITEM, BEAN> mRcv;
    private RvPageScrollController mRvPageScrollController;
    private MyDecoration myDecoration;

    public RecyclerViewPlus<ITEM, BEAN> getRcv() {
        return mRcv;
    }

    private boolean isAddItemDecoration;

    public boolean isAddItemDecoration() {
        return isAddItemDecoration;
    }

    public void setRcv(RecyclerViewPlus<ITEM, BEAN> rcv) {
        mRcv = rcv;
        initRecyclerViewPlus(mRcv);
        initRecyclerView();
        getRl().setRefreshContent(mRcv);
    }

    private RecyclerView.LayoutManager layoutManager;

    public RvPageScrollController getRvPageScrollController() {
        return mRvPageScrollController;
    }

    @Override
    public List<ITEM> getBaseList() {
        if (getRcv() == null) {
            return new ArrayList<>();
        } else {
            return getRcv().getList();
        }
    }

    public boolean isEmpty() {
        return getBaseList() == null || getBaseList().size() == 0;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setHorizontalLayoutManager(Context context) {
        FocusLinearLayoutManager linearLayoutManager = new FocusLinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(linearLayoutManager);
        setHorizontal(true);
    }

    public void setGridLayoutManager(Context context) {
        setGridLayoutManager(context, MusicConstants.getItemCount());
    }

    private void setGridLayoutManager(Context context, int itemCount) {
        layoutManager = new GridLayoutManager(context, itemCount);
    }

    public void setHorizontalGridLayoutManager() {
        setHorizontalGridLayoutManager(MusicConstants.getItemCount());
    }

    public void setHorizontalGridLayoutManager(int itemCount) {
        FocusGridLayoutManager gridLayoutManager = new FocusGridLayoutManager(mContext, itemCount);
        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        layoutManager = gridLayoutManager;
        setHorizontal(true);
    }

    @Override
    public P getPresenter() {
        return super.getPresenter();
    }

    @Override
    protected void initLayoutIfNotInitialized(Context context) {
        super.initLayoutIfNotInitialized(context);
        if (mRcv == null) {
            mRcv = newRecyclerViewPlus();
            initRecyclerViewPlus(mRcv);
            initRecyclerView();
            getRl().setRefreshContent(mRcv);
        }
    }

    protected RecyclerViewPlus<ITEM, BEAN> newRecyclerViewPlus() {

        RecyclerViewPlus rcv = new RecyclerViewPlus(mContext);

        return rcv;
    }

    private void initRecyclerViewPlus(RecyclerViewPlus<ITEM, BEAN> rcv) {
        rcv.setRecyclerViewBindListener(new RecyclerViewPlus.OnRecyclerViewBindListener<ITEM, HOLDER>() {
            @Override
            public RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent, int viewType) {
                return BaseRcvSrlListLayout.this.createItemViewHolder(LayoutInflater.from(mContext), parent, viewType);
            }

            @Override
            public void bindItemViewHolder(ITEM item, HOLDER holder, int position) {
                BaseRcvSrlListLayout.this.bindItemViewHolder(holder, item, position);
            }

            @Override
            public void bindItemViewHolder(ITEM item, HOLDER holder, int position, @NonNull List payloads) {
                BaseRcvSrlListLayout.this.bindItemViewHolder(holder, item, position, payloads);
            }
        });
    }

    /**
     * 添加分割线
     */
    public void addItemDecoration() {
        if (myDecoration == null) {
            myDecoration = new MyDecoration(getContext(), 0, 20, 0, 0);
            myDecoration.setDividerDrawable(getContext(), R.drawable.divider_transparent);
            myDecoration.setShowLastDivider(false);
            mRcv.addItemDecoration(myDecoration);
        }
    }

    protected void initRecyclerView() {
        if (getLayoutManager() == null) {
            FocusLinearLayoutManager linearLayoutManager = new FocusLinearLayoutManager(mContext);
            setLayoutManager(linearLayoutManager);
        }

        setRcvLayoutManager(getLayoutManager());

        /**
         * 使用 recyclerView 自带的加载更多
         */
        if (isHorizontal()) {
            setSwipeLoadMoreEnable(false);
            setSwipeRefreshEnable(false);
        }
        mRcv.setRcvScrollListener();

        mRcv.setOnItemClickListener((vh, position) -> {
            ITEM item = getRcv().getList().get(position);
            onItemClickListener(item);
            onItemClickListener(item, position);
            onItemClickListener(vh, item);
            onItemClickListener(vh, position);
            onItemClickListener(vh, item, position);
        });

        mRcv.setonItemLongClickListener((vh, position) -> {
            ITEM item = getRcv().getList().get(position);
            onItemLongClickListener(item);
            onItemLongClickListener(item, position);
            onItemLongClickListener(vh, item);
            onItemLongClickListener(vh, position);
            onItemLongClickListener(vh, item, position);
        });

        mRcv.setAnimatorEnable(false);

        addHeardViewSpace(0);
    }

    private void addHeardViewSpace(int space) {
        addView(true, space);
    }

    protected void addFootViewSpace(int space) {
        addView(false, space);
    }

    private void addView(boolean heard, int space) {
        if (mRcv != null && space > 0) {
            FrameLayout fl = new FrameLayout(mContext);
            fl.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, space));
            if (heard) {
                mRcv.addHeardView(fl);
            } else {
                mRcv.addFootView(fl);
            }
        }
    }

    public void onItemClickListener(ITEM item) {
    }

    public void onItemClickListener(ITEM item, int position) {
    }

    public void onItemClickListener(RecyclerView.ViewHolder vh, ITEM item) {
    }

    public void onItemClickListener(RecyclerView.ViewHolder vh, int position) {
    }

    public void onItemClickListener(RecyclerView.ViewHolder vh, ITEM item, int position) {

    }


    private void onItemLongClickListener(ITEM item) {

        //        ArrayList<ITEM> oldList = new ArrayList<>(mBaseList);
        //        mBaseList.remove(item);
        //        notifyData(mRcv.getAdapter(), oldList, mBaseList);
    }

    private void onItemLongClickListener(ITEM item, int position) {
    }

    private void onItemLongClickListener(RecyclerView.ViewHolder vh, ITEM item) {
    }

    private void onItemLongClickListener(RecyclerView.ViewHolder vh, int position) {
    }

    private void onItemLongClickListener(RecyclerView.ViewHolder vh, ITEM item, int position) {

    }

    /**
     * a
     *
     * @param inflater a
     * @param parent   a
     * @param viewType a
     * @return a
     */
    protected abstract HOLDER createItemViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType);

    protected abstract void bindItemViewHolder(HOLDER holder, ITEM item, int position);

    protected void bindItemViewHolder(HOLDER holder, ITEM item, int position, @NonNull List payloads) {

    }

    private void setRcvLayoutManager(RecyclerView.LayoutManager layoutManager) {
        if (mRcv == null) {
            return;
        }

        if (layoutManager == null) {
            return;
        }
        mRcv.initRecyclerView(layoutManager);
    }

    public void initScrollPageEvent(int pageItemCount) {
        initScrollPageEvent(pageItemCount, false);
    }

    /**
     * 初始化页数控制器
     *
     * @param pageItemCount                每页的条数
     * @param needAdjustPageWhenDataChange 当数据变化时，是否需要滚动上下页。
     */
    public void initScrollPageEvent(int pageItemCount, boolean needAdjustPageWhenDataChange) {
        if (mRcv == null) {
            Debug.d(TAG, "initScrollPageEvent() mRcv == null ... ");
        } else {
            if (mRvPageScrollController == null) {
                mRvPageScrollController = mRcv.initScrollPageEvent(pageItemCount,
                        isHorizontal() ? RecyclerView.HORIZONTAL : RecyclerView.VERTICAL,
                        new RvPageScrollController.PageScrollStatus() {
                            @Override
                            public void onNoPrePage() {
                                Debug.d(TAG, "PageScrollStatus onNoPrePage ... ");
                                BaseRcvSrlListLayout.this.onNoPrePage();
                            }

                            @Override
                            public void onNoNextPage() {
                                Debug.d(TAG, "PageScrollStatus onNoNextPage ... ");
                                if (isCanLoadMore()) {
                                    Debug.d(TAG, "PageScrollStatus 还能加载更多");
                                } else {
                                    Debug.d(TAG, "PageScrollStatus 没有下一页了。。。");
                                    BaseRcvSrlListLayout.this.onNoNextPage();
                                }
                            }

                            @Override
                            public void hasPrePage() {
                                Debug.d(TAG, "PageScrollStatus hasPrePage ... ");
                                BaseRcvSrlListLayout.this.onHasPrePage();
                            }

                            @Override
                            public void hasNextPage() {
                                Debug.d(TAG, "PageScrollStatus hasNextPage ... ");
                                BaseRcvSrlListLayout.this.onHasNextPage();
                            }
                        });
            }
            mRvPageScrollController.setNeedDataChangeScrollAdjust(needAdjustPageWhenDataChange);
        }
    }

    protected boolean isCanLoadMore() {
        return false;
    }

    protected void onNoPrePage() {

    }

    protected void onNoNextPage() {

    }

    protected void onHasPrePage() {

    }

    protected void onHasNextPage() {

    }

    public ITEM getLastItem() {
        return getRcv() != null ?
                getRcv().getLastItem() : null;
    }

    ITEM getFirstItem() {
        return getRcv() != null ?
                getRcv().getFirstItem() : null;
    }

    public int getListSize() {
        return getRcv() == null ? 0 : getRcv().getListSize();
    }

    public void initBaseList() {
        if (getRcv() == null) {
            Debug.d(TAG, "initBaseList getRcv() == null ... ");
        } else {
            getRcv().setList(new ArrayList<>());
        }
    }

    public void addFootView(View view) {
        if (mRcv == null) {
            Debug.d(TAG, "addFootView() mRcv == null ... ");
        } else {
            mRcv.addFootView(view);
        }
    }

    @Override
    public void callbackLoadDataStart() {
        super.callbackLoadDataStart();
        if (mRcv != null) {
            mRcv.callbackLoadDataStart();
            mRcv.setIsRefreshing(true);
        }
    }

    @Override
    public void callbackDataLoading() {
        super.callbackDataLoading();
        if (mRcv != null) {
            mRcv.callbackDataLoading();
        }
    }

    @Override
    public void callbackDataError(String msg) {
        if (mRcv.isLoadMore()) {
            Debug.d(TAG, "callbackDataError mRcv.isLoadMore() ...");
        } else {
            notifyDataSetChanged();
            super.callbackDataError(msg);
        }
        mRcv.callbackDataError(msg);
    }

    @Override
    public void callbackDataFailure(int code, String msg) {
        if (mRcv.isLoadMore()) {
            Debug.d(TAG, "callbackDataFailure mRcv.isLoadMore() ...");
        } else {
            notifyDataSetChanged();
            super.callbackDataFailure(code, msg);
        }
        mRcv.callbackDataFailure(code, msg);
    }

    @Override
    public void callbackDataComplete() {
        super.callbackDataComplete();
        mRcv.callbackDataComplete();
    }

    public void notifyDataSetChanged() {
        callFrom();
        Debug.d(TAG, "notifyDataSetChanged ... ");
        if (mRcv == null) {
            Debug.d(TAG, "notifyDataSetChanged mRcv == null ... ");
        } else {
            mRcv.notifyDataSetChanged();
            notifyFocus();
        }
    }

    public void notifyDataWithRvPagerChange() {
        notifyDataSetChanged();
    }

    protected void notifyDataWithRvPagerChange(Object payload) {
        RecyclerViewPlus rcv = getRcv();
        for (int i = 0; i <= rcv.getAdapter().getItemCount() - 1; i++) {
            rcv.getAdapter().notifyItemChanged(i, payload);
        }
        notifyFocus();
    }

    protected void notifyDataWithRvForRemove(int position) {
        getRcv().getAdapter().notifyItemRemoved(position);
        notifyFocus();
    }

    protected void notifyFocus() {
        if (onPagerChangeListener != null) {
            ClientApplication.getHandler().post(() -> {
                if (onPagerChangeListener != null) {
                    onPagerChangeListener.onPagerChange();
                }
            });
        }
    }

    @Override
    public void callGenerateListData(List<ITEM> list) {
        if (getRcv().getList() == null || getRcv().getList().size() == 0) {
            callbackDataEmpty();
        }
    }

    @Override
    public void callbackList(List<ITEM> listOrg, List<ITEM> listCurrent) {
        super.callbackList(listOrg, listCurrent);
        if (getRcv() != null) {
            getRcv().callbackList(listOrg, listCurrent);
        }
    }

    @Override
    public void callbackRemoveItem(ITEM item, List<ITEM> listOrg, List<ITEM> listCurrent) {
        if (getRcv() != null) {
            getRcv().callbackRemoveItem(item, listOrg, listCurrent);
        }
    }

    @Override
    public void callbackRemoveItemByPosition(int position, List<ITEM> listOrg, List<ITEM> listCurrent) {
        if (getRcv() != null) {
            getRcv().callbackRemoveItemByPosition(position, listOrg, listCurrent);
        }
    }

    @Override
    public void callbackDataRefreshSuccess(List<ITEM> list) {
        if (getRcv() != null) {
            getRcv().callbackDataRefreshSuccess(list);
        }
    }

    @Override
    public void callbackLoadDataSuccess(List<ITEM> list) {
        super.callbackLoadDataSuccess(list);
        if (getRcv() != null) {
            getRcv().callbackLoadDataSuccess(list);
            notifyDataWithRvPagerChange();
        }
    }

    @Override
    public void callDataReLoad() {
        mRcv.callDataReLoad();
    }

    @Override
    public void callbackDataRefreshSuccess(BEAN bean) {

    }

    @Override
    public void callbackDataEmpty() {
        super.callbackDataEmpty();
        mRcv.callbackDataEmpty();
    }

    @Override
    public void callbackHideLoadStatus() {
        mRcv.setFooterVisible(View.GONE);
    }

    protected int mSelectPosition = -1;

    private OnRvChangeListener onRvChangeListener;

    void setOnRvChangeListener(OnRvChangeListener onRvChangeListener) {
        this.onRvChangeListener = onRvChangeListener;
    }

    public void selectCurrentPlayItem(int position) {
        Debug.d(TAG, "selectCurrentPlayItem " + position);
        if (mSelectPosition != 0 && mSelectPosition == position) {
            Debug.d(TAG, "selectCurrentPlayItem mSelectPosition == position ... not need reSelect... ");
        } else {
            mSelectPosition = position;
            if (onRvChangeListener == null) {
                Debug.d(TAG, "onItemSelect notifyDataWithRvPagerChange ... " + mSelectPosition);
                notifyDataWithRvPagerChange();
            } else {
                Debug.d(TAG, "onItemSelect notifyDataChange ... " + mSelectPosition);
                notifyDataChange();
            }
            //        getRcv().scrollToPosition(callbackSwitchPosition);
            if (FocusStateConfigure.LAST_VIEW_EVENT_IS_KNOB) {
                Debug.d(TAG, "正在用旋钮操作的，在播放列表，就不要自动滚动到当前播放位置了。。。");
                Debug.d(TAG, "FocusStateConfigure.LAST_VIEW_EVENT_IS_KNOB 没有触摸过是true ... ");
            } else {
                Debug.d(TAG, "是手动触摸的，在播放列表，就需要自动滚动到当前播放位置了。。。");
                Debug.d(TAG, "FocusStateConfigure.LAST_VIEW_EVENT_IS_KNOB 触摸过是false ... ");
                RecyclerView.LayoutManager layoutManager = getLayoutManager();
                if (layoutManager != null && layoutManager instanceof LinearLayoutManager) {
                    //如果是横向滑动的就不要位移
                    if (((LinearLayoutManager) layoutManager).getOrientation() == HORIZONTAL) {
                        ((LinearLayoutManager) layoutManager).scrollToPosition(position);
                    } else {
                        ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(position, getOffset());
                    }
                }
                getRcv().updateScrollPageEvent(position);
            }
            ClientApplication.getHandler().post(() -> {
                if (onRvChangeListener != null) {
                    onRvChangeListener.onRvChange(position);
                }
            });
        }
    }

    public void notifyDataChange() {
        notifyDataSetChanged();
    }

    /**
     * 当前item 需要居中，获取偏移量
     *
     * @return 偏移量
     */
    private int getOffset() {
        //居中的位置等于 recyclerView 的高度的一半 减去 recyclerView 头部的高度和 减去 当前选中的item 的高度的一半
        return getRcvHeightHalf() - getHeadHeightCount() - getItemHalf();
    }

    /**
     * recyclerView 的高度的一半。
     *
     * @return recyclerView 的高度的一半
     */
    private int getRcvHeightHalf() {
        return getRcv().getHeight() / 2;
    }

    /**
     * recyclerView 头部的高度和。
     *
     * @return recyclerView 头部的高度和
     */
    private int getHeadHeightCount() {
        return getRcv().getBaseHFAdapter().getHeaderHeightCount();
    }

    /**
     * 当前选中的item 的高度的一半
     *
     * @return 当前选中的item 的高度的一半
     */
    private int getItemHalf() {
        View view = getRcv().getLayoutManager().findViewByPosition(mSelectPosition);
        if (view == null) {
            return 0;
        } else {
            return view.getHeight() / 2;
        }
    }

    @Override
    public void recycle() {
        super.recycle();
        //由于采用单例了，这里也不能调用了。
        //        if (mRcv != null) {
        //            mRcv.clearOnChildAttachStateChangeListeners();
        //            mRcv.clearOnScrollListeners();
        //            mRcv.clearAnimation();
        //        }
    }

    protected OnPagerChangeListener onPagerChangeListener;

    public void setOnPagerChangeListener(OnPagerChangeListener onPagerChangeListener) {
        this.onPagerChangeListener = onPagerChangeListener;
    }

    public interface OnPagerChangeListener {
        void onPagerChange();

        void onPagerDeleteChange(int deletePosition);
    }
}
