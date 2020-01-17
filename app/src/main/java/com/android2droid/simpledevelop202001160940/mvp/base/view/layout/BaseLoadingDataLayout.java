package com.android2droid.simpledevelop202001160940.mvp.base.view.layout;

import com.android2droid.simpledevelop202001160940.mvp.base.model.BaseLoadingDataBean;
import com.android2droid.simpledevelop202001160940.mvp.base.status.BaseLoadingStatus;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/10 16:39 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/10 16:39 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 *
 * @author luokaixuan
 */
public class BaseLoadingDataLayout<DATA, BEAN extends BaseLoadingDataBean<DATA>,
        P extends BaseLoadingStatus.BasePLoadingStatus<BEAN>>
        extends BaseLoadingLayout<BEAN, P> {

}
