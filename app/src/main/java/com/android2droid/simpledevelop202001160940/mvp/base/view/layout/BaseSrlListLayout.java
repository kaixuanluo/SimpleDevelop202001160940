package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import com.android2droid.simpledevelop202001160940.mvp.base.model.BaseListBean;
import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseListStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author created by luokaixuan
 * @date 2019/9/9
 * 这个类是用来干嘛的
 */
public abstract class BaseSrlListLayout<ITEM, BEAN extends BaseListBean<ITEM>
        , P extends BaseListStatus.BasePListStatus<ITEM, BEAN>>
        extends BaseLoadingLayout<BEAN, P> implements BaseListStatus.BaseVListStatus<ITEM, BEAN> {

    private int mFirstPage;

    public int getFirstPage() {
        return mFirstPage;
    }

    @Override
    public void callSetFirstPage(int firstPage) {
        getPresenter().callSetFirstPage(firstPage);
    }

    @Override
    public void callbackSetFirstPage(int firstPage) {
        this.mFirstPage = firstPage;
    }

    @Override
    public void callbackHideLoadStatus() {

    }

    public List<ITEM> getBaseList() {
        return new ArrayList<>();
    }

    @Override
    public void callSetLoadList(List<ITEM> list) {
        if (getPresenter() != null) {
            getPresenter().callSetLoadList(list);
        }
    }

    @Override
    public void callGenerateListData(List<ITEM> list) {

    }

    @Override
    public void callbackList(List<ITEM> listOrg, List<ITEM> listCurrent) {

    }

    @Override
    public void callbackDataRefreshSuccess(List<ITEM> list) {

    }

    @Override
    public void callbackLoadDataSuccess(List<ITEM> list) {

    }

    @Override
    public void callbackDataRefreshSuccess(BEAN bean) {

    }


    @Override
    public void callRemoveItem(ITEM item) {
        if (getPresenter() != null) {
            getPresenter().callRemoveItem(item);
        }
    }

    @Override
    public void callRemoveItemByPosition(int position) {
        if (getPresenter() != null) {
            getPresenter().callRemoveItemByPosition(position);
        }
    }

}
