package com.android2droid.simpledevelop202001160940.mvp.base.model;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseLoadMoreListStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by luokaixuan
 * Created Date 2018/4/17.
 * Description 加载更多 Module
 *
 * @author luokaixuan
 */
public class BaseLoadMoreListModule<ITEM, BEAN extends BaseListBean<ITEM>,
        P extends BaseLoadMoreListStatus.BasePLoadMoreListStatus<ITEM, BEAN>>
        extends BaseListModule<ITEM, BEAN, P> implements BaseLoadMoreListStatus.BaseMLoadMoreListStatus<ITEM, BEAN> {

    private static final int HANDLER_THREAD_LOAD_MORE_SUCCESS = 1;
    private static final int HANDLER_THREAD_LOAD_MORE_ERROR = 2;
    private static final int HANDLER_THREAD_LOAD_MORE_FINISH = 3;

    protected int mLoadMorePage;
    private final Handler mBaseLoadMoreHandler;
    private final BaseLoadMoreRunnable mBaseLoadMoreRunnable;
    private BaseLoadMoreCallbackHandler mBaseLoadMoreCallbackHandler;

    public int getLoadMorePage() {
        return mLoadMorePage;
    }

    @Override
    public void callbackLoadMorePage(int loadMorePage) {
        getPresenter().callbackLoadMorePage(loadMorePage);
    }

    @Override
    public void callSetLoadMorePage(int loadMorePage) {
        mLoadMorePage = loadMorePage;
        callbackLoadMorePage(loadMorePage);
    }

    public BaseLoadMoreListModule(P baseLoadMoreListPresenter) {
        super(baseLoadMoreListPresenter);
        HandlerThread baseLoadMoreHandlerThread = new HandlerThread("BaseLoadMoreHandlerThread");
        baseLoadMoreHandlerThread.start();
        mBaseLoadMoreHandler = new Handler(baseLoadMoreHandlerThread.getLooper());
        mBaseLoadMoreRunnable = new BaseLoadMoreRunnable();
        mBaseLoadMoreCallbackHandler = new BaseLoadMoreCallbackHandler();
    }

    @Override
    public void callLoadMoreData(ITEM data) {
        callbackStartLoadMoreData();
    }

    @Override
    public void callbackStartLoadMoreData() {
        getPresenter().callbackStartLoadMoreData();
    }

    @Override
    public void callSetLoadMoreData(BEAN bean) {
        callbackLoadMoreDataSuccess(bean.getList());
        callbackDataComplete();
    }

    @Override
    public void callGenerateListMoreData(List<ITEM> list) {

    }

    @Override
    public void callbackNoMoreData() {
        getPresenter().callbackNoMoreData();
    }

    @Override
    public void callbackHasMoreData() {
        getPresenter().callbackHasMoreData();
    }

    @Override
    public void callSetLoadMoreDataEnable(boolean enable) {
        callbackLoadMoreDataEnable(enable);
    }

    @Override
    public void callbackHideLoadStatus() {

    }

    @Override
    public void callbackLoadMoreDataEnable(boolean enable) {
        getPresenter().callbackLoadMoreDataEnable(enable);
    }

    @Override
    public void callbackLoadMoreDataSuccess(BEAN bean) {
        callbackLoadMoreDataSuccess(bean == null ? null : bean.getList());
    }

    @Override
    public void callbackLoadMoreDataSuccess(List<ITEM> list) {
        getPresenter().callbackLoadMoreDataSuccess(list);
        if (list == null || list.size() == 0) {
            callbackNoMoreData();
        } else {
            ArrayList<ITEM> orgList = new ArrayList<>(mCurrentList);
            mCurrentList.addAll(list);
            callbackLoadMoreList(orgList, list, mCurrentList);
        }
        setLoadMoreSuccessPage();
    }

    @Override
    public void callbackLoadMoreList(List<ITEM> listOrg, List<ITEM> listMore, List<ITEM> listCurrent) {
        getPresenter().callbackLoadMoreList(listOrg, listMore, listCurrent);
    }

    @Override
    public void callbackLoadMoreDataError(int code, String msg) {
        getPresenter().callbackLoadMoreDataError(code, msg);
    }

    @Override
    public void callbackLoadMoreDataEmpty() {
        getPresenter().callbackLoadMoreDataEmpty();
    }

    @Override
    public void callbackDataRefreshSuccess(List<ITEM> list) {
        super.callbackDataRefreshSuccess(list);
        mLoadMorePage = getFirstPage() + 1;
    }

    @Override
    public void callbackLoadDataSuccess(BEAN bean) {
        super.callbackLoadDataSuccess(bean);
        mLoadMorePage = getFirstPage() + 1;
    }

    @Override
    public void callDataRefresh() {
        super.callDataRefresh();
        //        mLoadMorePage = mFirstPage;//这里不能,不能一开始刷新就设置加载更多的页数，否则在刷新的时候，加载更多会页数混乱。
    }

    @Override
    public void callLoadData() {
        super.callLoadData();
        //        mLoadMorePage = mFirstPage;//这里不能
    }

    protected void setLoadMoreSuccessPage() {
        mLoadMorePage++;
    }

    public void okGoGetMore(Class<BEAN> bean, String url) {
        okGoGetMore(bean, url, false);
    }

    public void okGoGetMore(Class<BEAN> bean, String url, boolean showLoading) {
        Map<String, String> queryMap = initQueryMap();
        if (queryMap != null) {
            queryMap.put("page", mLoadMorePage + "");
        }
        loadMore(bean, url, queryMap, showLoading);
    }

    public void okGoPostMore(Class<BEAN> bean, final String url) {
        Map<String, String> queryMap = initQueryMap();
        if (queryMap != null) {
            queryMap.put("page", mLoadMorePage + "");
        }
    }

    public void loadMore(Class<BEAN> bean, final String url, Map<String, String> queryMap) {
        loadMore(bean, url, queryMap, false);
    }

    public void loadMore(Class<BEAN> bean, final String url, Map<String, String> queryMap, boolean showLoading) {
        super.load(url, queryMap, new BaseLoadMoreJsonCallback(bean, showLoading));
    }

    class BaseLoadMoreJsonCallback extends BaseLoadingJsonCallback {

        public BaseLoadMoreJsonCallback(Class<BEAN> clazz, boolean isLoading) {
            super(clazz, isLoading);
        }

    }

    protected void handlerThreadLoadMoreData() {
        mBaseLoadMoreHandler.removeCallbacks(mBaseLoadMoreRunnable);
        mBaseLoadMoreHandler.post(mBaseLoadMoreRunnable);
    }

    public class BaseLoadMoreRunnable implements Runnable {

        @Override
        public void run() {
            BEAN bean = asyncLoadMoreData();
            Message message = mBaseLoadMoreCallbackHandler.obtainMessage();
            message.obj = bean;
            message.what = HANDLER_THREAD_LOAD_MORE_SUCCESS;
            mBaseLoadMoreCallbackHandler.sendMessage(message);
        }
    }

    @SuppressLint("HandlerLeak")
    private class BaseLoadMoreCallbackHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case HANDLER_THREAD_LOAD_MORE_SUCCESS:
                    if (msg.obj == null) {
                        callbackLoadMoreDataEmpty();
                    } else {
                        BEAN bean = (BEAN) msg.obj;
                        callbackLoadMoreDataSuccess(bean);
                    }
                    callbackDataComplete();
                    break;
                case HANDLER_THREAD_LOAD_MORE_ERROR:
                    callbackLoadMoreDataError(HANDLER_THREAD_LOAD_MORE_ERROR, "加载失败 。。。");
                    callbackDataComplete();
                    break;
                case HANDLER_THREAD_LOAD_MORE_FINISH:
                    callbackDataComplete();
                    break;
                default:
                    break;
            }
        }
    }

    protected BEAN asyncLoadMoreData() {
        return null;
    }

    @Override
    public void recycle() {
        super.recycle();
        mBaseLoadMoreHandler.removeCallbacksAndMessages(null);
    }

    //    public synchronized void asyncDataGetMore() {
//        // 指定 subscribe() 所在的线程，也就是上面call()方法调用的线程
//        dataObservableGet()
//                .subscribeOn(Schedulers.io())
//                // 指定 Subscriber 回调方法所在的线程，也就是onCompleted, onError, onNext回调的线程
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new DisposableObserver<BEAN>() {
//                    @Override
//                    public void onNext(BEAN bean) {
//                        callbackLoadMoreDataSuccess(bean.getList());
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

}
