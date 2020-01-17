package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import android.support.v7.widget.RecyclerView;

import com.didi365.miudrive.navi.main.music.base.model.BaseListBean;
import com.didi365.miudrive.navi.main.music.base.status.BaseLoadPreListStatus;
import com.didi365.miudrive.navi.main.music.base.status.BaseLoadSinglePageStatus;

import java.util.List;

/**
 * Created by luokaixuan
 * Created Date 2018/4/19.
 * Description 基础单页视图
 * @author luokaixuan
 */
public abstract class BaseSinglePageLoadPreMoreRcvSrlListLayout<ITEM, BEAN extends BaseListBean<ITEM>
        , P extends BaseLoadPreListStatus.BasePLoadPreListStatus<ITEM, BEAN>
        , HOLDER extends RecyclerView.ViewHolder> extends BaseLoadPreMoreRcvSrlListLayout<ITEM, BEAN, P, HOLDER>
        implements BaseLoadSinglePageStatus<ITEM, BEAN> {

    private int mCurrentSinglePage;
    @Override
    public void callGenerateListMoreData(List<ITEM> list) {
        //设置数据只有一页
    }

    @Override
    protected void getPreList(List<ITEM> list) {
        //设置数据只有一页
    }

//    @Override
//    protected void loadMoreNotifyData() {//不会移动到第一个item.需要等外部Srl隐藏底部加载更多。
//        if (getRcv() != null) {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    BaseLoadSinglePageLayout.super.loadMoreNotifyData();
//                    getRcv().scrollToPosition(0);
//                }
//            }, 700);
////            RecyclerView.LayoutManager layoutManager = getLayoutManager();
////            if (layoutManager != null && layoutManager instanceof LinearLayoutManager) {
////                ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(0, 300);
////            }
//        }
//    }

    @Override
    public void callbackDataComplete() {
        super.callbackDataComplete();
        setHeardText(getCurrentSinglePage() == getFirstPage());
    }

    @Override
    public void callbackCurrentSinglePage(int currentSinglePage) {
        mCurrentSinglePage = currentSinglePage;
    }

    public int getCurrentSinglePage() {
        return mCurrentSinglePage;
    }
}
