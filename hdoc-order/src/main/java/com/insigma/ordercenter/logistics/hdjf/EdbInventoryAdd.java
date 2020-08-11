package com.insigma.ordercenter.logistics.hdjf;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class EdbInventoryAdd extends BaseVO {

    /**
     * orderInfo : {"checkOrderCode":"PD23659","storage_no":"30","checkTime":"2016-11-12 10:50:55","checkType":"年中盘点","remark":"盘点说明"}
     * product_info : [{"checkOrderCode":"PD23659","barCode":"ALB015A","quantity":"1"},{"checkOrderCode":"PD23659","barCode":"ALB016A","quantity":"1"}]
     */

    private OrderInfoBean orderInfo;
    private List<ProductInfoBean> product_info;

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<ProductInfoBean> getProduct_info() {
        return product_info;
    }

    public void setProduct_info(List<ProductInfoBean> product_info) {
        this.product_info = product_info;
    }

    public static class OrderInfoBean {
        /**
         * checkOrderCode : PD23659
         * storage_no : 30
         * checkTime : 2016-11-12 10:50:55
         * checkType : 年中盘点
         * remark : 盘点说明
         */

        private String checkOrderCode;
        private String storage_no;
        private String checkTime;
        private String checkType;
        private String remark;

        public String getCheckOrderCode() {
            return checkOrderCode;
        }

        public void setCheckOrderCode(String checkOrderCode) {
            this.checkOrderCode = checkOrderCode;
        }

        public String getStorage_no() {
            return storage_no;
        }

        public void setStorage_no(String storage_no) {
            this.storage_no = storage_no;
        }

        public String getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(String checkTime) {
            this.checkTime = checkTime;
        }

        public String getCheckType() {
            return checkType;
        }

        public void setCheckType(String checkType) {
            this.checkType = checkType;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    public static class ProductInfoBean {
        /**
         * checkOrderCode : PD23659
         * barCode : ALB015A
         * quantity : 1
         */

        private String checkOrderCode;
        private String barCode;
        private String quantity;

        public String getCheckOrderCode() {
            return checkOrderCode;
        }

        public void setCheckOrderCode(String checkOrderCode) {
            this.checkOrderCode = checkOrderCode;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }
    }
}
