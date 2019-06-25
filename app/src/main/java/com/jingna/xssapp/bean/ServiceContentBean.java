package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/23.
 */

public class ServiceContentBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : {"id":"5","servicename":"啊大苏打倒萨","backimg":"upload/Admin/2019/05/20/7c37ef69191cb947b561580c05e05e6415583401227.jpg","tel":"15244615473","servicetext":"啊大苏打倒萨","reference":"PHA+5ZWK5aSn6IuP5omT5YCS6JCoPC9wPg==","servicescope":"PHA+5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCoPC9wPg==","servicestandards":"PHA+5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCoPC9wPg==","warmprompt":"PHA+5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCoPC9wPg==","professionaltools":"PHA+5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCoPC9wPg==","Serviceguarantee":"PHA+5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCoPC9wPg==","price":[{"info":"基础收费:0"},{"info":"轻度清洁:4/米"},{"info":"深度清洁:6/米"}],"evaluate":[{"id":"7","text":"哈哈","on_satisfied":"0","img":["upload/APP/2019/05/28/196c164506841e43410e84d9bd9f7d0d1559011120421.jpg","upload/APP/2019/05/28/196c164506841e43410e84d9bd9f7d0d1559011120294.jpg"],"username":"186****7319"}]}
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
         * id : 5
         * servicename : 啊大苏打倒萨
         * backimg : upload/Admin/2019/05/20/7c37ef69191cb947b561580c05e05e6415583401227.jpg
         * tel : 15244615473
         * servicetext : 啊大苏打倒萨
         * reference : PHA+5ZWK5aSn6IuP5omT5YCS6JCoPC9wPg==
         * servicescope : PHA+5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCoPC9wPg==
         * servicestandards : PHA+5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCoPC9wPg==
         * warmprompt : PHA+5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCoPC9wPg==
         * professionaltools : PHA+5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCoPC9wPg==
         * Serviceguarantee : PHA+5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCo5ZWK5aSn6IuP5omT5YCS6JCoPC9wPg==
         * price : [{"info":"基础收费:0"},{"info":"轻度清洁:4/米"},{"info":"深度清洁:6/米"}]
         * evaluate : [{"id":"7","text":"哈哈","on_satisfied":"0","img":["upload/APP/2019/05/28/196c164506841e43410e84d9bd9f7d0d1559011120421.jpg","upload/APP/2019/05/28/196c164506841e43410e84d9bd9f7d0d1559011120294.jpg"],"username":"186****7319"}]
         */

        private String id;
        private String servicename;
        private String backimg;
        private String tel;
        private String servicetext;
        private String reference;
        private String servicescope;
        private String servicestandards;
        private String warmprompt;
        private String professionaltools;
        private String Serviceguarantee;
        private List<PriceBean> price;
        private List<EvaluateBean> evaluate;
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

        public String getBackimg() {
            return backimg;
        }

        public void setBackimg(String backimg) {
            this.backimg = backimg;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getServicetext() {
            return servicetext;
        }

        public void setServicetext(String servicetext) {
            this.servicetext = servicetext;
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

        public List<EvaluateBean> getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(List<EvaluateBean> evaluate) {
            this.evaluate = evaluate;
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

        public static class EvaluateBean {
            /**
             * id : 7
             * text : 哈哈
             * on_satisfied : 0
             * img : ["upload/APP/2019/05/28/196c164506841e43410e84d9bd9f7d0d1559011120421.jpg","upload/APP/2019/05/28/196c164506841e43410e84d9bd9f7d0d1559011120294.jpg"]
             * username : 186****7319
             */

            private String id;
            private String text;
            private String on_satisfied;
            private String username;
            private List<String> img;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getOn_satisfied() {
                return on_satisfied;
            }

            public void setOn_satisfied(String on_satisfied) {
                this.on_satisfied = on_satisfied;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public List<String> getImg() {
                return img;
            }

            public void setImg(List<String> img) {
                this.img = img;
            }
        }
    }
}
