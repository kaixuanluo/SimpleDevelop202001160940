package com.android2droid.simpledevelop202001160940.mvp.base.util;

import java.text.DecimalFormat;
import java.util.Formatter;
import java.util.Locale;

/**
 * Created by luokaixuan
 * Created Date 2018/1/3.
 * Description 音乐工具
 */

public class MusicUtil {

    private static String TAG = MusicUtil.class.getSimpleName();

    public static String makeTimeString(long musicTime) {
        if (musicTime >= 0) {
            return String.format("%02d", musicTime / 60) + ":" + String.format("%02d", musicTime % 60);
        } else {
            return "00:00";
        }
    }

    private static final int NUM_MIN = 10000;

    public static String getNum(long num) {
        DecimalFormat df = new DecimalFormat("#.#");
        if (num > NUM_MIN) {
            return (df.format((float) num / NUM_MIN)) + "w";
        } else {
            return num + "";
        }
    }

    private static long lastStartTime;

    public static long getDelayTime(long delayOrgTime) {

        //延迟0.5秒再播放，可以防止快速点击。
        long delayTime;

        //获取本次调用播放方法的时间
        long startTime = System.currentTimeMillis();

        //本次调用播放的时间距离上次调用播放的时间
        if (startTime - lastStartTime > delayOrgTime) {
            //距离上次调用超过了默认的延时时间，则不延时。
            Debug.d(TAG, "startTime - lastStartTime > delayOrgTime " + (startTime - lastStartTime));
            delayTime = 0;
        } else {
            delayTime = delayOrgTime;
            Debug.d(TAG, "startTime - lastStartTime <= delayOrgTime " + delayTime);
        }

        //记录本次调用播放方法的时间
        lastStartTime = System.currentTimeMillis();

        return delayTime;

    }

    //    public static int getMusicListRandom() {
    //        Debug.e(TAG, "getMusicListRandom size: " + sMusicManageList.size());
    //        return (int) (Math.random() * sMusicManageList.size());
    //    }

    /**
     * Returns the specified millisecond time formatted as a string.
     *
     * @param timeMs The time to format as a string, in milliseconds.
     * @return The time formatted as a string.
     */
    public static String getStringForTime(long timeMs) {

        StringBuilder formatBuilder;
        Formatter formatter;

        /* @param builder   The builder that {@code formatter} will write to.
         * @param formatter The formatter.*/
        formatBuilder = new StringBuilder();
        formatter = new Formatter(formatBuilder, Locale.getDefault());

        if (timeMs == (Long.MIN_VALUE + 1)) {
            timeMs = 0;
        }
        long totalSeconds = (timeMs + 500) / 1000;
        long seconds = totalSeconds % 60;
        long minutes = (totalSeconds / 60) % 60;
        long hours = totalSeconds / 3600;
        formatBuilder.setLength(0);
        return hours > 0 ? formatter.format("%d:%02d:%02d", hours, minutes, seconds).toString() :
                formatter.format("%02d:%02d", minutes, seconds).toString();
    }
}
