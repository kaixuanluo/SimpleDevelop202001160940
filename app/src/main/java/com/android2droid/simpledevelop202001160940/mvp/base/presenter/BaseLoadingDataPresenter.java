package com.android2droid.simpledevelop202001160940.mvp.base.presenter;

import android.content.Context;

import com.android2droid.simpledevelop202001160940.mvp.base.model.BaseLoadingDataBean;
import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseLoadingStatus;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/12 14:23 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/12 14:23 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 *
 * @author luokaixuan
 */
public abstract class BaseLoadingDataPresenter<DATA, BEAN extends BaseLoadingDataBean<DATA>,
        M extends BaseLoadingStatus.BaseMLoadingStatus<BEAN>
        , V extends BaseLoadingStatus.BaseVLoadingStatus<BEAN>>
        extends BaseLoadingPresenter<BEAN, M, V> {

    public BaseLoadingDataPresenter(Context context) {
        super(context);
    }
}
