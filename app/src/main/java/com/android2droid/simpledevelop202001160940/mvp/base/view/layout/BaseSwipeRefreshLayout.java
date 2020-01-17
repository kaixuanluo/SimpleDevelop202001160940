package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;

import com.didi365.miudrive.navi.R;

/**
 * Created by 90678 on 2017/6/7.
 */

public class BaseSwipeRefreshLayout {

    SwipeRefreshLayout mSrl;

    public SwipeRefreshLayout getSrl() {
        return mSrl;
    }

    public View getRootView() {
        return getSrl();
    }

    public void setSrl(SwipeRefreshLayout srl) {
        mSrl = srl;
    }

    protected void setSwipeRefreshEnable(SwipeRefreshLayout srl, boolean enable) {
        if (srl != null) {
            srl.setEnabled(enable);
        }
    }

    public void setSwipeRefreshEnable(boolean enable) {
        SwipeRefreshLayout srl = getSrl();
        if (srl != null) {
            srl.setEnabled(enable);
        }
    }

    protected void setOnSwipeRefreshListener (SwipeRefreshLayout.OnRefreshListener listener) {
        SwipeRefreshLayout srl = getSrl();
        if (srl != null) {
            srl.setOnRefreshListener(listener);
        }
    }

    protected void setSwipeRefreshing(boolean refreshing) {
        SwipeRefreshLayout srl = getSrl();
        if (srl != null) {
            srl.setRefreshing(refreshing);
        }
    }

    protected View addSwipeRefreshView (Context context, View subView) {
        mSrl = new SwipeRefreshLayout(context);
        mSrl.addView(subView);
//        mSrl.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                callLoadData();
//            }
//        });
        setSwipeRefreshEnable(getSrl(), false);
        return mSrl;
//        return container;
    }

    public BaseSwipeRefreshLayout() {

    }

    public boolean isRefreshing() {
        return getSrl().isRefreshing();
    }

}
