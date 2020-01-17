package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android2droid.simpledevelop202001160940.R;
import com.android2droid.simpledevelop202001160940.mvp.ClientApplication;
import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseLoadingStatus;
import com.android2droid.simpledevelop202001160940.mvp.base.util.Debug;
import com.android2droid.simpledevelop202001160940.mvp.base.util.ViewUtil;
import com.android2droid.simpledevelop202001160940.mvp.base.view.view.JsonCallbackView;

/**
 * @author created by luokaixuan
 * @date 2019/11/23
 * 这个类是用来干嘛的
 */
public abstract class BaseLoadingLayout<BEAN, P extends BaseLoadingStatus.BasePLoadingStatus<BEAN>>
        extends BaseLayout implements BaseLoadingStatus.BaseVLoadingStatus<BEAN> {

    public final String TAG = this.getClass().getSimpleName();

    public TextView mLoadErrorTv;
    public TextView mTvEmpty;
    public LoadStatesLayout mLoadStatesLayout;
    private boolean showLoadingDialog = true;
    protected JsonCallbackView mJsonCallbackView;
    protected Context mContext;
    private static BaseLoadingDataLayout mLayout;
    private ViewGroup mParentView;
    private ViewGroup mRootView;

    private BaseLoadingStatus.BaseVLoadingStatus<BEAN> mBaseVLoadingStatus;

    private boolean isInitialized;
    protected FrameLayout mContentFl;
    public LinearLayout mLlError;
    public LinearLayout mLlEmpty;

    public BaseVLoadingStatus<BEAN> getBaseVLoadingStatus() {
        return mBaseVLoadingStatus;
    }

    public void setBaseVLoadingStatus(BaseVLoadingStatus<BEAN> mBaseVLoadingStatus) {
        this.mBaseVLoadingStatus = mBaseVLoadingStatus;
    }

    public Context getContext() {
        return mContext;
    }

    public P getPresenter() {
        return mPresenter;
    }

    public void setPresenter(P mPresenter) {
        this.mPresenter = mPresenter;
    }

    private P mPresenter;

    private BEAN bean;

    public BEAN getBean() {
        return bean;
    }

    public void setBean(BEAN bean) {
        this.bean = bean;
    }

    public ViewGroup getLayoutRootView() {
        return mRootView;
    }

    public void setRootView(ViewGroup mLoadingRootView) {
        this.mRootView = mLoadingRootView;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public P newPresenter() {
        return null;
    }

    public static BaseLoadingDataLayout getInstance() {
        if (mLayout == null) {
            mLayout = new BaseLoadingDataLayout();
        }
        return mLayout;
    }

    public void initLayout(Context context) {
        //单例模式了，只能初始化一次。
        if (isInitialized) {
            Debug.d(TAG, "initBaseLoadingLayout isInitialized true");
        } else {
            initLayoutIfNotInitialized(context);
            isInitialized = true;
        }
    }

    /**
     * 如果没有初始化就初始化，否则不初始化第二次。
     */
    protected void initLayoutIfNotInitialized(Context context) {
        initLoadingLayout(context);
    }

    private void initLoadingLayout(Context context) {

        mContext = context;
        mPresenter = newPresenter();
        mPresenter.setBaseVLoadingStatus(this);
        mLoadStatesLayout = (LoadStatesLayout) LayoutInflater.from(context).inflate(R.layout.layout_loading, null);

        mLlError = mLoadStatesLayout.findViewById(R.id.load_error_ll);
        mLlError.setOnClickListener(view -> callDataRefresh());
        mLlEmpty = mLoadStatesLayout.findViewById(R.id.load_empty_ll);
        mLlEmpty.setOnClickListener(view -> callDataRefresh());

        mLoadErrorTv = mLoadStatesLayout.findViewById(R.id.load_error_tv);

        mLoadErrorTv.setOnClickListener(view -> callDataRefresh());

        mContentFl = mLoadStatesLayout.findViewById(R.id.load_content_fl);

        mTvEmpty = mLoadStatesLayout.findViewById(R.id.tv_empty);
        mTvEmpty.setOnClickListener(view -> callDataRefresh());
        setRootView(mLoadStatesLayout);
    }

    public void setErrorContent(View errorContent) {
        mLlError.removeAllViews();
        mLlError.addView(errorContent);
    }

    public void setEmptyContent(View emptyContent) {
        mLlEmpty.removeAllViews();
        mLlEmpty.addView(emptyContent);
    }

    public void setStateView(View viewState, int position) {
        mLoadStatesLayout.setStateView(viewState, position);
    }

    protected ViewGroup getLoadingContent() {
        return mContentFl;
    }

    public void attachToParentView(ViewGroup parentView) {
        parentView.addView(mRootView);
        setParentView(parentView);
    }

    public void attachToParentView(ViewGroup parentView, int attachToPosition) {
        parentView.addView(mRootView, attachToPosition);
        setParentView(parentView);
    }

    public void attachToParentView(ViewGroup parentView, int width, int height) {
        parentView.addView(mRootView, width, height);
        setParentView(parentView);
    }

    public void attachToParentView(ViewGroup parentView, ViewGroup.LayoutParams params) {
        parentView.addView(mRootView, params);
        setParentView(parentView);
    }

    public void attachToParentView(ViewGroup parentView, int attachToPosition, ViewGroup.LayoutParams params) {
        parentView.addView(mRootView, attachToPosition, params);
        setParentView(parentView);
    }

    public void setParentView(ViewGroup mParentView) {
        this.mParentView = mParentView;
        onAttach();
    }

    public ViewGroup getParentView() {
        return mParentView;
    }

    public void detachFromParentView() {
        if (getParentView() != null) {
            getParentView().removeAllViews();
        }
    }

    public void setEmptyText(String emptyText) {
        if (mTvEmpty != null) {
            ViewUtil.setText(mTvEmpty, emptyText);
        }
    }

    public void callLoadDataIfEmpty() {
        if (bean == null) {
            callLoadData();
        }
    }

    @Override
    public void callLoadData() {
        getPresenter().callLoadData();
    }

    @Override
    public void callSetRefreshDataEnable(boolean isRefreshEnable) {
        getPresenter().callSetRefreshDataEnable(isRefreshEnable);
    }

    @Override
    public void callbackSetRefreshDataEnable(boolean isRefreshEnable) {

    }

    @Override
    public void callSetLoadData(BEAN bean) {
        final P presenter = getPresenter();
        if (presenter != null) {
            presenter.callSetLoadData(bean);
        }
    }

    @Override
    public void callbackLoadDataStart() {
        if (mLoadStatesLayout != null) {
            mLoadStatesLayout.callbackLoadDataStart();
        }
        if (mJsonCallbackView == null) {
            mJsonCallbackView = new JsonCallbackView();
        }
        if (isShowLoadingDialog()) {
            mJsonCallbackView.showLoading(mContext, "");
        }
    }

    public boolean isShowLoadingDialog() {
        return showLoadingDialog;
    }

    public void setShowLoadingDialog(boolean showLoadingDialog) {
        this.showLoadingDialog = showLoadingDialog;
    }

    @Override
    public void callbackDataLoading() {
        if (mLoadStatesLayout != null) {
            mLoadStatesLayout.callbackDataLoading();
        }
    }

    @Override
    public void callbackDataRefreshSuccess(BEAN bean) {

    }

    @Override
    public void callbackLoadDataSuccess(BEAN bean) {
        this.bean = bean;
        if (mLoadStatesLayout != null) {
            mLoadStatesLayout.callbackLoadDataSuccess(bean);
        }
        if (mBaseVLoadingStatus != null) {
            mBaseVLoadingStatus.callbackLoadDataSuccess(bean);
        }
    }

    @Override
    public void callbackDataEmpty() {
        if (mLoadStatesLayout != null) {
            mLoadStatesLayout.callbackDataEmpty();
            requestFocus();
        }
    }

    @Override
    public void callSetDataError(String msg) {
        if (getPresenter() != null) {
            getPresenter().callSetDataError(msg);
        }
    }

    @Override
    public void callbackDataFailure(int code, String msg) {
        if (mLoadStatesLayout != null) {
            mLoadStatesLayout.callbackDataFailure(code, msg);
            requestFocus();
        }
    }

    private void requestFocus() {

    }

    @Override
    public void callbackDataError(String msg) {
        if (mLoadStatesLayout != null) {
            mLoadStatesLayout.callbackDataError(msg);
            requestFocus();
        }
    }

    public int getDisplayedChild() {
        return mLoadStatesLayout.mFlp.getDisplayedChild();
    }

    public void requestErrorTvFocus() {
        if (mLoadErrorTv.getVisibility() == View.VISIBLE) {
            mLoadErrorTv.requestFocusFromTouch();
        }
    }

    public boolean isErrorTvFocus() {
        if (mLoadErrorTv.getVisibility() == View.VISIBLE) {
        }
        return false;
    }

    public void requestEmptyTvFocus() {
        if (mTvEmpty.getVisibility() == View.VISIBLE) {
            mTvEmpty.requestFocusFromTouch();
        }
    }

    public boolean isEmptyTvFocus() {
        if (mTvEmpty.getVisibility() == View.VISIBLE) {
        }
        return false;
    }

    @Override
    public void callbackDataComplete() {
        if (mLoadStatesLayout != null) {
            mLoadStatesLayout.callbackDataComplete();
        }
        if (mJsonCallbackView != null) {
            mJsonCallbackView.dismissLoading();
        }
    }

    public void addLoadStatus(BaseVLoadingStatus<BEAN> baseVLoadingStatus) {
        mBaseVLoadingStatus = baseVLoadingStatus;
    }

    @Override
    public void callDataReLoad() {//错误重试。
        getPresenter().callDataReLoad();
    }

    @Override
    public void callDataRefresh() {//下拉刷新
        getPresenter().callDataRefresh();
    }

    public void displayContent(int contentType) {
        if (mLoadStatesLayout == null) {
            Debug.d(TAG, "displayContent mLoadStatesLayout == null ... ");
        } else {
            mLoadStatesLayout.displayContent(contentType);
        }
    }

    @Override
    public void recycle() {

        //单例了，不能再清除了。
        //        if (mPresenter != null) {
        //            mPresenter = null;
        //        }
        //
        //        if (mLoadStatesLayout != null) {
        //            mLoadStatesLayout.removeAllViews();
        //            mLoadStatesLayout = null;
        //        }

        //        mContext = null;

        detachFromParentView();
    }

    /**
     * 日志打印方法
     * 从什么地方开始调用的路径日志
     */
    protected void callFrom() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StringBuilder log = new StringBuilder();
        for (int i = stackTrace.length - 1; i >= 0; i--) {
            StackTraceElement stackTraceElement = stackTrace[i];
            String className = stackTraceElement.getClassName();
            String fileName = stackTraceElement.getFileName();
            String methodName = stackTraceElement.getMethodName();
            int lineNumber = stackTraceElement.getLineNumber();
            String packageName = ClientApplication.getApplication().getPackageName();
            if (className.startsWith(packageName)) {
                //                className = className.replaceFirst(packageName + ".", "");
                if (fileName != null) {
                    fileName = fileName.replaceAll(".java", "");
                }
                if (TextUtils.equals(methodName, "callFrom")) {
                    //过滤掉当前callFrom打印方法的输出
                } else {
                    log.append("->").append(fileName).append(".").append(methodName).append("(").append(lineNumber).append(") \n");
                }
            }
        }
        Debug.d(TAG + "", log.toString());
    }
}
