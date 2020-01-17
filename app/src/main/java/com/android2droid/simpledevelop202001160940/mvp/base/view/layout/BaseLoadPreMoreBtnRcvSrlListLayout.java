package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import android.support.v7.widget.RecyclerView;

import com.didi365.miudrive.navi.main.music.base.model.BaseListBean;
import com.didi365.miudrive.navi.main.music.base.status.BaseLoadPreListStatus;

/**
 * @author created by luokaixuan
 * @date 2019/10/11
 * 这个类是用来干嘛的
 */
public abstract class BaseLoadPreMoreBtnRcvSrlListLayout<ITEM, DATA, BEAN extends BaseListBean<ITEM>
        , P extends BaseLoadPreListStatus.BasePLoadPreListStatus<ITEM, BEAN>
        , HOLDER extends RecyclerView.ViewHolder> extends BaseLoadPreMoreRcvSrlListLayout<ITEM, BEAN, P, HOLDER> {
}
