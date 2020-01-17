package com.android2droid.simpledevelop202001160940.mvp.base.presenter;

/**
 * 基础的数据与UI交互控制类
 * lcl
 * 2017/12/24
 */

public abstract class BasePresenter {
    protected String TAG = getClass().getSimpleName();
    //必须实现释放的方法
    public abstract void recycle();
}
