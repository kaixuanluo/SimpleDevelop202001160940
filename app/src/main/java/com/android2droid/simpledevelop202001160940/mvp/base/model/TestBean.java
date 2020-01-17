package com.android2droid.simpledevelop202001160940.mvp.base.model;

import java.util.List;

/**
 * Created by luokaixuan
 * Created Date 2017/12/30.
 * Description 测试
 */

public class TestBean {


    /**
     * status : 1
     * now : 1514624438
     * info : success
     * code : 000000
     * data : {"list":[{"id":"552","track_id":"8145","rec_id":"57","user_id":"1806979","track_name":"遇见","track_artist":"孙燕姿","track_url":"http://ws.stream.qqmusic.qq.com/8145.m4a?fromtag=38","is_collection":0},{"id":"551","track_id":"200397365","rec_id":"57","user_id":"1806979","track_name":"三生三世","track_artist":"张杰","track_url":"http://ws.stream.qqmusic.qq.com/200397365.m4a?fromtag=38","is_collection":0},{"id":"550","track_id":"397467","rec_id":"57","user_id":"1806979","track_name":"等一分钟","track_artist":"徐誉滕","track_url":"http://ws.stream.qqmusic.qq.com/397467.m4a?fromtag=38","is_collection":0},{"id":"549","track_id":"5016168","rec_id":"57","user_id":"1806979","track_name":"李白","track_artist":"李荣浩","track_url":"http://ws.stream.qqmusic.qq.com/5016168.m4a?fromtag=38","is_collection":0},{"id":"548","track_id":"169763","rec_id":"57","user_id":"1806979","track_name":"暖暖","track_artist":"梁静茹","track_url":"http://ws.stream.qqmusic.qq.com/169763.m4a?fromtag=38","is_collection":0},{"id":"547","track_id":"204281778","rec_id":"57","user_id":"1806979","track_name":"不再犹豫 (REMIX版)","track_artist":"缝纫机乐队","track_url":"http://ws.stream.qqmusic.qq.com/204281778.m4a?fromtag=38","is_collection":0},{"id":"546","track_id":"209378295","rec_id":"57","user_id":"1806979","track_name":"差一步","track_artist":"大壮","track_url":"http://ws.stream.qqmusic.qq.com/209378295.m4a?fromtag=38","is_collection":0},{"id":"545","track_id":"109191643","rec_id":"57","user_id":"1806979","track_name":"刚好遇见你","track_artist":"李玉刚","track_url":"http://ws.stream.qqmusic.qq.com/109191643.m4a?fromtag=38","is_collection":0},{"id":"544","track_id":"104690200","rec_id":"57","user_id":"1806979","track_name":"再见只是陌生人","track_artist":"庄心妍","track_url":"http://ws.stream.qqmusic.qq.com/104690200.m4a?fromtag=38","is_collection":0},{"id":"543","track_id":"204251789","rec_id":"57","user_id":"1806979","track_name":"我多喜欢你，你会知道","track_artist":"王俊琪","track_url":"http://ws.stream.qqmusic.qq.com/204251789.m4a?fromtag=38","is_collection":0},{"id":"542","track_id":"5156899","rec_id":"57","user_id":"1806979","track_name":"在人间","track_artist":"王建房","track_url":"http://ws.stream.qqmusic.qq.com/5156899.m4a?fromtag=38","is_collection":0}],"userid":"1806979","accid":"","line":"0","status":"0","nickname":"梦回旧景","sex":"0","photo":"https://src.didi365.com/didi365/Upload/Miudrive/musicSingerPhoto/20171226/5a421322307d7_300x300.jpg","is_collection":0}
     */

    private int status;
    private int now;
    private String info;
    private String code;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNow() {
        return now;
    }

