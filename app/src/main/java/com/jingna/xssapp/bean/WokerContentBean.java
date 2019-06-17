package com.jingna.xssapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/23.
 */

public class WokerContentBean {

    /**
     * code : 200
     * message : 获取成功!
     * obj : {"id":"3","name":"祁连超","headimg":"upload/Admin/2019/05/23/c1df2451e9b4578775811c6f8e73d615155857990713.jpg","servicenum":"0","essentialinformation":"PHA+56WB6L+e6LaFPC9wPjxwPuelgei/nui2hTwvcD4=","age":"22","experience":[{"id":"1","companyname":"哈尔滨静娜科技","start_time":"2016-5-1","end_time":"2019-5-1","position":"php","salary":"100000","jobdescription":"5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA","gid":"3"}],"train":[{"id":"1","organizationname":"图灵","start_time":"2015-5-1","end_time":"2015-9-1","description":"PHA+5Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G1PC9wPg==","gid":"3"}],"img":[{"id":"7","imgurl":"upload/Admin/2019/05/20/bc70955361c4ad1b2fe3d3586eb386b61558338199689.jpg","sid":"3"},{"id":"8","imgurl":"upload/Admin/2019/05/20/bc70955361c4ad1b2fe3d3586eb386b61558338199354.jpg","sid":"3"},{"id":"9","imgurl":"upload/Admin/2019/05/20/bc70955361c4ad1b2fe3d3586eb386b61558338199591.jpg","sid":"3"},{"id":"10","imgurl":"upload/Admin/2019/05/20/bc70955361c4ad1b2fe3d3586eb386b61558338199635.jpg","sid":"3"}],"table":[{"id":"13","type_name":"蒸汽洗车","uid":"5"},{"id":"12","type_name":"普通洗车","uid":"5"},{"id":"11","type_name":"家电清洗","uid":"5"}]}
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
         * id : 3
         * name : 祁连超
         * headimg : upload/Admin/2019/05/23/c1df2451e9b4578775811c6f8e73d615155857990713.jpg
         * servicenum : 0
         * essentialinformation : PHA+56WB6L+e6LaFPC9wPjxwPuelgei/nui2hTwvcD4=
         * age : 22
         * experience : [{"id":"1","companyname":"哈尔滨静娜科技","start_time":"2016-5-1","end_time":"2019-5-1","position":"php","salary":"100000","jobdescription":"5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA","gid":"3"}]
         * train : [{"id":"1","organizationname":"图灵","start_time":"2015-5-1","end_time":"2015-9-1","description":"PHA+5Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G1PC9wPg==","gid":"3"}]
         * img : [{"id":"7","imgurl":"upload/Admin/2019/05/20/bc70955361c4ad1b2fe3d3586eb386b61558338199689.jpg","sid":"3"},{"id":"8","imgurl":"upload/Admin/2019/05/20/bc70955361c4ad1b2fe3d3586eb386b61558338199354.jpg","sid":"3"},{"id":"9","imgurl":"upload/Admin/2019/05/20/bc70955361c4ad1b2fe3d3586eb386b61558338199591.jpg","sid":"3"},{"id":"10","imgurl":"upload/Admin/2019/05/20/bc70955361c4ad1b2fe3d3586eb386b61558338199635.jpg","sid":"3"}]
         * table : [{"id":"13","type_name":"蒸汽洗车","uid":"5"},{"id":"12","type_name":"普通洗车","uid":"5"},{"id":"11","type_name":"家电清洗","uid":"5"}]
         */

        private String id;
        private String name;
        private String headimg;
        private String servicenum;
        private String essentialinformation;
        private String age;
        private List<ExperienceBean> experience;
        private List<TrainBean> train;
        private List<ImgBean> img;
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

        public String getEssentialinformation() {
            return essentialinformation;
        }

        public void setEssentialinformation(String essentialinformation) {
            this.essentialinformation = essentialinformation;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public List<ExperienceBean> getExperience() {
            return experience;
        }

        public void setExperience(List<ExperienceBean> experience) {
            this.experience = experience;
        }

        public List<TrainBean> getTrain() {
            return train;
        }

        public void setTrain(List<TrainBean> train) {
            this.train = train;
        }

        public List<ImgBean> getImg() {
            return img;
        }

        public void setImg(List<ImgBean> img) {
            this.img = img;
        }

        public List<TableBean> getTable() {
            return table;
        }

        public void setTable(List<TableBean> table) {
            this.table = table;
        }

        public static class ExperienceBean {
            /**
             * id : 1
             * companyname : 哈尔滨静娜科技
             * start_time : 2016-5-1
             * end_time : 2019-5-1
             * position : php
             * salary : 100000
             * jobdescription : 5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA5ZOI5bCU5ruo6Z2Z5aic56eR5oqA
             * gid : 3
             */

            private String id;
            private String companyname;
            private String start_time;
            private String end_time;
            private String position;
            private String salary;
            private String jobdescription;
            private String gid;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCompanyname() {
                return companyname;
            }

            public void setCompanyname(String companyname) {
                this.companyname = companyname;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getSalary() {
                return salary;
            }

            public void setSalary(String salary) {
                this.salary = salary;
            }

            public String getJobdescription() {
                return jobdescription;
            }

            public void setJobdescription(String jobdescription) {
                this.jobdescription = jobdescription;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }
        }

        public static class TrainBean {
            /**
             * id : 1
             * organizationname : 图灵
             * start_time : 2015-5-1
             * end_time : 2015-9-1
             * description : PHA+5Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G15Zu+54G1PC9wPg==
             * gid : 3
             */

            private String id;
            private String organizationname;
            private String start_time;
            private String end_time;
            private String description;
            private String gid;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrganizationname() {
                return organizationname;
            }

            public void setOrganizationname(String organizationname) {
                this.organizationname = organizationname;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }
        }

        public static class ImgBean {
            /**
             * id : 7
             * imgurl : upload/Admin/2019/05/20/bc70955361c4ad1b2fe3d3586eb386b61558338199689.jpg
             * sid : 3
             */

            private String id;
            private String imgurl;
            private String sid;

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

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }
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
