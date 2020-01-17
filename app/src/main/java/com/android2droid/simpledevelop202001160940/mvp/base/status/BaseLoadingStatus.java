package com.android2droid.simpledevelop202001160940.mvp.base.status;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/10 16:52 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/10 16:52 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public interface BaseLoadingStatus<BEAN> extends BaseStatus {

    /**
     * 加载数据
     */
    void callLoadData();

    void callSetRefreshDataEnable(boolean isRefreshEnable);

    void callbackSetRefreshDataEnable(boolean isRefreshEnable);

    /**
     * 调用设置数据
     */
    void callSetLoadData(BEAN bean);

    /**
     * 开始加载
     */
    void callbackLoadDataStart();

    /**
     * 加载中
     */
    void callbackDataLoading();

    /**
     * 加载成功
     */
    void callbackLoadDataSuccess(BEAN bean);

    /**
     * 数据为空
     */
    void callbackDataEmpty();

    /**
     * 加载失败
     */
    void callbackDataFailure(int code, String msg);

    void callSetDataError(String msg);
    /**
     * 发生错误
     */
    void callbackDataError(String msg);

    /**
     * 加载完成
     */
    void callbackDataComplete();

    /**
     * 错误重新加载
     */
    void callDataReLoad();

    /**
     * 下拉刷新。
     */
    void callDataRefresh();

    /**
     * 刷新成功
     *
     * @param bean 刷新成功的数据
     */
    void callbackDataRefreshSuccess(BEAN bean);

    interface BaseMLoadingStatus<BEAN> extends BaseLoadingStatus<BEAN> {
    }

    interface BaseVLoadingStatus<BEAN> extends BaseLoadingStatus<BEAN> {

    }

    interface BasePLoadingStatus<BEAN> extends BaseLoadingStatus<BEAN> {
        void setBaseVLoadingStatus(BaseVLoadingStatus<BEAN> vLoadingStatus);
    }
}
