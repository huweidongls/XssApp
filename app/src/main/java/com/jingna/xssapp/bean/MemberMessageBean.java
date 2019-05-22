package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/22.
 */

public class MemberMessageBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"id":"1","addtime":"2019-05-22 09:15:24","typename":"室内卫生","text":"您的订单:14657613系统已经派单","order_id":"5","uid":"1"},{"id":"2","addtime":"2019-05-22 09:18:11","typename":"室内卫生","text":"您的订单:14657613系统已经派单","order_id":"5","uid":"1"},{"id":"3","addtime":"2019-05-22 09:18:14","typename":"室内卫生","text":"您的订单:14657613系统已经派单","order_id":"5","uid":"1"}]
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
         * id : 1
         * addtime : 2019-05-22 09:15:24
         * typename : 室内卫生
         * text : 您的订单:14657613系统已经派单
         * order_id : 5
         * uid : 1
         */

        private String id;
        private String addtime;
        private String typename;
        private String text;
        private String order_id;
        private String uid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
