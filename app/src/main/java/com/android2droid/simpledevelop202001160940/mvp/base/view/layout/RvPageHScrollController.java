package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

/**
 * @author created by luokaixuan
 * @date 2019/10/12
 * 这个类是用来干嘛的
 */
public class RvPageHScrollController extends RvPageScrollController {
    @Override
    protected void scrollDistance(float dx, float dy, int lastScrollPosition) {
        super.scrollDistance(dx, lastScrollPosition);
    }

    @Override
    protected float getPageDistance() {
        return mRcv.getWidth();
    }
}
