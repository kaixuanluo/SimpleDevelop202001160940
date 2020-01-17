package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import android.content.Context;

import com.android2droid.simpledevelop202001160940.mvp.base.model.BaseListBean;
import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseLoadPreListStatus;

import java.util.List;

/**
 * @author created by luokaixuan
 * @date 2019/12/11
 * 这个类是用来干嘛的
 */
public class RecyclerViewLoadPre<ITEM, BEAN extends BaseListBean<ITEM>>
        extends RecyclerViewLoadMore<ITEM, BEAN> implements BaseLoadPreListStatus<ITEM, BEAN> {

    public RecyclerViewLoadPre(Context context) {
        super(context);
    }

    @Override
    public void callLoadPreData(ITEM data) {

    }

    @Override
    public void callSetLoadPreData(BEAN list) {

    }

    @Override
    public void callbackNoPreData() {

    }

    @Override
    public void callbackHasPreData() {

    }

    @Override
    public void callbackLoadPreDataEnable(boolean enable) {

    }

    @Override
    public void callbackLoadPreDataSuccess(BEAN bean) {

    }

    @Override
    public void callbackLoadPreDataSuccess(List<ITEM> list) {

    }

    @Override
    public void callbackLoadPreList(List<ITEM> listOrg, List<ITEM> listPre, List<ITEM> listCurrent) {

    }

    @Override
    public void callbackLoadPreDataError(int code, String msg) {

    }

    @Override
    public void callbackLoadPreDataEmpty() {

    }

    @Override
    public void callbackLoadPrePage(int loadPrePage) {

    }
}
