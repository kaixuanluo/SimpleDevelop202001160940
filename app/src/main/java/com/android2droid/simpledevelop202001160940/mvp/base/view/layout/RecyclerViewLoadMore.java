package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android2droid.simpledevelop202001160940.R;
import com.android2droid.simpledevelop202001160940.mvp.base.model.BaseListBean;
import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseListStatus;
import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseLoadMoreListStatus;
import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseLoadingStatus;
import com.android2droid.simpledevelop202001160940.mvp.base.util.Debug;
import com.android2droid.simpledevelop202001160940.mvp.base.view.adapter.BaseHFAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/20 13:48 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/20 13:48 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 *
 * @author luokaixuan
 */
public class RecyclerViewLoadMore<ITEM, BEAN extends BaseListBean<ITEM>> extends ScaleRecyclerView
        implements BaseLoadingStatus<BEAN>
        , BaseListStatus<ITEM, BEAN>, BaseLoadMoreListStatus<ITEM, BEAN> {

    protected static final String TAG = RecyclerViewLoadMore.class.getSimpleName();

    protected int lastVisibleItemPosition;

    private boolean mEnableLoadMore = true;

    private BaseListAdapter<ITEM> mBaseListAdapter;

    public int getLastVisibleItemPosition() {
        return lastVisibleItemPosition;
    }

    List<ITEM> list = new ArrayList<>();

    public List<ITEM> getList() {
        return list;
    }

    public void setList(List<ITEM> list) {
        mBaseListAdapter.setItems(list);
        this.list = list;
    }

    public RecyclerViewLoadMore(Context context) {
        super(context);
        setClipToPadding(false);
        setClipChildren(false);
    }

    public RecyclerViewLoadMore(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewLoadMore(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private boolean mIsLoading, mHasMore, mIsLoadMore, mIsRefreshing;

    //    private List<ITEM> mBaseList;
    private TextView mTvFooter;
    private View mItemFootView;
    private BaseHFAdapter mBaseHFAdapter;

    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;

    private OnRecyclerViewItemLongClickListener mOnRecyclerViewItemLongClickListener;

    private OnRecyclerViewLoadMoreListener mOnRecyclerViewLoadMoreListener;

    private OnRecyclerViewLoadPreListener mOnRecyclerViewLoadPreListener;

    private OnReloadListener mOnReloadListener;

    public boolean isLoadMoreing() {
        return mIsLoading;
    }

    public void setLoadMoring(boolean loading) {
        mIsLoading = loading;
    }

    public boolean isHasMore() {
        return mHasMore;
    }

    public void setHasMore(boolean hasMore) {
        mHasMore = hasMore;
    }

    public boolean isLoadMore() {
        return mIsLoadMore;
    }

    public void setLoadMore(boolean loadMore) {
        mIsLoadMore = loadMore;
    }

    public boolean isRefreshing() {
        return mIsRefreshing;
    }

    public void setIsRefreshing(boolean mIsRefreshing) {
        this.mIsRefreshing = mIsRefreshing;
    }

    //    public List<ITEM> getBaseList() {
//        return mBaseList;
//    }

    public TextView getTvFooter() {
        return mTvFooter;
    }

    public void setTvFooter(TextView tvFooter) {
        mTvFooter = tvFooter;
    }

    public View getItemFootView() {
        return mItemFootView;
    }

    public void setItemFootView(View itemFootView) {
        mItemFootView = itemFootView;
    }

    public BaseHFAdapter getBaseHFAdapter() {
        return mBaseHFAdapter;
    }

    public void setBaseHFAdapter(BaseHFAdapter baseHFAdapter) {
        mBaseHFAdapter = baseHFAdapter;
    }

    private OnRecyclerItemClickListener mOnItemClickListener;

    private OnScrollListener mOnScrollListener;

    public ITEM getLastItem() {
        return getList() != null && getList().size() > 0 ?
                getList().get(getList().size() - 1) : null;
    }

    public ITEM getFirstItem() {
        return getList() != null && getList().size() > 0 ?
                getList().get(0) : null;
    }

    public int getListSize() {
//        return mBaseHFAdapter == null ? 0 : mBaseHFAdapter.getItemCount();
        return getList() == null ? 0 : getList().size();
    }

    /**
     * 绑定item
     *
     * @param <ITEM>   条目
     * @param <HOLDER> 条目view容器
     */
    interface OnRecyclerViewBindListener<ITEM, HOLDER> {
        RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent, int viewType);

        void bindItemViewHolder(ITEM item, HOLDER holder, int position);

        void bindItemViewHolder(ITEM item, HOLDER holder, int position, @NonNull List payloads);
    }

    private OnRecyclerViewBindListener mRecyclerViewBindListener;

    public OnRecyclerViewBindListener getRecyclerViewBindListener() {
        return mRecyclerViewBindListener;
    }

    public void setRecyclerViewBindListener(OnRecyclerViewBindListener mRecyclerViewBindListener) {
        this.mRecyclerViewBindListener = mRecyclerViewBindListener;
    }

    public void initRecyclerView(LayoutManager layoutManager) {
        mBaseListAdapter = new BaseListAdapter();
        mBaseListAdapter.setRecyclerViewBindListener(mRecyclerViewBindListener);
        mBaseHFAdapter = initBaseHFAdapter(mBaseListAdapter);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//        setRecyclerViewManager(linearLayoutManager);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
//        setRecyclerViewManager(gridLayoutManager);
//        StaggeredGridLayoutManager staggeredGridLayoutManager =
//                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
//        setRecyclerViewManager(staggeredGridLayoutManager);
        setRecyclerViewManager(layoutManager);
        setHasFixedSize(true);
//        BaseHFAdapter baseHFAdapter = new BaseHFAdapter(new BaseListAdapter());//能够添加头部尾部的适配器
//        addFootView(context, baseHFAdapter);//添加尾部。
        //设置适配器
        setRecyclerViewAdapter(mBaseHFAdapter);
//        setRcvScrollListener();//设置滚动监听。
        //设置条目点击
        addRcvItemTouchListener(mBaseHFAdapter);
    }

    protected BaseHFAdapter initBaseHFAdapter(BaseListAdapter baseListAdapter) {
        BaseHFAdapter baseHFAdapter = new BaseHFAdapter(baseListAdapter);
        return baseHFAdapter;
    }

    public void addFootView(View view) {
        getBaseHFAdapter().addFootView(view);
    }

    private void addFootView(Context context, BaseHFAdapter baseHFAdapter) {
        mItemFootView = LayoutInflater.from(context).inflate(R.layout.item_loading, null);
        FrameLayout.LayoutParams lytp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lytp.gravity = Gravity.CENTER;
        mItemFootView.setLayoutParams(lytp);
        mTvFooter = (TextView) mItemFootView.findViewById(R.id.progressTv);
        ImageView ivLoading = (ImageView) mItemFootView.findViewById(R.id.progressIv);
        AnimationDrawable loadingDrawable = (AnimationDrawable) ivLoading.getDrawable();
        loadingDrawable.start();
        setFooterClick(mItemFootView);
        baseHFAdapter.addFootView(mItemFootView);
    }

    public void addHeardView(View view) {
        getBaseHFAdapter().addHeaderView(view);
    }

    public void removeHeardView(View view) {
        getBaseHFAdapter().removeHearderView(view);
    }

    public void removeAllHeardView() {
        getBaseHFAdapter().removeAllHeardView();
    }

    protected void setFooterClick(View mItemFootView) {
        mItemFootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isHasMore()) {
                    return;
                }
                if (isHasMore() && isLoadMore()) {
                    mOnRecyclerViewLoadMoreListener.onLoadMore();
                } else {
                }
            }
        });
    }

