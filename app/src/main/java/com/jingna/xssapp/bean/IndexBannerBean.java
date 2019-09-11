package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/22.
 */

public class IndexBannerBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"id":"2","imgurl":"upload/Admin/2019/05/15/547e242e52fa3a363474e832ed0c034b15578991328.jpg","news_id":"0","is_new":"0"},{"id":"4","imgurl":"upload/Admin/2019/05/15/15be38f5d8eeed88b593653d897da7e815578910271.jpg","news_id":"8","is_new":"1"},{"id":"3","imgurl":"upload/Admin/2019/05/15/3aa0ca2f7d0b81849029644a38d3227f15578909805.jpg","news_id":"0","is_new":"0"},{"id":"7","imgurl":"upload/Admin/2019/09/11/62275a88df8a3d5299af1986dd28610c15681822205.jpg","news_id":"6","is_new":"0"}]
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
         * id : 2
         * imgurl : upload/Admin/2019/05/15/547e242e52fa3a363474e832ed0c034b15578991328.jpg
         * news_id : 0
         * is_new : 0
         */

        private String id;
        private String imgurl;
        private String news_id;
        private String is_new;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getIs_new() {
            return is_new;
        }

        public void setIs_new(String is_new) {
            this.is_new = is_new;
        }
    }
}
