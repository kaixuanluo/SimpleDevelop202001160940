package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.didi365.miudrive.navi.main.music.base.model.BaseListBean;
import com.didi365.miudrive.navi.main.music.base.status.BaseLoadMoreListStatus;
import com.didi365.miudrive.navi.utils.Debug;

import java.util.List;

/**
 * Created by luokaixuan
 * Created Date 2017/12/25.
 * Description
 * RecyclerView 和 SmartRefreshLayout
 * 加载更多
 *
 * @author luokaixuan
 */

public abstract class BaseLoadMoreRcvSrlListLayout<ITEM, BEAN extends BaseListBean<ITEM>
        , P extends BaseLoadMoreListStatus.BasePLoadMoreListStatus<ITEM, BEAN>
        , HOLDER extends RecyclerView.ViewHolder>
        extends BaseRcvSrlListLayout<ITEM, BEAN, P, HOLDER>
        implements BaseLoadMoreListStatus.BaseVLoadMoreListStatus<ITEM, BEAN> {

    private boolean loadMoreEnable;

    public boolean isLoadMoreEnable() {
        return loadMoreEnable;
    }

    private int loadMorePage;

    public int getLoadMorePage() {
        return loadMorePage;
    }

    @Override
    public void callbackLoadMorePage(int loadMorePage) {
        this.loadMorePage = loadMorePage;
    }

    @Override
    public P getPresenter() {
        return super.getPresenter();
    }

    @Override
    public void callSetLoadMoreDataEnable(boolean loadMoreEnable) {
        getPresenter().callSetLoadMoreDataEnable(loadMoreEnable);
    }

    @Override
    public void callbackLoadMoreDataEnable(boolean loadMoreEnable) {
        this.loadMoreEnable = loadMoreEnable;
        getRcv().setFooterVisible(loadMoreEnable ? View.VISIBLE : View.GONE);
        getRcv().callbackLoadMoreDataEnable(loadMoreEnable);
        if (isHorizontal()) {
            Debug.d(TAG, "is horizontal can't set swipe load more ... ");
        } else {
            setSwipeLoadMoreEnable(loadMoreEnable);
        }
    }

    @Override
    protected void initLayoutIfNotInitialized(Context context) {
        super.initLayoutIfNotInitialized(context);
        setOnSwipeOnLoadMoreListener(refreshLayout -> {
            callLoadMoreData(getLastItem());
            Log.e(" callLoadData 加载更多，", " callLoadData 加载更多，");
        });
    }

    @Override
    protected void initRecyclerView() {
        super.initRecyclerView();
        mRcv.setOnRecyclerViewLoadMoreListener(() -> {
            ITEM lastItem = getLastItem();
            if (isHorizontal()) {
                callLoadMoreData(lastItem);
            } else {
                getRl().autoLoadMore();
            }
            Log.e(" callLoadData 加载更多，", " callLoadData 加载更多，");
        });

    }

    @Override
    public void callSetLoadMoreData(BEAN bean) {
        mRcv.callSetLoadMoreData(bean);
        getPresenter().callSetLoadMoreData(bean);
    }

    @Override
    public void callSetLoadMorePage(int loadMorePage) {
        getPresenter().callSetLoadMorePage(loadMorePage);
    }

    @Override
    public void callbackNoMoreData() {
        mRcv.callbackNoMoreData();
        if (mSrl != null) {
//            mSrl.finishLoadMoreWithNoMoreData();//不能调用这个，否则下拉刷新无法回复。
        }
    }

    @Override
    public void callbackHasMoreData() {
        mRcv.callbackHasMoreData();
    }

    @Override
    public void callLoadMoreData(ITEM lastItem) {
        mRcv.callLoadMoreData(lastItem);
        getPresenter().callLoadMoreData(lastItem);
    }

    @Override
    public void callbackLoadMoreList(List<ITEM> listOrg, List<ITEM> listMore, List<ITEM> listCurrent) {
        if (getRcv() != null) {
            getRcv().callbackLoadMoreList(listOrg, listMore, listCurrent);
        }
    }

    @Override
    public void callbackDataComplete() {
        super.callbackDataComplete();
        if (getSrl() != null) {
            getSrl().finishLoadMore();
        }
    }

    @Override
    public void callbackLoadMoreDataSuccess(BEAN bean) {

    }

    @Override
    public void callbackLoadMoreDataEmpty() {
        if (getRcv() != null) {
            getRcv().callbackLoadMoreDataEmpty();
        }
    }

    @Override
    public void callbackLoadMoreDataError(int code, String msg) {
        if (getRcv() != null) {
            getRcv().callbackLoadMoreDataError(code, msg);
        }
    }

    @Override
    public void callbackLoadMoreDataSuccess(List<ITEM> list) {

        if (getRcv() != null) {
            getRcv().callbackLoadMoreDataSuccess(list);
        }
//        loadMoreNotifyData();
    }

    @Override
    public void callbackStartLoadMoreData() {
        if (getRcv() != null) {
            getRcv().callbackStartLoadMoreData();
        }
        if (mJsonCallbackView != null) {
            mJsonCallbackView.showLoading(getContext(), "");
        }
    }

    //    protected void loadMoreNotifyData() {
//        notifyDataSetChanged();
//        getRcv().getAdapter().notifyItemRangeInserted();
//    }

    @Override
    public void callGenerateListData(List<ITEM> list) {
        super.callGenerateListData(list);
    }

    @Override
    public void callbackDataRefreshSuccess(List<ITEM> list) {
        super.callbackDataRefreshSuccess(list);
    }

    @Override
    public void callGenerateListMoreData(List<ITEM> list) {
        if (list == null || list.size() == 0) {
            callbackNoMoreData();
        } else {
            if (getBaseList() != null) {
                getBaseList().addAll(list);
            }
        }
    }

}
