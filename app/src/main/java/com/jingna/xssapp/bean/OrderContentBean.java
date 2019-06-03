package com.jingna.xssapp.bean;

/**
 * Created by Administrator on 2019/5/28.
 */

public class OrderContentBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : {"id":"36","radio":"1","service_type":"家政清洗","order_code":"27165707364547","addtime":"2019-05-27 19:18:18","address":"黑龙江省-哈尔滨市-道外区-红平小区","remarks":""}
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
         * id : 36
         * radio : 1
         * service_type : 家政清洗
         * order_code : 27165707364547
         * addtime : 2019-05-27 19:18:18
         * address : 黑龙江省-哈尔滨市-道外区-红平小区
         * remarks :
         */

        private String id;
        private String radio;
        private String service_type;
        private String order_code;
        private String addtime;
        private String address;
        private String remarks;
        private String price;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRadio() {
            return radio;
        }

        public void setRadio(String radio) {
            this.radio = radio;
        }

        public String getService_type() {
            return service_type;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public String getOrder_code() {
            return order_code;
        }

        public void setOrder_code(String order_code) {
            this.order_code = order_code;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }
}