    public void setNow(int now) {
        this.now = now;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"id":"552","track_id":"8145","rec_id":"57","user_id":"1806979","track_name":"遇见","track_artist":"孙燕姿","track_url":"http://ws.stream.qqmusic.qq.com/8145.m4a?fromtag=38","is_collection":0},{"id":"551","track_id":"200397365","rec_id":"57","user_id":"1806979","track_name":"三生三世","track_artist":"张杰","track_url":"http://ws.stream.qqmusic.qq.com/200397365.m4a?fromtag=38","is_collection":0},{"id":"550","track_id":"397467","rec_id":"57","user_id":"1806979","track_name":"等一分钟","track_artist":"徐誉滕","track_url":"http://ws.stream.qqmusic.qq.com/397467.m4a?fromtag=38","is_collection":0},{"id":"549","track_id":"5016168","rec_id":"57","user_id":"1806979","track_name":"李白","track_artist":"李荣浩","track_url":"http://ws.stream.qqmusic.qq.com/5016168.m4a?fromtag=38","is_collection":0},{"id":"548","track_id":"169763","rec_id":"57","user_id":"1806979","track_name":"暖暖","track_artist":"梁静茹","track_url":"http://ws.stream.qqmusic.qq.com/169763.m4a?fromtag=38","is_collection":0},{"id":"547","track_id":"204281778","rec_id":"57","user_id":"1806979","track_name":"不再犹豫 (REMIX版)","track_artist":"缝纫机乐队","track_url":"http://ws.stream.qqmusic.qq.com/204281778.m4a?fromtag=38","is_collection":0},{"id":"546","track_id":"209378295","rec_id":"57","user_id":"1806979","track_name":"差一步","track_artist":"大壮","track_url":"http://ws.stream.qqmusic.qq.com/209378295.m4a?fromtag=38","is_collection":0},{"id":"545","track_id":"109191643","rec_id":"57","user_id":"1806979","track_name":"刚好遇见你","track_artist":"李玉刚","track_url":"http://ws.stream.qqmusic.qq.com/109191643.m4a?fromtag=38","is_collection":0},{"id":"544","track_id":"104690200","rec_id":"57","user_id":"1806979","track_name":"再见只是陌生人","track_artist":"庄心妍","track_url":"http://ws.stream.qqmusic.qq.com/104690200.m4a?fromtag=38","is_collection":0},{"id":"543","track_id":"204251789","rec_id":"57","user_id":"1806979","track_name":"我多喜欢你，你会知道","track_artist":"王俊琪","track_url":"http://ws.stream.qqmusic.qq.com/204251789.m4a?fromtag=38","is_collection":0},{"id":"542","track_id":"5156899","rec_id":"57","user_id":"1806979","track_name":"在人间","track_artist":"王建房","track_url":"http://ws.stream.qqmusic.qq.com/5156899.m4a?fromtag=38","is_collection":0}]
         * userid : 1806979
         * accid :
         * line : 0
         * status : 0
         * nickname : 梦回旧景
         * sex : 0
         * photo : https://src.didi365.com/didi365/Upload/Miudrive/musicSingerPhoto/20171226/5a421322307d7_300x300.jpg
         * is_collection : 0
         */

        private String userid;
        private String accid;
        private String line;
        private String status;
        private String nickname;
        private String sex;
        private String photo;
        private int is_collection;
        private List<ListBean> list;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getAccid() {
            return accid;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }

        public String getLine() {
            return line;
        }

        public void setLine(String line) {
            this.line = line;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getIs_collection() {
            return is_collection;
        }

        public void setIs_collection(int is_collection) {
            this.is_collection = is_collection;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 552
             * track_id : 8145
             * rec_id : 57
             * user_id : 1806979
             * track_name : 遇见
             * track_artist : 孙燕姿
             * track_url : http://ws.stream.qqmusic.qq.com/8145.m4a?fromtag=38
             * is_collection : 0
             */

            private String id;
            private String track_id;
            private String rec_id;
            private String user_id;
            private String track_name;
            private String track_artist;
            private String track_url;
            private int is_collection;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTrack_id() {
                return track_id;
            }

            public void setTrack_id(String track_id) {
                this.track_id = track_id;
            }

            public String getRec_id() {
                return rec_id;
            }

            public void setRec_id(String rec_id) {
                this.rec_id = rec_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getTrack_name() {
                return track_name;
            }

            public void setTrack_name(String track_name) {
                this.track_name = track_name;
            }

            public String getTrack_artist() {
                return track_artist;
            }

            public void setTrack_artist(String track_artist) {
                this.track_artist = track_artist;
            }

            public String getTrack_url() {
                return track_url;
            }

            public void setTrack_url(String track_url) {
                this.track_url = track_url;
            }

            public int getIs_collection() {
                return is_collection;
            }

            public void setIs_collection(int is_collection) {
                this.is_collection = is_collection;
            }
        }
    }
}
