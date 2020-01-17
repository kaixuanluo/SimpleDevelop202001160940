package com.android2droid.simpledevelop202001160940.mvp.base.status;

import com.didi365.miudrive.navi.main.music.base.model.BaseListBean;
import com.didi365.miudrive.navi.main.music.base.model.BaseLoadingDataBean;

import java.util.List;

/**
 * @author created by luokaixuan
 * @date 2019/8/28
 * 这个类是用来干嘛的
 */
public class BaseLoadMoreListStatusImpl<ITEM, BEAN extends BaseListBean<ITEM>>
        extends BaseListStatsImpl<ITEM, BEAN> implements BaseLoadMoreListStatus.BaseVLoadMoreListStatus<ITEM, BEAN> {

    @Override
    public void callLoadMoreData(ITEM data) {

    }

    @Override
    public void callbackStartLoadMoreData() {

    }

    @Override
    public void callSetLoadMoreData(BEAN list) {

    }

    @Override
    public void callGenerateListMoreData(List<ITEM> list) {

    }

    @Override
    public void callbackNoMoreData() {

    }

    @Override
    public void callbackHasMoreData() {

    }

    @Override
    public void callSetLoadMoreDataEnable(boolean enable) {

    }

    @Override
    public void callbackHideLoadStatus() {

    }

    @Override
    public void callbackLoadMoreDataEnable(boolean enable) {

    }

    @Override
    public void callbackLoadMoreDataSuccess(BEAN bean) {

    }

    @Override
    public void callbackLoadMoreDataSuccess(List<ITEM> list) {

    }

    @Override
    public void callbackLoadMoreList(List<ITEM> listOrg, List<ITEM> listMore, List<ITEM> listCurrent) {

    }

    @Override
    public void callbackLoadMoreDataError(int code, String msg) {

    }

    @Override
    public void callbackLoadMoreDataEmpty() {

    }

    @Override
    public void callbackLoadMorePage(int loadMorePage) {

    }

    @Override
    public void callSetLoadMorePage(int loadMorePage) {

    }
}
