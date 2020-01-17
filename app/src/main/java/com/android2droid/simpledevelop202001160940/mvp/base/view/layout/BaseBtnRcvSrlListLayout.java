package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import android.support.v7.widget.RecyclerView;

import com.didi365.miudrive.navi.main.music.base.model.BaseListBean;
import com.didi365.miudrive.navi.main.music.base.status.BaseListStatus;

/**
 * @author created by luokaixuan
 * @date 2019/10/15
 * 这个类是用来干嘛的
 */
public abstract class BaseBtnRcvSrlListLayout<ITEM, BEAN extends BaseListBean<ITEM>
        , P extends BaseListStatus.BasePListStatus<ITEM, BEAN>
        , HOLDER extends RecyclerView.ViewHolder> extends BaseRcvSrlListLayout<ITEM,BEAN, P, HOLDER> {

}
