package com.android2droid.simpledevelop202001160940.mvp.base.view.view;

import android.content.Context;
import android.os.Handler;

/**
 * @author luokaixuan
 */

public class JsonCallbackView implements JsonCallbackStatus {

//    private DialogLoading loadingDialog;

    AutoDismissHandler mAutoDismissHandler;
    AutoDismissRunnable mAutoDismissRunnable;
    private String TAG = getClass().getSimpleName();

    public JsonCallbackView() {
        this.mAutoDismissHandler = new AutoDismissHandler();
        this.mAutoDismissRunnable = new AutoDismissRunnable();
    }

    private class AutoDismissHandler extends Handler {

    }

    private class AutoDismissRunnable implements Runnable {

        @Override
        public void run() {
            dismissLoading();
        }
    }

    // 执行强制退出操作
    @Override
    public void requestStatusQuit(Context context, String tip) {
        if (context != null) {
//            new CommonAutoTopDialog(context, tip).show();
        }
    }

    @Override
    public void onTimeout(Context context) {
        if (context != null) {
//            new CommonAutoTopDialog(context, context.getResources().getString(R.string.time_out)).show();
        }
    }

    @Override
    public void onDisconnect(Context context) {
        if (context != null) {
//            new CommonAutoTopDialog(context, context.getResources().getString(R.string.network_time_out)).show();
        }
    }

    @Override
    public void showLoading(Context context, String loadingMsg) {
//        showLoading(context, loadingMsg, true);
    }

    @Override
    public void showLoading(Context context, String loadingMsg, boolean autoDismiss) {
//        Debug.d(TAG, "showLoading " + context);
//        if (context == null) {
//            Debug.d(TAG, "context == null ... ");
//        } else {
//            if (loadingDialog == null) {
//                Debug.d(TAG, "loadingDialog == null ... ");
//            } else {
//                Debug.d(TAG, "loadingDialog != null ... ");
//                dismissLoading();
//            }
//            //每次都要重新创建，不能复用。
//            if (TextUtils.isEmpty(loadingMsg)) {
//                loadingDialog = new DialogLoading(context);
//            } else {
//                loadingDialog = new DialogLoading(context, loadingMsg);
//            }
//
//            Debug.d(TAG, "showLoading ...");
//            loadingDialog.show();
//
//            if (autoDismiss) {
//                if (mAutoDismissHandler != null && mAutoDismissRunnable != null) {
//                    mAutoDismissHandler.removeCallbacks(mAutoDismissRunnable);
//                    mAutoDismissHandler.postDelayed(mAutoDismissRunnable, 5000);
//                }
//            }
//        }
    }

    @Override
    public void dismissLoading() {
        mAutoDismissHandler.removeCallbacks(mAutoDismissRunnable);
//        if (loadingDialog != null) {
//            loadingDialog.dismiss();
//        }
    }

}
