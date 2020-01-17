package com.android2droid.simpledevelop202001160940.mvp.base.util;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.FloatRange;

import com.android2droid.simpledevelop202001160940.R;
import com.android2droid.simpledevelop202001160940.mvp.ClientApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by luokaixuan
 * Created Date 2017/12/25.
 * Description 文字工具类
 */

public class ViewUtil {
    private static String TAG = ViewUtil.class.getSimpleName();

    /**
     * 设置文字
     *
     * @param tv 需要设置的文字控件
     * @param s  需要设置的文字
     * @return 是否设置成功
     */
    public static boolean setText(TextView tv, String s) {
        if (tv == null) {
            Debug.e(TAG, "textView is null ... ");
            return false;
        }
        if (TextUtils.isEmpty(s)) {
            Debug.e(TAG, "string is null ... ");
            s = "";
        }
        tv.setText(s);
        return true;

    }

    public static boolean setText(TextView tv, Spanned s) {
        if (tv == null) {
            Debug.e(TAG, "textView is null ... ");
            return false;
        }
        if (TextUtils.isEmpty(s)) {
            Debug.e(TAG, "string is null ... ");
            s = new SpannableString("");
        }
        tv.setText(s);
        return true;

    }

    public static boolean setTextIfEmptyGone(TextView tv, String s) {
        if (tv == null) {
            Debug.e(TAG, "textView is null ... ");
            return false;
        }
        if (TextUtils.isEmpty(s)) {
            Debug.e(TAG, "string is null ... ");
            s = "";
            setVisible(tv, View.GONE);
        } else {
            tv.setText(s);
            setVisible(tv, View.VISIBLE);
        }
        return true;

    }

    public static boolean setTextColor(TextView tv, int color) {
        if (tv == null) {
            Debug.e(TAG, "textView is null ... ");
            return false;
        }
        tv.setTextColor(ClientApplication.getApplication().getResources().getColor(color));
        return true;
    }

