package com.android2droid.simpledevelop202001160940.mvp.base.model;

import java.util.List;

/**
 * @author created by luokaixuan
 * @date 2019/11/23
 * 这个类是用来干嘛的
 */
public interface BaseListBeanInterface<ITEM> {
    /**获取播放列表
     * @return 获取播放列表
     */
    List<ITEM> getList();
}
