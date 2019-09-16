package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/9/16.
 */

public class AllCommentBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"id":"19","order_id":"97","text":"很满意这次工人的服务","on_satisfied":"0","uid":"25","is_anonymous":"0","fid":"50","img":"upload/Admin/2019/06/20/de289b0685cceb8ba608b5db9af041dc15610049155.png","addtime":"2019-06-20 12:34:55","servicename":"测试楼体外清洗","username":"","headimg":""},{"id":"20","order_id":"97","text":"很满意这次工人的服务","on_satisfied":"0","uid":"25","is_anonymous":"0","fid":"50","img":"upload/Admin/2019/06/20/de289b0685cceb8ba608b5db9af041dc15610049155.png","addtime":"2019-06-20 12:34:55","servicename":"测试楼体外清洗","username":"","headimg":""},{"id":"21","order_id":"97","text":"很满意这次工人的服务","on_satisfied":"0","uid":"25","is_anonymous":"0","fid":"50","img":"upload/Admin/2019/06/20/de289b0685cceb8ba608b5db9af041dc15610049155.png","addtime":"2019-06-20 12:34:55","servicename":"测试楼体外清洗","username":"","headimg":""},{"id":"22","order_id":"97","text":"很满意这次工人的服务","on_satisfied":"0","uid":"25","is_anonymous":"0","fid":"50","img":"upload/Admin/2019/06/20/de289b0685cceb8ba608b5db9af041dc15610049155.png","addtime":"2019-06-20 12:34:55","servicename":"测试楼体外清洗","username":"","headimg":""},{"id":"23","order_id":"97","text":"很满意这次工人的服务","on_satisfied":"0","uid":"25","is_anonymous":"0","fid":"50","img":"upload/Admin/2019/06/20/de289b0685cceb8ba608b5db9af041dc15610049155.png","addtime":"2019-06-20 12:38:59","servicename":"测试楼体外清洗","username":"","headimg":""},{"id":"24","order_id":"97","text":"很满意这次工人的服务","on_satisfied":"0","uid":"25","is_anonymous":"0","fid":"50","img":"upload/Admin/2019/06/20/de289b0685cceb8ba608b5db9af041dc15610049155.png","addtime":"2019-06-20 12:39:10","servicename":"测试楼体外清洗","username":"","headimg":""},{"id":"25","order_id":"97","text":"很满意这次工人的服务","on_satisfied":"0","uid":"25","is_anonymous":"0","fid":"50","img":"upload/Admin/2019/06/20/de289b0685cceb8ba608b5db9af041dc15610049155.png","addtime":"2019-06-20 12:39:11","servicename":"测试楼体外清洗","username":"","headimg":""}]
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
         * id : 19
         * order_id : 97
         * text : 很满意这次工人的服务
         * on_satisfied : 0
         * uid : 25
         * is_anonymous : 0
         * fid : 50
         * img : upload/Admin/2019/06/20/de289b0685cceb8ba608b5db9af041dc15610049155.png
         * addtime : 2019-06-20 12:34:55
         * servicename : 测试楼体外清洗
         * username :
         * headimg :
         */

        private String id;
        private String order_id;
        private String text;
        private String on_satisfied;
        private String uid;
        private String is_anonymous;
        private String fid;
        private String img;
        private String addtime;
        private String servicename;
        private String username;
        private String headimg;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getOn_satisfied() {
            return on_satisfied;
        }

        public void setOn_satisfied(String on_satisfied) {
            this.on_satisfied = on_satisfied;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getIs_anonymous() {
            return is_anonymous;
        }

        public void setIs_anonymous(String is_anonymous) {
            this.is_anonymous = is_anonymous;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
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
    }
}
