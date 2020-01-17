package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.didi365.miudrive.navi.ClientApplication;
import com.didi365.miudrive.navi.R;
import com.didi365.miudrive.navi.main.music.base.model.BaseLoadingDataBean;
import com.didi365.miudrive.navi.main.music.base.status.BaseLoadingStatus;
import com.didi365.miudrive.navi.utils.Debug;
import com.didi365.miudrive.navi.utils.ResourcesUtil;
import com.didi365.miudrive.navi.utils.ViewUtil;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/12 9:56 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/12 9:56 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public class LoadingFixedFlipper<T> extends FixedFlipper implements BaseLoadingStatus<T> {

    TextView mTvError;
    LinearLayout mLlError;
    public static int CONTENT = 0 ;
    public static int ERROR = 1;
    public static int EMPTY = 2;
    public static int LOADING = 3;
    public static int OFFLINE = 4;
    private String TAG = this.getClass().getSimpleName();

    public LoadingFixedFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mTvError = (TextView) findViewById(R.id.load_error_tv);

    }

    void showError(String msg) {
        Debug.d(TAG, "showError");
        setDisplayedChild(ERROR);
//        mTvError.setText(msg);
        ViewUtil.changeTextViewColor(mTvError,ResourcesUtil.getString(R.string.loading_error), ResourcesUtil.getString(R.string.loading_error2), ClientApplication.getApplication().getResources().getColor(R.color.theme_color));

        mTvError.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus) {
                ViewUtil.changeTextViewColor(mTvError, ResourcesUtil.getString(R.string.loading_error), ResourcesUtil.getString(R.string.loading_error2), ClientApplication.getApplication().getResources().getColor(R.color.theme_focus_tv));
            }else {
                ViewUtil.changeTextViewColor(mTvError, ResourcesUtil.getString(R.string.loading_error), ResourcesUtil.getString(R.string.loading_error2), ClientApplication.getApplication().getResources().getColor(R.color.theme_color));
            }
        });
    }

    void showFailure(int code, String msg) {
        Debug.d(TAG, "showFailure");
        setDisplayedChild(ERROR);
//        mTvError.setText("code : " + code + " msg: " + msg);
        ViewUtil.changeTextViewColor(mTvError,ResourcesUtil.getString(R.string.loading_error), ResourcesUtil.getString(R.string.loading_error2), ClientApplication.getApplication().getResources().getColor(R.color.theme_color));

        mTvError.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus) {
                ViewUtil.changeTextViewColor(mTvError, ResourcesUtil.getString(R.string.loading_error), ResourcesUtil.getString(R.string.loading_error2), ClientApplication.getApplication().getResources().getColor(R.color.theme_focus_tv));
            }else {
                ViewUtil.changeTextViewColor(mTvError,ResourcesUtil.getString(R.string.loading_error), ResourcesUtil.getString(R.string.loading_error2), ClientApplication.getApplication().getResources().getColor(R.color.theme_color));
            }
        });
    }

    void showContent() {
        Debug.d(TAG, "showContent");
        setDisplayedChild(CONTENT);
    }

    public void showEmpty() {
        Debug.d(TAG, "showEmpty");
        setDisplayedChild(EMPTY);
    }

    void showLoading() {
        Debug.d(TAG, "showLoading");
        setDisplayedChild(LOADING);
    }

    public void displayContentByType(int type) {
        Debug.d(TAG, "displayContentByType");
        setDisplayedChild(type);
    }

    @Override
    public void callLoadData() {

    }

    @Override
    public void callSetRefreshDataEnable(boolean isRefreshEnable) {

    }

    @Override
    public void callbackSetRefreshDataEnable(boolean isRefreshEnable) {

    }

    @Override
    public void callSetLoadData(T t) {

    }

    @Override
    public void callbackLoadDataStart() {

    }

    @Override
    public void callbackDataLoading() {
        showLoading();
    }

    @Override
    public void callbackLoadDataSuccess(T data) {
        showContent();
    }

    @Override
    public void callbackDataEmpty() {
        showEmpty();
    }

    @Override
    public void callbackDataFailure(int code, String msg) {
        showFailure(code, msg);
    }

    @Override
    public void callSetDataError(String msg) {

    }

    @Override
    public void callbackDataError(String msg) {
        showError(msg);
    }

    @Override
    public void callbackDataComplete() {

    }

    @Override
    public void callDataReLoad() {

    }

    @Override
    public void callDataRefresh() {

    }

    @Override
    public void callbackDataRefreshSuccess(T t) {

    }

}
