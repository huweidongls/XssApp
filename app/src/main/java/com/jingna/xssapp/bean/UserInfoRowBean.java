package com.jingna.xssapp.bean;

/**
 * Created by Administrator on 2019/5/22.
 */

public class UserInfoRowBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : {"id":"1","phone":"18686817319","headimg":"http://127.0.0.1/include/timg.jpg","nickname":"","sex":"0","birthday":""}
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
         * id : 1
         * phone : 18686817319
         * headimg : http://127.0.0.1/include/timg.jpg
         * nickname :
         * sex : 0
         * birthday :
         */

        private String id;
        private String phone;
        private String headimg;
        private String nickname;
        private String sex;
        private String birthday;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }
    }
}
