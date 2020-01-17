package com.android2droid.simpledevelop202001160940.mvp.base.presenter;

import android.content.Context;

import com.didi365.miudrive.navi.main.music.base.model.BaseListBean;
import com.didi365.miudrive.navi.main.music.base.status.BaseLoadSinglePageStatus;

/**
 * Created by luokaixuan
 * Created Date 2018/4/23.
 * Description 基础单页 数据视图连接器
 */
public abstract class BaseLoadSinglePagePresenter<ITEM, BEAN extends BaseListBean<ITEM>,
        M extends BaseLoadSinglePageStatus.BaseMLoadSinglePageStatus<ITEM, BEAN>,
        V extends BaseLoadSinglePageStatus.BaseVLoadSinglePageStatus<ITEM, BEAN>>
        extends BaseLoadPreListPresenter<ITEM,BEAN, M, V> {

    public BaseLoadSinglePagePresenter(Context context) {
        super(context);
    }
}
