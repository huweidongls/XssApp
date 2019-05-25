package com.jingna.xssapp.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2019/4/8.
 */

public class WxPayBean {

    /**
     * code : 200
     * message : 生成订单成功!
     * obj : {"appId":"wxc3bb9837cc1ebb6d","partnerId":"1530281181","prepayId":"wx251721525264358ebe9114036322217300","package":"Sign=WXPay","nonceStr":"0fztGejq7Sf39F0v","timestamp":1558776112,"sign":"E57491C3BA785769880431004AB17FBB","pay_type":0}
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
         * prepayId : wx251721525264358ebe9114036322217300
         * package : Sign=WXPay
         * nonceStr : 0fztGejq7Sf39F0v
         * timestamp : 1558776112
         * sign : E57491C3BA785769880431004AB17FBB
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
