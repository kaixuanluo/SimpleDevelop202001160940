package com.android2droid.simpledevelop202001160940.mvp.base.presenter;

import android.content.Context;

import com.android2droid.simpledevelop202001160940.mvp.base.model.BaseListBean;
import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseLoadMoreListStatus;
import com.android2droid.simpledevelop202001160940.mvp.base.util.Debug;

import java.util.List;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/18 15:50 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/18 15:50 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 *
 * @author luokaixuan
 */
public abstract class BaseLoadMoreListPresenter<ITEM, BEAN extends BaseListBean<ITEM>,
        M extends BaseLoadMoreListStatus.BaseMLoadMoreListStatus<ITEM, BEAN>,
        V extends BaseLoadMoreListStatus.BaseVLoadMoreListStatus<ITEM, BEAN>>
        extends BaseListPresenter<ITEM, BEAN, M, V> implements BaseLoadMoreListStatus.BasePLoadMoreListStatus<ITEM, BEAN> {

    private int mLoadMorePage;

    public BaseLoadMoreListPresenter(Context context) {
        super(context);
    }

    /**
     * 这个复写方法必须要，能够直接获取到正确的类型。
     *
     * @return 获取当前数据模型。
     */
    @Override
    public M getModule() {
        return super.getModule();
    }

    @Override
    public void callSetLoadMoreData(BEAN bean) {
        getModule().callSetLoadMoreData(bean);
    }

    @Override
    public void callGenerateListMoreData(List<ITEM> list) {

    }

    @Override
    public void callbackStartLoadMoreData() {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackStartLoadMoreData();
            }
        }
    }

    @Override
    public void callbackLoadMoreList(List<ITEM> listOrg, List<ITEM> listMore, List<ITEM> listCurrent) {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackLoadMoreList(listOrg, listMore, listCurrent);
            }
        }
    }

    @Override
    public void callbackNoMoreData() {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackNoMoreData();
            }
        }
    }

    @Override
    public void callbackHasMoreData() {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackHasMoreData();
            }
        }
    }

    @Override
    public void callSetLoadMoreDataEnable(boolean enable) {
        getModule().callSetLoadMoreDataEnable(enable);
    }

    @Override
    public void callbackLoadMoreDataEnable(boolean enable) {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackLoadMoreDataEnable(enable);
            }
        }
    }

    @Override
    public void callbackLoadMoreDataSuccess(BEAN bean) {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackLoadMoreDataSuccess(bean);
            }
        }
    }

    @Override
    public void callbackLoadMoreDataSuccess(List<ITEM> list) {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackLoadMoreDataSuccess(list);
            }
        }
    }

    @Override
    public void callbackLoadMoreDataError(int code, String msg) {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackLoadMoreDataError(code, msg);
            }
        }
    }

    @Override
    public void callbackLoadMoreDataEmpty() {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackLoadMoreDataEmpty();
            }
        }
    }

    @Override
    public void callbackLoadMorePage(int loadMorePage) {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackLoadMorePage(loadMorePage);
            }
        }
    }

    @Override
    public void callSetLoadMorePage(int loadMorePage) {
        getModule().callSetLoadMorePage(loadMorePage);
    }

    public int getLoadMorePage() {
        return mLoadMorePage;
    }

    @Override
    public void callbackDataRefreshSuccess(List<ITEM> list) {
        super.callbackDataRefreshSuccess(list);
    }

    @Override
    public void callDataRefresh() {
        super.callDataRefresh();
        if (mModule == null) {
            Debug.d(TAG, "callDataRefresh mModule == null ... ");
        } else {
            mModule.callDataRefresh();
        }
    }

    @Override
    public void callLoadMoreData(ITEM data) {
        if (mModule == null) {
            Debug.d(TAG, "callLoadMoreData mModule == null ... ");
        } else {
            mModule.callLoadMoreData(data);
        }
    }

    @Override
    public void recycle() {
        super.recycle();
        mLoadStatusList = null;
    }
}