    public static boolean setImage(ImageView iv, int id) {
        if (iv == null) {
            Debug.e(TAG, "imageView is null ... ");
            return false;
        }
        if (id == 0) {
            iv.setVisibility(View.GONE);
            Debug.e(TAG, "res id is 0 ... ");
        }
        iv.setImageResource(id);
        return true;
    }

//    public static boolean setImage(Context context, ImageView iv, String url) {
//        if (iv == null) {
//            Debug.e(TAG, "imageView is null ... ");
//            return false;
//        }
//        if (TextUtils.isEmpty(url)) {
//            Debug.e(TAG, "res id is 0 ... ");
//            return false;
//        }
//        if (context == null) {
//            Debug.e(TAG, "context is null ... ");
//            return false;
//        } else {
//            if (context instanceof Activity) {
//                if (((Activity) context).isDestroyed()) {
//                    Debug.e(TAG, "Activity is destroyed ... ");
//                    return false;
//                } else {
//                    GlideUtils.loadBitmap(context, iv, url);
//                    return true;
//                }
//            } else {
//                GlideUtils.loadBitmap(context, iv, url);
//            }
//            return true;
//        }
//    }

//    public static boolean setImage(Context context, ImageView iv, String url, int placeId, int errorId) {
//        if (iv == null) {
//            Debug.e(TAG, "imageView is null ... ");
//            return false;
//        }
//        if (TextUtils.isEmpty(url)) {
//            Debug.e(TAG, "res id is 0 ... ");
//            return false;
//        }
//        if (context == null) {
//            Debug.e(TAG, "context is null ... ");
//            return false;
//        } else {
//            if (context instanceof Activity) {
//                if (((Activity) context).isDestroyed()) {
//                    Debug.e(TAG, "Activity is destroyed ... ");
//                    return false;
//                } else {
//                    GlideUtils.loadBitmap(context, iv, url, placeId, errorId);
//                    return true;
//                }
//            } else {
//                GlideUtils.loadBitmap(context, iv, url, placeId, errorId);
//            }
//            return true;
//        }
//    }

//    public static boolean setBlurImage(Context context, ImageView iv, String url) {
//        if (iv == null) {
//            Debug.e(TAG, "imageView is null ... ");
//            return false;
//        }
//        if (TextUtils.isEmpty(url)) {
//            Debug.e(TAG, "res id is 0 ... ");
//            return false;
//        }
//        if (context == null) {
//            Debug.e(TAG, "context is null ... ");
//            return false;
//        } else {
//            if (context instanceof Activity) {
//                if (((Activity) context).isDestroyed()) {
//                    Debug.e(TAG, "Activity is destroyed ... ");
//                    return false;
//                } else {
//                    GlideUtils.loadBlurBitmap(context, iv, url, 25);
//                    return true;
//                }
//            } else {
//                GlideUtils.loadBlurBitmap(context, iv, url, 25);
//                return true;
//            }
//        }
//    }

//    public static boolean setCircleImage(Context context, ImageView iv, String url) {
//        if (iv == null) {
//            Debug.e(TAG, "imageView is null ... ");
//            return false;
//        }
//        if (TextUtils.isEmpty(url)) {
//            Debug.e(TAG, "res id is 0 ... ");
//            return false;
//        }
//        if (context == null) {
//            Debug.e(TAG, "context is null ... ");
//            return false;
//        } else {
//            if (context instanceof Activity) {
//                if (((Activity) context).isDestroyed()) {
//                    Debug.e(TAG, "Activity is destroyed ... ");
//                    return false;
//                } else {
//                    GlideUtils.loadCircleBitmap(context, iv, url);
//                    return true;
//                }
//            } else {
//                GlideUtils.loadCircleBitmap(context, iv, url);
//                return true;
//            }
//        }
//    }

//    public static boolean setCircleImage(Context context, ImageView iv, Object url, int errorSrc) {
//        if (iv == null) {
//            Debug.e(TAG, "imageView is null ... ");
//            return false;
//        }
//        if (url == null) {
//            Debug.e(TAG, "res id is 0 ... ");
//            return false;
//        }
//        if (context == null) {
//            Debug.e(TAG, "context is null ... ");
//            return false;
//        } else {
//            if (context instanceof Activity) {
//                if (((Activity) context).isDestroyed()) {
//                    Debug.e(TAG, "Activity is destroyed ... ");
//                    return false;
//                } else {
//                    GlideUtils.loadCircleBitmap(context, iv, url, errorSrc, errorSrc);
//                    return true;
//                }
//            } else {
//                GlideUtils.loadCircleBitmap(context, iv, url, errorSrc, errorSrc);
//                return true;
//            }
//        }
//    }

//    public static boolean setCircleImage(Context context, ImageView iv, int url) {
//        if (iv == null) {
//            Debug.e(TAG, "imageView is null ... ");
//            return false;
//        }
//        if (0 == url) {
//            Debug.e(TAG, "res id is 0 ... ");
//            return false;
//        }
//        if (context == null) {
//            Debug.e(TAG, "context is null ... ");
//            return false;
//        } else {
//            if (context instanceof Activity) {
//                if (((Activity) context).isDestroyed()) {
//                    Debug.e(TAG, "Activity is destroyed ... ");
//                    return false;
//                } else {
//                    GlideUtils.loadCircleBitmap(context, iv, url);
//                    return true;
//                }
//            } else {
//                GlideUtils.loadCircleBitmap(context, iv, url);
//                return true;
//            }
//        }
//    }

//    public static boolean setCircleImage(Context context, ImageView iv, Bitmap url) {
//        if (iv == null) {
//            Debug.e(TAG, "imageView is null ... ");
//            return false;
//        }
//        if (null == url) {
//            Debug.e(TAG, "res id is 0 ... ");
//            return false;
//        }
//        if (context == null) {
//            Debug.e(TAG, "context is null ... ");
//            return false;
//        } else {
//            if (context instanceof Activity) {
//                if (((Activity) context).isDestroyed()) {
//                    Debug.e(TAG, "Activity is destroyed ... ");
//                    return false;
//                } else {
//                    GlideUtils.loadCircleBitmap(context, iv, url);
//                    return true;
//                }
//            } else {
//                GlideUtils.loadCircleBitmap(context, iv, url);
//                return true;
//            }
//        }
//    }

    public static boolean setVisible(View view, int visiable) {
        if (view == null) {
            Debug.e(TAG, "view is null ... ");
            return false;
        }
        view.setVisibility(visiable);
        return true;
    }

