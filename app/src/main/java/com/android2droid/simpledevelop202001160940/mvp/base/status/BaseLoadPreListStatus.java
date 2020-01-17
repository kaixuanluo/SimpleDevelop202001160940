package com.android2droid.simpledevelop202001160940.mvp.base.status;

import com.android2droid.simpledevelop202001160940.mvp.base.model.BaseListBean;

import java.util.List;

/**
 * Created by luokaixuan
 * Created Date 2018/4/17.
 * Description 加载上一页列表状态
 */
public interface BaseLoadPreListStatus<ITEM, BEAN extends BaseListBean<ITEM>> extends BaseLoadMoreListStatus<ITEM, BEAN> {
    void callLoadPreData(ITEM data);

    void callSetLoadPreData(BEAN list);

    void callbackNoPreData();

    void callbackHasPreData();

    void callbackLoadPreDataEnable(boolean enable);

    void callbackLoadPreDataSuccess(BEAN bean);

    void callbackLoadPreDataSuccess(List<ITEM> list);

    void callbackLoadPreList(List<ITEM> listOrg, List<ITEM> listPre, List<ITEM> listCurrent);

    void callbackLoadPreDataError(int code, String msg);

    void callbackLoadPreDataEmpty();

    void callbackLoadPrePage(int loadPrePage);

    interface BaseMLoadPreListStatus<ITEM, BEAN extends BaseListBean<ITEM>> extends BaseLoadPreListStatus<ITEM, BEAN>, BaseMLoadMoreListStatus<ITEM, BEAN> {

    }

    interface BaseVLoadPreListStatus<ITEM, BEAN extends BaseListBean<ITEM>> extends BaseLoadPreListStatus<ITEM, BEAN>, BaseVLoadMoreListStatus<ITEM, BEAN> {

    }

    interface BasePLoadPreListStatus<ITEM, BEAN extends BaseListBean<ITEM>> extends BaseLoadPreListStatus<ITEM, BEAN>, BasePLoadMoreListStatus<ITEM, BEAN> {

    }
}
