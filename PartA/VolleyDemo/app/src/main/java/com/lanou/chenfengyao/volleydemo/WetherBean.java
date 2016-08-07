package com.lanou.chenfengyao.volleydemo;

import java.util.List;

/**
 * Created by ChenFengYao on 16/5/22.
 */
public class WetherBean {

    /**
     * errNum : 0
     * errMsg : success
     * retData : [{"province_cn":"辽宁","district_cn":"大连","name_cn":"大连","name_en":"dalian","area_id":"101070201"},{"province_cn":"辽宁","district_cn":"大连","name_cn":"瓦房店","name_en":"wafangdian","area_id":"101070202"},{"province_cn":"辽宁","district_cn":"大连","name_cn":"金州","name_en":"jinzhou","area_id":"101070203"},{"province_cn":"辽宁","district_cn":"大连","name_cn":"普兰店","name_en":"pulandian","area_id":"101070204"},{"province_cn":"辽宁","district_cn":"大连","name_cn":"旅顺","name_en":"lvshun","area_id":"101070205"},{"province_cn":"辽宁","district_cn":"大连","name_cn":"长海","name_en":"changhai","area_id":"101070206"},{"province_cn":"辽宁","district_cn":"大连","name_cn":"庄河","name_en":"zhuanghe","area_id":"101070207"}]
     */

    private int errNum;
    private String errMsg;
    /**
     * province_cn : 辽宁
     * district_cn : 大连
     * name_cn : 大连
     * name_en : dalian
     * area_id : 101070201
     */

    private List<RetDataEntity> retData;

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<RetDataEntity> getRetData() {
        return retData;
    }

    public void setRetData(List<RetDataEntity> retData) {
        this.retData = retData;
    }

    public static class RetDataEntity {
        private String province_cn;
        private String district_cn;
        private String name_cn;
        private String name_en;
        private String area_id;

        public String getProvince_cn() {
            return province_cn;
        }

        public void setProvince_cn(String province_cn) {
            this.province_cn = province_cn;
        }

        public String getDistrict_cn() {
            return district_cn;
        }

        public void setDistrict_cn(String district_cn) {
            this.district_cn = district_cn;
        }

        public String getName_cn() {
            return name_cn;
        }

        public void setName_cn(String name_cn) {
            this.name_cn = name_cn;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }
    }
}
