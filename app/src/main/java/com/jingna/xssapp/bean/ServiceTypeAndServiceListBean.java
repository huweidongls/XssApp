package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/23.
 */

public class ServiceTypeAndServiceListBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"id":"15","typename":"上门洗车子类","service":[{"id":"6","imgurl":"upload/Admin/2019/05/23/d90b0183d8aa4d75e3aa169a297bad2215585754820.jpg","servicename":"asd撒大苏打"}]}]
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
         * id : 15
         * typename : 上门洗车子类
         * service : [{"id":"6","imgurl":"upload/Admin/2019/05/23/d90b0183d8aa4d75e3aa169a297bad2215585754820.jpg","servicename":"asd撒大苏打"}]
         */

        private String id;
        private String typename;
        private List<ServiceBean> service;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public List<ServiceBean> getService() {
            return service;
        }

        public void setService(List<ServiceBean> service) {
            this.service = service;
        }

        public static class ServiceBean {
            /**
             * id : 6
             * imgurl : upload/Admin/2019/05/23/d90b0183d8aa4d75e3aa169a297bad2215585754820.jpg
             * servicename : asd撒大苏打
             */

            private String id;
            private String imgurl;
            private String servicename;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public String getServicename() {
                return servicename;
            }

            public void setServicename(String servicename) {
                this.servicename = servicename;
            }
        }
    }
}
