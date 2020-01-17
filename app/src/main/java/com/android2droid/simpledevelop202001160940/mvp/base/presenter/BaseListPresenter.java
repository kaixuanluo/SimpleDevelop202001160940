package com.android2droid.simpledevelop202001160940.mvp.base.presenter;

import android.content.Context;

import com.android2droid.simpledevelop202001160940.mvp.base.model.BaseListBean;
import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseListStatus;

import java.util.ArrayList;
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
public abstract class BaseListPresenter<ITEM, BEAN extends BaseListBean<ITEM>,
        M extends BaseListStatus.BaseMListStatus<ITEM, BEAN>
        , V extends BaseListStatus.BaseVListStatus<ITEM, BEAN>>
        extends BaseLoadingPresenter<BEAN, M, V> implements BaseListStatus.BasePListStatus<ITEM, BEAN> {

    protected List<ITEM> mBaseList = new ArrayList<>();

    public BaseListPresenter(Context context) {
        super(context);
    }

    public List<ITEM> getBaseList() {
        return mBaseList;
    }

    private int mFirstPage;

    public int getFirstPage() {
        return mFirstPage;
    }

    @Override
    public void callbackSetFirstPage(int firstPage) {
        mFirstPage = firstPage;
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackSetFirstPage(firstPage);
            }
        }
    }


    @Override
    public void callbackHideLoadStatus() {
        if (mLoadStatusList != null) {
            for (V v : mLoadStatusList) {
                v.callbackHideLoadStatus();
            }
        }
    }

    @Override
    public M getModule() {
        return super.getModule();
    }

    @Override
    public void addLoadStatus(V baseLoadingStatus) {
        super.addLoadStatus(baseLoadingStatus);
    }

    public void setBaseList(List<ITEM> mBaseList) {
        this.mBaseList = mBaseList;
    }

    @Override
    public void setModule(M mBaseListModule) {
        super.setModule(mBaseListModule);
    }

    @Override
    public void callGenerateListData(List list) {

    }

    @Override
    public void callSetLoadList(List<ITEM> list) {
        getModule().callSetLoadList(list);
    }

    @Override
    public void callbackList(List<ITEM> listOrg, List<ITEM> listCurrent) {
        if (mLoadStatusList != null) {
            for (V itemBaseListStatus : mLoadStatusList) {
                itemBaseListStatus.callbackList(listOrg, listCurrent);
            }
        }
    }

    @Override
    public void callRemoveItemByPosition(int position) {
        getModule().callRemoveItemByPosition(position);
    }

    @Override
    public void callRemoveItem(ITEM item) {
        getModule().callRemoveItem(item);
    }

    @Override
    public void callbackRemoveItem(ITEM item, List<ITEM> listOrg, List<ITEM> listCurrent) {
        if (mLoadStatusList != null) {
            for (BaseListStatus<ITEM, BEAN> itemBaseListStatus : mLoadStatusList) {
                if (itemBaseListStatus != null) {
                    itemBaseListStatus.callbackRemoveItem(item, listOrg, listCurrent);
                }
            }
        }
    }

    @Override
    public void callbackRemoveItemByPosition(int position, List<ITEM> listOrg, List<ITEM> listCurrent) {
        if (mLoadStatusList != null) {
            for (BaseListStatus<ITEM, BEAN> itemBaseListStatus : mLoadStatusList) {
                if (itemBaseListStatus != null) {
                    itemBaseListStatus.callbackRemoveItemByPosition(position, listOrg, listCurrent);
                }
            }
        }
    }

    @Override
    public void callbackLoadDataSuccess(List<ITEM> list) {
        if (mLoadStatusList != null) {
            for (BaseListStatus<ITEM, BEAN> itemBaseListStatus : mLoadStatusList) {
                if (itemBaseListStatus != null) {
                    itemBaseListStatus.callbackLoadDataSuccess(list);
                }
            }
        }
    }

    @Override
    public void callbackDataRefreshSuccess(List<ITEM> list) {
        if (mLoadStatusList != null) {
            for (BaseListStatus<ITEM, BEAN> itemBaseListStatus : mLoadStatusList) {
                if (itemBaseListStatus != null) {
                    itemBaseListStatus.callbackDataRefreshSuccess(list);
                }
            }
        }
    }

    @Override
    public void callSetFirstPage(int firstPage) {
        getModule().callSetFirstPage(firstPage);
    }

    public void addStatus(V baseListStatus) {
        mLoadStatusList.add(baseListStatus);
    }

    public void removeStatus(V baseListStatus) {
        mLoadStatusList.remove(baseListStatus);
    }

}
