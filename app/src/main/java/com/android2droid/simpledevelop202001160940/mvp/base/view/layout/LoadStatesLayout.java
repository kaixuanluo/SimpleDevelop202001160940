package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.didi365.miudrive.navi.R;
import com.didi365.miudrive.navi.main.music.base.status.BaseLoadingStatus;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2016/10/12 15:12 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2016/10/12 15:12 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public class LoadStatesLayout<BEAN> extends FrameLayout implements BaseLoadingStatus<BEAN> {

    public LoadingFixedFlipper<BEAN> mFlp;

    public LoadStatesLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mFlp = (LoadingFixedFlipper<BEAN>) findViewById(R.id.flp);
    }

    @Override
    public void callbackLoadDataSuccess(BEAN data) {
        mFlp.callbackLoadDataSuccess(data);
    }

    @Override
    public void callbackDataEmpty() {
        mFlp.callbackDataEmpty();
    }

    @Override
    public void callbackDataFailure(int code, String msg) {
        mFlp.callbackDataFailure(code, msg);
    }

    @Override
    public void callSetDataError(String msg) {

    }

    @Override
    public void callbackDataError(String msg) {
        mFlp.callbackDataError(msg);
    }

    @Override
    public void callbackDataLoading() {
        mFlp.callbackDataLoading();
    }

    @Override
    public void callbackDataComplete() {
        mFlp.callbackDataComplete();
    }

    @Override
    public void callLoadData() {
        mFlp.callLoadData();
    }

    @Override
    public void callSetRefreshDataEnable(boolean isRefreshEnable) {

    }

    @Override
    public void callbackSetRefreshDataEnable(boolean isRefreshEnable) {

    }

    @Override
    public void callbackLoadDataStart() {
        mFlp.callbackLoadDataStart();
    }

    @Override
    public void callDataReLoad() {
        mFlp.callDataReLoad();
    }

    @Override
    public void callDataRefresh() {
        mFlp.callDataRefresh();
    }

    @Override
    public void callbackDataRefreshSuccess(BEAN data) {

    }

    @Override
    public void callSetLoadData(BEAN data) {

    }

    public void displayContent(int contentType) {
        if (mFlp == null) {
            Log.d("LoadStatesLayout", " displayContent mFlp == null ... ");
        } else {
            mFlp.displayContentByType(contentType);
        }
    }

    public void setStateView(View viewState, int position) {
        if (mFlp == null) {
            Log.d("addStateView", " mFlp == null ... ");
        } else {
            if (mFlp.getChildCount() > position) {
                mFlp.removeViewAt(position);
            }
            mFlp.addView(viewState, position);
        }
    }
}
