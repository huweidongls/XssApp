package com.jingna.xssapp.bean;

/**
 * Created by Administrator on 2019/5/27.
 */

public class TransactionContentBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : {"price":"216","radio":"1","pay_type":"1","pay_time":"2019-05-27 10:07:39","order_sn":"27165506311587","wx_order_sn":"4200000334201905276785028335"}
     */

    private int code;
    private String message;
    private ObjBean obj;

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

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * price : 216
         * radio : 1
         * pay_type : 1
         * pay_time : 2019-05-27 10:07:39
         * order_sn : 27165506311587
         * wx_order_sn : 4200000334201905276785028335
         */

        private String price;
        private String radio;
        private String pay_type;
        private String pay_time;
        private String order_sn;
        private String wx_order_sn;
        private String refundtime;
        private String refundcode;
        private String refundprice;

        public String getRefundprice() {
            return refundprice;
        }

        public void setRefundprice(String refundprice) {
            this.refundprice = refundprice;
        }

        public String getRefundcode() {
            return refundcode;
        }

        public void setRefundcode(String refundcode) {
            this.refundcode = refundcode;
        }

        public String getRefundtime() {
            return refundtime;
        }

        public void setRefundtime(String refundtime) {
            this.refundtime = refundtime;
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

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getWx_order_sn() {
            return wx_order_sn;
        }

        public void setWx_order_sn(String wx_order_sn) {
            this.wx_order_sn = wx_order_sn;
        }
    }
}
