package com.android2droid.simpledevelop202001160940.mvp.base.presenter;

import android.content.Context;

import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseLoadingStatus;
import com.android2droid.simpledevelop202001160940.mvp.base.util.Debug;

import java.util.ArrayList;
import java.util.List;

/**
 * @author created by luokaixuan
 * @date 2019/11/23
 * 这个类是用来干嘛的
 */
public abstract class BaseLoadingPresenter<BEAN, M extends BaseLoadingStatus.BaseMLoadingStatus<BEAN>
        , V extends BaseLoadingStatus.BaseVLoadingStatus<BEAN>>
        extends BasePresenter implements BaseLoadingStatus.BasePLoadingStatus<BEAN> {

    protected List<V> mLoadStatusList;

    protected Context mContext;

    protected M mModule;

    /**
     * 蜻蜓FM可能Token失效了。
     */
    private int QT_NET_STAT = 403;

    public BaseLoadingPresenter(Context context) {
        mContext = context;
        mLoadStatusList = new ArrayList<>();
        setModule(newModule());
    }

    /**
     * @return 数据处理器
     */
    protected abstract M newModule();

    public M getModule() {
        return mModule;
    }

    public void setModule(M baseLoadingModule) {
        this.mModule = baseLoadingModule;
    }

    public List<V> getLoadStatusList() {
        return mLoadStatusList;
    }

    public void addLoadStatus(V baseLoadingStatus) {
        if (mLoadStatusList != null) {
            mLoadStatusList.add(baseLoadingStatus);
        }
    }

    @Override
    public void setBaseVLoadingStatus(BaseVLoadingStatus<BEAN> vLoadingStatus) {
        addLoadStatus((V) vLoadingStatus);
    }

    @Override
    public void callLoadData() {
        if (mModule == null) {
            Debug.d(TAG, "callLoadData mModule == null ... ");
        } else {
            mModule.callLoadData();
        }
    }

    @Override
    public void callSetRefreshDataEnable(boolean isRefreshEnable) {
        getModule().callSetRefreshDataEnable(isRefreshEnable);
    }

    @Override
    public void callbackSetRefreshDataEnable(boolean isRefreshEnable) {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackSetRefreshDataEnable(isRefreshEnable);
            }
        }
    }

    @Override
    public void callSetLoadData(BEAN bean) {
        getModule().callSetLoadData(bean);
    }

    @Override
    public void callbackLoadDataStart() {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackLoadDataStart();
            }
        }
    }

    @Override
    public void callbackDataLoading() {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackDataLoading();
            }
        }
    }

    @Override
    public void callbackLoadDataSuccess(BEAN bean) {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackLoadDataSuccess(bean);
            }
        }

    }

    @Override
    public void callbackDataEmpty() {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackDataEmpty();
            }
        }
    }

    @Override
    public void callSetDataError(String msg) {
        if (getModule() != null) {
            getModule().callSetDataError(msg);
        }
    }

    @Override
    public void callbackDataFailure(int code, String msg) {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackDataFailure(code, msg);
            }
        }

        if (code == QT_NET_STAT) {
            tokenRefresh();
        }

    }

    private void tokenRefresh() {

    }

    @Override
    public void callbackDataError(String msg) {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackDataError(msg);
            }
        }
    }

    @Override
    public void callbackDataComplete() {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackDataComplete();
            }
        }
    }

    @Override
    public void callDataReLoad() {
        getModule().callDataReLoad();
    }

    @Override
    public void callDataRefresh() {
        getModule().callDataRefresh();
    }

    @Override
    public void callbackDataRefreshSuccess(BEAN bean) {
        if (mLoadStatusList != null) {
            for (BaseLoadingStatus<BEAN> baseLoadingStatus : mLoadStatusList) {
                baseLoadingStatus.callbackDataRefreshSuccess(bean);
            }
        }
    }

    @Override
    public void recycle() {
//        if (getModule() != null) {
//            getModule().recycle();
//        }
        mContext = null;
        mLoadStatusList = null;
    }

}
