package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/28.
 */

public class PayServiceListBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"id":"5","servicename":"啊大苏打倒萨"},{"id":"6","servicename":"asd撒大苏打"}]
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
         * id : 5
         * servicename : 啊大苏打倒萨
         */

        private String id;
        private String servicename;
        private String specifications_status;

        public String getSpecifications_status() {
            return specifications_status;
        }

        public void setSpecifications_status(String specifications_status) {
            this.specifications_status = specifications_status;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getServicename() {
            return servicename;
        }

        public void setServicename(String servicename) {
            this.servicename = servicename;
        }
    }
}
