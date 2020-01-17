package com.android2droid.simpledevelop202001160940.mvp.base.model;

import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseLoadingStatus;

/**
 * Created by luokaixuan
 * Created Date 2018/4/17.
 * Description 基础 Module
 *
 * @author luokaixuan
 */
public class BaseLoadingDataModule<DATA, BEAN extends BaseLoadingDataBean<DATA>,
        P extends BaseLoadingStatus.BasePLoadingStatus<BEAN>>
        extends BaseLoadingModule<BEAN, P> {

    public BaseLoadingDataModule(P baseLoadingPresenter) {
        super(baseLoadingPresenter);
    }
}
