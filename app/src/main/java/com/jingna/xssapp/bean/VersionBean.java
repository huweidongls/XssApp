package com.jingna.xssapp.bean;

/**
 * Created by Administrator on 2019/9/19.
 */

public class VersionBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : {"id":"6","version":"1.0","content":"1","addtime":"2019-09-19 16:26:16","type":"0"}
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
         * id : 6
         * version : 1.0
         * content : 1
         * addtime : 2019-09-19 16:26:16
         * type : 0
         */

        private String id;
        private String version;
        private String content;
        private String addtime;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
