package com.jingna.xssapp.bean;

/**
 * Created by Administrator on 2019/5/21.
 */

public class MemberInfoBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : {"id":"1","headimg":"http://127.0.0.1/include/time.jpg","num":"1","tel":"15244615473"}
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
         * headimg : http://127.0.0.1/include/time.jpg
         * num : 1
         * tel : 15244615473
         */

        private String id;
        private String headimg;
        private String num;
        private String tel;
        private String red;

        public String getRed() {
            return red;
        }

        public void setRed(String red) {
            this.red = red;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }
}
