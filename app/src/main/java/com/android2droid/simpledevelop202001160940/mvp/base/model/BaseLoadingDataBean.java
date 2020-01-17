package com.android2droid.simpledevelop202001160940.mvp.base.model;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/13 10:27 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/13 10:27 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public abstract class BaseLoadingDataBean<DATA> extends BaseLoadingBean {

    /**
     * status : 1
     * now : 1514188947
     * info : success
     * code : 000000
     * data : {"num":0,"genres":[{"id":"29","genre_name":"慢摇","genre_icon":"https://src.didi365.com/didi365/Upload/MiusicGenre/20170911/59b5697c90c28_300x300.jpg","genre_id":"15"},{"id":"35","genre_name":"禅曲","genre_icon":"https://srctest.didi365.com/didi365/Upload/MiusicGenre/20171115/5a0be08f97e19_300x300.jpg","genre_id":"21"},{"id":"30","genre_name":"日系","genre_icon":"https://src.didi365.com/didi365/Upload/MiusicGenre/20170911/59b56f141eb7a_300x300.jpg","genre_id":"16"},{"id":"18","genre_name":"电子","genre_icon":"https://src.didi365.com/didi365/Upload/MiusicGenre/20170911/59b58e0e8ddc7_300x300.jpg","genre_id":"4"},{"id":"34","genre_name":"影视","genre_icon":"https://src.didi365.com/didi365/Upload/MiusicGenre/20170911/59b59cd2546e2_300x300.jpg","genre_id":"20"},{"id":"33","genre_name":"韩流","genre_icon":"https://src.didi365.com/didi365/Upload/MiusicGenre/20170911/59b59841989fe_300x300.jpg","genre_id":"19"}],"menu":[{"id":"28","user_id":"1002302","nickname":"李海洋","sex":"1","photo":"https://srctest.didi365.com/didi365/Upload/Miudrive/musicSingerPhoto/20171017/59e57499e4895_455x399.jpg","count":"12"},{"id":"41","user_id":"1005334","nickname":"Koreyoshi","sex":"0","photo":"https://srctest.didi365.com/didi365/Upload/Miudrive/musicSingerPhoto/20171031/59f7e7451bbb2_300x300.jpg","count":"10"},{"id":"39","user_id":"1005372","nickname":"眼睛去旅行","sex":"2","photo":"https://srctest.didi365.com/didi365/Upload/Miudrive/musicSingerPhoto/20170916/59bcf4345b3da_200x200.jpg","count":"11"},{"id":"30","user_id":"1001731","nickname":"劲风、Cow","sex":"0","photo":"https://src.didi365.com/didi365/Upload/Miudrive/musicSingerPhoto/20170910/59b517c540d64_300x300.jpg","count":"10"},{"id":"38","user_id":"1005371","nickname":"Amy Ms","sex":"0","photo":"https://srctest.didi365.com/didi365/Upload/Miudrive/musicSingerPhoto/20170916/59bcf395a9a53_250x250.png","count":"11"},{"id":"31","user_id":"1001636","nickname":"嗨湾汀","sex":"0","photo":"https://src.didi365.com/didi365/Upload/Miudrive/musicSingerPhoto/20170910/59b5187487133_300x300.jpg","count":"13"}]}
     */
    protected DATA data;

    public DATA getData(){
        return data;
    }

    public void setData(DATA data) {
        this.data = data;
    };

}
