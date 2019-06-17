package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/23.
 */

public class WokerListBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"id":"5","name":"工人_7319","headimg":"upload/APP/2019/06/11/a52835702ee7f0f076f0ec2bd0679c98156023428717.jpg","servicenum":"0","age":"0","table":[{"id":"13","type_name":"蒸汽洗车","uid":"5"},{"id":"12","type_name":"普通洗车","uid":"5"},{"id":"11","type_name":"家电清洗","uid":"5"},{"id":"10","type_name":"家政清洗","uid":"5"}]},{"id":"9","name":"工人_4492","headimg":"upload/APP/2019/06/13/9801b09d2e83d09668660543cf8ee94d156039315513.jpg","servicenum":"0","age":"0","table":[{"id":"13","type_name":"蒸汽洗车","uid":"5"},{"id":"12","type_name":"普通洗车","uid":"5"},{"id":"11","type_name":"家电清洗","uid":"5"},{"id":"10","type_name":"家政清洗","uid":"5"}]}]
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
         * name : 工人_7319
         * headimg : upload/APP/2019/06/11/a52835702ee7f0f076f0ec2bd0679c98156023428717.jpg
         * servicenum : 0
         * age : 0
         * table : [{"id":"13","type_name":"蒸汽洗车","uid":"5"},{"id":"12","type_name":"普通洗车","uid":"5"},{"id":"11","type_name":"家电清洗","uid":"5"},{"id":"10","type_name":"家政清洗","uid":"5"}]
         */

        private String id;
        private String name;
        private String headimg;
        private String servicenum;
        private String age;
        private List<TableBean> table;

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

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public List<TableBean> getTable() {
            return table;
        }

        public void setTable(List<TableBean> table) {
            this.table = table;
        }

        public static class TableBean {
            /**
             * id : 13
             * type_name : 蒸汽洗车
             * uid : 5
             */

            private String id;
            private String type_name;
            private String uid;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }
        }
    }
}
