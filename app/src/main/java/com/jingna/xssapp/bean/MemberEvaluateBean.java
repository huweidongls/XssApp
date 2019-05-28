package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/28.
 */

public class MemberEvaluateBean {

    /**
     * code : 200
     * message : 评价成功!
     * obj : [{"id":"24","serviceid":"5","radio":"5","img":"upload/Admin/2019/05/20/7c37ef69191cb947b561580c05e05e6415583401227.jpg","servicename":"啊大苏打倒萨","username":"hhh","headimg":"upload/APP/2019/05/24/476b665645b5d23758e8a6ab5cbace17155868649914.png","addtime":"2019-05-28 14:35:16","text":"哈哈"}]
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
         * id : 24
         * serviceid : 5
         * radio : 5
         * img : upload/Admin/2019/05/20/7c37ef69191cb947b561580c05e05e6415583401227.jpg
         * servicename : 啊大苏打倒萨
         * username : hhh
         * headimg : upload/APP/2019/05/24/476b665645b5d23758e8a6ab5cbace17155868649914.png
         * addtime : 2019-05-28 14:35:16
         * text : 哈哈
         */

        private String id;
        private String serviceid;
        private String radio;
        private String img;
        private String servicename;
        private String username;
        private String headimg;
        private String addtime;
        private String text;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getServiceid() {
            return serviceid;
        }

        public void setServiceid(String serviceid) {
            this.serviceid = serviceid;
        }

        public String getRadio() {
            return radio;
        }

        public void setRadio(String radio) {
            this.radio = radio;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getServicename() {
            return servicename;
        }

        public void setServicename(String servicename) {
            this.servicename = servicename;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
