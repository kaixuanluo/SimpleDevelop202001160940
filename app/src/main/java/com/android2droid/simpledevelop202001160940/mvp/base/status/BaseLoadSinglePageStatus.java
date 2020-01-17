package com.android2droid.simpledevelop202001160940.mvp.base.status;

import com.android2droid.simpledevelop202001160940.mvp.base.model.BaseListBean;

/**
 * @author created by luokaixuan
 * @date 2019/9/5
 * 这个类是用来干嘛的
 */
public interface BaseLoadSinglePageStatus<ITEM, BEAN extends BaseListBean<ITEM>> extends BaseLoadPreListStatus<ITEM, BEAN> {
    void callbackCurrentSinglePage(int currentSinglePage);

    interface BaseMLoadSinglePageStatus<ITEM, BEAN extends BaseListBean<ITEM>> extends BaseLoadSinglePageStatus<ITEM, BEAN>, BaseMLoadPreListStatus<ITEM, BEAN> {

    }

    interface BaseVLoadSinglePageStatus<ITEM, BEAN extends BaseListBean<ITEM>> extends BaseLoadSinglePageStatus<ITEM, BEAN>, BaseVLoadPreListStatus<ITEM, BEAN> {

    }

    interface BasePLoadSinglePageStatus<ITEM, BEAN extends BaseListBean<ITEM>> extends BaseLoadSinglePageStatus<ITEM, BEAN>, BasePLoadPreListStatus<ITEM, BEAN> {

    }
}
