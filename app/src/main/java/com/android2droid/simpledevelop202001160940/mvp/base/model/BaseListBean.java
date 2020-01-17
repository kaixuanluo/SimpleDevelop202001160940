package com.android2droid.simpledevelop202001160940.mvp.base.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/18 13:43 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/18 13:43 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 * @author luokaixuan
 */
public class BaseListBean<ITEM> implements BaseListBeanInterface<ITEM>{

    @Override
    public List<ITEM> getList() {
        return new ArrayList<>();
    }
}
