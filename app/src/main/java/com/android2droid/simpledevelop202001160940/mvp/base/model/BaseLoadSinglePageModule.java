package com.android2droid.simpledevelop202001160940.mvp.base.model;

import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseLoadSinglePageStatus;

/**
 * @author created by luokaixuan
 * @date 2019/8/29
 * 这个类是用来干嘛的
 */
public class BaseLoadSinglePageModule<ITEM, BEAN extends BaseListBean<ITEM>,
        P extends BaseLoadSinglePageStatus.BasePLoadSinglePageStatus<ITEM, BEAN>>
        extends BaseLoadPreListModule<ITEM, BEAN, P> implements BaseLoadSinglePageStatus<ITEM, BEAN> {

    private int currentSinglePage;

    public int getCurrentSinglePage() {
        return currentSinglePage;
    }

    @Override
    public void callbackCurrentSinglePage(int currentSinglePage) {
        this.currentSinglePage = currentSinglePage;
    }

    public BaseLoadSinglePageModule(P baseLoadPreListPresenter) {
        super(baseLoadPreListPresenter);
    }

    @Override
    protected void setLoadMoreSuccessPage() {
        currentSinglePage = mLoadMorePage;
        super.setLoadMoreSuccessPage();
        int loadPrePage = currentSinglePage - 1;
        //只有一页，将向上页设置为向下页
        callbackLoadPrePage(loadPrePage < mFirstPage ? mFirstPage : loadPrePage);
    }

    @Override
    protected void setLoadPreSuccessPage() {
        currentSinglePage = loadPrePage;
        super.setLoadPreSuccessPage();
        int loadMorePage = currentSinglePage + 1;
        //只有一页，将向下页设置为向上页
        callSetLoadMorePage(loadMorePage);
    }

}
