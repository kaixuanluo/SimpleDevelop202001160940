package com.android2droid.simpledevelop202001160940.mvp.base.presenter;

import android.content.Context;

import com.didi365.miudrive.navi.main.music.base.model.BaseListBean;
import com.didi365.miudrive.navi.main.music.base.status.BaseLoadPreListStatus;

import java.util.List;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/18 15:50 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/18 15:50 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public abstract class BaseLoadPreListPresenter<ITEM, BEAN extends BaseListBean<ITEM>,
        M extends BaseLoadPreListStatus.BaseMLoadPreListStatus<ITEM, BEAN>,
        V extends BaseLoadPreListStatus.BaseVLoadPreListStatus<ITEM, BEAN>>
        extends BaseLoadMoreListPresenter<ITEM, BEAN, M, V> implements BaseLoadPreListStatus<ITEM, BEAN> {

    protected int loadPrePage;

    public BaseLoadPreListPresenter(Context context) {
        super(context);
    }

    public int getLoadPrePage() {
        return loadPrePage;
    }

    @Override
    public void callbackLoadPrePage(int loadPrePage) {
        this.loadPrePage = loadPrePage;
    }

    @Override
    public void callLoadPreData(ITEM data) {
        callbackDataLoading();
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callLoadPreData(data);
            }
        }
    }

    @Override
    public void callSetLoadPreData(BEAN bean) {
        getModule().callSetLoadPreData(bean);
    }

    @Override
    public void callbackNoPreData() {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackNoPreData();
            }
        }
    }

    @Override
    public void callbackHasPreData() {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackHasPreData();
            }
        }
    }

    @Override
    public void callbackLoadPreDataEnable(boolean enable) {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackLoadPreDataEnable(enable);
            }
        }
    }

    @Override
    public void callbackLoadPreDataSuccess(BEAN bean) {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackLoadPreDataSuccess(bean);
            }
        }
    }

    @Override
    public void callbackLoadPreDataSuccess(List<ITEM> list) {

        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackLoadPreDataSuccess(list);
            }
        }
    }

    @Override
    public void callbackLoadPreDataError(int code, String msg) {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackLoadPreDataError(code, msg);
            }
        }
    }

    @Override
    public void callbackLoadPreDataEmpty() {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackLoadPreDataEmpty();
            }
        }
    }

    @Override
    public void callDataRefresh() {
        super.callDataRefresh();
    }

    @Override
    public void callLoadData() {
        super.callLoadData();
    }
}
