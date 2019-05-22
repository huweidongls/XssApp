package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/22.
 */

public class PriceListBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"serverid":"5","imgurl":"upload/Admin/2019/05/15/547e242e52fa3a363474e832ed0c034b15578991328.jpg"},{"serverid":"5","imgurl":"upload/Admin/2019/05/15/547e242e52fa3a363474e832ed0c034b15578991328.jpg"},{"serverid":"5","imgurl":"upload/Admin/2019/05/15/547e242e52fa3a363474e832ed0c034b15578991328.jpg"},{"serverid":"5","imgurl":"upload/Admin/2019/05/15/547e242e52fa3a363474e832ed0c034b15578991328.jpg"},{"serverid":"5","imgurl":"upload/Admin/2019/05/15/547e242e52fa3a363474e832ed0c034b15578991328.jpg"}]
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
         * serverid : 5
         * imgurl : upload/Admin/2019/05/15/547e242e52fa3a363474e832ed0c034b15578991328.jpg
         */

        private String serverid;
        private String imgurl;

        public String getServerid() {
            return serverid;
        }

        public void setServerid(String serverid) {
            this.serverid = serverid;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }
}
