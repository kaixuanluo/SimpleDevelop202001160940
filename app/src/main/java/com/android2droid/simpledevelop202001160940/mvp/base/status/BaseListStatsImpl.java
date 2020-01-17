package com.android2droid.simpledevelop202001160940.mvp.base.status;

import com.didi365.miudrive.navi.main.music.base.model.BaseListBean;
import com.didi365.miudrive.navi.main.music.base.model.BaseLoadingDataBean;

import java.util.List;

/**
 * Created by luokaixuan
 * Created Date 2018/10/13.
 * Description TODO
 */
public class BaseListStatsImpl<ITEM, BEAN extends BaseListBean<ITEM>> extends
        BaseLoadingStatusImpl<BEAN> implements BaseListStatus<ITEM, BEAN> {

    @Override
    public void callSetLoadList(List<ITEM> list) {

    }

    @Override
    public void callGenerateListData(List<ITEM> list) {

    }

    @Override
    public void callbackList(List<ITEM> listOrg, List<ITEM> listCurrent) {

    }

    @Override
    public void callRemoveItemByPosition(int position) {

    }

    @Override
    public void callRemoveItem(ITEM item) {

    }

    @Override
    public void callbackRemoveItemByPosition(int position, List<ITEM> listOrg, List<ITEM> listCurrent) {

    }

    @Override
    public void callbackRemoveItem(ITEM item, List<ITEM> listOrg, List<ITEM> listCurrent) {

    }

    @Override
    public void callbackLoadDataSuccess(List<ITEM> list) {

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
    public void callbackHideLoadStatus() {

    }

}
