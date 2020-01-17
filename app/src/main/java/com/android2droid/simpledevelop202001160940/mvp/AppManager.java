package com.android2droid.simpledevelop202001160940.mvp;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.android2droid.simpledevelop202001160940.mvp.base.util.Debug;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Activity管理类，Activity创建类的时候加入管理栈、Activity销毁的时候移出管理栈
 * lcl
 * data 2017/12/19
 */

public class AppManager {

    //日志标记
    private final static String TAG = AppManager.class.getSimpleName();

    //维护Activity 的list
    private static List<Activity> mActivityS = Collections
            .synchronizedList(new LinkedList<Activity>());

    //实例
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 获取Activity管理集合
     *
     * @return
     */
    public List<Activity> getmActivityS() {
        return mActivityS;
    }

    /**
     * @param activity 作用说明 ：添加一个activity到管理里
     */
    public void pushActivity(Activity activity) {
        mActivityS.add(activity);
        Debug.e(TAG, "把" + activity.getClass().getSimpleName() + "加入到AppManager,当前集合大小:" + mActivityS.size());
    }

    /**
     * @param activity 作用说明 ：删除一个activity在管理里
     */
    public void popActivity(Activity activity) {
        mActivityS.remove(activity);
        Debug.e(TAG, "把" + activity.getClass().getSimpleName() + "从AppManager集合移除,当前集合大小:" + mActivityS.size());
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        if (mActivityS == null || mActivityS.isEmpty()) {
            return null;
        }
        Activity activity = mActivityS.get(mActivityS.size() - 1);
        return activity;
    }

    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    public void finishCurrentActivity() {
        if (mActivityS == null || mActivityS.isEmpty()) {
            return;
        }
        Activity activity = mActivityS.get(mActivityS.size() - 1);
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (mActivityS == null || mActivityS.isEmpty()) {
            return;
        }
        if (activity != null) {
            mActivityS.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        if (mActivityS == null || mActivityS.isEmpty()) {
            return;
        }
        for (Activity activity : mActivityS) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 按照指定类名找到activity
     *
     * @param cls
     * @return
     */
    public Activity findActivity(Class<?> cls) {
        Activity targetActivity = null;
        if (mActivityS != null) {
            for (Activity activity : mActivityS) {
                if (activity.getClass().equals(cls)) {
                    targetActivity = activity;
                    break;
                }
            }
        }
        return targetActivity;
    }

    /**
     * @return 作用说明 ：获取当前最顶部activity的实例
     */
    public Activity getTopActivity() {
        Activity mBaseActivity = null;
        synchronized (mActivityS) {
            final int size = mActivityS.size() - 1;
            if (size < 0) {
                return null;
            }
            mBaseActivity = mActivityS.get(size);
        }
        return mBaseActivity;
    }

    /**
     * @return 作用说明 ：获取当前最顶部的acitivity 名字
     */
    public String getTopActivityName() {
        Activity mBaseActivity = null;
        synchronized (mActivityS) {
            final int size = mActivityS.size() - 1;
            if (size < 0) {
                return null;
            }
            mBaseActivity = mActivityS.get(size);
        }
        return mBaseActivity.getClass().getName();
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        if (mActivityS == null) {
            return;
        }
        for (Activity activity : mActivityS) {
            activity.finish();
        }
        mActivityS.clear();
    }

    /**
     * 退出应用程序
     */
    public static void appExit() {
        try {
            Debug.e(TAG, "app exit");
            finishAllActivity();
        } catch (Exception e) {
            Debug.d(TAG, "exit exception e=" + e.getMessage());
        }
    }

    /**
     * 重启App
     *
     * @param context
     */
    public void restartApp(Context context) {
        Intent i = context.getPackageManager().getLaunchIntentForPackage(
                context.getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }

    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }

    /**
     * 判断某个Activity 界面是否在前台
     *
     * @param context
     * @return
     */
    public static boolean isForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (topActivity.getPackageName().equals(context.getPackageName())) {
                Debug.d(TAG, "当前程序处于前台");
                return true;
            }
        }
        Debug.d(TAG, "当前程序处于后台");
        return false;
    }

}
