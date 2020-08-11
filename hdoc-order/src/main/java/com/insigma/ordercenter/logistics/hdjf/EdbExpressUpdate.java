package com.insigma.ordercenter.logistics.hdjf;

import com.google.gson.annotations.SerializedName;
import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class EdbExpressUpdate extends BaseVO {

    /**
     * orderInfo : {"Orderid":"S1707270002564","Express":"2","storage_id":"10","Csmemo":"大猫测试"}
     */

    private OrderInfoBean orderInfo;
    @SerializedName("orderInfo")
    private List<OrderInfoBean> orderInfoX;
    private List<ProductInfoBean> product_info;

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<OrderInfoBean> getOrderInfoX() {
        return orderInfoX;
    }

    public void setOrderInfoX(List<OrderInfoBean> orderInfoX) {
        this.orderInfoX = orderInfoX;
    }

    public List<ProductInfoBean> getProduct_info() {
        return product_info;
    }

    public void setProduct_info(List<ProductInfoBean> product_info) {
        this.product_info = product_info;
    }

    public static class OrderInfoBean {
        /**
         * Orderid : S1707270002564
         * Express : 2
         * storage_id : 10
         * Csmemo : 大猫测试
         */

        private String Orderid;
        private String Express;
        private String storage_id;
        private String Csmemo;

        public String getOrderid() {
            return Orderid;
        }

        public void setOrderid(String Orderid) {
            this.Orderid = Orderid;
        }

        public String getExpress() {
            return Express;
        }

        public void setExpress(String Express) {
            this.Express = Express;
        }

        public String getStorage_id() {
            return storage_id;
        }

        public void setStorage_id(String storage_id) {
            this.storage_id = storage_id;
        }

        public String getCsmemo() {
            return Csmemo;
        }

        public void setCsmemo(String Csmemo) {
            this.Csmemo = Csmemo;
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
