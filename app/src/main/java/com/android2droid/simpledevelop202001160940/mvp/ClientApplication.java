package com.android2droid.simpledevelop202001160940.mvp;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.android2droid.simpledevelop202001160940.mvp.base.util.Debug;

/**
 * @author created by luokaixuan
 * @date 2020/1/16
 * 这个类是用来干嘛的
 */
public class ClientApplication extends Application {

    private static Context applicationContext;
    private String TAG = ClientApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        registerActivityListener();
    }

    public static Context getApplication(){
        return applicationContext;
    }

    /**
     * 注册Activity生命周期监听，
     * 自Android 4.0（API-14）开始，Application 中多了一个方法registerActivityLifecycleCallbacks
     * 目前项目使用最低版本是17所有判断更改成Build.VERSION_CODES.JELLY_BEAN_MR1
     */
    private void registerActivityListener() {
        Debug.d(TAG, "registerActivityListener is run");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            registerActivityLifecycleCallbacks(new MyActivityLifecycle());
        }
    }
}
