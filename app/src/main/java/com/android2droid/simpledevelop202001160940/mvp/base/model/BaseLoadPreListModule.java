package com.android2droid.simpledevelop202001160940.mvp.base.model;

import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseLoadPreListStatus;
import com.android2droid.simpledevelop202001160940.mvp.base.util.Debug;

import java.util.List;
import java.util.Map;

/**
 * Created by luokaixuan
 * Created Date 2018/4/17.
 * Description Module
 */
public class BaseLoadPreListModule<ITEM, BEAN extends BaseListBean<ITEM>,
        P extends BaseLoadPreListStatus.BasePLoadPreListStatus<ITEM, BEAN>>
        extends BaseLoadMoreListModule<ITEM, BEAN, P> implements BaseLoadPreListStatus<ITEM, BEAN> {

    protected int loadPrePage;

    public int getLoadPrePage() {
        return loadPrePage;
    }

    @Override
    public void callbackLoadPrePage(int loadPrePage) {
        this.loadPrePage = loadPrePage;
    }

    public BaseLoadPreListModule(P baseLoadPreListPresenter) {
        super(baseLoadPreListPresenter);
    }

    public void okGoPre(Class<BEAN> bean, String url) {
        if (loadPrePage < mFirstPage) {//上一页是否到第一页了。
            Debug.d(TAG, "loadPrePage < mFirstPage ... " + "loadPrePage " + loadPrePage + " mFirstPage " + mFirstPage);
        } else {
            loadPre(bean, url, initQueryMap());
        }
    }

    @Override
    protected Map<String, String> initQueryMap() {
        Map<String, String> map = super.initQueryMap();
        map.put("page", loadPrePage + "");
        return map;
    }

    @Override
    public void callSetLoadPreData(BEAN bean) {
        callbackLoadPreDataSuccess(bean.getList());
    }

    @Override
    public void callbackLoadDataSuccess(BEAN bean) {
        super.callbackLoadDataSuccess(bean);
    }

    @Override
    public void callbackLoadPreDataSuccess(BEAN bean) {
        getPresenter().callbackLoadPreDataSuccess(bean);
    }

    @Override
    public void callbackLoadPreDataSuccess(List<ITEM> list) {
        setLoadPreSuccessPage();
        getPresenter().callbackLoadPreDataSuccess(list);
    }

    @Override
    public void callbackLoadPreList(List<ITEM> listOrg, List<ITEM> listPre, List<ITEM> listCurrent) {
        getPresenter().callbackLoadPreList(listOrg, listPre, listCurrent);
    }

    @Override
    public void callbackLoadPreDataError(int code, String msg) {
        getPresenter().callbackLoadPreDataError(code, msg);
    }

    @Override
    public void callbackLoadPreDataEmpty() {
        getPresenter().callbackLoadPreDataEmpty();
    }

    protected void setLoadPreSuccessPage() {
        if (loadPrePage > mFirstPage) {
            loadPrePage--;
            Debug.d(TAG, "loadPrePage-- " + loadPrePage);
        } else {
            Debug.d(TAG, "loadPrePage is < = mFirstPage ... ");
        }
    }

    public void loadPre(Class<BEAN> bean, final String url, Map<String, String> queryMap) {
        super.load(url, queryMap, new BaseLoadPreJsonCallback(bean, false));
    }

    private class BaseLoadPreJsonCallback extends BaseLoadMoreJsonCallback {

        public BaseLoadPreJsonCallback(Class<BEAN> clazz, boolean isLoading) {
            super(clazz, isLoading);
        }
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
    public void callDataRefresh() {
        super.callDataRefresh();
        loadPrePage = mFirstPage;
    }

    @Override
    public void callLoadData() {
        super.callLoadData();
        loadPrePage = mFirstPage;
    }
}
