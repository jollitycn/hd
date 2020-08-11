package com.insigma.ordercenter.logistics.hdjf;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class EdbReturnStore  extends BaseVO {

    private List<CorderInfoBean> corderInfo;
    private List<RproductInfoBean> rproductInfo;

    public List<CorderInfoBean> getCorderInfo() {
        return corderInfo;
    }

    public void setCorderInfo(List<CorderInfoBean> corderInfo) {
        this.corderInfo = corderInfo;
    }

    public List<RproductInfoBean> getRproductInfo() {
        return rproductInfo;
    }

    public void setRproductInfo(List<RproductInfoBean> rproductInfo) {
        this.rproductInfo = rproductInfo;
    }

    public static class CorderInfoBean {
        /**
         * ordernum : 20549778
         * wresingnum : 89642354865122
         * stoaffirm : 0
         * expressnum : 981632
         * expresscom : 圆通
         * retime : 2017-06-10 10:00
         */

        private String ordernum;
        private String wresingnum;
        private String stoaffirm;
        private String expressnum;
        private String expresscom;
        private String retime;

        public String getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public String getWresingnum() {
            return wresingnum;
        }

        public void setWresingnum(String wresingnum) {
            this.wresingnum = wresingnum;
        }

        public String getStoaffirm() {
            return stoaffirm;
        }

        public void setStoaffirm(String stoaffirm) {
            this.stoaffirm = stoaffirm;
        }

        public String getExpressnum() {
            return expressnum;
        }

        public void setExpressnum(String expressnum) {
            this.expressnum = expressnum;
        }

        public String getExpresscom() {
            return expresscom;
        }

        public void setExpresscom(String expresscom) {
            this.expresscom = expresscom;
        }

        public String getRetime() {
            return retime;
        }

        public void setRetime(String retime) {
            this.retime = retime;
        }
    }

    public static class RproductInfoBean {
        /**
         * barcode : 20549778
         * wresingnum : 89642354865122
         * pronum : 1
         * reamount : 100.00
         */

        private String barcode;
        private String wresingnum;
        private String pronum;
        private String reamount;

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getWresingnum() {
            return wresingnum;
        }

        public void setWresingnum(String wresingnum) {
            this.wresingnum = wresingnum;
        }

        public String getPronum() {
            return pronum;
        }

        public void setPronum(String pronum) {
            this.pronum = pronum;
        }

        public String getReamount() {
            return reamount;
        }

        public void setReamount(String reamount) {
            this.reamount = reamount;
        }
    }
}
