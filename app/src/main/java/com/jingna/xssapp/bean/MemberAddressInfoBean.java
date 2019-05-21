package com.jingna.xssapp.bean;

/**
 * Created by Administrator on 2019/5/21.
 */

public class MemberAddressInfoBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : {"id":"2","name":"祁连超","tel":"15244615473","address":"黑龙江省-哈尔滨市-南岗区","detailedaddress":"汉广街41号","uid":"1","radio":"0"}
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
         * id : 2
         * name : 祁连超
         * tel : 15244615473
         * address : 黑龙江省-哈尔滨市-南岗区
         * detailedaddress : 汉广街41号
         * uid : 1
         * radio : 0
         */

        private String id;
        private String name;
        private String tel;
        private String address;
        private String detailedaddress;
        private String uid;
        private String radio;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDetailedaddress() {
            return detailedaddress;
        }

        public void setDetailedaddress(String detailedaddress) {
            this.detailedaddress = detailedaddress;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getRadio() {
            return radio;
        }

        public void setRadio(String radio) {
            this.radio = radio;
        }
    }
}
