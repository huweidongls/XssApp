package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/23.
 */

public class ServiceTypeListBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"id":"1","typename":"家政清洗"},{"id":"3","typename":"上门洗车"},{"id":"2","typename":"家电清洗"},{"id":"12","typename":"测试分类2222"},{"id":"10","typename":"劳动力服务"},{"id":"9","typename":"室内漏点查找"},{"id":"8","typename":"管路清洗"},{"id":"7","typename":"新楼装修除甲醛"},{"id":"6","typename":"学校/KTV"},{"id":"5","typename":"酒店/厨房"},{"id":"4","typename":"机电设备清洗"}]
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
         * id : 1
         * typename : 家政清洗
         */

        private String id;
        private String typename;

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
    }
}
