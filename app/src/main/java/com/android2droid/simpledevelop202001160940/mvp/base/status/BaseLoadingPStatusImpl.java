package com.android2droid.simpledevelop202001160940.mvp.base.status;

import com.didi365.miudrive.navi.main.music.base.model.BaseLoadingDataBean;

/**
 * @author created by luokaixuan
 * @date 2019/11/1
 * 这个类是用来干嘛的
 */
public class BaseLoadingPStatusImpl<DATA, BEAN extends BaseLoadingDataBean<DATA>> implements BaseLoadingStatus.BasePLoadingStatus<BEAN> {
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

    @Override
    public void setBaseVLoadingStatus(BaseVLoadingStatus<BEAN> vLoadingStatus) {

    }
}
