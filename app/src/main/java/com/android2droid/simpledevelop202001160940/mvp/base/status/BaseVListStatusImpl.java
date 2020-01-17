package com.android2droid.simpledevelop202001160940.mvp.base.status;

import com.didi365.miudrive.navi.main.music.base.model.BaseListBean;
import com.didi365.miudrive.navi.main.music.base.model.BaseLoadingDataBean;

import java.util.List;

/**
 * @author created by luokaixuan
 * @date 2019/10/10
 * 这个类是用来干嘛的
 */
public class BaseVListStatusImpl<ITEM, BEAN extends BaseListBean<ITEM>> implements BaseListStatus.BaseVListStatus<ITEM, BEAN> {

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
    public void callbackLoadDataStart() {

    }

    @Override
    public void callbackDataLoading() {

    }

    @Override
    public void callbackLoadDataSuccess(BEAN bean) {

    }

    @Override
    public void callbackDataEmpty() {

    }

    @Override
    public void callbackDataFailure(int code, String msg) {

    }

    @Override
    public void callSetDataError(String msg) {

    }

    @Override
    public void callbackDataError(String msg) {

    }

    @Override
    public void callbackDataComplete() {

    }

    @Override
    public void callDataReLoad() {

    }

    @Override
    public void callDataRefresh() {

    }

    @Override
    public void callbackDataRefreshSuccess(BEAN bean) {

    }
}
