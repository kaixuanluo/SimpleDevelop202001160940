package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import android.support.v7.widget.RecyclerView;

import com.didi365.miudrive.navi.main.music.base.model.BaseListBean;
import com.didi365.miudrive.navi.main.music.base.status.BaseLoadPreListStatus;

import java.util.List;

/**
 * Created by luokaixuan
 * Created Date 2017/12/25.
 * Description
 * RecyclerView 和 SmartRefreshLayout
 * 加载上一页，加载下一页。
 * @author luokaixuan
 */

public abstract class BaseLoadPreMoreRcvSrlListLayout<ITEM, BEAN extends BaseListBean<ITEM>
        , P extends BaseLoadPreListStatus.BasePLoadPreListStatus<ITEM, BEAN>
        , HOLDER extends RecyclerView.ViewHolder>
        extends BaseLoadMoreBtnRcvSrlListLayout<ITEM, BEAN, P, HOLDER>
        implements BaseLoadPreListStatus<ITEM, BEAN> {

    private int mLoadPrePage;

    public int getLoadPrePage() {
        return mLoadPrePage;
    }

    @Override
    public void callbackLoadPrePage(int mLoadPrePage) {
        this.mLoadPrePage = mLoadPrePage;
    }

    @Override
    public void callSetLoadPreData(BEAN bean) {
        getPresenter().callSetLoadPreData(bean);
    }

    @Override
    public void callLoadPreData(ITEM data) {

    }

    @Override
    public void callbackNoPreData() {

    }

    @Override
    public void callbackHasPreData() {

    }

    @Override
    public void callbackLoadPreDataEnable(boolean enable) {

    }

    @Override
    public void callbackLoadPreDataSuccess(BEAN bean) {

    }

    @Override
    public void callbackLoadPreDataSuccess(List<ITEM> list) {

    }

    @Override
    public void callbackLoadPreDataError(int code, String msg) {

    }

    @Override
    public void callbackLoadPreDataEmpty() {

    }

    protected void getPreList(List<ITEM> list) {
        if (getBaseList() != null) {
            getBaseList().addAll(0, list);
        }
    }

    @Override
    protected void onRefreshListener() {
        if (getPresenter() != null) {
            if (getLoadPrePage() == getFirstPage()) {
                super.onRefreshListener();//如果到首页了就刷新。
            } else {
                getPresenter().callLoadPreData(getFirstItem());
            }
        }
    }

}
