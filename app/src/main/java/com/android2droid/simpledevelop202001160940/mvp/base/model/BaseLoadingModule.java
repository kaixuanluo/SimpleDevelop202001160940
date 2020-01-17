package com.android2droid.simpledevelop202001160940.mvp.base.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.android2droid.simpledevelop202001160940.mvp.base.json.JsonCallback;
import com.android2droid.simpledevelop202001160940.mvp.base.json.JsonDataCallback;
import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseLoadingStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author created by luokaixuan
 * @date 2019/11/23
 * 这个类是用来干嘛的
 */
public class BaseLoadingModule<BEAN, P extends BaseLoadingStatus.BasePLoadingStatus<BEAN>>
        extends BaseModule implements BaseLoadingStatus.BaseMLoadingStatus<BEAN> {


    protected final String TAG = getClass().getSimpleName();

    P baseLoadingPresenter;

    private static final int HANDLER_THREAD_LOAD_SUCCESS = 1;
    private static final int HANDLER_THREAD_LOAD_ERROR = 2;
    private static final int HANDLER_THREAD_LOAD_FINISH = 3;

    Context mContext;
    protected final BaseLoadingRunnable mBaseLoadingRunnable;
    protected final Handler mBaseLoadingHandler;
    protected BaseLoadingCallbackHandler mBaseLoadingCallbackHandler;
    private BEAN mBean;

    public Map<String, String> getParams() {
        return mParams;
    }

    public void setParams(Map<String, String> mParams) {
        this.mParams = mParams;
    }

    public BaseLoadingModule putParams(String key, String value) {
        if (getParams() != null) {
            getParams().put(key, value);
        }
        return this;
    }

    private Map<String, String> mParams = new HashMap();

    public BaseLoadingModule(P baseLoadingPresenter) {

        this.baseLoadingPresenter = baseLoadingPresenter;

        HandlerThread mBaseLoadingHandlerThread = new HandlerThread("mBaseLoadingHandlerThread");
        mBaseLoadingHandlerThread.start();

        mBaseLoadingHandler = new Handler(mBaseLoadingHandlerThread.getLooper());
        mBaseLoadingRunnable = new BaseLoadingRunnable();
        mBaseLoadingCallbackHandler = new BaseLoadingCallbackHandler();

    }

    public BEAN getBean() {
        return mBean;
    }

    public P getPresenter() {
        return baseLoadingPresenter;
    }

    protected Map<String, String> initQueryMap() {
        return mParams;
    }

    @Override
    public void callLoadData() {
        callbackLoadDataStart();
    }

    @Override
    public void callSetRefreshDataEnable(boolean isRefreshEnable) {
        callbackSetRefreshDataEnable(isRefreshEnable);
    }

    @Override
    public void callbackSetRefreshDataEnable(boolean isRefreshEnable) {
        getPresenter().callbackSetRefreshDataEnable(isRefreshEnable);
    }

    @Override
    public void callSetLoadData(BEAN bean) {
        callbackLoadDataSuccess(bean);
        callbackDataComplete();
    }

    public void load(Class<BEAN> bean, final String url, Map<String, String> queryMap) {
        load(bean, url, queryMap, false);
    }

    public void okGoRefresh(Class<BEAN> bean, final String url) {
        okGoRefresh(bean, initQueryMap(), url, false);
    }

    public void okGoRefresh(Class<BEAN> bean, final String url, boolean showLoading) {
        okGoRefresh(bean, initQueryMap(), url, showLoading);
    }

    public void okGoPostRefresh(Class<BEAN> bean, final String url) {

    }

    protected void okGoRefresh(Class<BEAN> bean, Map<String, String> queryMap, final String url,
                               boolean showLoading) {
        load(bean, url, queryMap);
    }

    public void load(Class<BEAN> bean, final String url, Map<String, String> queryMap, boolean showLoading) {

    }

    public void load(final String url, Map<String, String> queryMap, BaseLoadingJsonCallback callback) {

    }

    protected class BaseLoadingJsonCallback extends JsonCallback<BEAN> {

        BaseLoadingJsonCallback(Class<BEAN> clazz, boolean isLoading) {

        }

    }

//    protected BEAN dataGet() {
//        return null;
//    }
//
//    protected Observable<BEAN> dataObservableGet() {
//        return Observable.create(emitter -> {
//            try {
//                emitter.onNext(dataGet());
//                emitter.onComplete();
//            } catch (Exception e) {
//                emitter.onError(e);
//            }
//        });
//    }
//
//    public synchronized void asyncDataGet() {
//        // 指定 subscribe() 所在的线程，也就是上面call()方法调用的线程
//        dataObservableGet()
//                .subscribeOn(Schedulers.io())
//                // 指定 Subscriber 回调方法所在的线程，也就是onCompleted, onError, onNext回调的线程
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new DisposableObserver<BEAN>() {
//                    @Override
//                    public void onNext(BEAN bean) {
//                        callbackLoadDataSuccess(bean);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callbackDataError(e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        callbackDataComplete();
//                    }
//                });
//    }

    protected void handlerThreadLoadData() {
        mBaseLoadingHandler.removeCallbacks(mBaseLoadingRunnable);
        mBaseLoadingHandler.post(mBaseLoadingRunnable);
    }

    public class BaseLoadingRunnable implements Runnable {

        @Override
        public void run() {
            BEAN bean = asyncLoadData();
            Message message = mBaseLoadingCallbackHandler.obtainMessage();
            message.obj = bean;
            message.what = HANDLER_THREAD_LOAD_SUCCESS;
            mBaseLoadingCallbackHandler.sendMessage(message);
        }
    }

    @SuppressLint("HandlerLeak")
    public class BaseLoadingCallbackHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HANDLER_THREAD_LOAD_SUCCESS:
                    if (msg.obj == null) {
                        callbackDataEmpty();
                    } else {
                        BEAN bean = (BEAN) msg.obj;
                        callbackLoadDataSuccess(bean);
                    }
                    callbackDataComplete();
                    break;
                case HANDLER_THREAD_LOAD_ERROR:
                    callbackDataError("加载失败 。。。");
                    callbackDataComplete();
                    break;
                case HANDLER_THREAD_LOAD_FINISH:
                    callbackDataComplete();
                    break;
                default:
                    break;
            }
        }
    }

    protected BEAN asyncLoadData() {
        return null;
    }

    @Override
    public void callbackLoadDataStart() {
        if (baseLoadingPresenter != null) {
            baseLoadingPresenter.callbackLoadDataStart();
        }
        callbackDataLoading();
    }

    @Override
    public void callbackDataLoading() {
        if (baseLoadingPresenter != null) {
            baseLoadingPresenter.callbackDataLoading();
        }
    }

    @Override
    public void callbackLoadDataSuccess(BEAN bean) {
        mBean = bean;
        if (baseLoadingPresenter != null) {
            baseLoadingPresenter.callbackLoadDataSuccess(bean);
        }
        callbackDataRefreshSuccess(bean);
    }

    @Override
    public void callbackDataEmpty() {
        if (baseLoadingPresenter != null) {
            baseLoadingPresenter.callbackDataEmpty();
        }
    }

    @Override
    public void callbackDataFailure(int code, String msg) {
        if (baseLoadingPresenter != null) {
            baseLoadingPresenter.callbackDataFailure(code, msg);
        }
    }

    @Override
    public void callSetDataError(String msg) {
        callbackDataError(msg);
        callbackDataComplete();
    }

    @Override
    public void callbackDataError(String msg) {
        if (baseLoadingPresenter != null) {
            baseLoadingPresenter.callbackDataError(msg);
        }
    }

    @Override
    public void callbackDataComplete() {
        if (baseLoadingPresenter != null) {
            baseLoadingPresenter.callbackDataComplete();
        }
    }

    @Override
    public void callDataReLoad() {

    }

    @Override
    public void callDataRefresh() {
        callLoadData();
    }

    public void recycle() {
        mContext = null;
        mBaseLoadingHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void callbackDataRefreshSuccess(BEAN bean) {
        if (baseLoadingPresenter != null) {
            baseLoadingPresenter.callbackDataRefreshSuccess(bean);
        }
    }

}
