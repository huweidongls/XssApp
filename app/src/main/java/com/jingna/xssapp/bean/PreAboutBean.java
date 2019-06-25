package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/25.
 */

public class PreAboutBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : {"servicePrice":[{"id":"15","price":"3","text":"米","tollitem":"深度清洁"},{"id":"16","price":"2","text":"米","tollitem":"轻度清洁"}],"Coupon":[{"id":"24","option":"全场通用/新手红包/减20元"}]}
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
        private String basicservice;
        private List<ServicePriceBean> servicePrice;
        private List<CouponBean> Coupon;

        public String getBasicservice() {
            return basicservice;
        }

        public void setBasicservice(String basicservice) {
            this.basicservice = basicservice;
        }

        public List<ServicePriceBean> getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(List<ServicePriceBean> servicePrice) {
            this.servicePrice = servicePrice;
        }

        public List<CouponBean> getCoupon() {
            return Coupon;
        }

        public void setCoupon(List<CouponBean> Coupon) {
            this.Coupon = Coupon;
        }

        public static class ServicePriceBean {
            /**
             * id : 15
             * price : 3
             * text : 米
             * tollitem : 深度清洁
             */

            private String id;
            private String price;
            private String text;
            private String tollitem;
            private int isSelect = 0;

            public ServicePriceBean(String id, String price, String text, String tollitem) {
                this.id = id;
                this.price = price;
                this.text = text;
                this.tollitem = tollitem;
            }

            public int getIsSelect() {
                return isSelect;
            }

            public void setIsSelect(int isSelect) {
                this.isSelect = isSelect;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getTollitem() {
                return tollitem;
            }

            public void setTollitem(String tollitem) {
                this.tollitem = tollitem;
            }
        }

        public static class CouponBean {
            /**
             * id : 24
             * option : 全场通用/新手红包/减20元
             */

            private String id;
            private String option;
            private String money;

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOption() {
                return option;
            }

            public void setOption(String option) {
                this.option = option;
            }
        }
    }
}
