package com.jingna.xssapp.bean;

/**
 * Created by Administrator on 2019/5/28.
 */

public class EvaluateInfoBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : {"id":"24","img":"upload/Admin/2019/05/20/7c37ef69191cb947b561580c05e05e6415583401227.jpg","title":"啊大苏打倒萨","addtime":"2019-05-27 10:07:32","service_start_time":"0","service_end_time":"0"}
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
         * id : 24
         * img : upload/Admin/2019/05/20/7c37ef69191cb947b561580c05e05e6415583401227.jpg
         * title : 啊大苏打倒萨
         * addtime : 2019-05-27 10:07:32
         * service_start_time : 0
         * service_end_time : 0
         */

        private String id;
        private String img;
        private String title;
        private String addtime;
        private String service_start_time;
        private String service_end_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
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
    }
}
