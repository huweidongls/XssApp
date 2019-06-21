package com.jingna.xssapp.bean;

import java.util.List;

import me.zhouzhuo.zzletterssidebar.anotation.Letter;
import me.zhouzhuo.zzletterssidebar.entity.SortModel;

/**
 * Created by Administrator on 2019/6/21.
 */

public class AllCityBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"id":"110000","area_name":"北京","radio":"0"},{"id":"120000","area_name":"天津","radio":"0"},{"id":"130000","area_name":"河北省","radio":"0"},{"id":"140000","area_name":"山西省","radio":"0"},{"id":"150000","area_name":"内蒙古自治区","radio":"0"},{"id":"210000","area_name":"辽宁省","radio":"0"},{"id":"220000","area_name":"吉林省","radio":"0"},{"id":"230000","area_name":"黑龙江省","radio":"1"},{"id":"310000","area_name":"上海","radio":"0"},{"id":"320000","area_name":"江苏省","radio":"0"},{"id":"330000","area_name":"浙江省","radio":"0"},{"id":"340000","area_name":"安徽省","radio":"0"},{"id":"350000","area_name":"福建省","radio":"0"},{"id":"360000","area_name":"江西省","radio":"0"},{"id":"370000","area_name":"山东省","radio":"0"},{"id":"410000","area_name":"河南省","radio":"0"},{"id":"420000","area_name":"湖北省","radio":"0"},{"id":"430000","area_name":"湖南省","radio":"0"},{"id":"440000","area_name":"广东省","radio":"0"},{"id":"450000","area_name":"广西壮族自治区","radio":"0"},{"id":"460000","area_name":"海南省","radio":"0"},{"id":"500000","area_name":"重庆","radio":"0"},{"id":"510000","area_name":"四川省","radio":"0"},{"id":"520000","area_name":"贵州省","radio":"0"},{"id":"530000","area_name":"云南省","radio":"0"},{"id":"540000","area_name":"西藏自治区","radio":"0"},{"id":"610000","area_name":"陕西省","radio":"0"},{"id":"620000","area_name":"甘肃省","radio":"0"},{"id":"630000","area_name":"青海省","radio":"0"},{"id":"640000","area_name":"宁夏回族自治区","radio":"0"},{"id":"650000","area_name":"新疆维吾尔自治区","radio":"0"},{"id":"710000","area_name":"台湾","radio":"0"},{"id":"810000","area_name":"香港特别行政区","radio":"0"},{"id":"820000","area_name":"澳门特别行政区","radio":"0"},{"id":"900000","area_name":"钓鱼岛","radio":"0"}]
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

    public static class ObjBean extends SortModel {
        /**
         * id : 110000
         * area_name : 北京
         * radio : 0
         */

        private String id;
        @Letter(isSortField = true)
        private String area_name;
        private String radio;
        private String cid;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getRadio() {
            return radio;
        }

        public void setRadio(String radio) {
            this.radio = radio;
        }
    }
}