//    public ITEM getLastItem() {
//        return mBaseList != null && mBaseList.size() > 0 ? mBaseList.get(mBaseList.size() - 1) : null;
//    }

    protected void setRecyclerViewManager(LayoutManager layoutManager) {
        setLayoutManager(layoutManager);
    }

    protected void setRecyclerViewAdapter(Adapter adapter) {
        setAdapter(adapter);
    }

    //            private int lastVisibleItemPosition;
    private int[] lastPositions;

    private int totalItemCount;

    /**
     * 预留条数
     */
    private int visibleThreshold = 0;

    int mNewState;

    void setRcvScrollListener() {
        if (mOnScrollListener != null) {
            removeOnScrollListener(mOnScrollListener);
        }

        mOnScrollListener = new OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mNewState = newState;
//                Debug.d(TAG, "onScrollStateChanged ... ");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Debug.d(TAG, "onScrolled ... ");
                //更新位置记录
                updatePosition();

                //调用滑动监听
                updateScrollListener();

            }
        };
        addOnScrollListener(mOnScrollListener);
    }

    protected void updatePosition() {
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager != null) {
            if (layoutManager instanceof LinearLayoutManager) {
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else if (layoutManager instanceof GridLayoutManager) {
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (lastPositions == null) {
                    lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                lastVisibleItemPosition = findMax(lastPositions);
            } else {

            }
        }

        //这个包括头部和尾部
        totalItemCount = layoutManager.getItemCount() - (mBaseHFAdapter.getHeadersCount() + mBaseHFAdapter.getFootersCount());

    }

    private void updateScrollListener() {
        if (mEnableLoadMore) {
            //是否允许加载更多。
            if (isRefreshing()) {
                Debug.d(TAG, " 正在刷新中 is refreshing ... ");
            } else {
                if (isLoadMoreing()) {
                    Debug.d(TAG, " 正在加载更多中 is isLoadMoreing ... ");
                } else {
                    if (isHasMore()) {
                        Debug.d(TAG, "还有更多条目 。。。 ");
                        Debug.d(TAG, "总条数 " + totalItemCount);
                        Debug.d(TAG, "最后可见的条数的位置 " + lastVisibleItemPosition);
                        //                if (getBaseList() != null) {//这个不包括头部和尾部
//                    totalItemCount = getBaseList().size();
                        if (totalItemCount > 0 && totalItemCount <= (lastVisibleItemPosition + 1 + visibleThreshold)) {
                            Debug.d(TAG, "recyclerView 调用加载更多 。。。");
                            setLoadMoring(true);
                            setLoadMore(true);
                            if (mOnRecyclerViewLoadMoreListener != null) {
                                mOnRecyclerViewLoadMoreListener.onLoadMore();
                            }
                        } else {
                            Debug.d(TAG, "没有到底部。不用调用加载更多 。。。 ");
                        }
                    } else {
                        Debug.d(TAG, "没有更多条目 。。。 不用加载更多。。。");
                    }
                }
            }
        } else {
            Debug.d(TAG, "is not enable load more ... ");
        }
    }

    private static int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private void addRcvItemTouchListener(final BaseHFAdapter baseHFAdapter) {
        if (mOnItemClickListener != null) {
            removeOnItemTouchListener(mOnItemClickListener);
        }
        mOnItemClickListener = new OnRecyclerItemClickListener(this) {
            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }

            @Override
            public void onItemClick(ViewHolder vh) {
                //屏蔽头部和尾部点击
                if (baseHFAdapter.isHeaderViewPos(vh.getAdapterPosition())) {

                } else if (baseHFAdapter.isFooterViewPos(vh.getAdapterPosition())) {

                } else {
                    if (mOnRecyclerViewItemClickListener != null) {
                        mOnRecyclerViewItemClickListener.onItemClick(vh, vh.getAdapterPosition() - baseHFAdapter.getHeadersCount());
                    }
                }
            }

            @Override
            public void onItemLongClick(ViewHolder vh) {
                //屏蔽头部和尾部点击
                if (baseHFAdapter.isHeaderViewPos(vh.getAdapterPosition())) {

                } else if (baseHFAdapter.isFooterViewPos(vh.getAdapterPosition())) {

                } else {
                    if (mOnRecyclerViewItemLongClickListener != null) {
                        mOnRecyclerViewItemLongClickListener.onItemLongClick(vh, vh.getAdapterPosition() - baseHFAdapter.getHeadersCount());
                    }
                }
            }
        };
        addOnItemTouchListener(mOnItemClickListener);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnRecyclerViewItemClickListener = listener;
    }

    public void setonItemLongClickListener(OnRecyclerViewItemLongClickListener listener) {
        this.mOnRecyclerViewItemLongClickListener = listener;
    }

    public void setOnRecyclerViewLoadMoreListener(OnRecyclerViewLoadMoreListener listener) {
        mOnRecyclerViewLoadMoreListener = listener;
    }

    public void setOnRecyclerViewLoadPreListener(OnRecyclerViewLoadPreListener mOnRecyclerViewLoadPreListener) {
        this.mOnRecyclerViewLoadPreListener = mOnRecyclerViewLoadPreListener;
    }

    public OnRecyclerViewLoadPreListener getOnRecyclerViewLoadPreListener() {
        return mOnRecyclerViewLoadPreListener;
    }

    public OnRecyclerViewLoadMoreListener getmOnRecyclerViewLoadMoreListener() {
        return mOnRecyclerViewLoadMoreListener;
    }

    public void setOnReloadListener(OnReloadListener onReloadListener) {
        mOnReloadListener = onReloadListener;
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(ViewHolder vh, int position);
    }

    public interface OnRecyclerViewItemLongClickListener {
        void onItemLongClick(ViewHolder vh, int position);
    }

    public interface OnRecyclerViewLoadMoreListener {
        void onLoadMore();
    }

    public interface OnRecyclerViewLoadPreListener {
        void onLoadPre();
    }

    public interface OnReloadListener {
        void onReload();
    }

    protected void setFooterText(String text) {
        if (mTvFooter != null) {
            mTvFooter.setText(text);
        }
    }

