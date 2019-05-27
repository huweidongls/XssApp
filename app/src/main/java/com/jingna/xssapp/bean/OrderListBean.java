package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/27.
 */

public class OrderListBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"id":"24","order_code":"27165506311587","pretime":"2019-05-27 10:07","address":"北京市-北京市-东城区-hncf","radio":"1","servicename":"啊大苏打倒萨","ico":"upload/Admin/2019/05/20/7c37ef69191cb947b561580c05e05e6415583401226.jpg"}]
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
         * order_code : 27165506311587
         * pretime : 2019-05-27 10:07
         * address : 北京市-北京市-东城区-hncf
         * radio : 1
         * servicename : 啊大苏打倒萨
         * ico : upload/Admin/2019/05/20/7c37ef69191cb947b561580c05e05e6415583401226.jpg
         */

        private String id;
        private String order_code;
        private String pretime;
        private String address;
        private String radio;
        private String servicename;
        private String ico;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder_code() {
            return order_code;
        }

        public void setOrder_code(String order_code) {
            this.order_code = order_code;
        }

        public String getPretime() {
            return pretime;
        }

        public void setPretime(String pretime) {
            this.pretime = pretime;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getRadio() {
            return radio;
        }

        public void setRadio(String radio) {
            this.radio = radio;
        }

        public String getServicename() {
            return servicename;
        }

        public void setServicename(String servicename) {
            this.servicename = servicename;
        }

        public String getIco() {
            return ico;
        }

        public void setIco(String ico) {
            this.ico = ico;
        }
    }
}
