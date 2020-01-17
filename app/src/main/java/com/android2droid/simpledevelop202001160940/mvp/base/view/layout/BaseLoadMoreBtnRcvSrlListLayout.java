package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.didi365.miudrive.navi.R;
import com.didi365.miudrive.navi.focus.configure.FocusStateConfigure;
import com.didi365.miudrive.navi.main.music.base.model.BaseListBean;
import com.didi365.miudrive.navi.main.music.base.status.BaseLoadMoreListStatus;
import com.didi365.miudrive.navi.utils.Debug;
import com.didi365.miudrive.navi.utils.ViewUtil;

/**
 * @author created by luokaixuan
 * @date 2019/10/11
 * 这个类是用来干嘛的
 */
public abstract class BaseLoadMoreBtnRcvSrlListLayout<ITEM, BEAN extends BaseListBean<ITEM>
        , P extends BaseLoadMoreListStatus.BasePLoadMoreListStatus<ITEM, BEAN>
        , HOLDER extends RecyclerView.ViewHolder> extends BaseLoadMoreRcvSrlListLayout<ITEM, BEAN, P, HOLDER> implements View.OnClickListener {

    private ImageView mPreHBtn;
    private ImageView mMoreHBtn;

    private ImageView mPreVBtn;
    private ImageView mMoreVBtn;

    private FrameLayout mPreFl;
    private FrameLayout mMoreFl;

    @Override
    protected void initLayoutIfNotInitialized(Context context) {
        super.initLayoutIfNotInitialized(context);

        if (isHorizontal()) {
            FrameLayout preMoreHorizontalView = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.land_layout_pre_more_btn_horizontal, null);
            mPreHBtn = preMoreHorizontalView.findViewById(R.id.audio_list_horizontal_pre_ib);
            mMoreHBtn = preMoreHorizontalView.findViewById(R.id.audio_list_horizontal_more_ib);
            mPreFl = preMoreHorizontalView.findViewById(R.id.audio_list_horizontal_pre_fl);
            mMoreFl = preMoreHorizontalView.findViewById(R.id.audio_list_horizontal_more_fl);
            mPreFl.setOnClickListener(this);
            mMoreFl.setOnClickListener(this);
            preMoreHorizontalView.addView(getLayoutRootView(), 0);
            initPreNextView();
            setRootView(preMoreHorizontalView);
        } else {
            LinearLayout preMoreVerticalView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.land_layout_pre_more_btn_vertical, null);
            mPreVBtn = preMoreVerticalView.findViewById(R.id.audio_list_vertical_pre_ib);
            mMoreVBtn = preMoreVerticalView.findViewById(R.id.audio_list_vertical_more_ib);
            mPreFl = preMoreVerticalView.findViewById(R.id.audio_list_vertical_pre_fl);
            mMoreFl = preMoreVerticalView.findViewById(R.id.audio_list_vertical_more_fl);
            mPreFl.setOnClickListener(this);
            mMoreFl.setOnClickListener(this);
            FrameLayout preMoreVerticalContentFl = preMoreVerticalView.findViewById(R.id.audio_list_vertical_content_fl);
            preMoreVerticalContentFl.addView(getLayoutRootView());
            initPreNextView();
            setRootView(preMoreVerticalView);
        }
        ViewUtil.setVisible(mPreFl, !FocusStateConfigure.LAST_VIEW_EVENT_IS_KNOB);
        ViewUtil.setVisible(mMoreFl, !FocusStateConfigure.LAST_VIEW_EVENT_IS_KNOB);
        //让自动滚动到底部的时候最后可见position能够加1
        if (mRcv.getItemDecorationCount() == 0) {
            addFootViewSpace(1);
        }
    }

    private void initPreNextView() {
        setPreViewEnable(false);
        setNextViewEnable(false);
    }

    @Override
    protected boolean isCanLoadMore() {
        return getRcv().isHasMore();
    }

    @Override
    protected void onNoPrePage() {
        super.onNoPrePage();
        setPreViewEnable(false);
    }

    @Override
    protected void onNoNextPage() {
        super.onNoNextPage();
        setNextViewEnable(false);
    }

    @Override
    protected void onHasPrePage() {
        super.onHasPrePage();
        setPreViewEnable(true);
    }

    @Override
    protected void onHasNextPage() {
        super.onHasNextPage();
        setNextViewEnable(true);
    }

    private void setPreViewEnable(boolean preViewEnable) {
        ViewUtil.setImage(mPreHBtn, preViewEnable ? R.drawable.selector_music_list_pre_h_icon : R.drawable.audio_pre_h_2);
        ViewUtil.setImage(mPreVBtn, preViewEnable ? R.drawable.selector_music_list_pre_v_icon : R.drawable.audio_pre_v_2);
    }

    private void setNextViewEnable(boolean nextViewEnable) {
        ViewUtil.setImage(mMoreHBtn, nextViewEnable ? R.drawable.selector_music_list_next_h_icon : R.drawable.audio_next_h_2);
        ViewUtil.setImage(mMoreVBtn, nextViewEnable ? R.drawable.selector_music_list_next_v_icon : R.drawable.audio_next_v_2);
    }

    @Override
    public void onAttach() {
        super.onAttach();
        Debug.d(TAG, "上次操作的是 “旋钮” 吗？ " + FocusStateConfigure.LAST_VIEW_EVENT_IS_KNOB);
        Debug.d(TAG, "上次操作的是旋钮，就隐藏上下按钮，上次操作的是触摸，显示上下按钮。。。");
        ViewUtil.setVisible(mPreFl, !FocusStateConfigure.LAST_VIEW_EVENT_IS_KNOB);
        ViewUtil.setVisible(mMoreFl, !FocusStateConfigure.LAST_VIEW_EVENT_IS_KNOB);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.audio_list_horizontal_pre_fl:
            case R.id.audio_list_vertical_pre_fl:
                if (getRvPageScrollController() != null) {
                    getRvPageScrollController().personHandScrollToPrePageWithDelay();
                }
                break;
            case R.id.audio_list_horizontal_more_fl:
            case R.id.audio_list_vertical_more_fl:
                if (getRvPageScrollController() != null) {
                    getRvPageScrollController().personHandScrollToNextPageWithDelay();
                }
                break;
            default:
                break;
        }
    }
}
