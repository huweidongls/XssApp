package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/9/5.
 */

public class AddressCityListBean {
    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"name":"黑龙江省","city":{"name":"鸡西市","area":["鸡冠区","恒山区","滴道区","梨树区","城子河区","麻山区","鸡东县","虎林市","密山市"]}}]
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
         * name : 黑龙江省
         * city : {"name":"鸡西市","area":["鸡冠区","恒山区","滴道区","梨树区","城子河区","麻山区","鸡东县","虎林市","密山市"]}
         */

        private String name;
        private CityBean city;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public CityBean getCity() {
            return city;
        }

        public void setCity(CityBean city) {
            this.city = city;
        }

        public static class CityBean {
            /**
             * name : 鸡西市
             * area : ["鸡冠区","恒山区","滴道区","梨树区","城子河区","麻山区","鸡东县","虎林市","密山市"]
             */

            private String name;
            private List<String> area;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getArea() {
                return area;
            }

            public void setArea(List<String> area) {
                this.area = area;
            }
        }
    }
}
