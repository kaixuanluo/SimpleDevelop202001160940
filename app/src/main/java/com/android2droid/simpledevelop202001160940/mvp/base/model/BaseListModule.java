package com.android2droid.simpledevelop202001160940.mvp.base.model;

import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseListStatus;
import com.android2droid.simpledevelop202001160940.mvp.base.util.Debug;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by luokaixuan
 * Created Date 2018/4/18.
 * Description 基础bean
 */
public class BaseListModule<ITEM, BEAN extends BaseListBean<ITEM>,
        P extends BaseListStatus.BasePListStatus<ITEM, BEAN>>
        extends BaseLoadingModule<BEAN, P> implements BaseListStatus.BaseMListStatus<ITEM, BEAN> {

    protected int mFirstPage = 0;

    protected List<ITEM> mCurrentList = new ArrayList<>();

    @Override
    public void callbackSetFirstPage(int firstPage) {
        getPresenter().callbackSetFirstPage(firstPage);
    }

    @Override
    public void callbackHideLoadStatus() {
        getPresenter().callbackHideLoadStatus();
    }

    public int getFirstPage() {
        return mFirstPage;
    }

    public BaseListModule(P baseLoadingPresenter) {
        super(baseLoadingPresenter);

    }

    @Override
    public void callDataRefresh() {
        mCurrentList = new ArrayList<>();
        callSetLoadList(mCurrentList);
        super.callDataRefresh();
    }

    @Override
    public void callSetLoadList(List<ITEM> list) {
        callbackLoadDataSuccess(list);
        callbackDataComplete();
    }

    @Override
    public void callGenerateListData(List<ITEM> list) {

    }

    @Override
    public void callSetLoadData(BEAN bean) {
        super.callSetLoadData(bean);
        //这里不能加，父类也会回调，会调两次。
//        callSetLoadData(bean.getList());
    }

    public void callSetLoadData(List<ITEM> list) {
        callSetLoadList(list);
    }

    @Override
    public void callbackLoadDataSuccess(BEAN bean) {
        super.callbackLoadDataSuccess(bean);
        if (bean == null) {
            Debug.d(TAG, "callbackLoadDataSuccess bean == null ... ");
        } else {
            callbackLoadDataSuccess(bean.getList());
        }
    }

    @Override
    public void callbackList(List<ITEM> listOrg, List<ITEM> listCurrent) {
        mCurrentList = listCurrent;
        getPresenter().callbackList(listOrg, listCurrent);
    }

    @Override
    public void callRemoveItem(ITEM item) {
        List<ITEM> mOrgList = mCurrentList;
        if (mCurrentList == null) {
            Debug.d(TAG, "callRemoveItem mCurrentList == null ...");
        } else if (item == null) {
            Debug.d(TAG, "callRemoveItem item == null ...");
        } else {
            boolean remove = mCurrentList.remove(item);
            if (remove) {
                Debug.d(TAG, "callRemoveItem remove success ...");
                callbackRemoveItem(item, mOrgList, mCurrentList);
            } else {
                Debug.d(TAG, "callRemoveItem remove failed ...");
            }
        }
    }

    @Override
    public void callRemoveItemByPosition(int position) {
        List<ITEM> mOrgList = mCurrentList;
        if (mCurrentList == null) {
            Debug.d(TAG, "callRemoveItemByPosition mCurrentList == null ...");
        } else if (position >= mCurrentList.size()) {
            Debug.d(TAG, "callRemoveItemByPosition position >= mCurrentList.size() ...");
        } else {
            ITEM item = mCurrentList.remove(position);
            if (item == null) {
                Debug.d(TAG, "callRemoveItemByPosition remove failed ...");
            } else {
                Debug.d(TAG, "callRemoveItemByPosition remove success ...");
                callbackRemoveItemByPosition(position, mOrgList, mCurrentList);
            }
        }
    }

    @Override
    public void callbackRemoveItem(ITEM item, List<ITEM> listOrg, List<ITEM> listCurrent) {
        callbackList(listOrg, listCurrent);
        getPresenter().callbackRemoveItem(item, listOrg, listCurrent);
    }

    @Override
    public void callbackRemoveItemByPosition(int position, List<ITEM> listOrg, List<ITEM> listCurrent) {
        callbackList(listOrg, listCurrent);
        getPresenter().callbackRemoveItemByPosition(position, listOrg, listCurrent);
    }

    @Override
    public void callbackLoadDataSuccess(List<ITEM> list) {
        callbackList(mCurrentList, list);
        getPresenter().callbackLoadDataSuccess(list);
        if (list == null || list.size() == 0) {
            callbackDataEmpty();
        }
    }

    @Override
    public void callbackDataRefreshSuccess(BEAN bean) {
        super.callbackDataRefreshSuccess(bean);
        callbackDataRefreshSuccess(bean == null ? null : bean.getList());
    }

    @Override
    public void callbackDataRefreshSuccess(List<ITEM> list) {
        callbackList(mCurrentList, list);
        getPresenter().callbackDataRefreshSuccess(list);
    }

    @Override
    public void callSetFirstPage(int firstPage) {
        this.mFirstPage = firstPage;
        callbackSetFirstPage(firstPage);
    }

    @Override
    protected void okGoRefresh(Class<BEAN> bean, Map<String, String> queryMap, String url, boolean showLoading) {
        if (queryMap == null) {
            Debug.e(TAG, "queryMap is null ... ");
        } else {
            queryMap.put("page", mFirstPage + "");
        }
        super.okGoRefresh(bean, queryMap, url, showLoading);
    }

}