//    protected void setFooterText(@StringRes int text) {
//        if (mTvFooter != null) {
//            mTvFooter.setText(getString(text));
//        }
//    }

//    protected void setFooterClick() {
//        mItemFootView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!mHasMore) {
//                    return;
//                }
////                if (getLastItem() == null) {
////                    callLoadData();
////                } else {
////                    loadListMoreData(getLastItem());
////                }
//                if (mIsLoadMore && getLastItem() != null) {
//                    callLoadMoreData(getLastItem());
//                } else {
//                    callLoadData();
//                }
//            }
//        });
//    }

    public void setFooterVisible(int visibility) {
        if (mItemFootView != null) {
            mItemFootView.findViewById(R.id.item_loading).setVisibility(visibility);
        }
    }

    protected void setFooterEnable(boolean enable) {
        if (mItemFootView != null) {
            mItemFootView.setEnabled(enable);
        }
    }

    @Override
    public void callLoadData() {
    }

    @Override
    public void callSetRefreshDataEnable(boolean isRefreshEnable) {

    }

    @Override
    public void callbackSetRefreshDataEnable(boolean isRefreshEnable) {

    }

    @Override
    public void callSetLoadData(BEAN bean) {

    }

    @Override
    public void callSetLoadMoreData(BEAN bean) {

    }

    @Override
    public void callbackLoadDataStart() {
//        super.callbackLoadDataStart();
        setLoadMore(false);
        setFooterText("开始加载。。。");
        setFooterVisible(View.VISIBLE);
        setFooterEnable(false);
    }

    @Override
    public void callbackNoMoreData() {
        setHasMore(false);
        setFooterText("没有更多。。。");
        setFooterEnable(false);
    }

    @Override
    public void callbackHasMoreData() {
        setHasMore(true);
//        setFooterText("加载中。。。");
        setFooterEnable(false);
    }

    @Override
    public void callSetLoadMoreDataEnable(boolean enable) {

    }

    @Override
    public void callbackHideLoadStatus() {

    }

    @Override
    public void callbackLoadMoreDataEnable(boolean enable) {
        mEnableLoadMore = enable;
        if (enable) {
            callbackHasMoreData();
        } else {
            callbackNoMoreData();
        }
    }

    @Override
    public void callbackLoadMoreDataSuccess(BEAN bean) {

    }

    @Override
    public void callbackLoadMoreDataSuccess(List<ITEM> list) {
        if (list == null || list.size() == 0) {
            Debug.d(TAG, "callbackLoadMoreDataSuccess list == null || list.size() == 0 没有更多数据。。。");
            callbackNoMoreData();
        } else {
            Debug.d(TAG, "callbackLoadMoreDataSuccess 还有更多数据。。。");
        }
    }

    @Override
    public void callbackLoadMoreDataError(int code, String msg) {
        if (!isLoadMore()) {
            setFooterVisible(View.GONE);
        }
        setFooterText("加载失败， 点击重试。。。");
        setFooterEnable(true);
    }

    @Override
    public void callbackLoadMoreDataEmpty() {
        callbackNoMoreData();
    }

    @Override
    public void callbackLoadMorePage(int loadMorePage) {

    }

    @Override
    public void callSetLoadMorePage(int loadMorePage) {

    }

    public boolean ismEnableLoadMore() {
        return mEnableLoadMore;
    }

    @Override
    public void callbackDataError(String msg) {
        if (!isLoadMore()) {
            setFooterVisible(View.GONE);
        }
        setFooterText("加载错误，点击重试。。。");
        setFooterEnable(true);
    }

    @Override
    public void callbackDataFailure(int code, String msg) {

    }

    @Override
    public void callSetDataError(String msg) {

    }

    @Override
    public void callLoadMoreData(ITEM lastItem) {
//        setFooterText("加载更多，加载中。。。");
        setFooterEnable(false);
        setLoadMore(true);
//        mOnRecyclerViewLoadMoreListener.onLoadMore();
    }

    @Override
    public void callbackStartLoadMoreData() {
        Debug.d(TAG, "回调开始加载更多 。。。");
    }

    @Override
    public void callbackDataComplete() {
        setIsRefreshing(false);
        setLoadMoring(false);
        Debug.d(TAG, "callbackDataComplete ... ");
    }

    @Override
    public void callbackLoadDataSuccess(BEAN data) {

    }

    @Override
    public void callSetLoadList(List<ITEM> list) {

    }

    @Override
    public void callGenerateListData(List<ITEM> list) {
    }

    @Override
    public void callbackList(List<ITEM> listOrg, List<ITEM> listCurrent) {
        if (mBaseListAdapter == null) {
            Debug.d(TAG, "callbackList mBaseListAdapter == null ... ");
        } else {
            Debug.d(TAG, "callbackList mBaseListAdapter != null ... ");
            Debug.d(TAG, "callbackList list " + (list == null ? null : list.toString()));
            setList(listCurrent);
        }
    }

    @Override
    public void callbackRemoveItem(ITEM item, List<ITEM> listOrg, List<ITEM> listCurrent) {
        notifyDataSetChanged();
    }

    @Override
    public void callbackRemoveItemByPosition(int position, List<ITEM> listOrg, List<ITEM> listCurrent) {
        notifyItemRemoved(position);
    }

    private void notifyItemRemoved(int position) {
        if (getAdapter() == null) {
            Debug.d(TAG, "notifyDataSetChanged mBaseListAdapter == null ... ");
        } else {
            if (getList().size() > 0) {
                getAdapter().notifyItemRemoved(position);
                //需要添加范围刷新，让列表同步。
                getAdapter().notifyItemRangeChanged(position, getList().size() - position);
            } else {
                notifyDataSetChanged();
                Debug.d(TAG, "notifyItemRemoved list size <= 0 can't notifyItemRangeChanged ... ");
            }
        }
    }

    @Override
    public void callRemoveItemByPosition(int position) {

    }

    @Override
    public void callRemoveItem(ITEM item) {

    }

    @Override
    public void callbackLoadDataSuccess(List<ITEM> list) {
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        if (getAdapter() == null) {
            Debug.d(TAG, "notifyDataSetChanged mBaseListAdapter == null ... ");
        } else {
            Debug.d(TAG, "notifyDataSetChanged() 1 ");
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void callbackLoadMoreList(List<ITEM> listOrg, List<ITEM> listMore, List<ITEM> listCurrent) {
        notifyItemRangeChanged(listOrg, listMore);
    }

    public void notifyItemRangeChanged(List<ITEM> listOrg, List<ITEM> listMore) {
        if (getAdapter() == null) {
            Debug.d(TAG, "notifyDataSetChanged mBaseListAdapter == null ... ");
        } else {
            Debug.d(TAG, "notifyDataSetChanged() 2");
            int notifyStartPosition = listOrg.size() > 0 ? listOrg.size() - 1 : 0;
            getAdapter().notifyItemRangeChanged(notifyStartPosition, listMore.size(), listMore);
        }
    }

    @Override
    public void callbackDataRefreshSuccess(List<ITEM> list) {
    }

    @Override
    public void callSetFirstPage(int firstPage) {

    }

    @Override
    public void callbackSetFirstPage(int firstPage) {

    }

    @Override
    public void callGenerateListMoreData(List<ITEM> list) {

    }

    @Override
    public void callDataReLoad() {
//        super.callDataReLoad();
    }

    @Override
    public void callDataRefresh() {

    }

    @Override
    public void callbackDataRefreshSuccess(BEAN bean) {

    }

    @Override
    public void callbackDataEmpty() {
        setFooterText("");
        setFooterVisible(View.GONE);
    }

    @Override
    public void callbackDataLoading() {
//        super.callbackDataLoading();
        setFooterText("加载中。。。");
    }

    /**
     * 设置是否打开动画
     *
     * @param animatorEnable
     */
    public void setAnimatorEnable(boolean animatorEnable) {
        if (animatorEnable) {
            openDefaultAnimator();
        } else {
            closeRecyclerViewAnimator();
        }
    }

    /**
     * 关闭默认局部刷新动画
     */
    private void closeRecyclerViewAnimator() {
        getItemAnimator().setChangeDuration(0);
        getItemAnimator().setAddDuration(0);
        getItemAnimator().setMoveDuration(0);
        getItemAnimator().setRemoveDuration(0);
        ((SimpleItemAnimator) getItemAnimator()).setSupportsChangeAnimations(false);
    }

    /**
     * 打开默认局部刷新动画
     */
    private void openDefaultAnimator() {
        this.getItemAnimator().setAddDuration(120);
        this.getItemAnimator().setChangeDuration(250);
        this.getItemAnimator().setMoveDuration(250);
        this.getItemAnimator().setRemoveDuration(120);
        ((SimpleItemAnimator) this.getItemAnimator()).setSupportsChangeAnimations(true);
    }
}
