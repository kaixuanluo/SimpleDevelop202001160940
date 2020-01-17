package com.android2droid.simpledevelop202001160940.mvp.base.util;

import android.util.Log;

/**
 * @author created by luokaixuan
 * @date 2020/1/16
 * 这个类是用来干嘛的
 */
public class Debug {

    public static void d(String tag, String log) {
        Log.d(tag, log);
    }

    public static void e(String tag, String log) {
        Log.e(tag, log);
    }
}
