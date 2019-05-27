package com.jingna.xssapp.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2019/5/27.
 */

public class TopayOrderBean {

    /**
     * code : 200
     * message : 生成订单成功!
     * obj : {"appId":"wxc3bb9837cc1ebb6d","partnerId":"1530281181","prepayId":"wx25172621385040e392ae30cd6834326400","package":"Sign=WXPay","nonceStr":"zORG4VOU5WZ0WY2c","timestamp":1558776381,"sign":"2653184DCFD83100A4ACAA9DEAFD13B6","pay_type":0}
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
         * appId : wxc3bb9837cc1ebb6d
         * partnerId : 1530281181
         * prepayId : wx25172621385040e392ae30cd6834326400
         * package : Sign=WXPay
         * nonceStr : zORG4VOU5WZ0WY2c
         * timestamp : 1558776381
         * sign : 2653184DCFD83100A4ACAA9DEAFD13B6
         * pay_type : 0
         */

        private String appId;
        private String partnerId;
        private String prepayId;
        @SerializedName("package")
        private String packageX;
        private String nonceStr;
        private int timestamp;
        private String sign;
        private int pay_type;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getPartnerId() {
            return partnerId;
        }

        public void setPartnerId(String partnerId) {
            this.partnerId = partnerId;
        }

        public String getPrepayId() {
            return prepayId;
        }

        public void setPrepayId(String prepayId) {
            this.prepayId = prepayId;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }
    }
}
