package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/23.
 */

public class ServiceContentBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : {"id":"6","servicename":"asd撒大苏打","backimg":"upload/Admin/2019/05/23/d90b0183d8aa4d75e3aa169a297bad22155857548220.jpg","servicetext":"asda啊实打实","tel":"15244615473","reference":"PHA+YXNk5pKS5aSn6IuP5omTPC9wPg==","servicescope":"PHA+YXNk5pKS5aSn6IuP5omTPC9wPg==","servicestandards":"PHA+YXNk5pKS5aSn6IuP5omTPC9wPg==","warmprompt":"PHA+YXNk5pKS5aSn6IuP5omTPC9wPg==","professionaltools":"PHA+YXNk5pKS5aSn6IuP5omTPC9wPg==","Serviceguarantee":"PHA+YXNk5pKS5aSn6IuP5omTPC9wPg==","price":[{"info":"基础收费:0"},{"info":"深度清洁:3/米"},{"info":"轻度清洁:2/米"}]}
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
         * id : 6
         * servicename : asd撒大苏打
         * backimg : upload/Admin/2019/05/23/d90b0183d8aa4d75e3aa169a297bad22155857548220.jpg
         * servicetext : asda啊实打实
         * tel : 15244615473
         * reference : PHA+YXNk5pKS5aSn6IuP5omTPC9wPg==
         * servicescope : PHA+YXNk5pKS5aSn6IuP5omTPC9wPg==
         * servicestandards : PHA+YXNk5pKS5aSn6IuP5omTPC9wPg==
         * warmprompt : PHA+YXNk5pKS5aSn6IuP5omTPC9wPg==
         * professionaltools : PHA+YXNk5pKS5aSn6IuP5omTPC9wPg==
         * Serviceguarantee : PHA+YXNk5pKS5aSn6IuP5omTPC9wPg==
         * price : [{"info":"基础收费:0"},{"info":"深度清洁:3/米"},{"info":"轻度清洁:2/米"}]
         */

        private String id;
        private String servicename;
        private String backimg;
        private String servicetext;
        private String tel;
        private String reference;
        private String servicescope;
        private String servicestandards;
        private String warmprompt;
        private String professionaltools;
        private String Serviceguarantee;
        private List<PriceBean> price;

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

        public String getBackimg() {
            return backimg;
        }

        public void setBackimg(String backimg) {
            this.backimg = backimg;
        }

        public String getServicetext() {
            return servicetext;
        }

        public void setServicetext(String servicetext) {
            this.servicetext = servicetext;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public String getServicescope() {
            return servicescope;
        }

        public void setServicescope(String servicescope) {
            this.servicescope = servicescope;
        }

        public String getServicestandards() {
            return servicestandards;
        }

        public void setServicestandards(String servicestandards) {
            this.servicestandards = servicestandards;
        }

        public String getWarmprompt() {
            return warmprompt;
        }

        public void setWarmprompt(String warmprompt) {
            this.warmprompt = warmprompt;
        }

        public String getProfessionaltools() {
            return professionaltools;
        }

        public void setProfessionaltools(String professionaltools) {
            this.professionaltools = professionaltools;
        }

        public String getServiceguarantee() {
            return Serviceguarantee;
        }

        public void setServiceguarantee(String Serviceguarantee) {
            this.Serviceguarantee = Serviceguarantee;
        }

        public List<PriceBean> getPrice() {
            return price;
        }

        public void setPrice(List<PriceBean> price) {
            this.price = price;
        }

        public static class PriceBean {
            /**
             * info : 基础收费:0
             */

            private String info;

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }
    }
}
