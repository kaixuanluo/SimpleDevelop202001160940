package com.android2droid.simpledevelop202001160940.mvp.base.status;

import com.android2droid.simpledevelop202001160940.mvp.base.model.BaseListBean;

import java.util.List;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/18 15:01 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/18 15:01 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public interface BaseLoadMoreListStatus<ITEM, BEAN extends BaseListBean<ITEM>> extends BaseListStatus<ITEM, BEAN> {

    void callLoadMoreData(ITEM data);

    void callbackStartLoadMoreData();

    void callSetLoadMoreData(BEAN bean);

    void callGenerateListMoreData(List<ITEM> list);

    void callbackNoMoreData();

    void callbackHasMoreData();

    void callSetLoadMoreDataEnable(boolean enable);

    void callbackLoadMoreDataEnable(boolean enable);

    void callbackLoadMoreDataSuccess(BEAN bean);

    void callbackLoadMoreDataSuccess(List<ITEM> list);

    void callbackLoadMoreList(List<ITEM> listOrg, List<ITEM> listMore, List<ITEM> listCurrent);

    void callbackLoadMoreDataError(int code, String msg);

    void callbackLoadMoreDataEmpty();

    void callbackLoadMorePage(int loadMorePage);

    void callSetLoadMorePage(int loadMorePage);

    interface BaseMLoadMoreListStatus<ITEM, BEAN extends BaseListBean<ITEM>> extends BaseLoadMoreListStatus<ITEM, BEAN>, BaseMListStatus<ITEM, BEAN> {

    }

    interface BaseVLoadMoreListStatus<ITEM, BEAN extends BaseListBean<ITEM>> extends BaseLoadMoreListStatus<ITEM, BEAN>, BaseVListStatus<ITEM, BEAN> {

    }

    interface BasePLoadMoreListStatus<ITEM, BEAN extends BaseListBean<ITEM>> extends BaseLoadMoreListStatus<ITEM, BEAN>, BasePListStatus<ITEM, BEAN> {

    }
}
