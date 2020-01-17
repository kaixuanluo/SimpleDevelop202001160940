package com.android2droid.simpledevelop202001160940.mvp.base.status;

import com.didi365.miudrive.navi.main.music.base.model.BaseListBean;

/**
 * @author created by luokaixuan
 * @date 2019/10/18
 * 这个类是用来干嘛的
 */
public class BaseListPStatsImpl<ITEM, BEAN extends BaseListBean<ITEM>> extends BaseListStatsImpl<ITEM, BEAN> implements BaseListStatus.BasePListStatus<ITEM, BEAN> {

    @Override
    public void setBaseVLoadingStatus(BaseVLoadingStatus<BEAN> vLoadingStatus) {

    }
}
