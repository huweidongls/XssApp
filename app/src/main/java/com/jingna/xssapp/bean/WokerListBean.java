package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/23.
 */

public class WokerListBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"id":"3","name":"祁连超","headimg":"upload/Admin/2019/05/20/b3cb3b71b97ba68d7c80b38f82023ffc15583202722.jpg","servicenum":"0"}]
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
         * id : 3
         * name : 祁连超
         * headimg : upload/Admin/2019/05/20/b3cb3b71b97ba68d7c80b38f82023ffc15583202722.jpg
         * servicenum : 0
         */

        private String id;
        private String name;
        private String headimg;
        private String servicenum;
        private String age;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

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

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getServicenum() {
            return servicenum;
        }

        public void setServicenum(String servicenum) {
            this.servicenum = servicenum;
        }
    }
}
