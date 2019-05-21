package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/21.
 */

public class CouponListBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"id":"1","discountname":"全场通用","termofvalidity":"永久有效","money":"20","decript":"新手红包","text":"此类优惠券只限于新手使用!"}]
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
         * discountname : 全场通用
         * termofvalidity : 永久有效
         * money : 20
         * decript : 新手红包
         * text : 此类优惠券只限于新手使用!
         */

        private String id;
        private String discountname;
        private String termofvalidity;
        private String money;
        private String decript;
        private String text;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDiscountname() {
            return discountname;
        }

        public void setDiscountname(String discountname) {
            this.discountname = discountname;
        }

        public String getTermofvalidity() {
            return termofvalidity;
        }

        public void setTermofvalidity(String termofvalidity) {
            this.termofvalidity = termofvalidity;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getDecript() {
            return decript;
        }

        public void setDecript(String decript) {
            this.decript = decript;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
