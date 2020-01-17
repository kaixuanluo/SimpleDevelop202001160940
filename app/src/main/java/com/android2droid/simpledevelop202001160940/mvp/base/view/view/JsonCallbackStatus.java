package com.android2droid.simpledevelop202001160940.mvp.base.view.view;

import android.content.Context;

public interface JsonCallbackStatus {

    // 执行强制退出操作
    void requestStatusQuit(Context context, String tip);

    void onTimeout(Context context);

    void onDisconnect(Context context);

    void showLoading(Context context, String loadingMsg);

    void showLoading(Context context, String loadingMsg, boolean autoDismiss);

    void dismissLoading();

}