    public static boolean setVisible(View view, boolean visible) {
        if (view == null) {
            Debug.e(TAG, "view is null ... ");
            return false;
        }
        if (visible) {
            return setVisible(view, View.VISIBLE);
        } else {
            return setVisible(view, View.GONE);
        }
    }

    public static boolean setSelected(View view) {
        return setSelected(view, true);
    }

    public static boolean setUnSelect(View view) {
        return setSelected(view, false);
    }

    public static boolean setSelected(View view, boolean select) {
        if (view == null) {
            Debug.e(TAG, "view is null ... ");
            return false;
        }
        view.setSelected(select);
        return true;
    }

    public static boolean setEnable(View view, boolean enable) {
        if (view == null) {
            Debug.e(TAG, "view is null ... ");
            return false;
        }
        view.setEnabled(enable);
        return true;
    }

    /**
     * 获取性别图片
     */
//    public static int getGenderImgSrc(String type) {
//        if (type != null) {
//            switch (type) {
//                case MusicConstants.MALE:
//                    return R.drawable.male;
//                case MusicConstants.FEMALE:
//                    return R.drawable.female;
//            }
//        }
//        return 0;
//    }

    /**
     * 查找数字，并对样式进行设置
     *
     * @param info
     */
//    public static void setNumberStyle(Context context, TextView textView, String info) {
//        SpannableString styledText = new SpannableString(info);
//        List<String> intStr = new ArrayList<>();
//        String regex = "([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])";
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(info);
//        while (m.find()) {
//            intStr.add(m.group().trim());
//        }
//        int firstPos = 0;
//        int lastPos = 0;
//
//        for (int i = 0; i < intStr.size(); i++) {
//            if (!intStr.get(i).trim().equals("")) {
//                // 获取到数字的位置
//                firstPos = info.indexOf(intStr.get(i), lastPos);
//                lastPos = firstPos + intStr.get(i).length();
//                // 让固定位置的数字显示指定样式
//                styledText.setSpan(new TextAppearanceSpan(context, R.style.index_hud_text_style), firstPos, lastPos,
//                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//        }
//        textView.setText(styledText);
//    }


    public static int MIN_CLICK_DELAY_TIME = 1000;
    public static long lastClickTime = 0;

