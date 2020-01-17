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
public interface BaseListStatus<ITEM, BEAN extends BaseListBean<ITEM>> extends BaseLoadingStatus<BEAN> {

    void callSetLoadList(List<ITEM> list);

    void callGenerateListData(List<ITEM> list);

    void callbackList(List<ITEM> listOrg, List<ITEM> listCurrent);

    void callRemoveItemByPosition(int position);

    void callRemoveItem(ITEM item);

    void callbackRemoveItemByPosition(int position, List<ITEM> listOrg, List<ITEM> listCurrent);

    void callbackRemoveItem(ITEM item, List<ITEM> listOrg, List<ITEM> listCurrent);

    void callbackLoadDataSuccess(List<ITEM> list);

    void callbackDataRefreshSuccess(List<ITEM> list);

    void callSetFirstPage(int firstPage);

    void callbackSetFirstPage(int firstPage);

    void callbackHideLoadStatus();

    interface BaseMListStatus<ITEM, BEAN extends BaseListBean<ITEM>> extends BaseListStatus<ITEM, BEAN>, BaseMLoadingStatus<BEAN> {

    }

    interface BaseVListStatus<ITEM, BEAN extends BaseListBean<ITEM>> extends BaseListStatus<ITEM, BEAN>, BaseVLoadingStatus<BEAN> {

    }

    interface BasePListStatus<ITEM, BEAN extends BaseListBean<ITEM>> extends BaseListStatus<ITEM, BEAN>, BasePLoadingStatus<BEAN> {

    }
}
