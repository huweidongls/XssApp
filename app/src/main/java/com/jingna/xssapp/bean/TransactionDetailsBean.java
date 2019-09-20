package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/21.
 */

public class TransactionDetailsBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"id":"1","typename":"室内卫生","addtime":"2019-05-21 14:15:44","price":"150","radio":"1","uid":"1"}]
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
         * typename : 室内卫生
         * addtime : 2019-05-21 14:15:44
         * price : 150
         * radio : 1
         * uid : 1
         */

        private String id;
        private String typename;
        private String addtime;
        private String price;
        private String radio;
        private String uid;
        private String refundtime;

        public String getRefundtime() {
            return refundtime;
        }

        public void setRefundtime(String refundtime) {
            this.refundtime = refundtime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRadio() {
            return radio;
        }

        public void setRadio(String radio) {
            this.radio = radio;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
