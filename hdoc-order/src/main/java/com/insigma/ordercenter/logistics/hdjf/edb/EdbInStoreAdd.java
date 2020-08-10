package com.insigma.ordercenter.logistics.hdjf.edb;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class EdbInStoreAdd extends BaseVO {

    private List<OrderInfoBean> orderInfo;
    private List<ProductInfoBean> product_info;

    public List<OrderInfoBean> getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(List<OrderInfoBean> orderInfo) {
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
         * instorage_no : 20549778
         * instorage_type : 正常入库
         * instorage_time : 2016-10-20 09:54
         * storage_no : 1
         * supplier_no : 1
         * delivery_no : EMS1265489
         * cost : 20
         * procure_cost : 10
         * other_cost : 5
         * pact_totalAmount : 100
         * out_pactNo : cs390233837
         * WL_company : 无菌物流
         * express_no : 1235345345
         */

        private String instorage_no;
        private String instorage_type;
        private String instorage_time;
        private String storage_no;
        private String supplier_no;
        private String delivery_no;
        private String cost;
        private String procure_cost;
        private String other_cost;
        private String pact_totalAmount;
        private String out_pactNo;
        private String WL_company;
        private String express_no;

        public String getInstorage_no() {
            return instorage_no;
        }

        public void setInstorage_no(String instorage_no) {
            this.instorage_no = instorage_no;
        }

        public String getInstorage_type() {
            return instorage_type;
        }

        public void setInstorage_type(String instorage_type) {
            this.instorage_type = instorage_type;
        }

        public String getInstorage_time() {
            return instorage_time;
        }

        public void setInstorage_time(String instorage_time) {
            this.instorage_time = instorage_time;
        }

        public String getStorage_no() {
            return storage_no;
        }

        public void setStorage_no(String storage_no) {
            this.storage_no = storage_no;
        }

        public String getSupplier_no() {
            return supplier_no;
        }

        public void setSupplier_no(String supplier_no) {
            this.supplier_no = supplier_no;
        }

        public String getDelivery_no() {
            return delivery_no;
        }

        public void setDelivery_no(String delivery_no) {
            this.delivery_no = delivery_no;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getProcure_cost() {
            return procure_cost;
        }

        public void setProcure_cost(String procure_cost) {
            this.procure_cost = procure_cost;
        }

        public String getOther_cost() {
            return other_cost;
        }

        public void setOther_cost(String other_cost) {
            this.other_cost = other_cost;
        }

        public String getPact_totalAmount() {
            return pact_totalAmount;
        }

        public void setPact_totalAmount(String pact_totalAmount) {
            this.pact_totalAmount = pact_totalAmount;
        }

        public String getOut_pactNo() {
            return out_pactNo;
        }

        public void setOut_pactNo(String out_pactNo) {
            this.out_pactNo = out_pactNo;
        }

        public String getWL_company() {
            return WL_company;
        }

        public void setWL_company(String WL_company) {
            this.WL_company = WL_company;
        }

        public String getExpress_no() {
            return express_no;
        }

        public void setExpress_no(String express_no) {
            this.express_no = express_no;
        }
    }

    public static class ProductInfoBean {
        /**
         * instorage_no : 20549778
         * productItem_no : 1
         * instorage_num : 10
         * storage_no : 1
         * batch : 10000
         * expire_Time : 2017-06-20
         */

        private String instorage_no;
        private String productItem_no;
        private String instorage_num;
        private String storage_no;
        private String batch;
        private String expire_Time;

        public String getInstorage_no() {
            return instorage_no;
        }

        public void setInstorage_no(String instorage_no) {
            this.instorage_no = instorage_no;
        }

        public String getProductItem_no() {
            return productItem_no;
        }

        public void setProductItem_no(String productItem_no) {
            this.productItem_no = productItem_no;
        }

        public String getInstorage_num() {
            return instorage_num;
        }

        public void setInstorage_num(String instorage_num) {
            this.instorage_num = instorage_num;
        }

        public String getStorage_no() {
            return storage_no;
        }

        public void setStorage_no(String storage_no) {
            this.storage_no = storage_no;
        }

        public String getBatch() {
            return batch;
        }

        public void setBatch(String batch) {
            this.batch = batch;
        }

        public String getExpire_Time() {
            return expire_Time;
        }

        public void setExpire_Time(String expire_Time) {
            this.expire_Time = expire_Time;
        }
    }
}
