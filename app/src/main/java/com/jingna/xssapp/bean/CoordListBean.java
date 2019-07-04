package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/4.
 */

public class CoordListBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : {"lng":"126.522805","lat":"45.691676","res":[{"id":"1","lng":"126.63128653991988","lat":"45.74200089760875","oid":"178"},{"id":"2","lng":"126.63128653991988","lat":"45.74200089760875","oid":"178"},{"id":"3","lng":"126.63128653991988","lat":"45.74200089760875","oid":"178"},{"id":"4","lng":"126.63128653991988","lat":"45.74200089760875","oid":"178"}]}
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
         * lng : 126.522805
         * lat : 45.691676
         * res : [{"id":"1","lng":"126.63128653991988","lat":"45.74200089760875","oid":"178"},{"id":"2","lng":"126.63128653991988","lat":"45.74200089760875","oid":"178"},{"id":"3","lng":"126.63128653991988","lat":"45.74200089760875","oid":"178"},{"id":"4","lng":"126.63128653991988","lat":"45.74200089760875","oid":"178"}]
         */

        private String lng;
        private String lat;
        private List<ResBean> res;

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public List<ResBean> getRes() {
            return res;
        }

        public void setRes(List<ResBean> res) {
            this.res = res;
        }

        public static class ResBean {
            /**
             * id : 1
             * lng : 126.63128653991988
             * lat : 45.74200089760875
             * oid : 178
             */

            private String id;
            private String lng;
            private String lat;
            private String oid;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getOid() {
                return oid;
            }

            public void setOid(String oid) {
                this.oid = oid;
            }
        }
    }
}
