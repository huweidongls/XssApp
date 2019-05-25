package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/24.
 */

public class ZixunListBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"id":"4","title":"聚焦大平台建设 宁波如何攀登\u201c下一个万亿\u201d高峰？","imgurl":"upload/Admin/2019/05/24/9d3b3726eaaed9d50ed3cd7962ee894215586766537.jpg","addtime":"2019-05-24 13:44:13"}]
     */

    private int code;
    private String message;
    private List<ObjBean> obj;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * id : 4
         * title : 聚焦大平台建设 宁波如何攀登“下一个万亿”高峰？
         * imgurl : upload/Admin/2019/05/24/9d3b3726eaaed9d50ed3cd7962ee894215586766537.jpg
         * addtime : 2019-05-24 13:44:13
         */

        private String id;
        private String title;
        private String imgurl;
        private String addtime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
    }
}
