package com.android2droid.simpledevelop202001160940.mvp.base.view.itemTouchHelper;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2016/12/19 10:20 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2016/12/19 10:20 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