    public static boolean checkDoubleClick() {
        boolean flag = true;
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            flag = false;
        }
        lastClickTime = currentTime;
        return flag;
    }


    /**
     * @param mTextView      view
     * @param allText        全部文字
     * @param needChangeText 需要改变颜色的文字
     * @param color          色值
     */
    public static void changeTextViewColor(TextView mTextView, String allText, String needChangeText, int color) {
        if (mTextView == null) {
            Debug.e(TAG, "TextView is null ... ");
            return;
        }
        if (allText == null) {
            Debug.e(TAG, "allText is null ... ");
            return;
        }
        if (needChangeText == null) {
            Debug.e(TAG, "needChangeText is null ... ");
            return;
        }
        int startPosition = allText.indexOf(needChangeText);
        if (startPosition == -1) {
            Debug.e(TAG, "needChangeText is not in allText  ... ");
            return;
        }
        int endPosition = startPosition + needChangeText.length();
        SpannableStringBuilder result = new SpannableStringBuilder(allText);
        result.setSpan(new ForegroundColorSpan(color), startPosition, endPosition, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mTextView.setText(result);
    }

    public static void changeTextViewColor(TextView mTextView, String allText, int color, String... needChangeTexts) {
        if (mTextView == null) {
            Debug.e(TAG, "TextView is null ... ");
            return;
        }
        if (allText == null) {
            Debug.e(TAG, "allText is null ... ");
            return;
        }
        if (needChangeTexts == null) {
            Debug.e(TAG, "needChangeText is null ... ");
            return;
        }

        SpannableStringBuilder result = new SpannableStringBuilder(allText);
        for (int i = 0; i < needChangeTexts.length; i++) {
            String needChangeText = needChangeTexts[i];
            int startPosition = allText.indexOf(needChangeText);
            if (startPosition == -1) {
                Debug.e(TAG, "needChangeText is not in allText  ... ");
                return;
            }
            int endPosition = startPosition + needChangeText.length();
            result.setSpan(new ForegroundColorSpan(color), startPosition, endPosition, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        mTextView.setText(result);
    }


    /**
     * @param mTextView      view
     * @param allText        全部文字
     * @param needChangeText 需要改变颜色的文字
     */
    public static void changeTextViewColor(TextView mTextView, String allText, String needChangeText) {
        if (mTextView == null) {
            Debug.e(TAG, "TextView is null ... ");
            return;
        }
        if (allText == null) {
            Debug.e(TAG, "allText is null ... ");
            return;
        }
        if (needChangeText == null) {
            Debug.e(TAG, "needChangeText is null ... ");
            return;
        }
        int startPosition = allText.indexOf(needChangeText);
        if (startPosition == -1) {
            Debug.e(TAG, "needChangeText is not in allText  ... ");
            return;
        }
        int endPosition = startPosition + needChangeText.length();
        SpannableStringBuilder result = new SpannableStringBuilder(allText);
        result.setSpan(new ForegroundColorSpan(Color.parseColor("#50D7ED")), startPosition, endPosition, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mTextView.setText(result);
    }

    /**
     * 设置TextView是否显示,根据文字长度
     * 超过10个字就不显示
     *
     * @param tv 需要设置的文字控件
     * @param s  需要设置的文字
     */
    public static void setVisibleWithNameLength(TextView tv, String s) {
        if (tv == null) {
            Debug.e(TAG, "textView is null ... ");
            return;
        }
        if (TextUtils.isEmpty(s)) {
            Debug.e(TAG, "string is null ... ");
            s = "";
            tv.setText(s);
        } else {
            if (s.length() > 11) {
                tv.setVisibility(View.GONE);
            } else {
                tv.setVisibility(View.VISIBLE);
                tv.setText(s);
            }
        }
    }

    /**
     * 设置图片的透明度
     *
     * @param view  控件
     * @param alpha 透明度
     *              Sets the opacity of the view to a value from 0 to 1, where 0 means the view is
     *              * completely transparent and 1 means the view is completely opaque.
     *              设置范围 0 到1，设置为0，视图将会完全不可见，设置为1视图完全可见。
     */
    public static void setAlpha(View view, @FloatRange(from = 0.0, to = 1.0) float alpha) {
        if (view == null) {
            Debug.d(TAG, "setAlpha view is null ");
            return;
        }
        if (alpha < 0) {
            Debug.d(TAG, "alpha is <0 need 0f - 1f");
            return;
        }
        if (alpha > 1) {
            Debug.d(TAG, "alpha is >1 need 0f - 1f");
            return;
        }
        view.setAlpha(alpha);
    }

    /**
     * 设置图片的透明度
     *
     * @param view      控件
     * @param fromAlpha 透明度
     *                  Sets the opacity of the view to a value from 0 to 1, where 0 means the view is
     *                  * completely transparent and 1 means the view is completely opaque.
     *                  设置范围 0 到1，设置为0，视图将会完全不可见，设置为1视图完全可见。
     */
    public static void setAlphaAnim(View view, long duration, @FloatRange(from = 0.0, to = 1.0) float fromAlpha, @FloatRange(from = 0.0, to = 1.0) float toAlpha) {
        if (view == null) {
            Debug.d(TAG, "setAlpha view is null ");
            return;
        }

        Animation animation = new AlphaAnimation(fromAlpha, toAlpha);
        animation.setDuration(5000);
//        view.setAlpha(alpha);
        view.setAnimation(animation);
//        view.startAnimation(animation);
        animation.start();
        animation.setFillAfter(true);
    }

    public static void stopAlphaAnim(View view) {
        if (view == null) {
            Debug.d(TAG, "setAlpha view is null ");
            return;
        }
        Animation animation = view.getAnimation();
        if (animation == null) {
            Debug.d(TAG, "view.getAnimation() == null ");
            return;
        }
        animation.cancel();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param dpValue 虚拟像素
     * @return 像素
     */
    public static int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * ClientApplication.getApplication().getResources().getDisplayMetrics().density);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param pxValue 像素
     * @return 虚拟像素
     */
    public static float px2dp(int pxValue) {
        return (pxValue / ClientApplication.getApplication().getResources().getDisplayMetrics().density);
    }

    private float sp2px(int sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, ClientApplication.getApplication().getResources().getDisplayMetrics());
    }
}
