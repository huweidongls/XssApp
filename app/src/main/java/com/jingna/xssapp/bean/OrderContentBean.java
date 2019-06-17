package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/28.
 */

public class OrderContentBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : {"id":"95","radio":"2","service_type":"家政清洗","order_code":"27203403264957","addtime":"2019-06-17 11:04:58","address":"河北省-承德市-双桥区-啦啦啦","remarks":"","price":"6","pretime":"2019-06-17 11:30","service_start_time":"0","service_end_time":"0","user":[{"id":"5","user":"15104513057","name":"工人_7319","headimg":"upload/APP/2019/06/11/a52835702ee7f0f076f0ec2bd0679c98156023428717.jpg"}]}
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
         * id : 95
         * radio : 2
         * service_type : 家政清洗
         * order_code : 27203403264957
         * addtime : 2019-06-17 11:04:58
         * address : 河北省-承德市-双桥区-啦啦啦
         * remarks :
         * price : 6
         * pretime : 2019-06-17 11:30
         * service_start_time : 0
         * service_end_time : 0
         * user : [{"id":"5","user":"15104513057","name":"工人_7319","headimg":"upload/APP/2019/06/11/a52835702ee7f0f076f0ec2bd0679c98156023428717.jpg"}]
         */

        private String id;
        private String radio;
        private String service_type;
        private String order_code;
        private String addtime;
        private String address;
        private String remarks;
        private String price;
        private String pretime;
        private String service_start_time;
        private String service_end_time;
        private List<UserBean> user;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPretime() {
            return pretime;
        }

        public void setPretime(String pretime) {
            this.pretime = pretime;
        }

        public String getService_start_time() {
            return service_start_time;
        }

        public void setService_start_time(String service_start_time) {
            this.service_start_time = service_start_time;
        }

        public String getService_end_time() {
            return service_end_time;
        }

        public void setService_end_time(String service_end_time) {
            this.service_end_time = service_end_time;
        }

        public List<UserBean> getUser() {
            return user;
        }

        public void setUser(List<UserBean> user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * id : 5
             * user : 15104513057
             * name : 工人_7319
             * headimg : upload/APP/2019/06/11/a52835702ee7f0f076f0ec2bd0679c98156023428717.jpg
             */

            private String id;
            private String user;
            private String name;
            private String headimg;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }
        }
    }
}
