package com.android2droid.simpledevelop202001160940.mvp;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.android2droid.simpledevelop202001160940.mvp.base.util.Debug;

public class MyActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = MyActivityLifecycle.class.getSimpleName();
    private int mFinalCount;

    private static int resumed;
    private static int paused;

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        Debug.d(TAG, "onActivityCreated is run");
        AppManager.getAppManager().pushActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        mFinalCount++; //如果mFinalCount ==1，说明是从后台到前台
        if (mFinalCount == 1) {
            //说明从后台回到了前台
            Debug.e(TAG, mFinalCount + "从后台回到了前台");
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ++resumed;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ++paused;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        mFinalCount--; //如果mFinalCount ==0，说明是前台到后台
        if (mFinalCount == 0) { //说明从前台回到了后台
            Debug.e(TAG, mFinalCount + "从前台回到了后台");
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Debug.d(TAG, "onActivityDestroyed is run");

        if (null == AppManager.getAppManager().getmActivityS()
                && AppManager.getAppManager().getmActivityS().isEmpty()) {
            return;
        }

        if (AppManager.getAppManager().getmActivityS().contains(activity)) {
            /**
             *  监听到 Activity销毁事件 将该Activity 从list中移除
             */
            AppManager.getAppManager().popActivity(activity);
        }
    }

    /**
     * 当所有 Activity 的状态中处于 resumed 的大于 paused 状态的，
     * 不能用stop，因为有些界面不会触发stop
     * 即可认为有Activity处于前台状态中
     *
     * @return
     */
    public static boolean isApplicationInForeground() {
        return resumed > paused;
    }
}
